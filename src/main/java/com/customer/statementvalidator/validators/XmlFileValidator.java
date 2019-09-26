/**
 * 
 */
package com.customer.statementvalidator.validators;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.customer.statementvalidator.errorhandler.CustomerValidatorApplicationException;
import com.customer.statementvalidator.errorhandler.ExceptionFactory;
import com.customer.statementvalidator.resources.Records;

/**
 * The class to handle the customer statement records 
 * received in the XML format separated by a comma.
 *
 */
@Component
public class XmlFileValidator {

  private static final Log LOGGER = LogFactory.getLog(XmlFileValidator.class);

  /**
   * Method to get the records from the uploaded customer statement records
   * in csv format
   * 
   * @param uploadedFile
   *            - the file containing statement data to be validated
   *                in xml format
   * @return Records
   *            - the records present in the xml file
   * @throws CustomerValidatorApplicationException
   *            - Application exception in case of IO Exception
   */

  public Records getRecordsFromXml(MultipartFile uploadedFile) throws CustomerValidatorApplicationException {

    Records records = null;
    try(InputStream is = uploadedFile.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));){
      String xmlString = br.lines().collect(Collectors.joining("\n"));

      JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);             
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      records = (Records) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));

    }catch (JAXBException | IOException exception){
      ExceptionFactory.logAndThrowApplicationException(LOGGER, "Exception while reading Xml file", 
          exception, "ERR_MSG_002", "Invalid File");
    }

    return records;
  }
}
