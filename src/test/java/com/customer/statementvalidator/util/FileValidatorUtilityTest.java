package com.customer.statementvalidator.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.customer.statementvalidator.resources.Record;
import com.customer.statementvalidator.resources.Records;
import com.customer.statementvalidator.util.FileValidatorUtility;

public class FileValidatorUtilityTest {

  FileValidatorUtility fileValidatorUtility;

  Records records;

  @Before
  public void setup() {
    fileValidatorUtility = new FileValidatorUtility();

    records = new Records();
    List<Record> recordList = new ArrayList<Record>();

    Record record = new Record(194261,"NL91RABO0315273637","Clothes from Jan Bakker",BigDecimal.valueOf(21.6),BigDecimal.valueOf(-41.83),BigDecimal.valueOf(-20.23));
    recordList.add(record);
    record = new Record(112806,"NL93ABNA0585619023","Tickets from Richard Bakker",BigDecimal.valueOf(102.12),BigDecimal.valueOf(+45.87),BigDecimal.valueOf(147.99));
    recordList.add(record);
    record = new Record(194261,"NL74ABNA0248990274","Flowers for Willem Dekker",BigDecimal.valueOf(26.32),BigDecimal.valueOf(+48.98),BigDecimal.valueOf(75.31));
    recordList.add(record);
    records.setRecord(recordList);
  }

  @Test
  public void checkInvalidRecordsTest() {

    List<Record> invalidRecords = fileValidatorUtility.checkInvalidEndBalance(records);

    Assert.assertEquals(1 , invalidRecords.size());
    Assert.assertEquals(194261, (int)invalidRecords.get(0).getReference());
  }

  @Test
  public void checkInvalidRecordsForNullTest() {

    Records records = null;
    List<Record> invalidRecords = fileValidatorUtility.checkInvalidEndBalance(records);

    Assert.assertEquals(0 , invalidRecords.size());
  }


  @Test
  public void checkDuplicateRecordsTest() {

    List<Record> invalidRecords = fileValidatorUtility.checkDuplicateReference(records);

    Assert.assertEquals(1 , invalidRecords.size());
    Assert.assertEquals(194261, (int)invalidRecords.get(0).getReference());
  }

  @Test
  public void checkDuplicateRecordsForNullTest() {

    Records records = null;
    List<Record> invalidRecords = fileValidatorUtility.checkDuplicateReference(records);

    Assert.assertEquals(0 , invalidRecords.size());
  }
}
