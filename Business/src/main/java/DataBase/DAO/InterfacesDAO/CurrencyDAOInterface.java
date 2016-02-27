package DataBase.DAO.InterfacesDAO;

import Business.Exceptions.CoreException;
import Business.domainObjects.DBObjects.Currency;
import java.util.List;

public interface CurrencyDAOInterface {
    Currency getCurrencyById(long id) throws CoreException;

    List<Currency> loadEveryCurrency() throws CoreException;

    void saveCurrency(Currency currency) throws CoreException;
}

