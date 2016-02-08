package com.util.Helpers;

import com.DAO.DBAccess;
import com.DAO.DBAccessObjects.DBAccessDetail;
import com.domain.Database.Detail;
import com.domain.VO.CategoryList;
import com.util.Exception.CoreException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by semri on 1/31/2016.
 */
public class DetailHelper {

    public static List<CategoryList> getCategoryList() throws CoreException {

        DBAccessDetail dbAccessDetail = ((DBAccessDetail) DBAccess.getDBAccessObject(Detail.class));
        List<Detail> details = (List<Detail>) (List<?>) dbAccessDetail.loadEveryRow();

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
