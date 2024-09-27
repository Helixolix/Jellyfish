package com.helixo.libjfl.jfl.io;

import com.helixo.func.Function;
import com.helixo.func.Functions;
import com.helixo.libjfl.Module;
import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.StringValue;
import com.helixo.syntax.value.Value;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

public class file implements Module {

    private static final DoubleFunction<NumberValue> doubleToNumber = v -> new NumberValue(v);

    @Override
    public void init() {
        Functions.set("Write_File", (Value[] args) -> {
            StringValue path = (StringValue) args[0];
            StringValue content = (StringValue) args[1];
            try {
                Files.write(Paths.get(path.asString()), content.asString().getBytes(StandardCharsets.UTF_8));
                return NumberValue.ZERO;
            } catch (IOException e) {
                throw new RuntimeException("Error writing file: " + e.getMessage());
            }
        });

        Functions.set("Read_File", (Value[] args) -> {
            StringValue path = (StringValue) args[0];
            try {
                String content = Files.readString(Paths.get(path.asString()));
                return new StringValue(content);
            } catch (IOException e) {
                throw new RuntimeException("Error reading file: " + e.getMessage());
            }
        });

        Functions.set("create_New_File", (Value[] args) -> {
            StringValue path = (StringValue) args[0];
            try {
                Files.createFile(Paths.get(path.asString()));
                return NumberValue.ZERO;
            } catch (IOException e) {
                throw new RuntimeException("Error creating file: " + e.getMessage());
            }
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
