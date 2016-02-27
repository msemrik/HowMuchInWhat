package Business.ObjectMediators;

import Business.Business;
import Business.Exceptions.CoreException;
import Business.domainObjects.DBObjects.Category;
import Business.domainObjects.DBObjects.Detail;
import DataBase.DAO.InterfacesDAO.DetailDAOInterface;
import java.io.IOException;
import java.util.List;

public class DetailMediator implements MediatorInterface {

    public static List<Detail> loadEveryDetail() throws CoreException, IOException {
        DetailDAOInterface detailDAOInterface = (DetailDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Detail.class);
        List<Detail> details = detailDAOInterface.loadEveryDetail();
        return details;
    }

    public static Detail getDetailById(long id) throws CoreException, IOException {
        DetailDAOInterface detailDAOInterface = (DetailDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Detail.class);
        Detail detail = detailDAOInterface.getDetailById(id);
        return detail;

    }

    public static List<Detail> getDetailsForCategory(Category category) throws CoreException, IOException {
        DetailDAOInterface detailDAOInterface = (DetailDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Detail.class);
        List<Detail> details = detailDAOInterface.getDetailsForCategory(category);
        return details;
    }

    public static Detail getDetailByName(String name) throws CoreException, IOException {
        DetailDAOInterface detailDAOInterface = (DetailDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Detail.class);
        Detail detail = detailDAOInterface.getDetailByName(name);
        return detail;
    }

    public static void saveDetail(Detail detail) throws CoreException, IOException {
        DetailDAOInterface detailDAOInterface = (DetailDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Detail.class);
        detailDAOInterface.saveDetail(detail);
    }


}