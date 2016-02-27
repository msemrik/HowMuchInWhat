package Business.domainObjects.DBObjects;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "person", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "currency_id"})})
public class Person extends Account {

    private String phoneNumber;

    public Person() {
        setTypeOfAccount("Person");
    }

    public Person(String name, Currency currency, Date startDate, Date endDate, String phoneNumber) {
        super(name, currency, startDate, endDate);
        this.phoneNumber = phoneNumber;
        setTypeOfAccount("Person");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
