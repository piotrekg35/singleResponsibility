package edu.agh.wfiis.solid.srp.task1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderValidator {
    public Map<Constraint, Boolean> validate(MuleMessage muleMessage, List<Constraint> constraints) {
        Map<Constraint, Boolean> unfulfilledConstraints = new HashMap<>();

        for (Constraint constraint : constraints) {

            String headerName = constraint.getHeaderName();
            String headerValue = muleMessage.getHeader(headerName);


            if (headerValue == null && constraint.isHeaderRequired()) {
                unfulfilledConstraints.put(constraint, false);
            }

            if (headerValue != null && !constraint.validate(headerValue)) {
                unfulfilledConstraints.put(constraint, true);
            }
        }
        return unfulfilledConstraints;
    }
}
