package tech.edev404.prueba.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import tech.edev404.prueba.model.entity.Producto;

@Repository
public interface ProductosRepository extends JpaRepository<Producto, UUID> {

    Optional<Producto> findFirstByCodigo(String codigo);

    @Modifying
    @Transactional
    @Query(value="UPDATE productos AS p SET p.habilitado = true WHERE p.id = :idProducto" , nativeQuery = true)
    void habilitarProductoById(UUID idProducto);

    @Modifying
    @Transactional
    @Query(value="UPDATE productos AS p SET p.habilitado = false WHERE p.id = :idProducto" , nativeQuery = true)
    void deshabilitarProductoById(UUID idProducto);
    
}
