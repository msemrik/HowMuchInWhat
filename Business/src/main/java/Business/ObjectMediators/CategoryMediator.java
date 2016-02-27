package Business.ObjectMediators;

import Business.Business;
import Business.Exceptions.CoreException;
import Business.domainObjects.DBObjects.Category;
import DataBase.DAO.InterfacesDAO.CategoryDAOInterface;
import java.io.IOException;
import java.util.List;

public class CategoryMediator {

    public static Category getCategoryById(long id) throws CoreException, IOException {
        CategoryDAOInterface categoryDAOInterface = (CategoryDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Category.class);
        Category category = (Category) categoryDAOInterface.getCategoryById(id);
        return category;
    }

    public static List<Category> loadEveryCategory() throws CoreException, IOException {
        CategoryDAOInterface categoryDAOInterface = (CategoryDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Category.class);
        List<Category> categories = categoryDAOInterface.loadEveryCategory();
        return categories;
    }


    public static void saveCategory(Category category) throws CoreException, IOException {
        CategoryDAOInterface categoryDAOInterface = (CategoryDAOInterface) Business.getBusiness().getDbAccess().getDbManager().getDBAccessObject(Category.class);
        categoryDAOInterface.saveCategory(category);
    }


}
