package com.tsystems.javaschool.tasks;

/**
 * Custom exception that is thrown by parser and extends RuntimeException. Doesn't do much, just re-uses parent method.
 *
 * Created by sevasan on 05.09.14.
 */
public class ParserException extends RuntimeException {
    public ParserException(String msg) {
        super(msg);
    }
}
