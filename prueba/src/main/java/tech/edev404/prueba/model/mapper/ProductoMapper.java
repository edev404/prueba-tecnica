package tech.edev404.prueba.model.mapper;

import org.springframework.stereotype.Service;

import tech.edev404.prueba.model.dto.ProductoDTO;
import tech.edev404.prueba.model.entity.Producto;

@Service
public class ProductoMapper implements GenericMapper<Producto, ProductoDTO>{

    @Override
    public Producto dtoToPojo(ProductoDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dtoToPojo'");
    }

    @Override
    public ProductoDTO pojoToDto(Producto pojo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pojoToDto'");
    }
    
}
