package org.autorizador.core;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.aot.DisabledInAotMode;

@Tag("application")
@ExtendWith(MockitoExtension.class)
@DisabledInAotMode
// https://stackoverflow.com/questions/77586355/after-upgrade-to-spring-boot-3-2-aot-failure-code-generation-does-not-support
public abstract class ApplicationTests {
}
