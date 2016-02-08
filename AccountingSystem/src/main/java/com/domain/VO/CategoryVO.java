package com.domain.VO;

import com.DAO.DBAccess;
import com.domain.Database.Category;
import com.util.Exception.CoreException;

public class CategoryVO {

    private Long id;
    private String name;

    public CategoryVO() {
    }

    public CategoryVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category getCategoryfromVO() throws CoreException {

       return ((Category) DBAccess.getDBAccessObject(Category.class).getObjectById(this.id));

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
