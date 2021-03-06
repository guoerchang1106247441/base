package com.persistence.base.tool.kit.date;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum DateType
{
  YEAR("yyyy", ""),  DDMMYYYY("DD/MM/YYYY", "(((0[1-9]|[12][0-9]|3[01])/((0[13578]|1[02]))|((0[1-9]|[12][0-9]|30)/(0[469]|11))|(0[1-9]|[1][0-9]|2[0-8])/(02))/([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}))|(29/02/(([0-9]{2})(0[48]|[24 68][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00)))"),  YYYY_MM("yyyy-MM", "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)"),  YYYY_MM_DD("yyyy-MM-dd", "^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])\001(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\001(?:29|30)|(?:0?[13578]|1[02])\001(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\002(?:29))$"),  YYYYMMDD("yyyyMMdd", "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)"),  TIMESTAMP("yyyy-MM-dd HH:mm:ss", "^((((19|20)(([02468][048])|([13579][26]))-0?2-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0?[1-9])|(1[0-2]))-((0?[1-9])|(1\\d)|(2[0-8])))|((((0?[13578])|(1[02]))-31)|(((0?[1,3-9])|(1[0-2]))-(29|30)))))\\s(20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$");
  
  private String pattern;
  private String regex;
  
  private DateType(String pattern, String regex)
  {
    this.pattern = pattern;
    this.regex = regex;
  }
  
  public static boolean match(String regex, String sDate)
  {
    Pattern pattern = Pattern.compile(regex);
    return pattern.matcher(sDate).matches();
  }
  
  public String getPattern()
  {
    return this.pattern;
  }
  
  public void setPattern(String pattern)
  {
    this.pattern = pattern;
  }
  
  public String getRegex()
  {
    return this.regex;
  }
  
  public void setRegex(String regex)
  {
    this.regex = regex;
  }
}



