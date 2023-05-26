package tech.edev404.prueba.model.mapper;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tech.edev404.prueba.model.entity.Usuario;
import tech.edev404.prueba.model.enums.AuthorityEnum;

@Service
public class UserDetailsMapper implements GenericMapper<Usuario, UserDetails> {

    @Override
    public Usuario dtoToPojo(UserDetails dto) {
        
        AuthorityEnum authority = AuthorityEnum.valueOf(dto.getAuthorities().stream().findFirst().get().toString());

        return Usuario.builder()
                .email(dto.getUsername())
                .password(dto.getPassword())
                .enabled(dto.isEnabled())
                .authority(authority)
                .build();
    }

    @Override
    public UserDetails pojoToDto(Usuario pojo) {
        return User.builder()
                .username(pojo.getEmail())
                .password(pojo.getPassword())
                .disabled(!(pojo.getEnabled()))
                .authorities(pojo.getAuthority().toString())
                .build();
    }

}
