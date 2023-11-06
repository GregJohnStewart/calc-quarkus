package org.acme.service;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CalculationRequest {
    @NonNull
    private BigDecimal numOne;
    @NonNull
    private CalcOp op;
    @NonNull
    private BigDecimal numTwo;
}
