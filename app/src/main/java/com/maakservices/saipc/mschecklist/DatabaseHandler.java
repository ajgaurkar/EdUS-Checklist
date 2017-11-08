package com.maakservices.saipc.mschecklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.maakservices.saipc.mschecklist.Controllers.ItemListData;

import java.util.ArrayList;

/**
 * Created by SAI PC on 11/23/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "msCheckListGlobalDatabase";
    private static final String TABLE_CHECK_LIST = "checklisttable";

    //VACCINE TABLE COLUMNS
    private static final String KEY_ITEM_ID = "id";
    private static final String KEY_ITEM_NAME = "itemName";
    private static final String KEY_ITEM_DETAIL = "itemDetail";
    private static final String KEY_MARKED_STATUS_CHECK = "markedStatus";
    private static final String KEY_ITEM_PRIORITY = "itemPriority";
    private static final String KEY_ITEM_CATEGORY = "itemCategory";
    private static final String KEY_CUSTOM_ENTRY = "customEntry";
    private static final String KEY_DELETED_STATUS = "deletedStatus";
    private static final String KEY_DEPARTURE_TYPE = "departureType";
    private static final String KEY_REMINDER_TIME = "reminderTime";
    private static final String KEY_REMINDER_FLAG = "reminderFlag";


    SQLiteDatabase db;
    Cursor cursor;

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE_CHECK_LIST = "CREATE TABLE IF NOT EXISTS " + TABLE_CHECK_LIST + "("
                + KEY_ITEM_ID + " TEXT , " + KEY_ITEM_NAME + " TEXT, " + KEY_ITEM_DETAIL + " TEXT ,"
                + KEY_MARKED_STATUS_CHECK + " INTEGER, " + KEY_DEPARTURE_TYPE + " TEXT ," + KEY_ITEM_CATEGORY
                + " TEXT , " + KEY_CUSTOM_ENTRY + " INTEGER, " + KEY_ITEM_PRIORITY + " TEXT ,"
                + KEY_DELETED_STATUS + " INTEGER ," + KEY_REMINDER_FLAG + " INTEGER ," + KEY_REMINDER_TIME + " TEXT " + ")";

//        String CREATE_TABLE_CHECK_LIST = "CREATE TABLE IF NOT EXISTS " + TABLE_CHECK_LIST + "("
//                + KEY_ITEM_ID + "INTEGER AUTO INCREMENT PRIMARY KEY, " + KEY_ITEM_NAME + " TEXT, " + KEY_MARKED_STATUS_CHECK + " INTEGER, "
//                + KEY_DEPARTURE_TYPE + "TEXT," + KEY_ITEM_CATEGORY + " TEXT, " + KEY_CUSTOM_ENTRY + " INTEGER, "
//                + KEY_DELETED_STATUS + " INTEGER " + ")";


        System.out.println("CREATE_TABLE_CHECK_LIST : " + CREATE_TABLE_CHECK_LIST);

        db.execSQL(CREATE_TABLE_CHECK_LIST);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECK_LIST);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding item
    public void addCheckListItem(ItemListData itemListData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_ID, itemListData.getItemId());
        values.put(KEY_ITEM_NAME, itemListData.getItemName());
        values.put(KEY_ITEM_DETAIL, itemListData.getItemDetail());
        values.put(KEY_ITEM_PRIORITY, itemListData.getPriority());
        values.put(KEY_ITEM_CATEGORY, itemListData.getCategory());
        values.put(KEY_MARKED_STATUS_CHECK, itemListData.getMarkStatus());
        values.put(KEY_CUSTOM_ENTRY, itemListData.getCustomEntry());
        values.put(KEY_DEPARTURE_TYPE, itemListData.getDepartureType());
        values.put(KEY_DELETED_STATUS, itemListData.getDeletedStatus());
        values.put(KEY_REMINDER_FLAG, itemListData.getReminderFlag());
        values.put(KEY_REMINDER_TIME, itemListData.getReminderTime());

        System.out.println("INSIDE addCheckListItem getItemId" + itemListData.getItemId());
        System.out.println("INSIDE addCheckListItem getItemName" + itemListData.getItemName());
        System.out.println("INSIDE addCheckListItem getItemDetail" + itemListData.getItemDetail());
        System.out.println("INSIDE addCheckListItem getPriority" + itemListData.getPriority());
        System.out.println("INSIDE addCheckListItem getCategory" + itemListData.getCategory());
        System.out.println("INSIDE addCheckListItem getMarkStatus" + itemListData.getMarkStatus());
        System.out.println("INSIDE addCheckListItem getCustomEntry" + itemListData.getCustomEntry());
        System.out.println("INSIDE addCheckListItem getDepartureType" + itemListData.getDepartureType());
        System.out.println("INSIDE addCheckListItem getDeletedStatus" + itemListData.getDeletedStatus());
        System.out.println("INSIDE addCheckListItem getReminderFlag" + itemListData.getReminderFlag());
        System.out.println("INSIDE addCheckListItem getReminderTime" + itemListData.getReminderTime());

        // Inserting Row
        long insertResult = db.insert(TABLE_CHECK_LIST, null, values);
        System.out.println("insertResult : " + insertResult);
        db.close(); // Closing database connection
    }

    public Cursor getAllCheckListDataByCursor() {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CHECK_LIST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public ArrayList<ItemListData> getItemList(String category, String departureType, int deleted, int custom) {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CHECK_LIST + " WHERE " + KEY_ITEM_CATEGORY + " = '" + category
                + "' AND " + KEY_DEPARTURE_TYPE + " = '" + departureType + "' AND " + KEY_DELETED_STATUS + " = " + deleted
                + " AND " + KEY_CUSTOM_ENTRY + " = " + custom;

        System.out.println("selectQuery : " + selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        System.out.println("cursor.getCount() : " + cursor.getCount());
        System.out.println("cursor.getColumnCount() : " + cursor.getColumnCount());

        ArrayList<ItemListData> itemList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                itemList.add(new ItemListData(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(7),
                        cursor.getString(5),
                        cursor.getInt(3),
                        cursor.getInt(6),
                        cursor.getInt(8),
                        cursor.getString(4),
                        cursor.getInt(9),
                        cursor.getString(10)));

                System.out.println("LOOOP ITEM LIST : " + itemList);


            } while (cursor.moveToNext());
        }
        System.out.println("FINAAAAAL ITEM LIST : " + itemList);

        return itemList;
    }

    public ArrayList<ItemListData> getItemListForTrash(int deletedStatus) {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CHECK_LIST + " WHERE " + KEY_DELETED_STATUS + " = " + deletedStatus;

        System.out.println("selectQuery : " + selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        System.out.println("cursor.getCount() : " + cursor.getCount());
        System.out.println("cursor.getColumnCount() : " + cursor.getColumnCount());

        ArrayList<ItemListData> itemList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                itemList.add(new ItemListData(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(7),
                        cursor.getString(5),
                        cursor.getInt(3),
                        cursor.getInt(6),
                        cursor.getInt(8),
                        cursor.getString(4),
                        cursor.getInt(9),
                        cursor.getString(10)));

                System.out.println("LOOOP ITEM LIST : " + itemList);


            } while (cursor.moveToNext());
        }
        System.out.println("FINAAAAAL ITEM LIST : " + itemList);

        return itemList;
    }


    // Updating Vaccine Tables
    public int updateItem(ItemListData itemListData) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_ID, itemListData.getItemId()); // Vaccine Name
        values.put(KEY_ITEM_NAME, itemListData.getItemName()); // Vaccine grup
        values.put(KEY_ITEM_PRIORITY, itemListData.getPriority()); // Vaccine name
        values.put(KEY_MARKED_STATUS_CHECK, itemListData.getMarkStatus()); // Vaccine duedate
        values.put(KEY_CUSTOM_ENTRY, itemListData.getCustomEntry()); // Vaccine taken date
        values.put(KEY_DEPARTURE_TYPE, itemListData.getDepartureType()); // Vaccine taken date
        values.put(KEY_DELETED_STATUS, itemListData.getDeletedStatus()); // Vaccine status
        values.put(KEY_ITEM_CATEGORY, itemListData.getCategory()); // Vaccine status
        values.put(KEY_ITEM_DETAIL, itemListData.getItemDetail()); // Vaccine status
        values.put(KEY_REMINDER_FLAG, itemListData.getReminderFlag()); // Vaccine status
        values.put(KEY_REMINDER_TIME, itemListData.getReminderTime()); // Vaccine status

        System.out.println("");
        System.out.println("KEY_ITEM_ID " + itemListData.getItemId()); // Vaccine Name
        System.out.println("KEY_ITEM_NAME " + itemListData.getItemName()); // Vaccine grup
        System.out.println("KEY_ITEM_PRIORITY " + itemListData.getPriority()); // Vaccine name
        System.out.println("KEY_MARKED_STATUS_CHECK " + itemListData.getMarkStatus()); // Vaccine duedate
        System.out.println("KEY_CUSTOM_ENTRY " + itemListData.getCustomEntry()); // Vaccine taken date
        System.out.println("KEY_DEPARTURE_TYPE " + itemListData.getDepartureType()); // Vaccine taken date
        System.out.println("KEY_DELETED_STATUS " + itemListData.getDeletedStatus()); // Vaccine status
        System.out.println("KEY_ITEM_CATEGORY " + itemListData.getCategory()); // Vaccine status
        System.out.println("KEY_ITEM_DETAIL " + itemListData.getItemDetail()); // Vaccine status
        System.out.println("getReminderTime " + itemListData.getReminderTime()); // Vaccine status
        System.out.println("getReminderFlag " + itemListData.getReminderFlag()); // Vaccine status


        // updating row
        return db.update(TABLE_CHECK_LIST, values, KEY_ITEM_ID + " = ?",
                new String[]{String.valueOf(itemListData.getItemId())});
    }

    //    public int deleteCalendarRecord(CalendarEntryListData calendarEntryListData) {
    public int deleteItemEntry(String itemId) {
        db = this.getWritableDatabase();

        System.out.println("DatabaseHandler itemId : " + itemId);
        // deleting record
        return db.delete(TABLE_CHECK_LIST, KEY_ITEM_ID + "= ?", new String[]{itemId});

    }

    //This method is used in MainActivtiy  Class to  delete all table from database at time of sign out .
    public void deleteDatabaseTables() {
        System.out.println("INSIDE DROP QUERY");

        SQLiteDatabase deleteObj = this.getReadableDatabase();
        deleteObj.delete(TABLE_CHECK_LIST, null, null);
        deleteObj.close();

    }

}

