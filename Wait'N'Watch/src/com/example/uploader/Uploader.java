package com.example.uploader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.example.database.Data;

public class Uploader{
	
	private final static String URL = "";
	private HttpClient httpclient;
	private HttpGet httpget;
	private HttpResponse response;
	
	public Uploader() {
		// TODO Auto-generated constructor stub
	}

	public void uploadData(Data data) {
		// TODO Auto-generated method stub
		
	}

	public int getWaitTime(Data data) {
		// TODO Auto-generated method stub
		return 10;
	}
}