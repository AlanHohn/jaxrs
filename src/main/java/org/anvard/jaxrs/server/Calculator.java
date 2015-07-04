package org.anvard.jaxrs.server;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.anvard.jaxrs.api.Calculation;

@Path("/calculator")
public class Calculator {

	@GET
	@Path("/calc/{op}/{left}/{right}")
    public Calculation calculate(@PathParam("op") String op, @PathParam("left") Integer left,
            @PathParam("right") Integer right) {
        Calculation result = new Calculation();
        result.setOperation(op);
        result.setLeft(left);
        result.setRight(right);
        return doCalc(result);
    }

	@POST
	@Path("/calc2")
    public Calculation calculate(Calculation calc) {
        return doCalc(calc);
    }

    private Calculation doCalc(Calculation c) {
        String op = c.getOperation();
        int left = c.getLeft();
        int right = c.getRight();
        if (op.equalsIgnoreCase("subtract")) {
            c.setResult(left - right);
        } else if (op.equalsIgnoreCase("multiply")) {
            c.setResult(left * right);
        } else if (op.equalsIgnoreCase("divide")) {
            c.setResult(left / right);
        } else {
            c.setResult(left + right);
        }
        return c;
    }
    
}
