package org.acme.interfaces.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
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

    @Inject
    ObjectMapper mapper;

    public void sendResult(CalculationResult result){
        this.quoteRequestEmitter.send(result);
    }

    @Incoming("calculator-requests")
    @Blocking
    public void process(JsonNode requestJson) throws JsonProcessingException {
        CalculationRequest request = this.mapper.treeToValue(requestJson, CalculationRequest.class);


        this.calculatorService.calculate(request);
    }

}
