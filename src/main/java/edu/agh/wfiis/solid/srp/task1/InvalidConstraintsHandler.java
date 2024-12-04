package edu.agh.wfiis.solid.srp.task1;

import java.text.MessageFormat;
import java.util.Map;

public class InvalidConstraintsHandler {
    public void handle(Map<Constraint, Boolean> unfulfilledConstraints) throws InvalidHeaderException {
        for (Constraint constraint : unfulfilledConstraints.keySet()) {
            if(!unfulfilledConstraints.get(constraint)) {
                throw new InvalidHeaderException("Required header " + constraint.getHeaderName() + " not specified");
            }
            throw new InvalidHeaderException(
                    MessageFormat.format("Invalid value format for header {0}.", constraint.getHeaderName()));
        }
    }
}
