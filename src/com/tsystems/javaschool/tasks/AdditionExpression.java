package com.tsystems.javaschool.tasks;

/**
 * Implements addition arithmetic expression. Field positive stands for + or - sign here.
 *
 * Created by sevasan on 04.09.14.
 */
public class AdditionExpression extends ArithmeticExpression {

    public AdditionExpression(Expression node, boolean positive){
        super(node, positive);
    }

    @Override
    public int getType() {
        return Expression.ADDITION;
    }

    @Override
    public double getValue() {
        double sum = 0.0;
        for (Term t : terms) {
            if (t.positive)
                sum += t.expression.getValue();
            else
                sum -= t.expression.getValue();
        }
        return sum;
    }
}
