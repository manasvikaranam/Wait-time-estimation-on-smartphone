package com.example.database;

public class Data {

	private int weekOfYear;
	private int dayOfWeek;
	private int dayInterval;
	
	public Data(int weekOfYear,int dayOfWeek,int dayInterval) {
		this.weekOfYear = weekOfYear;
		this.dayOfWeek = dayOfWeek;
		this.dayInterval = dayInterval;	
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
}