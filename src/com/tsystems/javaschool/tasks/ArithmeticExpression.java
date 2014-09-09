package com.tsystems.javaschool.tasks;

import java.util.ArrayList;

/**
 * Describes general arithmetic expression in form of a sequence of terms.
 *
 * Created by sevasan on 04.09.14.
 */
public abstract class ArithmeticExpression implements Expression {

    /**
     * Describes a separate piece of an arithmetical expression.
     */

    public class Term {

        public boolean positive; //+ or - for addition, * or / for multiplication
        public Expression expression;

        public Term(boolean positive, Expression expression) {
            super();
            this.positive = positive;
            this.expression = expression;
        }

    }

    protected ArrayList<Term> terms;

    public ArithmeticExpression() {
        this.terms = new ArrayList<Term>();
    }

    public ArithmeticExpression(Expression expression, boolean positive) {
        this.terms = new ArrayList<Term>();
        this.terms.add(new Term(positive, expression));
    }

    public void add(Expression expression, boolean positive) {
        this.terms.add(new Term(positive, expression));
    }
}
