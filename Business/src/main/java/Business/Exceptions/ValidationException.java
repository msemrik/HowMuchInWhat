package Business.Exceptions;

public class ValidationException extends NonStackTraceException {

    public ValidationException(String loggingMessage, String printingMessage) {
        super(loggingMessage, printingMessage);
    }

    public ValidationException(String loggingAndPrintingMessage) {
        super(loggingAndPrintingMessage, loggingAndPrintingMessage);
    }

/*
    public StackTraceElement[]  getStackTrace(){
        StackTraceElement[] stackTraceElements = new StackTraceElement[1];
        stackTraceElements[0] = new StackTraceElement();
        return stackTraceElements;
    }
*/

}
