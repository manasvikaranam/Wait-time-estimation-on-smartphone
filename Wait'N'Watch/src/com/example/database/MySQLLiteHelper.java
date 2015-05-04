package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLLiteHelper extends SQLiteOpenHelper{
	public static final String TABLE_DATA = "data";
	public static final String COLUMN_WEEKOFYEAR = "week_of_year";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_DAYOFWEEK = "day_of_week";
	public static final String COLUMN_TIMEINTERVAL = "time_interval";
	public static final String COLUMN_WAITTIME = "wait_time";
	private static final String DATABASE_NAME = "data.db";
	private static final int DATABASE_VERSION = 3;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table " + TABLE_DATA + "("  
			+ COLUMN_ID+		   " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_WEEKOFYEAR+   " INTEGER not null ," 
			+ COLUMN_DAYOFWEEK+    " INTEGER not null ,"
			+ COLUMN_TIMEINTERVAL+ " INTEGER not null ,"
			+ COLUMN_WAITTIME+ 	   " INTEGER not null "
			+ " );";

	public MySQLLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
		onCreate(db);
	}
}
