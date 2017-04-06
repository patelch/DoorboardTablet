package com.doorboard3.doorboardtablet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Olina on 4/5/17.
 */

public class DoorboardDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Doorboard.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DoorboardContract.MessageEntry.TABLE_NAME + " (" +
                    DoorboardContract.MessageEntry._ID + " INTEGER PRIMARY KEY," +
                    DoorboardContract.MessageEntry.COLUMN_NAME_ROOM + " TEXT," +
                    DoorboardContract.MessageEntry.COLUMN_NAME_NAME + " TEXT," +
                    DoorboardContract.MessageEntry.COLUMN_NAME_PROFILE_PIC + " TEXT," +
                    DoorboardContract.MessageEntry.COLUMN_NAME_DATE_TIME + " TEXT," +
                    DoorboardContract.MessageEntry.COLUMN_NAME_STATUS + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DoorboardContract.MessageEntry.TABLE_NAME;

    public DoorboardDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean roomContainsMessages(String room) {
        String query = "SELECT * FROM " + DoorboardContract.MessageEntry.TABLE_NAME + " WHERE " +
                DoorboardContract.MessageEntry.COLUMN_NAME_ROOM + " = '" + room + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    // Get messages in reverse order, so that more recent messages go to the top
    public ArrayList<Message> getMessagesForRoom(String room) {
        String query = "SELECT * FROM " + DoorboardContract.MessageEntry.TABLE_NAME + " WHERE " +
                DoorboardContract.MessageEntry.COLUMN_NAME_ROOM + " = '" + room + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        ArrayList messages = new ArrayList<Message>();
        while(cursor.moveToNext()) {
            String pic = cursor.getString(cursor.getColumnIndexOrThrow(
                    DoorboardContract.MessageEntry.COLUMN_NAME_PROFILE_PIC));
            Bitmap profilePic = base64ToBitmap(pic);
            String name = cursor.getString(cursor.getColumnIndexOrThrow(
                    DoorboardContract.MessageEntry.COLUMN_NAME_NAME));
            String status = cursor.getString(cursor.getColumnIndexOrThrow(
                    DoorboardContract.MessageEntry.COLUMN_NAME_STATUS));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(
                    DoorboardContract.MessageEntry.COLUMN_NAME_DATE_TIME));
            messages.add(new Message(profilePic, name, status, date));
        }
        cursor.close();

        // Reverse message order
        ArrayList reverseOrderMessages = new ArrayList<Message>();
        for (int i = messages.size() - 1; i >= 0; i--) {
            reverseOrderMessages.add(messages.get(i));
        }

        return reverseOrderMessages;
    }

    public Message getMessageForNameAndRoom(String name, String room) {
        String query = "SELECT * FROM " + DoorboardContract.MessageEntry.TABLE_NAME + " WHERE " +
                DoorboardContract.MessageEntry.COLUMN_NAME_ROOM + " = '" + room + "' AND " +
                DoorboardContract.MessageEntry.COLUMN_NAME_NAME + " = '" + name + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return null;
        }
        cursor.moveToNext();
        String pic = cursor.getString(cursor.getColumnIndexOrThrow(
                DoorboardContract.MessageEntry.COLUMN_NAME_PROFILE_PIC));
        Bitmap profilePic = base64ToBitmap(pic);
        String status = cursor.getString(cursor.getColumnIndexOrThrow(
                DoorboardContract.MessageEntry.COLUMN_NAME_STATUS));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(
                DoorboardContract.MessageEntry.COLUMN_NAME_DATE_TIME));
        cursor.close();
        return new Message(profilePic, name, status, date);
    }

    public void deleteMessage(String room) {
        String query = "DELETE FROM " + DoorboardContract.MessageEntry.TABLE_NAME + " WHERE " +
                DoorboardContract.MessageEntry.COLUMN_NAME_NAME + " = 'Daniel Chen' AND " +
                DoorboardContract.MessageEntry.COLUMN_NAME_ROOM + " = '" + room + "'";
        this.getWritableDatabase().execSQL(query);
    }

    public void insertMessage(Context ctx, String room, String message) {
        ContentValues values = new ContentValues();
        values.put(DoorboardContract.MessageEntry.COLUMN_NAME_ROOM, room);
        values.put(DoorboardContract.MessageEntry.COLUMN_NAME_NAME, "Daniel Chen");
        values.put(DoorboardContract.MessageEntry.COLUMN_NAME_STATUS, message);
        Bitmap b1 = BitmapFactory.decodeResource(ctx.getResources(), R.mipmap.ic_profile_1);
        values.put(DoorboardContract.MessageEntry.COLUMN_NAME_PROFILE_PIC, DoorboardDbHelper.bitmapToBase64(b1));
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm a", Locale.US);
        values.put(DoorboardContract.MessageEntry.COLUMN_NAME_DATE_TIME, sdf.format(Calendar.getInstance().getTime()));

        // Insert the new row, returning the primary key value of the new row
        this.getWritableDatabase().insert(DoorboardContract.MessageEntry.TABLE_NAME, null, values);
    }

    public void saveEvent(String eventName, String date, String startTime, String endTime, String room, String description) {
        ContentValues values = new ContentValues();
        values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_NAME, eventName);
        values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_DATE, date);
        values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_START_TIME, startTime);
        values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_END_TIME, endTime);
        values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_ROOM, room);
        values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_DESCRIPTION, description);

        this.getWritableDatabase().insert(DoorboardContract.ScheduleEntry.TABLE_NAME, null, values);
    }

    private boolean repeated(String entryDate, String entryDayOfWeek, String repeat,
                             String endRepeatDate, int month, int day, String dayOfWeek) {
        // if the end repeat date is smaller than the date, return false
        if (endRepeatDate.compareTo(month + "/" + day) < 0) {
            return false;
        }
        int entryDay = Integer.parseInt(entryDate.substring(entryDate.indexOf("/") + 1));
        switch (repeat) {
            case "never":
                return false;
            case "daily":
                return true;
            case "weekly":
                return entryDayOfWeek.equals(dayOfWeek);
            case "monthly":
                return day == entryDay;
            default:
                return false;
        }
    }

    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }
}
