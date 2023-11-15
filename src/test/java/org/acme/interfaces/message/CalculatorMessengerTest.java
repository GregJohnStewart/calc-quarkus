package org.acme.interfaces.message;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.service.CalcOp;
import org.acme.service.CalculationResult;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CalculatorMessengerTest {

    @Inject
    CalculatorMessenger messenger;

    @Test
    public void testSend() throws InterruptedException {
        CalculationResult original = CalculationResult.builder()
                .numOne( BigDecimal.ONE)
                .op(CalcOp.ADD).numTwo(BigDecimal.ONE)
                .result(BigDecimal.valueOf(2))
                .build();
        messenger.sendResult(original);
//        Thread.sleep(1_000);
//
//        assertEquals(1, testMessager.getReceived().size());
//        assertEquals(original, testMessager.getReceived().get(0));
    }
}