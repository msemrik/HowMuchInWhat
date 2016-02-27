package Business.domainObjects.DBObjects;

import javax.persistence.*;

@Entity
@Table(name = "detail")
public class Detail implements DBObject {

    @Id
    @GeneratedValue(generator = "DETAIL_SEQ")
    @SequenceGenerator(name = "DETAIL_SEQ", sequenceName = "DETAIL_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    Category category;

    String name;

    public Detail() {
    }

    public Detail(Category category, String name) {
        this.category = category;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toPrint(){
        return name  + "(id: " + id + ", category: " + category.toPrint() + ") ";
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", category=" + category.getName() +
                ", name='" + name + '\'' +
                '}';
    }
}
