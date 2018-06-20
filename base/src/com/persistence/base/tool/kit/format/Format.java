package com.persistence.base.tool.kit.format;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Format
{
  public static int double2int(double d)
    throws Exception
  {
    String value = Double.toString(d);
    if (value.indexOf(".") > 0) {
      value = value.substring(0, value.indexOf("."));
    }
    return Integer.parseInt(value);
  }
  
  public static Integer long2int(Long l)
  {
    if (null == l) {
      return null;
    }
    String value = Long.toString(l.longValue());
    return Integer.valueOf(Integer.parseInt(value));
  }
  
  public static Long int2Long(Integer i)
  {
    if (null == i) {
      return null;
    }
    String value = i.toString();
    return Long.valueOf(Long.parseLong(value));
  }
  
  public static String format(String pattern, double x)
  {
    if (null != pattern) {
      pattern = pattern.replaceAll("@", "#");
    }
    DecimalFormat df = new DecimalFormat(pattern);
    
    return df.format(x);
  }
  
  public static String format(String pattern, BigDecimal x)
  {
    if (null != pattern) {
      pattern = pattern.replaceAll("@", "#");
    }
    DecimalFormat df = new DecimalFormat(pattern);
    return df.format(x);
  }
  
  public static String format(String pattern, java.util.Date date)
  {
    if (null == date) {
      return null;
    }
    SimpleDateFormat df = new SimpleDateFormat(pattern);
    return df.format(date);
  }
  
  public static String datetime()
  {
    java.util.Date date = new java.util.Date();
    return format("yyyy.MM.dd HH:mm:ss", date);
  }
  
  public static String datetime(String format)
  {
    java.util.Date date = new java.util.Date();
    return format(format, date);
  }
  
  public static java.util.Date str2date(String sDate, String format)
    throws Exception
  {
    if (null == sDate) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.parse(sDate);
  }
  
  public static java.sql.Date str2sqldate(String sDate, String format)
    throws Exception
  {
    return new java.sql.Date(str2date(sDate, format).getTime());
  }
  
  public static String format2Price(double d)
  {
    return format("##,###,###,##0.00", d);
  }
  
  public static String format2PriceInput(double d)
  {
    return format("##########0.00", d);
  }
  
  public static java.util.Date trimTimeFromDate(java.util.Date d)
  {
    try
    {
      return str2date(format("yyyy.MM.dd", d), "yyyy.MM.dd");
    }
    catch (Exception e) {}
    return null;
  }
  
  public static boolean isNumeric(String str)
  {
    try
    {
      Double.parseDouble(str);
      return true;
    }
    catch (Exception e) {}
    return false;
  }
  
  public static boolean checkNumFraction(String str, int len)
  {
    if (len < 0) {
      return false;
    }
    String mm = "^[0-9]*[1-9][0-9]*$";
    if (len > 0) {
      mm = "^[0-9]+(.[0-9]{0," + len + "})?$";
    }
    Pattern pattern = Pattern.compile(mm);
    return pattern.matcher(str).matches();
  }
  
  public static String format2Number(double d)
  {
    return format("##,###,###,##0.000", d);
  }
}



