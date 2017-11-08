package com.maakservices.saipc.mschecklist.Controllers;

/**
 * Created by SAI PC on 11/22/2016.
 */
public class ItemListData {

    String itemId;
    String itemName;
    String itemDetail;
    String priority;
    String category;
    int markStatus;
    int customEntry;
    String departureType;
    int deletedStatus;
    int reminderFlag;
    String reminderTime;

    public ItemListData(String itemId, String itemName, String itemDetail, String priority, String category, int markStatus, int customEntry, int deletedStatus, String departureType, int reminderFlag, String reminderTime) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.priority = priority;
        this.category = category;
        this.markStatus = markStatus;
        this.customEntry = customEntry;
        this.departureType = departureType;
        this.reminderTime = reminderTime;
        this.deletedStatus = deletedStatus;
        this.reminderFlag = reminderFlag;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public int getReminderFlag() {
        return reminderFlag;
    }

    public void setReminderFlag(int reminderFlag) {
        this.reminderFlag = reminderFlag;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public String getDepartureType() {
        return departureType;
    }

    public void setDepartureType(String departureType) {
        this.departureType = departureType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMarkStatus() {
        return markStatus;
    }

    public void setMarkStatus(int markStatus) {
        this.markStatus = markStatus;
    }

    public int getCustomEntry() {
        return customEntry;
    }

    public void setCustomEntry(int customEntry) {
        this.customEntry = customEntry;
    }

    public int getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(int deletedStatus) {
        this.deletedStatus = deletedStatus;
    }
}
