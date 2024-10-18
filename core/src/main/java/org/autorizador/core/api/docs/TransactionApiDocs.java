package org.autorizador.core.api.docs;

import org.autorizador.core.api.dto.AccountCreateInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.autorizador.core.api.dto.TransactionInput;
import org.autorizador.core.api.dto.TransactionExecOutput;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(description = "Transaction Api ", name = "Transaction Api")
public interface TransactionApiDocs {

    @Operation(description = "Create transaction")
    @ApiResponses(value = {
            @ApiResponse(description = "Transaction Created", responseCode = "200"),
            @ApiResponse(description = "Input validation for transaction", responseCode = "400"),
            @ApiResponse(description = "Interal server error", responseCode = "500")
    })
    List<TransactionExecOutput> processTransaction(@RequestBody TransactionInput input);

    @Operation(description = "Process account create")
    @ApiResponses(value = {
            @ApiResponse(description = "Account Created", responseCode = "200"),
            @ApiResponse(description = "Input validation for account", responseCode = "400"),
            @ApiResponse(description = "Interal server error", responseCode = "500")
    })
    List<TransactionExecOutput> processAccountCreate(@RequestBody AccountCreateInput input);
}
