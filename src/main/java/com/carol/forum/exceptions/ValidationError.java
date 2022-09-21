package com.carol.forum.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ValidationError {
    private LocalDateTime timestamp;
    private Integer status;
    private List<ValidationFieldError> fieldErrors;
    private String path;
}
