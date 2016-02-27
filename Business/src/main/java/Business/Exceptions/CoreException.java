package Business.Exceptions;

/**
 * Created by M-Sem on 01/12/2015.
 */
public class CoreException extends Exception {

    private String forPrintingMessage;
/*
    protected CoreException(String loggingAndPrintingMessage) {
        super(loggingAndPrintingMessage);
        this.forPrintingMessage = loggingAndPrintingMessage;;
    }
*/
    protected CoreException(String loggingMessage, String forPrintingMessage) {
        super(loggingMessage);
        this.forPrintingMessage = forPrintingMessage;

    }

    public CoreException(String loggingMessage, String forPrintingMessage, Exception e) {
        super(loggingMessage, e);
        this.forPrintingMessage = forPrintingMessage;
    }

    public CoreException(String loggingAndPrintingMessage, Exception e) {
        super(loggingAndPrintingMessage, e);
        this.forPrintingMessage = loggingAndPrintingMessage;
    }

    public CoreException getExceptionCause() {
        Throwable exception = this;
        while (exception.getCause() != null && exception.getCause() instanceof CoreException)
            exception = exception.getCause();
        return (CoreException) exception;
    }

    public String getForPrintingMessage() {
        return forPrintingMessage;
    }

    public void setForPrintingMessage(String forPrintingMessage) {
        this.forPrintingMessage = forPrintingMessage;
    }
}