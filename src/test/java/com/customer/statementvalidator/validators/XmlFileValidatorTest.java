package com.customer.statementvalidator.validators;

import java.io.FileInputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.customer.statementvalidator.resources.Records;

public class XmlFileValidatorTest {

  XmlFileValidator xmlFileValidator;

  Records records;

  @Before
  public void setup() {
    xmlFileValidator = new XmlFileValidator();
  }

  @Test
  public void getRecordsFromCSVTest() {

    try {

      FileInputStream fis = new FileInputStream("src/test/resources/records.xml");
      MockMultipartFile multipartFile = new MockMultipartFile("file", fis);

      Records records = xmlFileValidator.getRecordsFromXml(multipartFile);

      Assert.assertEquals(10 , records.getRecord().size());
      Assert.assertEquals(130498, (int)records.getRecord().get(0).getReference());

    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Unwanted exception occured");
    }
  }

}
