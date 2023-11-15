package org.acme.interfaces.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.common.annotation.Blocking;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.acme.service.CalculationRequest;
import org.acme.service.CalculationResult;
import org.acme.service.CalculatorService;
import org.eclipse.microprofile.reactive.messaging.*;

@Slf4j
@ApplicationScoped
public class CalculatorMessenger {
    @Channel("calculator-results")
    Emitter<CalculationResult> quoteRequestEmitter;

    @Inject
    CalculatorService calculatorService;

    @Inject
    ObjectMapper mapper;

    public void sendResult(CalculationResult result){
        log.info("Sending result: {}", result);
        this.quoteRequestEmitter.send(result);
        log.info("Sent result: {}", result);
    }

    @Incoming("calculator-requests")
    @Blocking
    public void process(JsonObject requestJson) throws JsonProcessingException {
        log.info("Got request from Queue: {}", requestJson);
        CalculationRequest request = requestJson.mapTo(CalculationRequest.class);
        //will send the result back via sendResult() above on its own
        this.calculatorService.calculate(request);
    }

}
