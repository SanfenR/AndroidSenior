package com.mz.sanfen.ipc.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 2017/1/17.
 */

public class BookProvider extends ContentProvider {
    private static final String TAG = "BookProvider";

    public static final String AUTHORITY = "com.mz.sanfen.ipc.provider.BookProvider";

    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");

    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMatcher.match(uri)){
            case BOOK_URI_CODE:
                tableName = DBOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DBOpenHelper.USER_TABLE_NAME;
                break;
        }
        return tableName;
    }




    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        Log.e(TAG, "onCreate: current thread" + Thread.currentThread().getName());
        mDb = new DBOpenHelper(getContext()).getWritableDatabase();
        mDb.execSQL("insert into book value(3, 'Android');");
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.e(TAG, "query: current thread" + Thread.currentThread().getName());

        String table = getTableName(uri);

        if (table == null || "".equals(table))
            throw new IllegalArgumentException("Unsupported URi: " + uri);

        return mDb.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.e(TAG, "getType: current thread" + Thread.currentThread().getName());
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.e(TAG, "insert: current thread" + Thread.currentThread().getName());
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.e(TAG, "delete: current thread" + Thread.currentThread().getName());
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.e(TAG, "update: current thread" + Thread.currentThread().getName());
        return 0;
    }
}
