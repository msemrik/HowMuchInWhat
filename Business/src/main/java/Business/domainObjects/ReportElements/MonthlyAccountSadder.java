package Business.domainObjects.ReportElements;

import org.joda.time.DateTime;

/**
 * Created by semri on 2/6/2016.
 */
public class MonthlyAccountSadder {

    private int year;
    private int month;
    private String stringDate;
    private DateTime date;
    private Long sadderBeforeMonth;
    private Long sadderAfterMonth;
    private int numberOfMovements;

    public MonthlyAccountSadder(int year, int month, Long sadderBeforeMonth, Long sadderAfterMonth, int numberOfMovements) {
        this.year = year;
        this.month = month;
        this.stringDate = String.valueOf(month)+"/"+String.valueOf(year);
        this.date = new DateTime(year, month, 1, 0, 0, 0);
        this.sadderBeforeMonth = sadderBeforeMonth;
        this.sadderAfterMonth = sadderAfterMonth;
        this.numberOfMovements = numberOfMovements;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Long getSadderBeforeMonth() {
        return sadderBeforeMonth;
    }

    public void setSadderBeforeMonth(Long sadderBeforeMonth) {
        this.sadderBeforeMonth = sadderBeforeMonth;
    }

    public Long getSadderAfterMonth() {
        return sadderAfterMonth;
    }

    public void setSadderAfterMonth(Long sadderAfterMonth) {
        this.sadderAfterMonth = sadderAfterMonth;
    }

    public int getNumberOfMovements() {
        return numberOfMovements;
    }

    public void setNumberOfMovements(int numberOfMovements) {
        this.numberOfMovements = numberOfMovements;
    }
    public String getPointInfo(){
        return "Month: " + getStringDate() + ". Sadder Before Month: " + getSadderBeforeMonth() + ", Sadder After Month: " + getSadderAfterMonth() + ". Number of Movements: " + getNumberOfMovements();
    }
}
