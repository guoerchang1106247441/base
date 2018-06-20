package com.persistence.base.tool.kit;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class StringUtil
{
  public static String getParm(String sBuf, String sName)
  {
    return getParm(sBuf, sName, "&");
  }
  
  public static String getParm(String sBuf, String sName, String sTag)
  {
    String tagName = sName + "=";
    if (sBuf.indexOf(tagName) < 0) {
      return null;
    }
    String[] strlist = sBuf.split(sTag);
    for (int i = 0; i < strlist.length; i++) {
      if (strlist[i].indexOf(tagName) >= 0) {
        return strlist[i].substring(tagName.length());
      }
    }
    return null;
  }
  
  public static String splitEmail(String email)
  {
    if (isNull(email)) {
      return null;
    }
    String[] ss = email.split("@");
    if (ss.length != 2) {
      return null;
    }
    String userName = ss[0];
    String host = ss[1];
    if (userName.length() == 1) {
      return userName + "****@" + host;
    }
    if (userName.length() == 2)
    {
      String fc = userName.substring(0, 1);
      String lc = userName.substring(1);
      return fc + "****" + lc + "@" + host;
    }
    if (userName.length() == 3)
    {
      String fc = userName.substring(0, 2);
      String lc = userName.substring(2);
      return fc + "****" + lc + "@" + host;
    }
    String fc = userName.substring(0, 2);
    String lc = userName.substring(userName.length() - 2);
    return fc + "****" + lc + "@" + host;
  }
  
  public static String splitPhoneNo(String phone)
  {
    if (isNull(phone)) {
      return null;
    }
    if (phone.length() < 6) {
      return phone;
    }
    String ft = phone.substring(0, 3);
    String lt = phone.substring(phone.length() - 4);
    
    return ft + "****" + lt;
  }
  
  public static boolean isNull(String str)
  {
    return StringUtils.isBlank(str);
  }
  
  public static boolean isNotNull(Object str)
  {
    if (str == null) {
      return false;
    }
    return StringUtils.isNotBlank(str.toString());
  }
  
  public static String getMd5(String s)
  {
    if (null == s) {
      return null;
    }
    return getMd5(s.getBytes());
  }
  
  public static String rightTrim(String s)
  {
    if ((s == null) || (s.trim().length() == 0)) {
      return null;
    }
    if (s.trim().length() == s.length()) {
      return s;
    }
    if (!s.startsWith(" ")) {
      return s.trim();
    }
    return s.substring(0, s.indexOf(s.trim().substring(0, 1)) + s.trim().length());
  }
  
  public static String leftTrim(String s)
  {
    if ((s == null) || (s.trim().length() == 0)) {
      return null;
    }
    if (s.trim().length() == s.length()) {
      return s;
    }
    if (!s.startsWith(" ")) {
      return s;
    }
    return s.substring(s.indexOf(s.trim().substring(0, 1)));
  }
  
  public static String getMd5(byte[] bytes)
  {
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    try
    {
      MessageDigest mdTemp = MessageDigest.getInstance("MD5");
      mdTemp.update(bytes);
      byte[] md = mdTemp.digest();
      int j = md.length;
      char[] str = new char[j * 2];
      int k = 0;
      for (int i = 0; i < j; i++)
      {
        byte byte0 = md[i];
        str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
        str[(k++)] = hexDigits[(byte0 & 0xF)];
      }
      return new String(str);
    }
    catch (Exception e) {}
    return null;
  }
  
  public static String GUID()
  {
    return Identities.uuid2();
  }
  
  public static String getRand(int n)
  {
    Random rnd = new Random();
    String pass = "0";
    int x = rnd.nextInt(10);
    while (x == 0) {
      x = rnd.nextInt(10);
    }
    pass = String.valueOf(x);
    for (int i = 1; i < n; i++) {
      pass = pass + String.valueOf(rnd.nextInt(10));
    }
    return pass;
  }
  
  public static String fillLeft(String source, char fillChar, long len)
  {
    StringBuffer ret = new StringBuffer();
    if (null == source) {
      ret.append("");
    }
    if (source.length() > len)
    {
      ret.append(source);
      return ret.toString();
    }
    long slen = source.length();
    while (ret.toString().length() + slen < len) {
      ret.append(fillChar);
    }
    ret.append(source);
    return ret.toString();
  }
  
  public static String fillStr(int len, String repStr)
  {
    if (len <= 0) {
      return repStr;
    }
    StringBuilder ret = new StringBuilder();
    for (int i = 0; i < len; i++) {
      ret.append(repStr);
    }
    return ret.toString();
  }
  
  public static String fillRight(String source, char fillChar, int len)
  {
    StringBuffer ret = new StringBuffer();
    if (null == source) {
      ret.append("");
    }
    if (source.length() > len)
    {
      ret.append(source);
    }
    else
    {
      ret.append(source);
      while (ret.toString().length() < len) {
        ret.append(fillChar);
      }
    }
    return ret.toString();
  }
  
  public static String filterStr(String str)
  {
    if (StringUtils.isBlank(str)) {
      return str;
    }
    return str.replaceAll("'", "''");
  }
  
  public static String decode(String str)
  {
    return decode(str, "UTF-8");
  }
  
  public static String decode(String str, String encode)
  {
    try
    {
      str = URLDecoder.decode(str, encode);
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return str;
  }
  
  public static String escape(String src)
  {
    StringBuffer tmp = new StringBuffer();
    tmp.ensureCapacity(src.length() * 6);
    for (int i = 0; i < src.length(); i++)
    {
      char j = src.charAt(i);
      if ((Character.isDigit(j)) || (Character.isLowerCase(j)) || (Character.isUpperCase(j)))
      {
        tmp.append(j);
      }
      else if (j < 'Ā')
      {
        tmp.append("%");
        if (j < '\020') {
          tmp.append("0");
        }
        tmp.append(Integer.toString(j, 16));
      }
      else
      {
        tmp.append("%u");
        tmp.append(Integer.toString(j, 16));
      }
    }
    return tmp.toString();
  }
  
  public static String unescape(String src)
  {
    StringBuffer tmp = new StringBuffer();
    tmp.ensureCapacity(src.length());
    int lastPos = 0;int pos = 0;
    while (lastPos < src.length())
    {
      pos = src.indexOf("%", lastPos);
      if (pos == lastPos)
      {
        if (src.charAt(pos + 1) == 'u')
        {
          char ch = (char)Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
          tmp.append(ch);
          lastPos = pos + 6;
        }
        else
        {
          char ch = (char)Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
          tmp.append(ch);
          lastPos = pos + 3;
        }
      }
      else if (pos == -1)
      {
        tmp.append(src.substring(lastPos));
        lastPos = src.length();
      }
      else
      {
        tmp.append(src.substring(lastPos, pos));
        lastPos = pos;
      }
    }
    return tmp.toString();
  }
  
  public static String isoToGB(String src)
  {
    String strRet = null;
    try
    {
      strRet = new String(src.getBytes("ISO_8859_1"), "GB2312");
    }
    catch (Exception e) {}
    return strRet;
  }
  
  public static String isoToUTF(String src)
  {
    String strRet = null;
    try
    {
      strRet = new String(src.getBytes("ISO_8859_1"), "UTF-8");
    }
    catch (Exception e) {}
    return strRet;
  }
  
  public static String utfToGbk(String src)
  {
    String strRet = null;
    try
    {
      strRet = new String(src.getBytes("ISO_8859_1"), "UTF-8");
    }
    catch (Exception e) {}
    return strRet;
  }
  
  public static String getNextStr(String s)
  {
    if (StringUtils.isBlank(s)) {
      return s;
    }
    s = StringUtils.trim(s);
    String[] strs = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
    if (!ArrayUtils.contains(strs, s)) {
      return null;
    }
    return strs[(ArrayUtils.indexOf(strs, s) + 1)];
  }
  
  public static boolean isLen(String str, int num)
  {
    if (StringUtils.isBlank(str)) {
      return num < 0;
    }
    if (StringUtils.length(str) > num) {
      return true;
    }
    return str.getBytes().length > num;
  }
  
  public static boolean isOutLen(String str, int num)
  {
    return isLen(str, num);
  }
  
  public static String replaceStrToHTML(String str)
  {
    if (StringUtils.isBlank(str)) {
      return str;
    }
    return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\"", "&quot;");
  }
  
  public static String replaceHtmlToStr(String html)
  {
    if (StringUtils.isBlank(html)) {
      return null;
    }
    return html.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ").replaceAll("&quot;", "\"");
  }
  
  public static String replaceTagName(String str, String tagName, String value)
  {
    if (StringUtils.isBlank(value)) {
      return str;
    }
    Pattern p = Pattern.compile("\\{(.*?)\\}");
    Matcher m = p.matcher(str);
    while (m.find())
    {
      String name = m.group(1);
      if (null != name) {
        if (name.trim().equals(tagName)) {
          str = str.replaceFirst("\\{" + name + "\\}", value);
        }
      }
    }
    return str;
  }
  
  public static String replace(String str, Object... values)
  {
    if ((null == values) && (values.length <= 0)) {
      return str;
    }
    Pattern p = Pattern.compile("\\{(.*?)\\}");
    Matcher m = p.matcher(str);
    int i = 0;
    while ((m.find()) && 
      (i < values.length))
    {
      String name = m.group(1);
      String value = null == values[i] ? "null" : values[i].toString();
      i++;
      
      str = str.replaceFirst("\\{" + name + "\\}", value);
    }
    return str;
  }
  
  public static String toBinaryString(int i)
  {
    return Integer.toBinaryString(i);
  }
  
  public static String toBinaryString(long i)
  {
    return Long.toBinaryString(i);
  }
  
  public String toOctalString(int i)
  {
    return Integer.toOctalString(i);
  }
  
  public String toOctalString(long i)
  {
    return Long.toOctalString(i);
  }
  
  public String toHexString(int i)
  {
    return Integer.toHexString(i);
  }
  
  public static String toHexString(byte[] bytes)
  {
    if ((null == bytes) || (bytes.length == 0)) {
      return null;
    }
    int len = bytes.length;
    StringBuilder sb = new StringBuilder(len * 2);
    for (int n = 0; n < len; n++)
    {
      String hex = Integer.toHexString(bytes[n] & 0xFF);
      sb.append(((hex.length() == 1 ? "0" : "") + hex).toUpperCase());
    }
    return sb.toString();
  }
  
  public static byte[] hex2byte(String hex)
  {
    if (StringUtils.isBlank(hex)) {
      return null;
    }
    int len = hex.length() / 2;
    byte[] bytes = new byte[len];
    for (int i = 0; i < len; i++)
    {
      int high = Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
      int low = Integer.parseInt(hex.substring(i * 2 + 1, i * 2 + 2), 16);
      bytes[i] = ((byte)(high * 16 + low));
    }
    return bytes;
  }
  
  public byte uniteBytes(byte src0, byte src1)
  {
    byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();
    _b0 = (byte)(_b0 << 4);
    byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
    byte ret = (byte)(_b0 ^ _b1);
    return ret;
  }
  
  public String toHexString(long i)
  {
    return Long.toHexString(i);
  }
  
  public int parseBinaryInt(String s)
  {
    return Integer.parseInt(s, 2);
  }
  
  public long parseBinaryLong(String s)
  {
    return Long.parseLong(s, 2);
  }
  
  public int parseOctalInt(String s)
  {
    return Integer.parseInt(s, 8);
  }
  
  public int parseHexInt(String s)
  {
    return Integer.parseInt(s, 16);
  }
  
  public static List<String> findText(String start, String end, String content, int i)
  {
    StringBuilder builder = new StringBuilder();
    builder.append(start);
    builder.append("([\\s\\S]*?)");
    builder.append(end);
    
    return find(builder.toString(), i, content);
  }
  
  public static List<String> find(String regex, int i, String content)
  {
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(content);
    List<String> list = new ArrayList();
    while (m.find()) {
      try
      {
        list.add(m.group(i));
      }
      catch (IndexOutOfBoundsException e)
      {
        list.add(m.group());
      }
    }
    return list;
  }
  
  public static String replaceBlank(String str)
  {
    if (StringUtils.isBlank(str)) {
      return StringUtils.trim(str);
    }
    Matcher m = Pattern.compile("\\s*|\t|\r|\n").matcher(str);
    return m.replaceAll("");
  }
  
  public static String substring(String str, int start)
  {
    return StringUtils.substring(str, start);
  }
  
  public static String substring(String str, int start, int end)
  {
    return StringUtils.substring(str, start, end);
  }
  
  public static String format(double amt)
  {
    Locale locale = new Locale("zh", "CN");
    return format(amt, locale);
  }
  
  public static String format(double amt, Locale locale)
  {
    NumberFormat currFmt = NumberFormat.getCurrencyInstance(locale);
    return currFmt.format(amt);
  }
  
  private static boolean isChinese(char c)
  {
    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
    if ((ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) || (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) || (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) || (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) || (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) || (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) || (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)) {
      return true;
    }
    return false;
  }
  
  public static boolean isChinese(String strName)
  {
    char[] ch = strName.toCharArray();
    for (int i = 0; i < ch.length; i++)
    {
      char c = ch[i];
      if (isChinese(c)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isChineseByREG(String str)
  {
    if (str == null) {
      return false;
    }
    Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
    return pattern.matcher(str.trim()).find();
  }
  
  public static boolean isChineseByName(String str)
  {
    if (str == null) {
      return false;
    }
    String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
    Pattern pattern = Pattern.compile(reg);
    return pattern.matcher(str.trim()).find();
  }
  
  public static String SHA(String algorithm, String decript)
  {
    try
    {
      MessageDigest digest = MessageDigest.getInstance(algorithm);
      digest.update(decript.getBytes());
      byte[] messageDigest = digest.digest();
      StringBuffer hexString = new StringBuffer();
      for (int i = 0; i < messageDigest.length; i++)
      {
        String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
        if (shaHex.length() < 2) {
          hexString.append(0);
        }
        hexString.append(shaHex);
      }
      return hexString.toString();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return "";
  }
}



