package com.helixo.libjfl.jfl.util;

import com.helixo.lib.Function;
import com.helixo.lib.Functions;
import com.helixo.libjfl.Module;
import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.StringValue;
import com.helixo.syntax.value.Value;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

public class convert implements Module {
    private static final DoubleFunction<NumberValue> doubleToNumber = v -> new NumberValue(v);

    @Override
    public void init() {
        Functions.set("To_Int", args -> {
            if (args.length != 1) throw new RuntimeException("One arg expected");
            StringValue str = (StringValue) args[0];
            try {
                int number = Integer.parseInt(str.asString());
                return new NumberValue(number);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid string format for integer conversion: " + e.getMessage());
            }
        });

        Functions.set("To_String", (Value[] args) -> {
            if (args.length != 1) throw new RuntimeException("One arg expected");
            return new StringValue(String.valueOf(args[0].asNumber()));
        });
    }

    private static Function functionConvert(DoubleUnaryOperator op) {
        return args -> {
            if (args.length != 1) throw new RuntimeException("One arg expected");
            return doubleToNumber.apply(op.applyAsDouble(args[0].asNumber()));
        };
    }

    private static Function biFunctionConvert(DoubleBinaryOperator op) {
        return args -> {
            if (args.length != 2) throw new RuntimeException("Two args expected");
            return doubleToNumber.apply(op.applyAsDouble(args[0].asNumber(), args[1].asNumber()));
        };
    }
}
