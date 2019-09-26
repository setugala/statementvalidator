/**
 * 
 */
package com.customer.statementvalidator.validators;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.customer.statementvalidator.errorhandler.CustomerValidatorApplicationException;
import com.customer.statementvalidator.errorhandler.ExceptionFactory;
import com.customer.statementvalidator.resources.Record;
import com.customer.statementvalidator.resources.Records;

/**
 * The class to handle the customer statement records 
 * received in the CSV format separated by a comma.
 *
 */
@Component
public class CSVFileValidator {

  private static final Log LOGGER = LogFactory.getLog(CSVFileValidator.class);

  private static final Function<String, Record> mapToRecord = (line) -> {
    String[] p = line.split(",");
    return new Record(Integer.parseInt(p[0]), p[1], p[2], new BigDecimal(p[3]), new BigDecimal(p[4]), new BigDecimal(p[5]));
  };

  /**
   * Method to get the records from the uploaded customer statement records
   * in csv format
   * 
   * @param uploadedFile
   *            - the file containing statement data to be validated
   *                in xml format
   * @return Records
   *            - the records present in the csv file
   * @throws CustomerValidatorApplicationException
   *            - Application exception in case of IO Exception
   */
  public Records getRecordsFromCSV(MultipartFile uploadedFile) throws CustomerValidatorApplicationException {
    List<Record> recordList = null;
    try(InputStream is = uploadedFile.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));){

      recordList = br.lines()
          .skip(1)
          .map(mapToRecord)
          .collect(Collectors.toList());
    } catch (IOException exception) {
      ExceptionFactory.logAndThrowApplicationException(LOGGER, "IO Exception while reading CSV file", 
          exception, "ERR_MSG_001", "Invalid File");
    } 
    Records records = new Records();
    records.setRecord(recordList);

    return records;
  }
}
