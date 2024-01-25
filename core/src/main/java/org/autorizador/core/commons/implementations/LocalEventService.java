package org.autorizador.core.commons.implementations;

import org.autorizador.core.commons.EventService;
import org.slf4j.LoggerFactory;

public class LocalEventService implements EventService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LocalEventService.class);

    @Override
    public void send(Object message) {
        LOGGER.info("send()", message);
    }
}
