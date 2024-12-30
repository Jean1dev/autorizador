package org.autorizador.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.servlet.MockMvc;

@Tag("e2e")
@AutoConfigureMockMvc
@SpringBootTest(classes = Main.class)
@ActiveProfiles("dev")
@DisabledInAotMode // https://stackoverflow.com/questions/77586355/after-upgrade-to-spring-boot-3-2-aot-failure-code-generation-does-not-support
public abstract class ApiTests extends PgTestConfiguration {
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper mapper;
}
