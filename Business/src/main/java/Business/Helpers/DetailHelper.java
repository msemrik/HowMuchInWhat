package Business.Helpers;

import Business.Exceptions.CoreException;
import Business.ObjectMediators.DetailMediator;
import Business.domainObjects.DBObjects.Detail;
import Business.domainObjects.VO.CategoryList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailHelper {
    public static List<CategoryList> getCategoryList() throws CoreException, IOException {
        List<Detail> details = DetailMediator.loadEveryDetail();
        List<CategoryList> categories = new ArrayList<CategoryList>();
        for (Detail detail : details) {
            boolean added = false;
            for (CategoryList category : categories) {
                if (category.getName().equals(detail.getCategory().getName())) {
                    category.addDetail(detail);
                    added = true;
                    break;
                }
            }
            if (!added) {
                CategoryList categoryList = new CategoryList(detail.getCategory().getName());
                categoryList.addDetail(detail);
                categories.add(categoryList);
            }
        }
        return categories;
    }
}

