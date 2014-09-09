package com.tsystems.javaschool.tasks;

/**
 * Implements multiplication arithmetic expression. Field positive stands for * or / here.
 *
 * Created by sevasan on 06.09.14.
 */
public class MultiplicationExpression extends ArithmeticExpression {
    public MultiplicationExpression() {}

    public MultiplicationExpression(Expression a, boolean positive) {
        super(a, positive);
    }

    @Override
    public int getType() {
        return Expression.MULTIPLICATION;
    }

    @Override
    public double getValue() {
        double prod = 1.0;
        for (Term t : terms) {
            if (t.positive)
                prod *= t.expression.getValue();
            else
                prod /= t.expression.getValue();
        }
        return prod;
    }
}
