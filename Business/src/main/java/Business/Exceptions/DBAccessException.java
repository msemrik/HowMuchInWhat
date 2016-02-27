package Business.Exceptions;

/**
 * Created by semri on 2/18/2016.
 */
public class DBAccessException extends CoreException {

    public DBAccessException(String loggingMessage, String printingMessage, Exception e) {
        super(loggingMessage, printingMessage,e);
    }

    public DBAccessException(String loggingAndPrintingMessage, Exception e) {
        super(loggingAndPrintingMessage, loggingAndPrintingMessage,e);
    }
}
