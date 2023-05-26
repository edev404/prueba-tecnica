package tech.edev404.prueba.configuration.bootstrap;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tech.edev404.prueba.model.entity.Producto;
import tech.edev404.prueba.model.entity.Usuario;
import tech.edev404.prueba.model.enums.AuthorityEnum;
import tech.edev404.prueba.repository.ProductosRepository;
import tech.edev404.prueba.service.ProductosService;
import tech.edev404.prueba.service.UsuariosService;

@Component
@RequiredArgsConstructor
public class ExampleDataInitializer implements CommandLineRunner {

    private final UsuariosService usuariosService;
    private final ProductosService productosService;
    private final PasswordEncoder passwordEncoder;
    private final ProductosRepository productosRepository;
    
    @Override
    public void run(String... args) throws Exception {
        loadAdministrator();
        loadSampleProductos();
    }

    private void loadAdministrator(){
        if (usuariosService.emailDisponible("admininistrador@empresa.com")) {
            Usuario admininistrador = Usuario.builder()
                                            .nombre("administrador")
                                            .email("admininistrador@empresa.com")
                                            .apellido("principal")
                                            .telefono("3012116105")
                                            .password(passwordEncoder.encode("administrador"))
                                            .authority(AuthorityEnum.ROLE_ADMINISTRADOR)
                                            .enabled(true)
                                            .build();
            usuariosService.registerUser(admininistrador);
        }
    }

    private void loadSampleProductos(){
        if (productosRepository.count() == 0) {
            
            List<Producto> productos = List.of(
                Producto.builder()
                .codigo("ABC123").nombre("Producto 1")
                .cantidad(new BigDecimal("15"))
                .valorCompra(new BigDecimal("50.25"))
                .valorVenta(new BigDecimal("75.99"))
                .disponible(true)
                .build(), 
                Producto.builder()
                .codigo("DEF123").nombre("Producto 2")
                .cantidad(new BigDecimal("10"))
                .valorCompra(new BigDecimal("40.20"))
                .valorVenta(new BigDecimal("80.99"))
                .disponible(true)
                .build(),
                Producto.builder()
                .codigo("GHI123").nombre("Producto 3")
                .cantidad(new BigDecimal("20"))
                .valorCompra(new BigDecimal("20.25"))
                .valorVenta(new BigDecimal("50.99"))
                .disponible(true)
                .build(),
                Producto.builder()
                .codigo("JKL123").nombre("Producto 4")
                .cantidad(new BigDecimal("2"))
                .valorCompra(new BigDecimal("100.25"))
                .valorVenta(new BigDecimal("250.99"))
                .disponible(true)
                .build(),
                Producto.builder()
                .codigo("MNO123").nombre("Producto 5")
                .cantidad(new BigDecimal("10.5"))
                .valorCompra(new BigDecimal("50.25"))
                .valorVenta(new BigDecimal("75.99"))
                .disponible(true)
                .build(),
                Producto.builder()
                .codigo("PQR123").nombre("Producto 6")
                .cantidad(new BigDecimal("10.5"))
                .valorCompra(new BigDecimal("50.25"))
                .valorVenta(new BigDecimal("75.99"))
                .disponible(true)
                .build()
            );
    
            productosService.saveAll(productos);
        }

    }
    
}
