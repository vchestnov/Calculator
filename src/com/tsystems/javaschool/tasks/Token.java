package com.tsystems.javaschool.tasks;

/**
 * Describes single token of an arithmetical statement. Grammar includes digits, '.' (dot) as decimal mark,
 *                  parentheses, operations signs '+', '-', '*', '/' and 'END of input' token. Assignes fixed constants
 *                  for each token type.
 *
 * Created by sevasan on 06.09.14.
 */
public class Token {

    public static final int END = 0;
    public static final int OPEN_BRACKET = 1;
    public static final int CLOSE_BRACKET = 2;
    public static final int PLUSMINUS = 3;
    public static final int MULTDIV = 4;
    public static final int NUMBER = 5;

    public final int tokenId;
    public final String sequence;

    public Token(int tokenId, String sequence) {
        super();

        this.tokenId = tokenId;
        this.sequence = sequence;
    }
}
