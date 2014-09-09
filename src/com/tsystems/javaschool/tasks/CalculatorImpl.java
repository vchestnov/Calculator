package com.tsystems.javaschool.tasks;

import java.util.Locale;

/**
 * Calculator for arithmetical expressions.
 *
 * Created by sevasan on 04.09.14.
 */
public class CalculatorImpl implements Calculator {

    private Tokenizer tokenizer;

    /**
     * Constructor initialises tokenizer.
     */

    public CalculatorImpl() {
        tokenizer = new Tokenizer();

        /**
         * Please look at Token class to find appropriate token ID's.
         */

        tokenizer.add("\\(", 1); // open bracket
        tokenizer.add("\\)", 2); // close bracket
        tokenizer.add("[+-]", 3); // plus or minus
        tokenizer.add("[*/]", 4); // mult or divide
        tokenizer.add("[0-9]+\\.?[0-9]*",5); // float number
    }

    /**
     * Parses and then evaluates arithmetical statement
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     *
     * @return  <code>String</code> with evaluated result if no exceptions occurred
     *          <code>null</code> otherwise
     */

    @Override
    public String evaluate(String statement) {
        Parser parser = new Parser();

        try {
            tokenizer.tokenize(statement);
            return String.format(Locale.ENGLISH,"%.4f", parser.parse(tokenizer.getTokens()).getValue());
        } catch (ParserException ex) {
            return null;
        }
    }
}
