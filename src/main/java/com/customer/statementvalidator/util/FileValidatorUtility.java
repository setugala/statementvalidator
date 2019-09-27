/**
 * 
 */
package com.customer.statementvalidator.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.customer.statementvalidator.resources.Record;
import com.customer.statementvalidator.resources.Records;

/**
 * This class is utility class to handle all validations for the 
 * uploaded file
 *
 */
@Component
public class FileValidatorUtility {

  /**
   * Method to check the Records for invalid end balance
   * The end balance should be the sum of start balance and mutation
   * The method returns the returns the records which do not match the above criteria
   * 
   * @param records
   *            - the records to be validated for invalid end balance
   * @return List of Records
   *            - the records with the invalid end balance
   */
  public List<Record> checkInvalidEndBalance(Records records) {
    List<Record> invalidRecords = new ArrayList<>();
    if(null != records) {
      invalidRecords = records.getRecord()
          .parallelStream()
          .filter(record -> (record.getStartBalance().add(record.getMutation())).compareTo(record.getEndBalance()) != 0)
          .collect(Collectors.toList());
    }
    return invalidRecords;
  }

  /**
   * Method to check the Records with duplicate reference
   * 
   * @param records
   *            - the records to be validated for duplicate reference
   * @return List of Records
   *            - the records with the duplicate reference
   */
  public List<Record> checkDuplicateReference(Records records) {

    List<Record> duplicateReference = new ArrayList<>();
    if(null != records) {
      Set<Integer> uniqueReference = new HashSet<>();
      duplicateReference = records.getRecord()
          .parallelStream()
          .filter(record -> !uniqueReference.add(record.getReference()))
          .collect(Collectors.toList());
    }
    return duplicateReference;
  }
}
