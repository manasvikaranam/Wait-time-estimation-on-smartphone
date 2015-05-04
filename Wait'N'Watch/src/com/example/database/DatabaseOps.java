package com.example.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

//
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.amazonaws.services.simpledb.AmazonSimpleDB;

public class DatabaseOps {


	// Database fields
	private SQLiteDatabase database;
	private MySQLLiteHelper dbHelper;
	//private String[] allColumns = { MySQLLiteHelper.COLUMN_WEEKOFYEAR,MySQLLiteHelper.COLUMN_DAYOFWEEK,MySQLLiteHelper.COLUMN_TIMEINTERVAL };
	private AmazonSimpleDB sdb;
	private Properties propertiesAWS;
	private String domain;
	private TransferManager manager;
	private CognitoCachingCredentialsProvider credentialsProvider;
	private Context context;
	public DatabaseOps(Context context) {
		dbHelper = new MySQLLiteHelper(context);
		this.context = context;
	}

	/*public TransferManager getTransfermanagerInstance(Context context){
		if(manager ==null){
			manager = new TransferManager(getCognitoInstance());
		}
		return manager;
	}*/
	/*public CognitoCachingCredentialsProvider getCognitoInstance(){
		if(credentialsProvider == null)
			credentialsProvider = new CognitoCachingCredentialsProvider(
					context, // Context
					"us-east-1:a477b821-3f3f-48a1-a1e7-46e9ee73f027", // Identity Pool ID
					Regions.US_EAST_1 // Region
					);
		return credentialsProvider;
	}*/
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	/*public void setAWSDBInstance(){
		if(sdb == null){
			BasicAWSCredentials credentials = new BasicAWSCredentials(getProperties().getProperty("accessKey"), getProperties().getProperty("secretKey"));
			sdb = new AmazonSimpleDBClient(credentials);
		}

	}*/
	/*private Properties getProperties() {
		// TODO Auto-generated method stub
		if(propertiesAWS == null){

			propertiesAWS = new Properties();
			try {
				propertiesAWS.load(DatabaseOps.class.getResourceAsStream("AwsCredentials.properties"));

			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return propertiesAWS;
	}*/

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
		values.put(MySQLLiteHelper.COLUMN_WAITTIME, data.getWaitTime());
		long status = database.insert(MySQLLiteHelper.TABLE_DATA,null , values);
		if (status != -1) {
			return true;
		}

		return false;

	}

	public List<Data> getAllCollectedData() {
		// TODO Auto-generated method stub
		List<Data> returnList = new ArrayList<Data>();
		
		Cursor c = database.rawQuery("SELECT * FROM '"+MySQLLiteHelper.TABLE_DATA+"'", null);
		if(c.moveToFirst()){
            do{
               Data d = new Data(Integer.parseInt(c.getString(0)), Integer.parseInt(c.getString(1)),Integer.parseInt(c.getString(2)),Integer.parseInt(c.getString(3)));
               returnList.add(d);
        //Do something Here with values

            }while(c.moveToNext());
        }
		deleteALLData();
		return returnList;
	}

	private void deleteALLData() {
		// TODO Auto-generated method stub
		database.execSQL("delete from "+ MySQLLiteHelper.TABLE_DATA);
		
	}

	/*public void insertDataToAWS(Data data) {
		// TODO Auto-generated method stub
		setAWSDBInstance();
		sdb.createDomain(new CreateDomainRequest(getDomain()));
		List<ReplaceableAttribute> attributes = new ArrayList<ReplaceableAttribute>(1);
		attributes.add(new ReplaceableAttribute().withName("weekOfYear").withValue(Integer.toString(data.getWeekOfYear())));
		attributes.add(new ReplaceableAttribute().withName("dayOfWeek").withValue(Integer.toString(data.getDayOfWeek())));
		attributes.add(new ReplaceableAttribute().withName("dayInterval").withValue(Integer.toString(data.getDayInterval())));
		System.out.println(attributes);
		sdb.putAttributes(new PutAttributesRequest(getDomain(),data.toString(),attributes));
	}*/

	/*private String getDomain() {
		// TODO Auto-generated method stub
		if(domain == null){
			domain = getProperties().getProperty("domain");
		}
		return domain;
	}*/

	

}
