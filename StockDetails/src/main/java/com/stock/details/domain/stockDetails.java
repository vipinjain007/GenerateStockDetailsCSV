package com.stock.details.domain;

public class stockDetails {
	private String stockSymbol;
	private String currentPrice;
	private String yearTargetPrice;
	private String yearHigh;
	private String yearLow;

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getYearTargetPrice() {
		return yearTargetPrice;
	}

	public void setYearTargetPrice(String yearTargetPrice) {
		this.yearTargetPrice = yearTargetPrice;
	}

	public String getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(String yearHigh) {
		this.yearHigh = yearHigh;
	}

	public String getYearLow() {
		return yearLow;
	}

	public void setYearLow(String yearLow) {
		this.yearLow = yearLow;
	}

}
