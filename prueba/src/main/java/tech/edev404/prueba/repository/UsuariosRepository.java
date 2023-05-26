package tech.edev404.prueba.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.edev404.prueba.model.entity.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, UUID>{

    Optional<Usuario> findFirstByEmail(String email);
    
}
