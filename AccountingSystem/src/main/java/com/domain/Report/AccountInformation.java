package com.domain.Report;

import java.util.ArrayList;
import java.util.List;

public class AccountInformation {

    private List<String> labelList = new ArrayList<String>();
    private List<String> dataList = new ArrayList<String>();

    public AccountInformation(List<String> labelList, List<String> dataList) {

        this.labelList = labelList;
        this.dataList = dataList;

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
