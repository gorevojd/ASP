package com.example.gorevojd.laba6smelov;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GOREVOJD on 28.11.2016.
 */

public class MyContentProvider extends ContentProvider {

    public MyDBHelper dbHelper;
    public SQLiteDatabase db;

    static final String AUTHORITY = "my.authority";
    static final String GROUPS_PATH = "STUDGROUPS";
    static final String STUDENTS_PATH = "STUDENTS";
    static final String TEMPV_PATH = "TempV";

    public static final String GROUP_CONTENT_URI_STRING = "content://"+AUTHORITY+"/"+GROUPS_PATH;
    public static final String STUD_CONTENT_URI_STRING = "content://"+AUTHORITY+"/"+STUDENTS_PATH;
    public static final String TEMPV_CONTENT_URI_STRING = "content://"+AUTHORITY+"/"+TEMPV_PATH;

    public static final Uri GROUP_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+GROUPS_PATH);
    public static final Uri STUD_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+STUDENTS_PATH);
    public static final Uri TEMPV_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+TEMPV_PATH);

    static final int URI_GROUPS = 1;
    static final int URI_GROUPS_ID = 2;
    static final int URI_STUD_IDGR = 3;
    static final int URI_STUD_IDGR_IDS = 4;
    static final int URI_TEMPV = 5;
    static final int URI_TEMPV_ID = 6;

    static final String LOG_TAG = "Lab9";

    private static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, GROUPS_PATH, URI_GROUPS);
        uriMatcher.addURI(AUTHORITY, GROUPS_PATH + "/#", URI_GROUPS_ID);
        uriMatcher.addURI(AUTHORITY, STUDENTS_PATH + "/#", URI_STUD_IDGR);
        uriMatcher.addURI(AUTHORITY, STUDENTS_PATH + "/#/#", URI_STUD_IDGR_IDS);
        uriMatcher.addURI(AUTHORITY, TEMPV_PATH, URI_TEMPV);
        uriMatcher.addURI(AUTHORITY, TEMPV_PATH + "/#", URI_TEMPV_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDBHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        ArrayList<String> temp = GetGroupAndSelectionString(uri, selection);
        String GroupOrStStr = temp.get(0);
        Uri tempUri = Uri.parse("");
        switch(GroupOrStStr)
        {
            case(GROUPS_PATH):{
                tempUri = GROUP_CONTENT_URI;
            }break;
            case(STUDENTS_PATH):{
                tempUri = STUD_CONTENT_URI;
            }break;
            case(TEMPV_PATH):{
                tempUri = TEMPV_CONTENT_URI;
            }break;
        }
        selection = temp.get(1);

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(GroupOrStStr, projection, selection, selectionArgs, null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), tempUri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        String GroupOrStStr = new String();
        switch(uriMatcher.match(uri)){
            case URI_GROUPS:{
                GroupOrStStr = GROUPS_PATH;
            }break;
            case URI_GROUPS_ID:{
                GroupOrStStr = GROUPS_PATH;
            }break;
            case URI_STUD_IDGR:{
                GroupOrStStr = STUDENTS_PATH;
            }break;
            case URI_STUD_IDGR_IDS:{
                GroupOrStStr = STUDENTS_PATH;
            }break;
        }
        Uri tempUri = Uri.parse("");
        switch(GroupOrStStr)
        {
            case(GROUPS_PATH):{
                tempUri = GROUP_CONTENT_URI;
            }break;
            case(STUDENTS_PATH):{
                tempUri = STUD_CONTENT_URI;
            }break;
        }
        Long rowId = db.insertOrThrow(GroupOrStStr, null, contentValues);
        Log.d("Laba6 insert: ", String.valueOf(rowId));
        Uri resultUri = ContentUris.withAppendedId(tempUri, rowId);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        ArrayList<String> temp = GetGroupAndSelectionString(uri, selection);
        String GroupOrStStr = temp.get(0);
        selection = temp.get(1);
        db = dbHelper.getWritableDatabase();
        int CountDeleted = db.delete(GroupOrStStr, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return CountDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        ArrayList<String> temp = GetGroupAndSelectionString(uri, selection);
        String GroupOrStStr = temp.get(0);
        selection = temp.get(1);

        db = dbHelper.getWritableDatabase();
        int Count = db.update(GroupOrStStr, contentValues, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return Count;
    }

    public ArrayList<String> GetGroupAndSelectionString(Uri uri, String selection){
        String GroupString = new String();

        switch(uriMatcher.match(uri)){
            case URI_GROUPS:
                Log.d(LOG_TAG, "query: URI_GROUP");
                GroupString = GROUPS_PATH;
                break;
            case URI_GROUPS_ID : Log.d(LOG_TAG, "query: URI_GROUP_ID");
                String idgr1 = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection)) {
                    selection = "IDGROUP = " + idgr1;
                }
                else {
                    selection = selection + " and IDGROUP = " + idgr1;
                }
                GroupString = GROUPS_PATH;
                break;
            case URI_STUD_IDGR: Log.d(LOG_TAG, "query: URI_STUD");
                String idgr2 = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection)){
                    selection = "IDGROUP = " + idgr2;
                }
                else{
                    selection = selection + " and IDGROUP = " + idgr2;
                }
                GroupString = STUDENTS_PATH;
                break;
            case URI_STUD_IDGR_IDS: Log.d(LOG_TAG, "query: URI_STUD_ID");
                String ids = uri.getLastPathSegment();
                List<String> pathSegments = uri.getPathSegments();
                String idgr3 = pathSegments.get(pathSegments.size() - 2);
                if(TextUtils.isEmpty(selection)){
                    selection = "IDGROUP = " + idgr3 + " and IDSTUDENT = " + ids;
                }
                else{
                    selection = selection +  " IDGROUP = " + idgr3 + " and IDSTUDENT = " + ids;
                }
                GroupString = STUDENTS_PATH;
                break;
            case URI_TEMPV:
                Log.d(LOG_TAG, "query: URI_TEMPV");
                GroupString = TEMPV_PATH;
                break;
            case URI_TEMPV_ID:
                String idasd = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection)){
                    selection = "IDGR = " + idasd;
                }
                else{
                    selection = selection + " and IDGR = " + idasd;
                }
                GroupString = TEMPV_PATH;
                break;
            default:
                throw new IllegalArgumentException("WRONG URI: " + uri);
        }

        ArrayList<String> Result = new ArrayList<String>();
        Result.add(GroupString);
        Result.add(selection);

        return Result;
    }
}

