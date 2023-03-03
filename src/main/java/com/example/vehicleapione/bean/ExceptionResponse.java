package com.example.vehicleapione.bean;
import lombok.*;
import java.time.ZonedDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private int status;
    private String message;
    private ZonedDateTime data;
}
