package Business.domainObjects.DBObjects;

import javax.persistence.*;

@Entity
@Table(name = "category", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Category implements DBObject {

    @Id
    @GeneratedValue(generator = "CATEGORY_SEQ")
    @SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "CATEGORY_SEQ", allocationSize = 1)
    private Long id;

    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
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

    public String toPrint(){
        return name + "(id: " + id + ") ";
    }
}
