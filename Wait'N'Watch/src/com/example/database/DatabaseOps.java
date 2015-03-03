package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseOps {


	// Database fields
	private SQLiteDatabase database;
	private MySQLLiteHelper dbHelper;
	private String[] allColumns = { MySQLLiteHelper.COLUMN_WEEKOFYEAR,MySQLLiteHelper.COLUMN_DAYOFWEEK,MySQLLiteHelper.COLUMN_TIMEINTERVAL };

	public DatabaseOps(Context context) {
		dbHelper = new MySQLLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public boolean insertData(Data data) {
		// TODO Auto-generated method stub
		/*String query = "INSERT INTO " + MySQLLiteHelper.TABLE_DATA + " ("
	              + MySQLLiteHelper.COLUMN_WEEKOFYEAR  + ","
	              + MySQLLiteHelper.COLUMN_WEEKOFYEAR  + ","
	              + MySQLLiteHelper.COLUMN_TIMEINTERVAL+" ) "
	              + "Values ("+data.getWeekOfYear()+", "+data.getWeekOfYear()+","+data.getDayInterval()+")";
		database.execSQL(query);
		 */
		ContentValues values = new ContentValues();
		values.put(MySQLLiteHelper.COLUMN_WEEKOFYEAR, data.getWeekOfYear());
		values.put(MySQLLiteHelper.COLUMN_DAYOFWEEK, data.getDayOfWeek());
		values.put(MySQLLiteHelper.COLUMN_TIMEINTERVAL, data.getDayInterval());
		long status = database.insert(MySQLLiteHelper.TABLE_DATA,null , values);
		if (status != -1) {
			return true;
		}

		return false;

	}


}
