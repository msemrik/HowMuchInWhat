package Business.domainObjects.DBObjects;

import javax.persistence.*;

@Entity
@Table(name = "currency", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Currency implements DBObject {

    @Id
    @GeneratedValue(generator = "CURRENCY_SEQ")
    @SequenceGenerator(name = "CURRENCY_SEQ", sequenceName = "CURRENCY_SEQ", allocationSize = 1)
    private Long id;

    private String name;
    private String symbol;
    private long price;

    public Currency() {
    }

    public Currency(String name, String symbol, long price) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
    }

    @Override
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String toPrint(){
        return  symbol + "(id: " + id + ") ";
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
