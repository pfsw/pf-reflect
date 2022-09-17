package org.pfsw.reflect.testhelper;

import java.util.List;
import java.util.Vector;

@SuppressWarnings({ "rawtypes" })
public class Subclass2 extends Subclass1
{
  public static final String const_2_1 = "constant";

  private Subclass1 var_2_2 = new Subclass1();
  protected Object var_2_3 = null;
  List var_2_4 = new Vector();

  public Subclass2()
  {
    super();
  }

  Subclass2(int start)
  {
    this();
  }

  protected Subclass2(String name)
  {
    this();
    if ("bad".equals(name))
    {
      throw new IllegalArgumentException("name must not be bad");
    }
  }

  @SuppressWarnings("unused")
  private Subclass2(String name, List data)
  {
    this();
  }

  public String const_2_1()
  {
    return const_2_1;
  }
  public Subclass1 var_2_2()
  {
    return var_2_2;
  }
  public Object var_2_3()
  {
    return var_2_3;
  }
  public List var_2_4()
  {
    return var_2_4;
  }

}