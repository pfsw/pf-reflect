package org.pfsw.reflect.testhelper;

@SuppressWarnings("unused")
public class ExtendedPerson extends PersonData
{
  private String alpha;
  private String beta;
  private String gamma;
  private String delta;
  private String omega;

  public ExtendedPerson()
  {
    super();
  }

  public String getAlpha()
  {
    return this.alpha;
  }

  public void setAlpha(String alpha)
  {
    this.alpha = alpha;
  }

  protected void setBeta(String beta)
  {
    this.beta = beta;
  }

  String getGamma()
  {
    return this.gamma;
  }

  void setGamma(String gamma)
  {
    this.gamma = gamma;
  }

  private String getDelta()
  {
    return this.delta;
  }
}
