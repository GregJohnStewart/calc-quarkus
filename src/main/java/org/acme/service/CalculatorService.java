package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.service.CalculationResult.CalculationResultBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.acme.service.CalcOp.*;

@ApplicationScoped
public class CalculatorService {

    public CalculationResult calculate(CalculationRequest request){
        CalculationResultBuilder<?, ?> resultBuilder = CalculationResult.fromRequest(request);

        BigDecimal numOne = request.getNumOne();
        BigDecimal numTwo = request.getNumTwo();

        resultBuilder.result(
                switch (request.getOp()) {
                    case ADD -> numOne.add(numTwo);
                    case SUBTRACT -> numOne.subtract(numTwo);
                    case MULT -> numOne.multiply(numTwo);
                    case DIV -> numOne.divide(numTwo, RoundingMode.UNNECESSARY);
                }
        );
        return resultBuilder.build();
    }
}
