package tech.edev404.prueba.model.mapper;

import org.springframework.stereotype.Service;

import tech.edev404.prueba.model.dto.UsuarioDTO;
import tech.edev404.prueba.model.entity.Usuario;
import tech.edev404.prueba.model.enums.AuthorityEnum;

@Service
public class UsuarioMapper implements GenericMapper<Usuario, UsuarioDTO>{

    @Override
    public Usuario dtoToPojo(UsuarioDTO dto) {
        Usuario usuario = Usuario.builder()
                                .nombre(dto.getNombre())
                                .apellido(dto.getApellido())
                                .telefono(dto.getTelefono())
                                .email(dto.getEmail())
                                .password(dto.getPassword())
                                .authority(AuthorityEnum.valueOf(dto.getAuthority()))
                                .enabled(true)
                                .build();
        if (dto.getId() != null) {
            usuario.setId(dto.getId());
        }
        return usuario;
    }

    @Override
    public UsuarioDTO pojoToDto(Usuario pojo) {
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                                        .id(pojo.getId())
                                        .nombre(pojo.getNombre())
                                        .apellido(pojo.getApellido())
                                        .telefono(pojo.getTelefono())
                                        .email(pojo.getEmail())
                                        .password("")
                                        .authority(pojo.getAuthority().toString())
                                        .enabled(pojo.getEnabled())
                                        .build();
        return usuarioDTO;
    }
    
}
