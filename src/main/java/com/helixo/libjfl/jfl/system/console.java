package com.helixo.libjfl.jfl.system;

import com.helixo.func.Function;
import com.helixo.func.Functions;
import com.helixo.libjfl.Module;
import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.StringValue;
import com.helixo.syntax.value.Value;

import java.util.Scanner;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

public class console implements Module {
    private static final DoubleFunction<NumberValue> doubleToNumber = v -> new NumberValue(v);

    @Override
    public void init() {
        Functions.set("Introduce_Line", (Value[] args) -> {
            try(Scanner scanner = new Scanner(System.in)) {
                String a = scanner.nextLine();
                return new StringValue(a);
            }
        });

        Functions.set("Introduce_Int", (Value[] args) -> {
            Scanner scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            return new NumberValue(a);
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
