package com.example.codingchallengesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CodeQuest.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "users";
    private static final String COL_USER_ID = "user_id";
    private static final String COL_USERNAME = "username";
    private static final String COL_EMAIL = "email";
    private static final String COL_PHONE = "phone";
    private static final String COL_PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_USER_ID + " TEXT PRIMARY KEY, " +
                COL_USERNAME + " TEXT, " +
                COL_EMAIL + " TEXT UNIQUE, " +
                COL_PHONE + " TEXT UNIQUE, " +
                COL_PASSWORD + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(String username, String email, String phone, String password) {
        if (isEmailExists(email)) return false;
        if (isPhoneExists(phone)) return false;

        String userId = generateUniqueUserId(username);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_ID, userId);
        values.put(COL_USERNAME, username);
        values.put(COL_EMAIL, email);
        values.put(COL_PHONE, phone);
        values.put(COL_PASSWORD, password);

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE username = ? AND password = ?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean isPhoneExists(String phone) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE phone = ?", new String[]{phone});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean isUserIdExists(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE user_id = ?", new String[]{userId});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public String generateUniqueUserId(String baseUsername) {
        String userId = baseUsername;
        int counter = 1;

        while (isUserIdExists(userId)) {
            userId = baseUsername + counter;
            counter++;
        }

        return userId;
    }

    public int getUserIdCount(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE username = ?", new String[]{username});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public boolean updateUserProfile(String oldUserId, String newUsername, String newEmail, String newPhone) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor emailCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email = ? AND user_id != ?",
                new String[]{newEmail, oldUserId});
        if (emailCursor.getCount() > 0) {
            emailCursor.close();
            return false;
        }
        emailCursor.close();

        Cursor phoneCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE phone = ? AND user_id != ?",
                new String[]{newPhone, oldUserId});
        if (phoneCursor.getCount() > 0) {
            phoneCursor.close();
            return false;
        }
        phoneCursor.close();

        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, newUsername);
        values.put(COL_EMAIL, newEmail);
        values.put(COL_PHONE, newPhone);

        int updatedRows = db.update(TABLE_NAME, values, "user_id = ?", new String[]{oldUserId});
        return updatedRows > 0;
    }

    public Cursor getUserData(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE user_id = ?", new String[]{userId});
    }

    // ✅ Added: Get user details by user ID
    public Cursor getUserDetails(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE user_id = ?", new String[]{userId});
    }

    // ✅ Added: Check if email exists for other user
    public boolean isEmailExistsForOtherUser(String email, String currentUserId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email = ? AND user_id != ?", new String[]{email, currentUserId});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // ✅ Added: Check if phone exists for other user
    public boolean isPhoneExistsForOtherUser(String phone, String currentUserId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE phone = ? AND user_id != ?", new String[]{phone, currentUserId});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
