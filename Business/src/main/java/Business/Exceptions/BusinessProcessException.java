package Business.Exceptions;

public class BusinessProcessException extends CoreException {

    public BusinessProcessException(String loggingMessage, String printingMessage) {
        super(loggingMessage, printingMessage);
    }

    public BusinessProcessException(String loggingAndPrintingMessage) {
        super(loggingAndPrintingMessage, loggingAndPrintingMessage);
    }
}
