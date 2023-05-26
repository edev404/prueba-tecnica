package tech.edev404.prueba.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import tech.edev404.prueba.configuration.exception.EmailNotAvaliableException;
import tech.edev404.prueba.configuration.exception.EntityAlreadyOnStateException;
import tech.edev404.prueba.configuration.security.model.dto.NewUserRequest;
import tech.edev404.prueba.model.dto.UsuarioDTO;
import tech.edev404.prueba.model.entity.Usuario;
import tech.edev404.prueba.model.enums.AuthorityEnum;
import tech.edev404.prueba.model.mapper.UsuarioMapper;
import tech.edev404.prueba.service.UsuariosService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuarios")
public class UsuariosController {

    private final UsuariosService usuariosService;
    private final UsuarioMapper usuarioMapper;

    @GetMapping("/paginate")
    public ResponseEntity<Page<UsuarioDTO>> handleGetUsuarios(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "9") final Integer size) {

        final Pageable paging = PageRequest.of(page, size);
        final Page<Usuario> pageUsuarios = usuariosService.getUsuariosPaginate(paging);
        final Page<UsuarioDTO> pageUsuariosDTO = new PageImpl<UsuarioDTO>(
                pageUsuarios.map(usuarioMapper::pojoToDto).toList());
        return ResponseEntity.status(HttpStatus.OK).body(pageUsuariosDTO);

    }

    @PostMapping("/registrar")
    public ResponseEntity<HttpStatus> handleCreateUsuario(@RequestBody NewUserRequest newUserRequest)
            throws EntityAlreadyOnStateException {
        if (!usuariosService.emailDisponible(newUserRequest.getEmail())) {
            throw new EmailNotAvaliableException(String.format("Email no disponible"));
        }
        usuariosService.createNewUser(newUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{idUsuario}")
    public ResponseEntity<HttpStatus> handleUsuarioInformation(@PathVariable UUID idUsuario,
            @RequestBody final UsuarioDTO usuarioDTO)
            throws EntityNotFoundException, IllegalAccessException {
        Optional<Usuario> optional = usuariosService.getUsuarioById(idUsuario);
        if (!optional.isPresent()) {
            throw new EntityNotFoundException(String.format("Usuario no encontrado. UUID: %s", idUsuario));
        }
        Usuario current = optional.get();
        Usuario updated = usuarioMapper.dtoToPojo(usuarioDTO);
        if (!current.getAuthority().equals(AuthorityEnum.ROLE_ADMINISTRADOR)) {
            if (current.getEmail() != updated.getEmail() && usuariosService.emailDisponible(updated.getEmail())) {
                throw new EmailNotAvaliableException(String.format("Email no disponible"));
            }
            updated.setPassword(current.getPassword());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
}
