package com.example.database;

public class Data {

	private int weekOfYear;
	private int dayOfWeek;
	private int dayInterval;
	private int waitTime;
	
	public Data(int weekOfYear,int dayOfWeek,int dayInterval,int waitTime) {
		this.weekOfYear = weekOfYear;
		this.dayOfWeek = dayOfWeek;
		this.dayInterval = dayInterval;
		this.waitTime = waitTime;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public int getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(int weekOfYear) {
		this.weekOfYear = weekOfYear;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getDayInterval() {
		return dayInterval;
	}

	public void setDayInterval(int dayInterval) {
		this.dayInterval = dayInterval;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("WeekOFYEAR :"+this.weekOfYear+" \nDayofWEEK : "+this.dayOfWeek+"\nDayInterval :"+this.dayInterval);
	}
}
