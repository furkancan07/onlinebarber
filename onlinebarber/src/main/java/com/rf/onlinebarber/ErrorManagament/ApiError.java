package com.rf.onlinebarber.ErrorManagament;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp= LocalDateTime.now();
    private Map<String,String> errors;
}
