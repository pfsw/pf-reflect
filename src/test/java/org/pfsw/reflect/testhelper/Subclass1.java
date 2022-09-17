package org.pfsw.reflect.testhelper;

import java.util.Date;

public class Subclass1 extends Superclass
{
  public transient String var_1_1 = "sub_1_v1";
  private Integer var_1_2 = new Integer(7);
  char var_1_4 = 'X';
  transient final String dummy1 = "x";

  protected static Date var_1_3 = null;

  public Subclass1()
  {
    super();
  }

  public String var_1_1()
  {
    return var_1_1;
  }
  public Integer var_1_2()
  {
    return var_1_2;
  }
  public Date var_1_3()
  {
    return var_1_3;
  }
  public char var_1_4()
  {
    return var_1_4;
  }
  
  @Override
  public String dummy7(String name)
  {
    return "AAA: " + name;
  } 
  
  void setVar_1_4(char var_1_4)
  {
    this.var_1_4 = var_1_4;
  }
}