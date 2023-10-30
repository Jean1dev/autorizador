package org.autorizador.violations;

public class Violation {
    private ViolationDefinition violationDefinition;

    public Violation(ViolationDefinition violationDefinition) {
        this.violationDefinition = violationDefinition;
    }

    public static String buildFromEnum(ViolationDefinition violationDefinition) {
        return new Violation(violationDefinition).getViolationDefinition();
    }

    public String getViolationDefinition() {
        return violationDefinition.name();
    }
}
