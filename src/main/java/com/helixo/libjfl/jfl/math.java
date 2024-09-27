package com.helixo.libjfl.jfl;

import com.helixo.func.Function;
import com.helixo.func.Functions;
import com.helixo.libjfl.Module;
import com.helixo.syntax.expression.impl.VariabletExpression;
import com.helixo.syntax.value.NumberValue;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

public class math implements Module {

        private static final DoubleFunction<NumberValue> doubleToNumber = v -> new NumberValue(v);

        @Override
        public void init() {
            Functions.set("abs", functionConvert(Math::abs));
            Functions.set("cos", functionConvert(Math::cos));
            Functions.set("sin", functionConvert(Math::sin));
            Functions.set("sqrt", functionConvert(Math::sqrt));
            Functions.set("toDegrees", functionConvert(Math::toDegrees));
            Functions.set("toRadians", functionConvert(Math::toRadians));

            Functions.set("pow", biFunctionConvert(Math::pow));
            Functions.set("atan2", biFunctionConvert(Math::atan2));

            VariabletExpression.set("PI", new NumberValue(Math.PI));
            VariabletExpression.set("E", new NumberValue(Math.E));
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