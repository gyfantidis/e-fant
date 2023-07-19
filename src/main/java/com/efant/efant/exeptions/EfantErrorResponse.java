package com.efant.efant.exeptions;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EfantErrorResponse {

    private String errorMessage;
    private String errorCode;
    private Integer httpStatus;
    private String httpMessage;
    private Instant time;
}
