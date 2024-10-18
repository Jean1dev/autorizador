package org.autorizador.core.exception;

import java.util.List;

public record AppErrorResponse(String validationError, List<String> details)  {
}
