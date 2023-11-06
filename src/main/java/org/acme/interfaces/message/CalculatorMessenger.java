package org.acme.interfaces.message;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.service.CalculationRequest;
import org.acme.service.CalculationResult;
import org.acme.service.CalculatorService;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class CalculatorMessenger {
    @Channel("calculator-results")
    Emitter<CalculationResult> quoteRequestEmitter;

    @Inject
    CalculatorService calculatorService;

    public void sendResult(CalculationResult result){
        this.quoteRequestEmitter.send(result);
    }

    @Incoming("calculator-requests")
    @Blocking
    public void process(CalculationRequest request) {
        this.calculatorService.calculate(request);
    }

}
