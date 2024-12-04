package edu.agh.wfiis.solid.srp.task1;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRestRequest {

    public HttpRestRequest() {
    }

    public MuleMessage process(MuleMessage muleMessage, List<Constraint> validationConstraints) throws InvalidHeaderException {
        List<Constraint> headerConstraints;
        headerConstraints = getConstraintsThatCanBeHeaders(muleMessage,validationConstraints);
        setHeaders(muleMessage, headerConstraints);

        Map<Constraint, Boolean> unfulfilledConstraints;
        HeaderValidator headerValidator = new HeaderValidator();
        unfulfilledConstraints = headerValidator.validate(muleMessage, validationConstraints);
        InvalidConstraintsHandler invalidConstraintsHandler = new InvalidConstraintsHandler();
        invalidConstraintsHandler.handle(unfulfilledConstraints);
        return muleMessage;
    }

    private  List<Constraint> getConstraintsThatCanBeHeaders(MuleMessage muleMessage, List<Constraint> constraints) {
        List<Constraint> headerConstraints = new ArrayList<>();

        for (Constraint constraint : constraints) {

            String headerName = constraint.getHeaderName();
            String headerValue = muleMessage.getHeader(headerName);

            if (headerValue == null && constraint.getDefaultValue() != null) {
                headerConstraints.add(constraint);
            }
        }
        return headerConstraints;
    }

    private void setHeaders(MuleMessage muleMessage, List<Constraint> constraints) {
        for (Constraint constraint : constraints) {
            String headerName = constraint.getHeaderName();
            String headerValue = muleMessage.getHeader(headerName);

            headerValue = constraint.getDefaultValue();
            muleMessage.setHeader(headerName, headerValue);
        }
    }


}
