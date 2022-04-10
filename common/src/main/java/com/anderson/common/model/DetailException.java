package com.anderson.common.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DetailException {

    private int code;
    private String message;
    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

}
