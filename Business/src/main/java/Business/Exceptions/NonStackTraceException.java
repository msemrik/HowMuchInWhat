package Business.Exceptions;

/**
 * Created by M-Sem on 01/12/2015.
 */
public class NonStackTraceException extends CoreException {

    NonStackTraceException(String loggingMessage, String printingMessage){
        super(loggingMessage, printingMessage);
    }

}