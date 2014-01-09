package org.bibly.logic.exceptions;

@SuppressWarnings("serial")
public class UitleningException extends Exception
{

    private String message = "";

    public UitleningException()
    {
    }

    public UitleningException(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }


}
