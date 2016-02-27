package Business.ObjectMediators;

import Business.Business;
import Business.Exceptions.CoreException;
import Business.domainObjects.DBObjects.Currency;
import DataBase.DAO.InterfacesDAO.CurrencyDAOInterface;
import java.io.IOException;
import java.util.List;

public class CurrencyMediator implements MediatorInterface {

    public static Currency getCurrencyById(long id) throws CoreException, IOException {
        CurrencyDAOInterface currencyDAOInterface = (CurrencyDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Currency.class);
        Currency currency = currencyDAOInterface.getCurrencyById(id);
        return currency;

    }

    public static List<Currency> loadEveryCurrency() throws CoreException, IOException {
        CurrencyDAOInterface currencyDAOInterface = (CurrencyDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Currency.class);
        List<Currency> currencies = currencyDAOInterface.loadEveryCurrency();
        return currencies;
    }

    public static void saveCurrency(Currency currency) throws CoreException, IOException {
        CurrencyDAOInterface currencyDAOInterface = (CurrencyDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Currency.class);
        currencyDAOInterface.saveCurrency(currency);
    };


}
