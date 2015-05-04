package com.example.uploader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.database.Data;

public class Uploader{

	private final static String URL = "http://ec2-52-0-23-187.compute-1.amazonaws.com/add.php";
	private HttpClient httpclient;
	private HttpPost httppost;
	private HttpResponse response;
	private List<NameValuePair> parameters;

	public Uploader() {
		// TODO Auto-generated constructor stub
		parameters = new ArrayList<NameValuePair>();
		httpclient = new DefaultHttpClient();
		httppost = new HttpPost(URL);
	}

	public void uploadData(Data data) {
		// TODO Auto-generated method stub
		try {
			parameters.add(new BasicNameValuePair("weekOfYear",data.getWeekOfYear()+""));
			parameters.add(new BasicNameValuePair("dayOfWeek",data.getDayOfWeek()+""));
			parameters.add(new BasicNameValuePair("dayInterval",data.getDayInterval()+""));
			parameters.add(new BasicNameValuePair("waitTime",data.getWaitTime()+""));			
			httppost.setEntity(new UrlEncodedFormEntity(parameters));
			response = httpclient.execute(httppost);
			InputStream is  = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println(sb);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void uploadALLCollectedData(List<Data> dataList) {
		// TODO Auto-generated method stub
		for(Data d : dataList)
			uploadData(d);
		
	}
}