package tech.edev404.prueba.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tech.edev404.prueba.model.dto.ProductoDTO;
import tech.edev404.prueba.model.entity.Producto;
import tech.edev404.prueba.repository.ProductosRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductosService {

    private final ProductosRepository productosRepository;

    public Page<Producto> getProductosPaginate(Pageable paging) {
        return productosRepository.findAll(paging);
    }

    public Page<Producto> getProductosPaginateByExample(Pageable paging, Example<Producto> example) {
        return productosRepository.findAll(example, paging);
    }

    public boolean alreadyExistByCodigo(String codigo) {
        return false;
    }

    public void updateProducto(Producto producto) {
        productosRepository.save(producto);
    }

    public boolean alreadyExistById(UUID idProducto) {
        return productosRepository.existsById(idProducto);
    }

    public void deleteProductoById(UUID idProducto) {
        productosRepository.deleteById(idProducto);
    }

    public boolean codigoDisponible(String codigo) {
        Example<Producto> example = Example.of(Producto.builder().codigo(codigo).build());
        return productosRepository.exists(example);
    }

    public Optional<Producto> getProductoByCodigo(String codigo) {
        return productosRepository.findFirstByCodigo(codigo);
    }

    public Optional<Producto> getProductoById(UUID idProducto) {
        return productosRepository.findById(idProducto);
    }

    public void habilitarProducto(Producto producto) {
        productosRepository.habilitarProductoById(producto.getId());
    }

    public void deshabilitarProducto(Producto producto) {
        productosRepository.deshabilitarProductoById(producto.getId());
    }

    public boolean codigoDisponible(ProductoDTO productoDTO) {
        Optional<Producto> optional = getProductoByCodigo(productoDTO.getCodigo());
        if(optional.isPresent()){
            Producto producto = optional.get();
            return producto.getId().equals(productoDTO.getId());
        }
        return true;
    }

    public void createProducto(Producto producto) {
        productosRepository.save(producto);
    }

    public void saveAll(List<Producto> productos) {
        productosRepository.saveAll(productos);
    }
    
}
