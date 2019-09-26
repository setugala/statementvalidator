package com.customer.statementvalidator.validators;

import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.customer.statementvalidator.resources.Records;

public class CSVFileValidatorTest {

  CSVFileValidator csvFileValidator;

  Records records;

  @Before
  public void setup() {
    csvFileValidator = new CSVFileValidator();
  }

  @Test
  public void getRecordsFromCSVTest() {

    try {

      FileInputStream fis = new FileInputStream("src/test/resources/records.csv");
      MockMultipartFile multipartFile = new MockMultipartFile("file", fis);


      Records records = csvFileValidator.getRecordsFromCSV(multipartFile);


      Assert.assertEquals(10 , records.getRecord().size());
      Assert.assertEquals(194261, (int)records.getRecord().get(0).getReference());

    } catch (Exception e) {
      Assert.fail("Unwanted exception occured");
    }
  }


}
