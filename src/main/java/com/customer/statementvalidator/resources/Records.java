/**
 * 
 */
package com.customer.statementvalidator.resources;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="records")
@XmlAccessorType(XmlAccessType.FIELD)
public class Records {
  
  @XmlElement(name="record")
  private List<Record> record;

}
