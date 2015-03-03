package com.example.waitnwatch.layouts;

import java.util.Calendar;

import android.app.Activity;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.Data;
import com.example.database.DatabaseOps;
import com.example.uploader.Uploader;
import com.example.waitnwatch.R;


public class MainActivity extends ActionBarActivity {

	private TextView waitTimeView;
	private TextView status;
	private DatabaseOps databaseOps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		databaseOps = new DatabaseOps(this);
		setContentView(R.layout.activity_main);
		waitTimeView = (TextView) findViewById(R.id.waitTimeView);
		status = (TextView) findViewById(R.id.status);
		if(isConnected()){
			status.setText("Connected");
			status.setBackgroundColor(0xFF00CC00);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void checkWaitTime(View view){
		Data data = getCurrentData();
		int waitTime = getWaitTime(data);
		waitTimeView.setText("Wait time is "+ waitTime +" mins");
	}


	private int getWaitTime(Data data) {

		Uploader uploader = new Uploader();
		uploader.uploadData(data);
		return uploader.getWaitTime(data);
	}


	private Data getCurrentData() {
		// TODO Auto-generated method stub
		Calendar today = Calendar.getInstance();
		return new Data(today.get(Calendar.WEEK_OF_YEAR), today.get(Calendar.DAY_OF_WEEK),today.get(Calendar.HOUR_OF_DAY)*6+today.get(Calendar.MINUTE)/10);
	}
	public boolean isConnected(){
		ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo network = connectionManager.getActiveNetworkInfo();
		if (network != null && network.isConnected()) 
			return true;
		else
			return false;    
	}
	public boolean insertData(Data data){

		try {
			databaseOps.open();
			if(databaseOps.insertData(data))
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "Some database exception", Toast.LENGTH_LONG).show();
		}
		finally{
			databaseOps.close();
		}
		return false;

	}

}
