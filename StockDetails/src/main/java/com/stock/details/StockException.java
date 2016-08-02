package com.stock.details;


@SuppressWarnings("serial")
public class StockException extends Exception {

  public StockException() {

  }

  public StockException(String message) {
    super(message);
  }

  public StockException(Exception e) {
    super(e);
  }
}
