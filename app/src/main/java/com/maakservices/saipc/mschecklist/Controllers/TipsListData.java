package com.maakservices.saipc.mschecklist.Controllers;

/**
 * Created by Ajinkya on 12/1/2016.
 */
public class TipsListData {
    String tipId;
    String tipName;
    String tipDetail;
    String tipPriority;
    String tipCategory;
    Boolean tipExpandStatus;

    public TipsListData(String tipId, String tipName, String tipDetail, String tipPriority, String tipCategory, Boolean tipExpandStatus) {
        this.tipId = tipId;
        this.tipName = tipName;
        this.tipDetail = tipDetail;
        this.tipPriority = tipPriority;
        this.tipCategory = tipCategory;
        this.tipExpandStatus = tipExpandStatus;
    }

    public String getTipId() {
        return tipId;
    }

    public void setTipId(String tipId) {
        this.tipId = tipId;
    }

    public String getTipName() {
        return tipName;
    }

    public void setTipName(String tipName) {
        this.tipName = tipName;
    }

    public String getTipDetail() {
        return tipDetail;
    }

    public void setTipDetail(String tipDetail) {
        this.tipDetail = tipDetail;
    }

    public String getTipPriority() {
        return tipPriority;
    }

    public void setTipPriority(String tipPriority) {
        this.tipPriority = tipPriority;
    }

    public String getTipCategory() {
        return tipCategory;
    }

    public void setTipCategory(String tipCategory) {
        this.tipCategory = tipCategory;
    }

    public Boolean getTipExpandStatus() {
        return tipExpandStatus;
    }

    public void setTipExpandStatus(Boolean tipExpandStatus) {
        this.tipExpandStatus = tipExpandStatus;
    }
}
