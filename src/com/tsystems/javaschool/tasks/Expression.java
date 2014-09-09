package com.tsystems.javaschool.tasks;

/**
 * Interface for arithmetical expressions. Assigns constants for each expression type.
 *
 * Created by sevasan on 04.09.14.
 */
public interface Expression{

    public static final int CONSTANT = 1;
    public static final int ADDITION = 2;
    public static final int MULTIPLICATION = 3;

    public int getType();

    public double getValue();

}
