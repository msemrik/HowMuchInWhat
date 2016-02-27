package ReportElements;

import Business.domainObjects.DBObjects.Account;
import Business.domainObjects.DBObjects.BankAccount;
import Business.domainObjects.DBObjects.Person;
import java.util.ArrayList;
import java.util.List;

public class AccountInformation {

    private List<String> labelList = new ArrayList<String>();
    private List<String> dataList = new ArrayList<String>();

    public AccountInformation(Account account) {

        List<String> labels = new ArrayList<String>();
        labels.add("Id");
        labels.add("Name");
        labels.add("Account type");
        labels.add("Currency");
        labels.add("Account Sadder");

        List<String> data = new ArrayList<String>();
        data.add(String.valueOf(account.getId()));
        data.add(account.getName());
        data.add(account.getTypeOfAccount());
        data.add(account.getCurrency().getSymbol());
        data.add(String.valueOf(account.getAccountSadder()));


        if (account instanceof Person) {
            labels.add("Phone Number:");
            data.add(((Person) account).getPhoneNumber());
        } else if (account instanceof BankAccount) {
            labels.add("Bank");
            labels.add("Type of Account Bank");
            labels.add("Account Number");
            data.add(((BankAccount) account).getBank());
            data.add(account.getTypeOfAccount());
            data.add(((BankAccount) account).getAccountNumber());
        }

        this.labelList = labels;
        this.dataList = data;

    }

    public List<String> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<String> labelList) {
        this.labelList = labelList;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }
}
