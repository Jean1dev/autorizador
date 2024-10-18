package org.autorizador.core.api;

import org.autorizador.core.E2ETests;
import org.autorizador.core.api.dto.AccountCreateInput;
import org.autorizador.core.api.dto.TransactionInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionApiTest extends E2ETests {

    @Test
    @DisplayName("Deve criar uma transação")
    void processTransaction() throws Exception {
        var input = new TransactionInput("Loja", 100);

        final var request = post(TransactionApi.PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(input));

        final var response = this.mvc.perform(request)
                .andDo(print());

        response.andExpect(status().isOk());
    }

    @Test
    void processAccountCreate() throws Exception {
        var input = new AccountCreateInput(true, 1000);

        final var request = post(TransactionApi.PATH + "/account-create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(input));

        final var response = this.mvc.perform(request)
                .andDo(print());

        response.andExpect(status().isOk());
    }
}