package Business.domainObjects.VO;

import Business.Exceptions.CoreException;
import Business.ObjectMediators.CategoryMediator;
import Business.domainObjects.DBObjects.Category;
import java.io.IOException;

public class CategoryVO {

    private Long id;
    private String name;

    public CategoryVO() {
    }

    public CategoryVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category getCategoryfromVO() throws CoreException, IOException {

       return ((Category) CategoryMediator.getCategoryById(this.id));

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
