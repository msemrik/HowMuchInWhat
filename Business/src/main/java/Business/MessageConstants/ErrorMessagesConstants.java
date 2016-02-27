package Business.MessageConstants;

import Business.Business;

/**
 * Created by semri on 2/20/2016.
 */
public class ErrorMessagesConstants {

    public static String GENERIC_INTERNAL_ERROR = "There was an internal error, please, check the logs.";
    public static String ERROR_MOVEMENT_TODAY_AND_NOT_USE_ACTUAL_DATE = "If its a todays movement, please check 'Use Actual Date' option.";
    public static String ERROR_MOVEMENT_INITIAL_ACCOUNT_DATE_IS_AFTER =  "Error, initial movement for Account: %s, was after: %s";
    public static String ERROR_MOVEMENT_THE_SAME_AS_THE_LAST_SAVED = "This is exactly the same movement you had just saved. If you still want to continue, at least change Commentary.";
    public static String ERROR_MOVEMENT_RECALCULATING_SADDERS = "There was a problem Recalculating %s Sadders. SadderList Size %d . Expected Sadder: %d Founded Sadder: %d";;
}

