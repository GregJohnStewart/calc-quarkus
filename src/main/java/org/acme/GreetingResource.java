package org.acme;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
public class GreetingResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/calculate/{numOne}/{operation}/{numTwo}")
    @Produces(MediaType.TEXT_PLAIN)
    public String calc(
            @PathParam("numOne")
            String numOneStr,
            @PathParam("operation")
            String operation,
            @PathParam("numTwo")
            String numTwoStr
    ) {
        double numOne;
        double numTwo;
        double result;
        try {
            numOne = Double.parseDouble(numOneStr);
            numTwo = Double.parseDouble(numTwoStr);
        } catch (NullPointerException | NumberFormatException e) {
            throw new IllegalArgumentException("Number(s) were not parseable: " + e.getMessage(), e);
        }

        return String.valueOf(
                switch (operation) {
                    case "ADD" -> numOne + numTwo;
                    case "SUBTRACT" -> numOne - numTwo;
                    case "MULTIPLY" -> numOne * numTwo;
                    case "DIVIDE" -> numOne / numTwo;
                    default -> throw new IllegalArgumentException("Bad operation Given.");
                }
        );
    }
}
