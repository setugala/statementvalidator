package com.customer.statementvalidator.resources;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {

  @XmlAttribute
  private Integer reference;
  private String accountNumber;
  private String description;
  private BigDecimal startBalance;
  private BigDecimal mutation;
  private BigDecimal endBalance;
}
