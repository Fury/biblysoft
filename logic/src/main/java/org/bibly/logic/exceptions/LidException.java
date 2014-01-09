package org.bibly.logic.exceptions;

/**
 * Created by Dries on 11/14/13.
 */
@SuppressWarnings("serial")
public class LidException extends Exception
{
    public LidException()
    {
        super();
    }

    public LidException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public LidException(String message)
    {
        super(message);
    }

    public LidException(Throwable cause)
    {
        super(cause);
    }
}
