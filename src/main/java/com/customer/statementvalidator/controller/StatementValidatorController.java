/**
 * 
 */
package com.customer.statementvalidator.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.customer.statementvalidator.errorhandler.ValidatorApplicationException;
import com.customer.statementvalidator.errorhandler.ExceptionFactory;
import com.customer.statementvalidator.resources.Records;
import com.customer.statementvalidator.resources.Response;
import com.customer.statementvalidator.util.FileValidatorUtility;
import com.customer.statementvalidator.validators.CSVFileValidator;
import com.customer.statementvalidator.validators.XmlFileValidator;

/**
 * This class will be the controller for all Rest call to validate the 
 * customer statement
 *
 */
@RestController
@RequestMapping(path = "/validate")
public class StatementValidatorController {

  private static final Log LOGGER = LogFactory.getLog(StatementValidatorController.class);

  @Autowired
  private CSVFileValidator csvValidator;

  @Autowired
  private FileValidatorUtility validator;

  @Autowired
  private XmlFileValidator xmlValidator;

  /**
   * Rest call to validate the uploaded customer statement records
   * 
   * @param uploadedFile
   *            - the file containing statement data to be validated
   *                which can be csv or xml format
   * @return the failed records in json format
   */
  @PostMapping(produces = "application/json")
  public ResponseEntity<Object> validateFile(@RequestParam("file") MultipartFile uploadedFile)
  {

    if (uploadedFile.isEmpty()) {
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionFactory.getMessage("ERR_MSG_004", "No file recieved"));
    }

    String extension = "";

    int i = uploadedFile.getOriginalFilename().lastIndexOf('.');
    if (i >= 0) {
      extension = uploadedFile.getOriginalFilename().substring(i+1);
    }

    Response response = new Response();
    Records records = null;
    try {
      if("csv".equalsIgnoreCase(extension)) {
        records = csvValidator.getRecordsFromCSV(uploadedFile);
      } else if("xml".equalsIgnoreCase(extension)) {
        records = xmlValidator.getRecordsFromXml(uploadedFile);
      }else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionFactory.getMessage("ERR_MSG_003", "Invalid File"));
      }
      
      response.setDuplicateTransactionReference(validator.checkDuplicateReference(records));
      response.setInvalidEndBalance(validator.checkInvalidEndBalance(records));
    }catch(ValidatorApplicationException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessages());
    }catch(Exception exception) {
      LOGGER.error("Exception while validating csv file", exception);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionFactory.getMessage("ERR_MSG_999", "Internal Server Error"));
    }
    return ResponseEntity.ok(response);
  }

}
