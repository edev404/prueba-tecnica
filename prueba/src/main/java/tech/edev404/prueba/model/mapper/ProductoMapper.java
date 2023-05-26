package tech.edev404.prueba.model.mapper;

import org.springframework.stereotype.Service;

import tech.edev404.prueba.model.dto.ProductoDTO;
import tech.edev404.prueba.model.entity.Producto;

@Service
public class ProductoMapper implements GenericMapper<Producto, ProductoDTO>{

    @Override
    public Producto dtoToPojo(ProductoDTO dto) {
        Producto producto = Producto.builder()
                                    .codigo(dto.getCodigo())
                                    .nombre(dto.getNombre())
                                    .valorCompra(dto.getValorCompra())
                                    .valorVenta(dto.getValorVenta())
                                    .disponible(dto.getDisponible())
                                    .cantidad(dto.getCantidad())
                                    .build();
        if (dto.getId() != null) {
            producto.setId(dto.getId());
        }
        return producto;
    }

    @Override
    public ProductoDTO pojoToDto(Producto pojo) {
        ProductoDTO productoDTO = ProductoDTO.builder()
                                    .id(pojo.getId())
                                    .codigo(pojo.getCodigo())
                                    .nombre(pojo.getNombre())
                                    .valorCompra(pojo.getValorCompra())
                                    .valorVenta(pojo.getValorVenta())
                                    .disponible(pojo.getDisponible())
                                    .cantidad(pojo.getCantidad())
                                    .build();
        return productoDTO;
    }
    
}
