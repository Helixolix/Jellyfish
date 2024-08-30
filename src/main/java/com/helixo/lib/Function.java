package com.helixo.lib;

import com.helixo.syntax.value.Value;

@FunctionalInterface
public interface Function {
    Value execute(Value... args);
}
