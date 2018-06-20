package com.persistence.base.exception;

import com.persistence.base.tool.kit.StringUtil;


public class BaseException
  extends RuntimeException
{
  private static final long serialVersionUID = 1L;
  private Integer exceptionFLag;
  private String msg;
  
  public Integer getExceptionFLag()
  {
    return this.exceptionFLag;
  }
  
  public static void throwException(String msg)
  {
    throw new BaseException(msg);
  }
  
  public static void throwException(String msg, Object... values)
  {
    if (null == msg) {
      throwException(msg);
    }
    throwException(StringUtil.replace(msg, values));
  }
  
  public void setExceptionFLag(Integer exceptionFLag)
  {
    this.exceptionFLag = exceptionFLag;
  }
  
  public BaseException(Integer flag)
  {
    super("");
    this.exceptionFLag = flag;
  }
  
  public BaseException(Integer flag, String msg)
  {
    super(msg);
    this.exceptionFLag = flag;
    this.msg = msg;
  }
  
  public BaseException(String mess)
  {
    super(mess);
    this.msg = mess;
  }
  
  public BaseException(String mess, Exception ex)
  {
    super(mess, ex);
    this.msg = mess;
  }
  
  public String getMsg()
  {
    return this.msg;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
}



