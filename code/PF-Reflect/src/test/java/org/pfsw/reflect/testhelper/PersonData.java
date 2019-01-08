// ===========================================================================
// CONTENT  : CLASS PersonData
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 14/01/2012
// HISTORY  :
//  14/01/2012  mdu  CREATED
//
// Copyright (c) 2012, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect.testhelper ;

import java.math.BigDecimal;

@ExternalTypeId("person")
public class PersonData
{
	private String firstName;
	private String lastName;
	private int zipCode;
	private boolean female;
	private byte numberOfChildren;	
	private Integer vatId;
	private long transactions;
	private BigDecimal cash;
	
  public PersonData()
  {
    super() ;
  } // PersonData()

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public int getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(int zipCode)
	{
		this.zipCode = zipCode;
	}

	public boolean isFemale()
	{
		return female;
	}

	public void setFemale(boolean female)
	{
		this.female = female;
	}

	public byte getNumberOfChildren()
	{
		return numberOfChildren;
	}

	public void setNumberOfChildren(byte numberOfChildren)
	{
		this.numberOfChildren = numberOfChildren;
	}

	public void changeNameAndGender(String givenName, String surName, boolean isFemale) 
	{
		this.setFirstName(givenName);
		this.setLastName(surName);
		this.setFemale(isFemale);
	}

  public Integer getVatId()
  {
    return this.vatId;
  }

  public void setVatId(Integer vatId)
  {
    this.vatId = vatId;
  }

  public long getTransactions()
  {
    return this.transactions;
  }

  public void setTransactions(long transactions)
  {
    this.transactions = transactions;
  }

  public BigDecimal getCash()
  {
    return this.cash;
  }

  public void setCash(BigDecimal cash)
  {
    this.cash = cash;
  } 
} // class PersonData
