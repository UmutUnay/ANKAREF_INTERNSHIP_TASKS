package umut.unay.task5_spring_boot.exceptions;

public class EventNotFoundException extends RuntimeException
{
    public EventNotFoundException(String message)
    {
        super(message);
    }
}
