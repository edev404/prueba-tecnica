package tech.edev404.prueba.configuration.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EntityAlreadyOnStateException extends IllegalStateException {

    private String message;

    public EntityAlreadyOnStateException(String message){
        super(message);
        this.message = message;
    }
    
}
