package com.persistence.base.tool.kit.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Lunar
{
  private int year;
  private int month;
  private int day;
  private boolean leap;
  public static final String[] chineseNumber = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
  static SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  static final long[] lunarInfo = { 19416L, 19168L, 42352L, 21717L, 53856L, 55632L, 91476L, 22176L, 39632L, 21970L, 19168L, 42422L, 42192L, 53840L, 119381L, 46400L, 54944L, 44450L, 38320L, 84343L, 18800L, 42160L, 46261L, 27216L, 27968L, 109396L, 11104L, 38256L, 21234L, 18800L, 25958L, 54432L, 59984L, 28309L, 23248L, 11104L, 100067L, 37600L, 116951L, 51536L, 54432L, 120998L, 46416L, 22176L, 107956L, 9680L, 37584L, 53938L, 43344L, 46423L, 27808L, 46416L, 86869L, 19872L, 42448L, 83315L, 21200L, 43432L, 59728L, 27296L, 44710L, 43856L, 19296L, 43748L, 42352L, 21088L, 62051L, 55632L, 23383L, 22176L, 38608L, 19925L, 19152L, 42192L, 54484L, 53840L, 54616L, 46400L, 46496L, 103846L, 38320L, 18864L, 43380L, 42160L, 45690L, 27216L, 27968L, 44870L, 43872L, 38256L, 19189L, 18800L, 25776L, 29859L, 59984L, 27480L, 21952L, 43872L, 38613L, 37600L, 51552L, 55636L, 54432L, 55888L, 30034L, 22176L, 43959L, 9680L, 37584L, 51893L, 43344L, 46240L, 47780L, 44368L, 21977L, 19360L, 42416L, 86390L, 21168L, 43312L, 31060L, 27296L, 44368L, 23378L, 19296L, 42726L, 42208L, 53856L, 60005L, 54576L, 23200L, 30371L, 38608L, 19415L, 19152L, 42192L, 118966L, 53840L, 54560L, 56645L, 46496L, 22224L, 21938L, 18864L, 42359L, 42160L, 43600L, 111189L, 27936L, 44448L };
  
  private static final int yearDays(int y)
  {
    int sum = 348;
    for (int i = 32768; i > 8; i >>= 1) {
      if ((lunarInfo[(y - 1900)] & i) != 0L) {
        sum++;
      }
    }
    return sum + leapDays(y);
  }
  
  private static final int leapDays(int y)
  {
    if (leapMonth(y) != 0)
    {
      if ((lunarInfo[(y - 1900)] & 0x10000) != 0L) {
        return 30;
      }
      return 29;
    }
    return 0;
  }
  
  private static final int leapMonth(int y)
  {
    return (int)(lunarInfo[(y - 1900)] & 0xF);
  }
  
  private static final int monthDays(int y, int m)
  {
    if ((lunarInfo[(y - 1900)] & 65536 >> m) == 0L) {
      return 29;
    }
    return 30;
  }
  
  public final String animalsYear()
  {
    return animalsYear(this.year);
  }
  
  public final String animalsYear(int year)
  {
    String[] Animals = { " 鼠 ", " 牛 ", " 虎 ", " 兔 ", " 龙 ", " 蛇 ", " 马 ", " 羊 ", " 猴 ", " 鸡 ", " 狗 ", " 猪 " };
    return Animals[((year - 4) % 12)];
  }
  
  private static final String cyclicalm(int num)
  {
    String[] Gan = { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
    String[] Zhi = { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };
    return Gan[(num % 10)] + Zhi[(num % 12)];
  }
  
  public final String cyclical()
  {
    return cyclical(this.year);
  }
  
  public final String cyclical(int year)
  {
    int num = year - 1900 + 36;
    return cyclicalm(num);
  }
  
  public Lunar(Calendar cal)
  {
    int leapMonth = 0;
    Date baseDate = null;
    try
    {
      baseDate = chineseDateFormat.parse(" 1900-1-31 ");
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    int offset = (int)((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);
    int dayCyl = offset + 40;
    int monCyl = 14;
    



    int daysOfYear = 0;
				  int iYear = 0;
    for ( iYear = 1900; (iYear < 2050) && (offset > 0); iYear++)
    {
      daysOfYear = yearDays(iYear);
      offset -= daysOfYear;
      monCyl += 12;
    }
    if (offset < 0)
    {
      offset += daysOfYear;
      iYear--;
      monCyl -= 12;
    }
    this.year = iYear;
    int yearCyl = iYear - 1864;
    leapMonth = leapMonth(iYear);
    this.leap = false;
    
    int daysOfMonth = 0;
					int iMonth = 0 ;
    for ( iMonth = 1; (iMonth < 13) && (offset > 0); iMonth++)
    {
      if ((leapMonth > 0) && (iMonth == leapMonth + 1) && (!this.leap))
      {
        iMonth--;
        this.leap = true;
        daysOfMonth = leapDays(this.year);
      }
      else
      {
        daysOfMonth = monthDays(this.year, iMonth);
      }
      offset -= daysOfMonth;
      if ((this.leap) && (iMonth == leapMonth + 1)) {
        this.leap = false;
      }
      if (!this.leap) {
        monCyl++;
      }
    }
    if ((offset == 0) && (leapMonth > 0) && (getMonth() == leapMonth + 1)) {
      if (this.leap)
      {
        this.leap = false;
      }
      else
      {
        this.leap = true;
        iMonth--;
        monCyl--;
      }
    }
    if (offset < 0)
    {
      offset += daysOfMonth;
      iMonth--;
      monCyl--;
    }
    this.month = iMonth;
    this.day = (offset + 1);
  }
  
  public static String getChinaDayString(int day)
  {
    String[] chineseTen = { "初", "十", "廿", "卅" };
    int n = day % 10 == 0 ? 9 : day % 10 - 1;
    if (day > 30) {
      return "";
    }
    if (day == 10) {
      return " 初十 ";
    }
    return chineseTen[(day / 10)] + chineseNumber[n];
  }
  
  public String getChinaYearString()
  {
    String sYear = "";
    char[] cYear = Integer.valueOf(this.year).toString().toCharArray();
    for (int i = 0; i < cYear.length; i++) {
      if (cYear[i] == '0') {
        sYear = sYear + "零";
      } else {
        sYear = sYear + chineseNumber[(Integer.valueOf(java.lang.Character.valueOf(cYear[i]).toString()).intValue() - 1)];
      }
    }
    return sYear;
  }
  
  public int getYear()
  {
    return this.year;
  }
  
  public String toString()
  {
    return getChinaYearString() + " 年 " + (this.leap ? " 闰 " : "") + chineseNumber[(this.month - 1)] + " 月 " + getChinaDayString(this.day) + " 日";
  }
  
  public String getDate()
  {
    String date = this.year + "-";
    if (String.valueOf(this.month).length() == 1) {
      date = date + "0" + this.month;
    } else {
      date = date + this.month;
    }
    date = date + "-";
    if (String.valueOf(this.day).length() == 1) {
      date = date + "0" + this.day;
    } else {
      date = date + this.day;
    }
    return date;
  }
  
  public String getMonthDay()
  {
    String sMonth = String.valueOf(this.month);
    String sDay = String.valueOf(this.day);
    if (sMonth.length() == 1) {
      sMonth = "0" + sMonth;
    }
    if (sDay.length() == 1) {
      sDay = "0" + sDay;
    }
    return sMonth + "-" + sDay;
  }
  
  public static Calendar setTime(String date)
  {
    Calendar today = Calendar.getInstance();
    try
    {
      today.setTime(chineseDateFormat.parse(date));
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return today;
  }
  
  public static void main(String[] args)
    throws ParseException
  {
    Calendar today = setTime("1985-10-02");
    Lunar lunar = new Lunar(today);
  }
  
  public int getMonth()
  {
    return this.month;
  }
  
  public int getDay()
  {
    return this.day;
  }
  
  public void setYear(int year)
  {
    this.year = year;
  }
  
  public void setMonth(int month)
  {
    this.month = month;
  }
  
  public void setDay(int day)
  {
    this.day = day;
  }
}



