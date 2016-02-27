package DataBase.DAO.InterfacesDAO;

import Business.Exceptions.CoreException;
import Business.domainObjects.DBObjects.Category;
import Business.domainObjects.DBObjects.Detail;
import java.util.List;

public interface DetailDAOInterface {
    Detail getDetailById(long id) throws CoreException;

    Detail getDetailByName(String name) throws CoreException;

    List<Detail> getDetailsForCategory(Category category) throws CoreException;

    List<Detail> loadEveryDetail() throws CoreException;

    void saveDetail(Detail detail) throws CoreException;
    }
