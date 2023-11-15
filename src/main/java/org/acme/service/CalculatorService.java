package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.acme.interfaces.message.CalculatorMessenger;
import org.acme.service.CalculationResult.CalculationResultBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.acme.service.CalcOp.*;

@Slf4j
@ApplicationScoped
public class CalculatorService {

    @Inject
    CalculatorMessenger messenger;

    public CalculationResult calculate(CalculationRequest request, boolean sendOnQueue){
        log.info("Calculating {}", request);
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

        CalculationResult result = resultBuilder.build();
        log.info("Calculated result.");
        if(sendOnQueue) {
            this.messenger.sendResult(result);
        }
        return result;
    }
}
