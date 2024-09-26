package com.helixo.syntax.expression.impl;

import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.value.ArrayValue;
import com.helixo.syntax.value.Value;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArrayAccessExpression implements Expression {

    private final String variable;
    private final List<Expression> indices;

    @Override
    public Value eval() {
      return getArray().get(lastIndex());
    }

    private ArrayValue getArray() {
        ArrayValue array = consumeArray(VariabletExpression.get(variable));
        int last = indices.size() - 1;
        for (int i = 0; i < last; i++) array = consumeArray(array.get(index(i)));
        return array;
    }

    private int index(int index) {
        return (int) indices.get(index).eval().asNumber();
    }

    private int lastIndex() {
        return index(indices.size() -1);
    }

    private ArrayValue consumeArray(Value var){
        if (var instanceof ArrayValue) {
            return (ArrayValue) var;
        }else {
            throw new RuntimeException("Array expected");
        }
    }
}
