package com.helixo.syntax.tokens;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {
    private TokenType type;
    private String text;
}
