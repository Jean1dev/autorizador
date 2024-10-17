package org.autorizador.core.utils;

import java.util.UUID;

public final class IdUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }
}
