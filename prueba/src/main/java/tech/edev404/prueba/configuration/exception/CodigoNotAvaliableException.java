package tech.edev404.prueba.configuration.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CodigoNotAvaliableException extends IllegalStateException {

    private String message;

    public CodigoNotAvaliableException(String message){
        super(message);
        this.message = message;
    }

}
