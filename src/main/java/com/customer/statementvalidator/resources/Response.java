package com.customer.statementvalidator.resources;

import java.util.List;

import lombok.Data;

@Data
public class Response {

  private List<Record> invalidEndBalance;
  private List<Record> duplicateTransactionReference;
}
