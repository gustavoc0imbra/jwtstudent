package org.uniara.jwtstudent.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ResponseToken {
    private String message;
    private String token;
}
