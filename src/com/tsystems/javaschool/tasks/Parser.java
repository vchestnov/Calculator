package com.tsystems.javaschool.tasks;

import java.util.LinkedList;

/**
 * Parses simple arithmetical expressions. Takes tokenized statement and creates an expression tree.
 *
 * Created by sevasan on 06.09.14.
 */
public class Parser {
    /** tokens to parse*/
    LinkedList<Token> tokens;
    /** next token*/
    Token lookahead;

    /**
     *
     * @param tokens tokenized arithmetical statement.
     * @return the internal representation of arithmetical statement in form of expression tree.
     */

    public Expression parse(LinkedList<Token> tokens) {
        this.tokens = (LinkedList<Token>) tokens.clone();
        lookahead = this.tokens.getFirst();

        Expression expr = expression();

        if (lookahead.tokenId != Token.END) {
            throw new ParserException("Unexpected symbol found!");
        }

        return expr;
    }

    /**
     * Starts processing of the expression by taking out sign in front of it and then calling sumOp.
     *
     * @return
     */
    private Expression expression() {
        Expression expr = signedTerm();
        expr = sumOp(expr);
        return expr;
    }

    /**
     * Breaks arithmetical expression into addition terms
     *
     * @param expr to be processed
     * @return internal representation of the expression in form of addition terms (nodes of the tree)
     */

    private Expression sumOp(Expression expr) {
        //sumOp -> PLUSMINUS term sunOp
        if (lookahead.tokenId == Token.PLUSMINUS){
            AdditionExpression sum;
            if (expr.getType() == Expression.ADDITION)
                sum = (AdditionExpression) expr;
            else
                sum = new AdditionExpression(expr, true);

            //reduce the input and recursively calls sum_Op
            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            Expression t = term();
            sum.add(t, positive);

            return sumOp(sum);
        }
        return expr;
    }

    /**
     * Handles signed term: +/- and term after it.
     *
     * @return internal representation of a signed term
     */
    private Expression signedTerm() {
        if (lookahead.tokenId == Token.PLUSMINUS) {
            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            Expression t = term();

            if (positive)
                return t;
            else
                return new AdditionExpression(t, false);
        }
        return term();
    }

    /**
     * Handles terms: argument (as a factor) and termOp after it
     * @return internal representation of a term
     */
    private Expression term() {
        Expression f = argument();
        return termOp(f);
    }

    /**
     * Breaks arithmetical expression into multiplication factors.
     *
     * @param expression to be processed
     * @return internal representation of the expression in form of multiplication factors (nodes of the tree)
     */

    private Expression termOp(Expression expression) {

        //termOp -> MULTDIV factor termOp
        if (lookahead.tokenId == Token.MULTDIV) {
            MultiplicationExpression prod;

            if (expression.getType() == Expression.MULTIPLICATION)
                prod = (MultiplicationExpression) expression;
            else
                prod = new MultiplicationExpression(expression, true);

            //reducing the input and recursively calling termOp
            boolean positive = lookahead.sequence.equals("*");
            nextToken();
            Expression f = signedFactor();
            prod.add(f, positive);

            return termOp(prod);
        }
        return expression;
    }

    /**
     * Handles signed factor: +/- sign and argument after it (as a factor).
     *
     * @return the internal representation of a signed factor
     */

    private Expression signedFactor() {
        if (lookahead.tokenId == Token.PLUSMINUS) {
            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            Expression t = argument();
            if (positive)
                return t;
            else
                return new AdditionExpression(t, false);
        }
        return argument();
    }

    /**
     * Handles arithmetical argument: either a bracket expression (_something_) or a constant expression
     *
     * @return the internal representation of an argument
     */

    private Expression argument() {
        if (lookahead.tokenId == Token.OPEN_BRACKET) {
            nextToken();
            Expression expr = expression();
            if (lookahead.tokenId != Token.CLOSE_BRACKET)
                throw new ParserException("Closing bracket expected");
            nextToken();
            return expr;
        }

        if (lookahead.tokenId == Token.NUMBER) {
            Expression expr = new ExpressionWrapper(lookahead.sequence);
            nextToken();
            return expr;
        }

        if (lookahead.tokenId == Token.END)
            throw new ParserException("Unexpected end of input");
        else
            throw new ParserException("Unexpected symbol found");
    }

    /**
     * Pops next token. If all the tokens popped, places END token in lookahead.
     */

    private void nextToken() {
        tokens.pop();
        if (tokens.isEmpty())
            lookahead = new Token(Token.END, "");
        else
            lookahead = tokens.getFirst();
    }
}
