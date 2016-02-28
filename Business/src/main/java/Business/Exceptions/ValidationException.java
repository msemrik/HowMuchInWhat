package Business.Exceptions;

public class ValidationException extends NonStackTraceException {

    public ValidationException(String loggingMessage, String printingMessage) {
        super(loggingMessage, printingMessage);
    }

    public ValidationException(String loggingAndPrintingMessage) {
        super(loggingAndPrintingMessage, loggingAndPrintingMessage);
    }
}
