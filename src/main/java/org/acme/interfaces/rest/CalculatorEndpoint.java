package org.acme.interfaces.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.acme.service.CalcOp;
import org.acme.service.CalculationRequest;
import org.acme.service.CalculationResult;
import org.acme.service.CalculatorService;

import java.math.BigDecimal;

@Slf4j
@Path("/calculate")
@RequestScoped
public class CalculatorEndpoint {

    @Inject
    CalculatorService calculatorService;

    @GET
    @Path("/{numOne}/{operation}/{numTwo}")
    @Produces(MediaType.APPLICATION_JSON)
    public CalculationResult calc(
            @PathParam("numOne")
            BigDecimal numOne,
            @PathParam("operation")
            CalcOp operation,
            @PathParam("numTwo")
            BigDecimal numTwo
    ) {
        log.info("Got path based request to calculate");
        return this.calculatorService.calculate(
                CalculationRequest.builder()
                        .numOne(numOne)
                        .op(operation)
                        .numTwo(numTwo)
                        .build(),
                true
        );
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CalculationResult calc(
            CalculationRequest request
    ) {
        log.info("Got object based request to calculate");
        return this.calculatorService.calculate(request, true);
    }
}
