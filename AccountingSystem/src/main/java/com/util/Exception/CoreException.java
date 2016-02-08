package com.util.Exception;

/**
 * Created by M-Sem on 01/12/2015.
 */
public class CoreException extends Exception
{
    //Parameterless Constructor
    public CoreException() {}

    //Constructor that accepts a message
    public CoreException(String message)
    {
        super(message);
    }
}