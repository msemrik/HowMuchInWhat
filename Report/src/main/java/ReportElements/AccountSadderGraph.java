package ReportElements;

import Business.Exceptions.CoreException;
import Business.ObjectMediators.AccountSadderMediator;
import Business.domainObjects.DBObjects.Account;
import Business.domainObjects.ReportElements.MonthlyAccountSadder;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountSadderGraph {

    private List<DateTime> dateList = new ArrayList<DateTime>();
    private List<String> stringDateList = new ArrayList<String>();
    private List<Long> sadderList = new ArrayList<Long>();
    private List<String> pointInfo = new ArrayList<String>();

    public AccountSadderGraph(Account account) throws CoreException, IOException {
        List<MonthlyAccountSadder> accountSaddersList = AccountSadderMediator.getAccountSadderMonthlyHistoryForAccount(account);

        MonthlyAccountSadder monthlyAccountSadderElement = accountSaddersList.get(0);
        addNewNode(monthlyAccountSadderElement);

        for (int i = 1; i < accountSaddersList.size(); i++) {
            while (accountSaddersList.get(i).getDate().isAfter(dateList.get(dateList.size() - 1).plusMonths(1))) {
                DateTime newDateTime = dateList.get(dateList.size() - 1).plusMonths(1);
                Long sadder = sadderList.get(sadderList.size() -1);
                monthlyAccountSadderElement = new MonthlyAccountSadder(newDateTime.getYear(), newDateTime.getMonthOfYear(), sadder, sadder, 0);
                addNewNode(monthlyAccountSadderElement);
            }
            monthlyAccountSadderElement = accountSaddersList.get(i);
            addNewNode(monthlyAccountSadderElement);
        }

    }

    public void addNewNode(MonthlyAccountSadder monthlyAccountSadderElement){
        dateList.add(monthlyAccountSadderElement.getDate());
        stringDateList.add(monthlyAccountSadderElement.getStringDate());
        sadderList.add(monthlyAccountSadderElement.getSadderAfterMonth());
        pointInfo.add(monthlyAccountSadderElement.getPointInfo());
    }

    public List<DateTime> getDateList() {
        return dateList;
    }

    public void setDateList(List<DateTime> dateList) {
        this.dateList = dateList;
    }

    public List<Long> getSadderList() {
        return sadderList;
    }

    public void setSadderList(List<Long> sadderList) {
        this.sadderList = sadderList;
    }

    public List<String> getPointInfo() {
        return pointInfo;
    }

    public void setPointInfo(List<String> pointInfo) {
        this.pointInfo = pointInfo;
    }

    public List<String> getStringDateList() {
        return stringDateList;
    }

    public void setStringDateList(List<String> stringDateList) {
        this.stringDateList = stringDateList;
    }
}
