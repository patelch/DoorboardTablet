package com.doorboard3.doorboardtablet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Populator {

    private DoorboardDbHelper dbHelper;
    private Context ctx;

    public Populator(DoorboardDbHelper dbHelper, Context ctx) {
        this.dbHelper = dbHelper;
        this.ctx = ctx;
    }

    public void populate() {

        // Gets the data repository in write mode
        SQLiteDatabase wdb = dbHelper.getWritableDatabase();

        // Check to see if entries have not already been added
        if (!dbHelper.roomContainsMessages("Iribe 003")) {
            // Create dummy user 1
            ContentValues values = new ContentValues();
            values.put(DoorboardContract.MessageEntry.COLUMN_NAME_ROOM, "Iribe 003");
            values.put(DoorboardContract.MessageEntry.COLUMN_NAME_NAME, "David Mount");
            values.put(DoorboardContract.MessageEntry.COLUMN_NAME_STATUS, "Will be 5 min late to office hours");
            Bitmap b1 = BitmapFactory.decodeResource(ctx.getResources(), R.mipmap.ic_mount);
            values.put(DoorboardContract.MessageEntry.COLUMN_NAME_PROFILE_PIC, DoorboardDbHelper.bitmapToBase64(b1));
            values.put(DoorboardContract.MessageEntry.COLUMN_NAME_DATE_TIME, "05-03-2017 3:15 PM");

            // Insert the new row, returning the primary key value of the new row
            wdb.insert(DoorboardContract.MessageEntry.TABLE_NAME, null, values);

//            // Create dummy user 2
//            values = new ContentValues();
//            values.put(DoorboardContract.MessageEntry.COLUMN_NAME_ROOM, "Iribe 003");
//            values.put(DoorboardContract.MessageEntry.COLUMN_NAME_NAME, "Testudo");
//            values.put(DoorboardContract.MessageEntry.COLUMN_NAME_STATUS, "Out to lunch. Be back at 1");
//            Bitmap b2 = BitmapFactory.decodeResource(ctx.getResources(), R.mipmap.ic_profile_2);
//            values.put(DoorboardContract.MessageEntry.COLUMN_NAME_PROFILE_PIC, DoorboardDbHelper.bitmapToBase64(b2));
//            values.put(DoorboardContract.MessageEntry.COLUMN_NAME_DATE_TIME, "03-28-2017 12:15 PM");
//
//            // Insert the new row, returning the primary key value of the new row
//            wdb.insert(DoorboardContract.MessageEntry.TABLE_NAME, null, values);
        }

    }

    public void populateSchedule() {

        // Gets the data repository in write mode
        SQLiteDatabase wdb = dbHelper.getWritableDatabase();

        if (dbHelper.isEventsEmpty()) {
            ContentValues values = new ContentValues();
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_DATE, "Fri, April 07, 2017");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_NAME, "Test 1");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_START_TIME, "14:30");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_END_TIME, "15:00");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_ROOM, "Iribe 003");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_DESCRIPTION, "Test 1");
            wdb.insert(DoorboardContract.ScheduleEntry.TABLE_NAME, null, values);

            values = new ContentValues();
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_DATE, "Fri, April 07, 2017");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_NAME, "Test 3");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_START_TIME, "08:00");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_END_TIME, "09:00");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_ROOM, "Iribe 003");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_DESCRIPTION, "Test 2");
            wdb.insert(DoorboardContract.ScheduleEntry.TABLE_NAME, null, values);

            values = new ContentValues();
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_DATE, "Mon, April 10, 2017");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_NAME, "Test 2");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_START_TIME, "08:00");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_END_TIME, "09:00");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_ROOM, "Iribe 003");
            values.put(DoorboardContract.ScheduleEntry.COLUMN_NAME_DESCRIPTION, "Test 3");
            wdb.insert(DoorboardContract.ScheduleEntry.TABLE_NAME, null, values);
        }
    }
}
