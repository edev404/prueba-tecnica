package tech.edev404.prueba.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tech.edev404.prueba.configuration.security.model.dto.NewUserRequest;
import tech.edev404.prueba.model.entity.Usuario;
import tech.edev404.prueba.model.enums.AuthorityEnum;
import tech.edev404.prueba.repository.UsuariosRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuariosService {

    private final PasswordEncoder passwordEncoder;
    private final UsuariosRepository usuariosRepository;

    public boolean emailDisponible(String email) {
        Example<Usuario> example = Example.of(Usuario.builder().email(email).build());
        return !usuariosRepository.exists(example);
    }

    public void createNewUser(NewUserRequest newUserRequest) {
        Usuario newUser = Usuario.builder()
                                .nombre(newUserRequest.getNombre())
                                .apellido(newUserRequest.getApellido())
                                .telefono(newUserRequest.getTelefono())
                                .email(newUserRequest.getEmail())
                                .password(passwordEncoder.encode(newUserRequest.getPassword()))
                                .enabled(true)
                                .authority(AuthorityEnum.ROLE_SUPERVISOR)
                                .build();
        usuariosRepository.save(newUser);
    }

    public void registerUser(Usuario usuario){
        usuariosRepository.save(usuario);
    }

    public Optional<Usuario> getUsuarioById(UUID idUsuario) {
        return usuariosRepository.findById(idUsuario);
    }

    public Page<Usuario> getUsuariosPaginate(Pageable paging) {
        return usuariosRepository.findAll(paging);
    }
    
}
