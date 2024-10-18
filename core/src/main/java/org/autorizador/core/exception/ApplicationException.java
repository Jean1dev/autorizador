package org.autorizador.core.exception;

import java.util.List;

public class ApplicationException extends RuntimeException{

    public ApplicationException(List<String> exceptions) {
        super(exceptions.toString());
    }
}
