package DataBase.DAO.InterfacesDAO;

import Business.Exceptions.CoreException;
import Business.domainObjects.DBObjects.Category;
import java.util.List;

public interface CategoryDAOInterface {
    Category getCategoryById(long id) throws CoreException;

    List<Category> loadEveryCategory() throws CoreException;

    void saveCategory(Category category) throws CoreException;
}
