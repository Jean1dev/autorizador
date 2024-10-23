package org.autorizador.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@Tag("e2e")
@AutoConfigureMockMvc
@SpringBootTest(classes = Main.class)
@ActiveProfiles("dev")
public abstract class E2ETests {
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper mapper;
}
