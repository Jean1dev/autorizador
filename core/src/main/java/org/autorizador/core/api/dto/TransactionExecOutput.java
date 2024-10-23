package org.autorizador.core.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.autorizador.useCase.records.ProcessResultOutput;

import java.util.List;

public record TransactionExecOutput(
        @JsonProperty("account" ) AccountOutput account,
        @JsonProperty("violations" ) List<String> violations
) {

    public static TransactionExecOutput from(ProcessResultOutput output) {
        return new TransactionExecOutput(
                AccountOutput.from(output.account()),
                output.violations()
        );
    }
}
