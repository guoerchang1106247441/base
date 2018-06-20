package com.persistence.base.tool.kit;

import com.persistence.base.tool.kit.date.DateUtil;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class Identities
{
  private static final int[] prefix = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
  private static SecureRandom random = new SecureRandom();
  
  public static String uuid()
  {
    return UUID.randomUUID().toString();
  }
  
  public static String uuid2()
  {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
  
  public static long randomLong()
  {
    return Math.abs(random.nextLong());
  }
  
  public static String randomBase62(int length)
  {
    byte[] randomBytes = new byte[length];
    random.nextBytes(randomBytes);
    return Encodes.encodeBase62(randomBytes);
  }
  
  public static String getRandomNum(int digit)
  {
    digit = checkDigit(digit);
    if (digit == 1) {
      return String.valueOf((int)(Math.random() * 10.0D));
    }
    BigDecimal operand = new BigDecimal(10).pow(digit - 1);
    BigDecimal randomNum = new BigDecimal(Math.random());
    MathContext mc = new MathContext(digit, RoundingMode.DOWN);
    return randomNum.multiply(new BigDecimal(9)).multiply(operand).add(operand, mc).toString();
  }
  
  private static int checkDigit(int digit)
  {
    if (digit < 0) {
      digit = Math.abs(digit);
    } else if (digit == 0) {
      digit = 1;
    }
    return digit;
  }
  
  public static long randomLong(int digit)
  {
    if ((digit >= 19) || (digit <= 0)) {
      throw new IllegalArgumentException("digit should between 1 and 18(1<=digit<=18)");
    }
    String s = RandomStringUtils.randomNumeric(digit - 1);
    return Long.parseLong(getPrefix() + s);
  }
  
  public static long randomLong(int minDigit, int maxDigit)
  {
    if (minDigit > maxDigit) {
      throw new IllegalArgumentException("minDigit > maxDigit");
    }
    if ((minDigit <= 0) || (maxDigit >= 19)) {
      throw new IllegalArgumentException("minDigit <=0 || maxDigit>=19");
    }
    return randomLong(minDigit + getDigit(maxDigit - minDigit));
  }
  
  private static int getDigit(int max)
  {
    return RandomUtils.nextInt(max + 1);
  }
  
  private static String getPrefix()
  {
    return prefix[RandomUtils.nextInt(9)] + "";
  }
  
  public static void main(String[] args)
    throws ParseException
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    Date date = format.parse("2015-03-25 10:30:28.618");
    Date date2 = DateUtil.addHour(date, 1);
    date2 = DateUtil.addMinute(date2, 2);
    for (int i = 0; i < 200; i++) {
      System.out.println(format.format(new Date(randomLong(date.getTime(), date2.getTime()))) + " ");
    }
  }
  
  public static int randomInt(int min, int max)
  {
    return (int)(min + Math.random() * (max - min + 1));
  }
  
  public static long randomLong(long min, long max)
  {
    long l = (long) (min + (Math.random() * (max - min)));
    if ((l == min) || (l == max)) {
      return randomLong(min, max);
    }
    return l;
  }
}



