package com.tsystems.javaschool.tasks;

/**
 * Implements constant arithmetic expression, i.e. just get doubles from string tokens.
 *                  This is the leaf of the expression tree.
 *
 * Created by sevasan on 04.09.14.
 */
public class ExpressionWrapper implements Expression {
    private double value;

    public ExpressionWrapper(String value) {
        this.value = Double.valueOf(value);
    }

    @Override
    public int getType() {
        return Expression.CONSTANT;
    }

    @Override
    public double getValue() {
        return this.value;
    }
}
