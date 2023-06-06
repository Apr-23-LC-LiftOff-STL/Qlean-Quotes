package org.launchcode.qleanquotes.models;

import java.util.List;

/**
 * PaymentResult is an object representing the response back to the front end.
 */
public class PaymentResult {

  //TODO persist with order

  private String title;

  private List<com.squareup.square.models.Error> errors;

  public PaymentResult(String t, List<com.squareup.square.models.Error> errorMessages) {
    this.title = t;
    this.errors = errorMessages;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }

  public void setErrors(List<com.squareup.square.models.Error> errors) {
    this.errors = errors;
  }

  public List<com.squareup.square.models.Error> getErrors() {
    return this.errors;
  }
}
