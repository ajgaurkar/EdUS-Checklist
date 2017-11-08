package com.maakservices.saipc.mschecklist;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SAI PC on 11/23/2016.
 */
public class SessionManager {

    // Sharedpref file name
    private static final String PREF_NAME = "appPrefrences";

    public static final String SHOW_PRIORITY = "showPriority";
    public static final String CONFIRM_BEFORE_UNCHECK = "confirmBeforeUncheck";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String LIST_ITEM_EXPAND_STATUS = "listItemDetailStatus";
    public static final String GLOBAL_DATA_INSERTED = "global_data_inserted";


    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


//    public Boolean createLoginSession(String name, String gender, String dOB, float weight, float height, float headCircumference, String babyProfileFileName) {
//        Log.d("INTO shapref CREAT SESS", "INTO shapref CREAT SESS");
//        // Storing login value as TRUE
//        editor.putBoolean(IS_LOGIN, true);
//        // Storing name in pref
//        editor.putString(KEY_BABY_NAME, name);
//        editor.putString(KEY_BABY_PROFILE_FILE_NAME, babyProfileFileName);
//        System.out.println("_SESSION KEY_BABY_PROFILE_FILE_NAME" + babyProfileFileName);
//
//        // commit changes
//        editor.commit();
//
//        return true;
//    }


    /**
     * Get stored session data
     */

//    public HashMap<String, String> getBabyDetails() {
//        HashMap<String, String> baby = new HashMap<String, String>();
//
//
//        baby.put(KEY_BABY_NAME, pref.getString(KEY_BABY_NAME, null));
//        baby.put(KEY_BABY_GENDER, pref.getString(KEY_BABY_GENDER, null));
//        baby.put(KEY_BABY_DOB, pref.getString(KEY_BABY_DOB, null));
//
//        // return user
//        return baby;
//    }
    public String getSpecificStringValue(String dataKEY) {

        String itemValue = pref.getString(dataKEY, null);

        return itemValue;
    }

    public Boolean getSpecificBooleanValue(String dataKEY) {

        Boolean itemValue = pref.getBoolean(dataKEY, false);

        return itemValue;
    }

    //Inserts single STRING value into shared pref
    public void setSpecificStringDetail(String dataKEY, String dataValue) {
        editor.putString(dataKEY, dataValue);
        editor.commit();
    }

    //Inserts single FLOAT value into shared pref
    public void setSpecificBooleanDetail(String dataKEY, Boolean dataValue) {
        editor.putBoolean(dataKEY, dataValue);
        editor.commit();
    }

}