package tech.edev404.prueba.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.edev404.prueba.model.enums.AuthorityEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios", indexes = @Index(name="usuarios_unique", columnList = "email", unique = true))
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(length = 75, nullable = false)
    private String nombre;

    @Column(length = 75, nullable = false)
    private String apellido;

    @Column(length = 15)
    private String telefono;

    @Column(length = 25, nullable = false)
    private String email;

    @Column(length = 250, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
	private AuthorityEnum authority;

    @ColumnDefault(value = "true")
    private Boolean enabled;

    @CreationTimestamp
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    private LocalDateTime lastModified;
   
}
