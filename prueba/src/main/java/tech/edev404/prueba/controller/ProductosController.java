package tech.edev404.prueba.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import tech.edev404.prueba.configuration.exception.CodigoNotAvaliableException;
import tech.edev404.prueba.configuration.exception.EntityAlreadyOnStateException;
import tech.edev404.prueba.model.dto.ProductoDTO;
import tech.edev404.prueba.model.entity.Producto;
import tech.edev404.prueba.model.mapper.ProductoMapper;
import tech.edev404.prueba.service.ProductosService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/productos")
public class ProductosController {

    private final ProductosService productosService;
    private final ProductoMapper productoMapper;

    @GetMapping("/paginate")
    public ResponseEntity<Page<ProductoDTO>> handleGetProductos(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "9") final Integer size) {

        final Pageable paging = PageRequest.of(page, size);
        final Page<Producto> pageProductos = productosService.getProductosPaginate(paging);
        System.out.println(pageProductos);
        final Page<ProductoDTO> pageProductosDTO = new PageImpl<ProductoDTO>(
                pageProductos.stream().map(productoMapper::pojoToDto).toList());
                System.out.println(pageProductosDTO);
        return ResponseEntity.status(HttpStatus.OK).body(pageProductosDTO);

    }

    @GetMapping("/filtered")
    public ResponseEntity<Page<ProductoDTO>> handleGetProductosByExample(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "9") final Integer size,
            @RequestBody final ProductoDTO productoDTO) {
        final Pageable paging = PageRequest.of(page, size);
        final Example<Producto> example = Example
                .of(productoMapper.dtoToPojo(productoDTO));
        final Page<Producto> pageProductos = productosService.getProductosPaginateByExample(paging, example);
        final Page<ProductoDTO> pageProductosDTO = new PageImpl<ProductoDTO>(
                pageProductos.stream().map(productoMapper::pojoToDto).toList());
        return ResponseEntity.status(HttpStatus.OK).body(pageProductosDTO);
    }

    @GetMapping("/codigo")
    public ResponseEntity<ProductoDTO> handleGetProductoByCodigo(@RequestParam final String codigo)
            throws EntityNotFoundException {
        Optional<Producto> optional = productosService.getProductoByCodigo(codigo);
        if (!optional.isPresent()) {
            throw new EntityNotFoundException(String.format("Producto no encontrado. Codigo: %s", codigo));
        }
        final Producto producto = optional.get();
        final ProductoDTO productoDTO = productoMapper.pojoToDto(producto);
        return ResponseEntity.status(HttpStatus.OK).body(productoDTO);
    }

    @GetMapping("/codigo-disponible")
    public ResponseEntity<Boolean> handleCodigoDisponible(@RequestParam final String codigo)
            throws EntityNotFoundException {
        final Boolean disponible = productosService.codigoDisponible(codigo);
        return ResponseEntity.status(HttpStatus.OK).body(disponible);
    }

    @PutMapping("/update/{idProducto}")
    public ResponseEntity<HttpStatus> handleUpdateProducto(@PathVariable UUID idProducto,
            @RequestBody final ProductoDTO productoDTO)
            throws EntityNotFoundException, CodigoNotAvaliableException {
        if (!productosService.alreadyExistById(idProducto)) {
            throw new EntityNotFoundException(String.format("Producto no encontrado. UUID: %s", productoDTO.getId()));
        } else if (!productosService.codigoDisponible(productoDTO)) {
            throw new CodigoNotAvaliableException(
                    String.format("Codigo no disponible. Codigo: %s", productoDTO.getCodigo()));
        }
        final Producto producto = productoMapper.dtoToPojo(productoDTO);
        productosService.updateProducto(producto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> handleCreateProducto(@RequestBody ProductoDTO productoDTO)
            throws EntityAlreadyOnStateException {
        if (!productosService.codigoDisponible(productoDTO)) {
            throw new CodigoNotAvaliableException(
                    String.format("Codigo no disponible. Codigo: %s", productoDTO.getCodigo()));
        }
        Producto producto = productoMapper.dtoToPojo(productoDTO);
        productosService.createProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/habilitar/{codigo}")
    public ResponseEntity<HttpStatus> handleHabilitarProductoByCodigo(@PathVariable("codigo") String codigo)
            throws EntityNotFoundException, EntityAlreadyOnStateException {
        Optional<Producto> optional = productosService.getProductoByCodigo(codigo);
        if (!optional.isPresent()) {
            throw new EntityNotFoundException("Producto no disponible. No puede ser habilitado");
        } else if (optional.get().getDisponible()) {
            throw new EntityAlreadyOnStateException("Producto se encuentra habilitado. No puede ser habilitado");
        }
        productosService.habilitarProducto(optional.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/inhabilitar/{codigo}")
    public ResponseEntity<HttpStatus> handleInhabilitarProductoByCodigo(@PathVariable("codigo") String codigo)
            throws EntityNotFoundException, EntityAlreadyOnStateException {
        Optional<Producto> optional = productosService.getProductoByCodigo(codigo);
        if (!optional.isPresent()) {
            throw new EntityNotFoundException("Producto no disponible. No puede ser habilitado");
        } else if (!optional.get().getDisponible()) {
            throw new EntityAlreadyOnStateException("Producto se encuentra deshabilitado. No puede ser deshabilitado");
        }
        productosService.deshabilitarProducto(optional.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{idProducto}")
    public ResponseEntity<HttpStatus> handleDeleteProducto(@PathVariable UUID idProducto) {
        if (!productosService.alreadyExistById(idProducto)) {
            throw new EntityNotFoundException(String.format("Producto no encontrado. UUID: %s", idProducto));
        }
        productosService.deleteProductoById(idProducto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

}
