package com.persistence.base.tool.kit.date;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class DateUtil {
	private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal();
	private static final Object object = new Object();
	private static final long ONE_DAY = 1000 * 60 * 60 * 24;
	private static final int ONE_WEEK = 7;

	public static int getYear(Calendar c) {
		return getCalendar(c).get(1);
	}

	public static int getMonth(Calendar c) {
		return getCalendar(c).get(2);
	}

	public static int getDay(Calendar c) {
		return getCalendar(c).get(7);
	}

	public static int getHour(Calendar c) {
		return getCalendar(c).get(10);
	}

	public static int getMinute(Calendar c) {
		return getCalendar(c).get(12);
	}

	public static int getSecond(Calendar c) {
		return getCalendar(c).get(13);
	}

	public static Calendar beforeDay(Calendar c, int n) {
		long offset = n * 24 * 60 * 60 * 1000;
		return before(c, offset);
	}

	public static Calendar yesterday(Calendar c) {
		return beforeDay(c, 1);
	}

	public static Calendar before(Calendar c, long offset) {
		Calendar calendar = getCalendar(c);
		calendar.setTimeInMillis(calendar.getTimeInMillis() - offset);
		return calendar;
	}

	public static Calendar tomorrow(Calendar c) {
		return afterDay(c, 1);
	}

	public static Calendar afterDay(Calendar c, int n) {
		long offset = n * 24 * 60 * 60 * 1000;
		return after(c, offset);
	}

	public static Calendar after(Calendar c, long offset) {
		Calendar calendar = getCalendar(c);
		calendar.setTimeInMillis(calendar.getTimeInMillis() + offset);
		return calendar;
	}

	public static String format(Calendar c, String pattern) {
		Calendar calendar = getCalendar(c);
		return getDateFormat(pattern).format(calendar.getTime());
	}

	public static Calendar getCalendar(java.util.Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}

	public static int getDate(Calendar c) {
		return getCalendar(c).get(5);
	}

	public static Timestamp getTimestamp(java.util.Date d) {
		return new Timestamp(d.getTime());
	}

	public static Timestamp getTimestamp(Calendar c) {
		return new Timestamp(c.getTimeInMillis());
	}

	public static Calendar getCalendar(Timestamp ts) {
		Calendar c = Calendar.getInstance();
		c.setTime(ts);
		return c;
	}

	public static Calendar getCalender(String s) {
		Timestamp ts = Timestamp.valueOf(s);
		return getCalendar(ts);
	}

	public static String getDateTime() {
		return format(Calendar.getInstance(), DatePattern.YYYY_MM_DD_HH_MM_SS.getValue());
	}

	private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
		if (StringUtils.isBlank(pattern)) {
			pattern = "yyyy年MM月dd日 HH:mm:ss";
		}
		SimpleDateFormat dateFormat = (SimpleDateFormat) threadLocal.get();
		if (dateFormat == null) {
			synchronized (object) {
				dateFormat = new SimpleDateFormat(pattern);
				dateFormat.setLenient(false);
				threadLocal.set(dateFormat);
			}
		}
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}

	private static int getInteger(java.util.Date date, int dateType) {
		if (null == date) {
			date = new java.util.Date();
		}
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		return calendar.get(dateType);
	}

	private static String addInteger(String date, int dateType, int amount) {
		DatePattern DatePattern = getDatePattern(date);
		if (DatePattern == null) {
			return null;
		}
		java.util.Date myDate = StringToDate(date, DatePattern);
		myDate = addInteger(myDate, dateType, amount);
		return DateToString(myDate, DatePattern);
	}

	private static java.util.Date addInteger(java.util.Date date, int dateType, int amount) {
		if (null == date) {
			date = new java.util.Date();
		}
		Calendar calendar = getCalendar(date);
		calendar.add(dateType, amount);
		return calendar.getTime();
	}

	private static java.util.Date getAccurateDate(List<Long> timestamps) {
		java.util.Date date = null;
		long timestamp = 0L;
		Map<Long, long[]> map = new HashMap<Long, long[]>();
		List<Long> absoluteValues = new ArrayList<Long>();
		if ((timestamps != null) && (timestamps.size() > 0)) {
			if (timestamps.size() > 1) {
				for (int i = 0; i < timestamps.size(); i++) {
					for (int j = i + 1; j < timestamps.size(); j++) {
						long absoluteValue = Math.abs(((Long) timestamps.get(i)).longValue()
								- ((Long) timestamps.get(j)).longValue());

						absoluteValues.add(Long.valueOf(absoluteValue));
						long[] timestampTmp = { ((Long) timestamps.get(i)).longValue(),
								((Long) timestamps.get(j)).longValue() };

						map.put(Long.valueOf(absoluteValue), timestampTmp);
					}
				}
				long minAbsoluteValue = -1L;
				if (!absoluteValues.isEmpty()) {
					minAbsoluteValue = ((Long) absoluteValues.get(0)).longValue();
					for (int i = 1; i < absoluteValues.size(); i++) {
						if (minAbsoluteValue > ((Long) absoluteValues.get(i)).longValue()) {
							minAbsoluteValue = ((Long) absoluteValues.get(i)).longValue();
						}
					}
				}
				if (minAbsoluteValue != -1L) {
					long[] timestampsLastTmp = (long[]) map.get(Long.valueOf(minAbsoluteValue));

					long dateOne = timestampsLastTmp[0];
					long dateTwo = timestampsLastTmp[1];
					if (absoluteValues.size() > 1) {
						timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne : dateTwo;
					}
				}
			} else {
				timestamp = ((Long) timestamps.get(0)).longValue();
			}
		}
		if (timestamp != 0L) {
			date = new java.util.Date(timestamp);
		}
		return date;
	}

	public static boolean isDate(String date) {
		boolean isDate = false;
		if (StringUtils.isBlank(date)) {
			return isDate;
		}
		return getDate(date) != null;
	}

	public static DatePattern getDatePattern(String date) {
		DatePattern DatePattern = null;
		Map<Long, DatePattern> map = new HashMap<Long, DatePattern>();
		List<Long> timestamps = new ArrayList<Long>();
		for (DatePattern pattern : DatePattern.values()) {
			java.util.Date dateTmp = parse(date, pattern.getValue());
			if (dateTmp != null) {
				timestamps.add(Long.valueOf(dateTmp.getTime()));
				map.put(Long.valueOf(dateTmp.getTime()), pattern);
			}
		}
		DatePattern = (DatePattern) map.get(Long.valueOf(getAccurateDate(timestamps).getTime()));
		return DatePattern;
	}

	public static java.util.Date parse(String date, String parttern) {
		java.util.Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(parttern).parse(date);
			} catch (Exception e) {
			}
		}
		return myDate;
	}

	public static java.util.Date StringToDate(String date, DatePattern pattern) {
		java.util.Date myDate = null;
		if (null != pattern) {
			myDate = parse(date, pattern.getValue());
			return myDate;
		}
		List<Long> timestamps = new ArrayList<Long>();
		for (DatePattern style : DatePattern.values()) {
			java.util.Date dateTmp = parse(date, style.getValue());
			if (dateTmp != null) {
				timestamps.add(Long.valueOf(dateTmp.getTime()));
			}
		}
		myDate = getAccurateDate(timestamps);
		return myDate;
	}

	public static String DateToString(java.util.Date date, String parttern) {
		String dateString = null;
		if (date != null) {
			try {
				dateString = getDateFormat(parttern).format(date);
			} catch (Exception e) {
			}
		}
		return dateString;
	}

	public static String DateToString(java.util.Date date, DatePattern DatePattern) {
		String dateString = null;
		if (DatePattern != null) {
			dateString = DateToString(date, DatePattern.getValue());
		}
		return dateString;
	}

	public static String StringToString(String date, String parttern) {
		return format(date, null, parttern);
	}

	public static String format(String date, DatePattern DatePattern) {
		return format(date, null, DatePattern);
	}

	public static String format(String date, String olddParttern, String newParttern) {
		String dateString = null;
		if (olddParttern == null) {
			DatePattern style = getDatePattern(date);
			if (style != null) {
				java.util.Date myDate = parse(date, style.getValue());
				dateString = DateToString(myDate, newParttern);
			}
		} else {
			java.util.Date myDate = parse(date, olddParttern);
			dateString = DateToString(myDate, newParttern);
		}
		return dateString;
	}

	public static String format(String date, DatePattern olddDteStyle, DatePattern newDatePattern) {
		String dateString = null;
		if (olddDteStyle == null) {
			DatePattern style = getDatePattern(date);
			dateString = format(date, style.getValue(), newDatePattern.getValue());
		} else {
			dateString = format(date, olddDteStyle.getValue(), newDatePattern.getValue());
		}
		return dateString;
	}

	public static String addYear(String date, int yearAmount) {
		return addInteger(date, 1, yearAmount);
	}

	public static java.util.Date addYear(java.util.Date date, int yearAmount) {
		return addInteger(date, 1, yearAmount);
	}

	public static String addMonth(String date, int yearAmount) {
		return addInteger(date, 2, yearAmount);
	}

	public static java.util.Date addMonth(java.util.Date date, int yearAmount) {
		return addInteger(date, 2, yearAmount);
	}

	public static String addDay(String date, int dayAmount) {
		return addInteger(date, 5, dayAmount);
	}

	public static java.util.Date addDay(java.util.Date date, int dayAmount) {
		return addInteger(date, 5, dayAmount);
	}

	public static String addHour(String date, int hourAmount) {
		return addInteger(date, 11, hourAmount);
	}

	public static java.util.Date addHour(java.util.Date date, int hourAmount) {
		return addInteger(date, 11, hourAmount);
	}

	public static String addMinute(String date, int minuteAmount) {
		return addInteger(date, 12, minuteAmount);
	}

	public static java.util.Date addMinute(java.util.Date date, int minuteAmount) {
		return addInteger(date, 12, minuteAmount);
	}

	public static String addSecond(String date, int secondAmount) {
		return addInteger(date, 13, secondAmount);
	}

	public static java.util.Date addSecond(java.util.Date date, int secondAmount) {
		return addInteger(date, 13, secondAmount);
	}

	public static int getYear(String date) {
		return getYear(getDate(date));
	}

	public static int getYear(java.util.Date date) {
		return getInteger(date, 1);
	}

	public static int getMonth(String date) {
		return getMonth(getDate(date));
	}

	public static int getMonth(java.util.Date date) {
		return getInteger(date, 2);
	}

	public static int getDay(String date) {
		return getDay(getDate(date));
	}

	public static int getDay(java.util.Date date) {
		return getInteger(date, 5);
	}

	public static int getHour(String date) {
		return getHour(getDate(date));
	}

	public static int getHour(java.util.Date date) {
		return getInteger(date, 11);
	}

	public static int getMinute(String date) {
		return getMinute(getDate(date));
	}

	public static int getMinute(java.util.Date date) {
		return getInteger(date, 12);
	}

	public static int getSecond(String date) {
		return getSecond(getDate(date));
	}

	public static int getSecond(java.util.Date date) {
		return getInteger(date, 13);
	}

	public static String format(String date) {
		return format(date, DatePattern.YYYY_MM_DD);
	}

	public static String format(java.util.Date date) {
		Locale locale = new Locale("zh", "CN");
		return format(date, locale);
	}

	public static String format(java.util.Date date, Locale locale) {
		DateFormat df = DateFormat.getDateInstance(2, locale);
		return df.format(date);
	}

	public static java.util.Date getDate(String date) {
		return StringToDate(date, null);
	}

	public static String getDate(java.util.Date date) {
		return DateToString(date, DatePattern.YYYY_MM_DD);
	}

	public static String getTime(String date) {
		return format(date, DatePattern.HH_MM_SS);
	}

	public static String getTime(java.util.Date date) {
		return DateToString(date, DatePattern.HH_MM_SS);
	}

	public static Week getWeek(String date) {
		Week week = null;
		DatePattern DatePattern = getDatePattern(date);
		if (DatePattern != null) {
			java.util.Date myDate = StringToDate(date, DatePattern);
			week = getWeek(myDate);
		}
		return week;
	}

	public static Week getWeek(java.util.Date date) {
		Week week = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNumber = calendar.get(7) - 1;
		switch (weekNumber) {
		case 0:
			week = Week.SUNDAY;
			break;
		case 1:
			week = Week.MONDAY;
			break;
		case 2:
			week = Week.TUESDAY;
			break;
		case 3:
			week = Week.WEDNESDAY;
			break;
		case 4:
			week = Week.THURSDAY;
			break;
		case 5:
			week = Week.FRIDAY;
			break;
		case 6:
			week = Week.SATURDAY;
		}
		return week;
	}

	public static int getIntervalDays(String date, String otherDate) {
		return getIntervalDays(getDate(date), getDate(otherDate));
	}

	public static int getIntervalDays(java.util.Date date, java.util.Date otherDate) {
		date = getDate(getDate(date));
		long time = Math.abs(date.getTime() - otherDate.getTime());
		return (int) (time / 86400000L);
	}

	public long getNow() {
		return System.currentTimeMillis();
	}

	private static Calendar getCalendar(Calendar c) {
		if (c == null) {
			c = getCalendar();
		}
		return c;
	}

	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	public java.util.Date getNowDate() {
		return new java.util.Date(getNow());
	}

	public Timestamp getTimestamp() {
		return new Timestamp(getNow());
	}

	public java.sql.Date getToday() {
		return new java.sql.Date(getNow());
	}

	public Time getNowTime() {
		return new Time(getNow());
	}

	public static java.util.Date randomDate(String beginDate, String endDate) {
		return randomDate(beginDate, endDate, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	public static java.util.Date randomDate(String beginDate, String endDate, String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			java.util.Date start = format.parse(beginDate);
			java.util.Date end = format.parse(endDate);
			return randomDate(start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static java.util.Date randomDate(java.util.Date start, java.util.Date end) {
		try {
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());
			return new java.util.Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static long random(long begin, long end) {
		long random = (long) (begin + (Math.random() * (end - begin)));
		if ((random == begin) || (random == end)) {
			return random(begin, end);
		}
		return random;
	}

	public static SimpleDateFormat formatDate() {
		String formatDateStr = DateFormats.DATE_TIME_FORMAT;
		SimpleDateFormat formatDate = new SimpleDateFormat(formatDateStr);
		return formatDate;
	}

	/**
	 * 取得一段时间内每一天的时间
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @author Willie
	 * 创建时间： 2017-11-7
	 */
	public static List<String> getPeriodList(String beginDate, String endDate) {
		List<String> dateStrList = new ArrayList<String>(0);
		Date begin = StringToDate(beginDate, DatePattern.YYYY_MM_DD);
		Date end = StringToDate(endDate, DatePattern.YYYY_MM_DD);

		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(begin);

		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);

		if (end.compareTo(begin) == 0) {
			dateStrList.add(beginDate);
		} else if (end.compareTo(begin) > 0) {
			while (calEnd.compareTo(calBegin) >= 0) {
				dateStrList.add(DateToString(calBegin.getTime(), DatePattern.YYYY_MM_DD));
				calBegin.add(Calendar.DAY_OF_MONTH, 1);
			}
		}

		return dateStrList;
	}

	/**
	 * 获取某年最后一天日期
	 *
	 * @param year 年份
	 * @return Date 获取某年最后一天日期
	 */
	public static Date getLastDayOfYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 取得一段时间之间的周数(每7天为1周,包含起止日期)
	 * @param startDateStr 起始时间
	 * @param endDateStr 截至时间
	 * @return 一段时间之间的周数 例如：{第1周=[2017-07-01, 2017-07-07]}
	 * @author Willie
	 * 创建时间： 2017-11-9
	 */
	public static Map<String, Object> getWeeks(String startDateStr, String endDateStr) {
		Map<String, Object> weekMap = new LinkedHashMap<String, Object>(0);// 包含起止时间的周数

		Date d1 = StringToDate(startDateStr, DatePattern.YYYY_MM_DD);// 起始时间
		Date d2 = StringToDate(endDateStr, DatePattern.YYYY_MM_DD);// 截至时间

		int days = getIntervalDays(d1, d2);// 起止时间间隔的天数
		int weekSize = (int) (days / ONE_WEEK);// 起止时间间隔的周数

		if (0 == weekSize) {// 起止时间不足一周
			List<String> strDateList = new ArrayList<String>(0);
			strDateList.add(startDateStr);
			strDateList.add(endDateStr);
			weekMap.put("第1周", strDateList);
		} else {
			List<String> strDateList = new ArrayList<String>(0);
			strDateList.add(startDateStr);
			
			int extraDays = days % ONE_WEEK + 1;// 间隔N周后多出的天数
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d1);
			calendar.add(Calendar.DATE, 6);// 第一周的截止日期
			strDateList.add(DateToString(calendar.getTime(), DatePattern.YYYY_MM_DD));
			weekMap.put("第1周", strDateList);
			for (int i = 1; i < weekSize; i++) {
				List<String> dateList = new ArrayList<String>(0);
				calendar.add(Calendar.DATE, 1);
				dateList.add(DateToString(calendar.getTime(), DatePattern.YYYY_MM_DD));
				calendar.add(Calendar.DATE, 6);
				dateList.add(DateToString(calendar.getTime(), DatePattern.YYYY_MM_DD));
				weekMap.put("第" + (i + 1) + "周", dateList);
			}

			if (1 == extraDays) {
				List<String> dateList = new ArrayList<String>(0);
				calendar.add(Calendar.DATE, extraDays);
				dateList.add(DateToString(calendar.getTime(), DatePattern.YYYY_MM_DD));
				dateList.add(DateToString(calendar.getTime(), DatePattern.YYYY_MM_DD));
				weekMap.put("第" + (weekSize + 1) + "周", dateList);
			} else {
				List<String> dateList = new ArrayList<String>(0);
				calendar.add(Calendar.DATE, 1);
				dateList.add(DateToString(calendar.getTime(), DatePattern.YYYY_MM_DD));
				calendar.add(Calendar.DATE, extraDays - 1);
				dateList.add(DateToString(calendar.getTime(), DatePattern.YYYY_MM_DD));
				weekMap.put("第" + (weekSize + 1) + "周", dateList);
			}

		}
		return weekMap;
	}

	/**
	 * 取得起止时间的季度
	 *
	 * @param date1 起始时间
	 * @param date2 截止时间
	 * @return
	 * @throws ParseException
	 */
	public static Map<String, Object> getQuarters(String date1, String date2) {
		Map<String, Object> quarterMap = new LinkedHashMap<String, Object>(0);

		Date d1 = StringToDate(date1, DatePattern.YYYY_MM_DD);
		Date d2 = StringToDate(date2, DatePattern.YYYY_MM_DD);

		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(d1);
		calendar2.setTime(d2);

		// 指定日期的年份
		Integer year1 = calendar1.get(Calendar.YEAR);
		Integer year2 = calendar2.get(Calendar.YEAR);

		int yearSize = year2.intValue() - year1.intValue();// 起止日期间隔年
		if (yearSize > 1) {
			Date endDayOfYear = getLastDayOfYear(year1);// 起始时间所在年份的最后一天
			quarterMap = getQuarterMaps4SameYear(date1, DateToString(endDayOfYear, DatePattern.YYYY_MM_DD));
			year1++;
			for (int i = 0; i < yearSize - 1; i++) {
				Date sDate = getFirstDateOfYear(year1);
				Date eDate = getLastDayOfYear(year1);

				Map<String, Object> nextQuarterMap = getQuarterMaps4SameYear(
						DateToString(sDate, DatePattern.YYYY_MM_DD), DateToString(eDate, DatePattern.YYYY_MM_DD));
				quarterMap.putAll(nextQuarterMap);
				year1++;
			}
			Date startDayOfYear = getFirstDateOfYear(year2);// 截至时间所在年份的第一天
			Map<String, Object> nextYearQuarterMap = getQuarterMaps4SameYear(
					DateToString(startDayOfYear, DatePattern.YYYY_MM_DD), date2);
			quarterMap.putAll(nextYearQuarterMap);

		} else if (yearSize == 1) {
			Date endDayOfYear = getLastDayOfYear(year1);// 起始时间所在年份的最后一天
			quarterMap = getQuarterMaps4SameYear(date1, DateToString(endDayOfYear, DatePattern.YYYY_MM_DD));

			Date startDayOfYear = getFirstDateOfYear(year2);// 截至时间所在年份的第一天
			Map<String, Object> nextYearQuarterMap = getQuarterMaps4SameYear(
					DateToString(startDayOfYear, DatePattern.YYYY_MM_DD), date2);
			quarterMap.putAll(nextYearQuarterMap);
		} else {// 同一年
			quarterMap = getQuarterMaps4SameYear(date1, date2);
		}

		return quarterMap;
	}

	/**
	 * 取得同一年的季度
	 *
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static Map<String, Object> getQuarterMaps4SameYear(String date1, String date2) {
		Map<String, Object> quarterMap = new LinkedHashMap<String, Object>(0);

		Date d1 = StringToDate(date1, DatePattern.YYYY_MM_DD);
		Date d2 = StringToDate(date2, DatePattern.YYYY_MM_DD);

		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(d1);
		calendar2.setTime(d2);

		// 指定日期的年份
		Integer year1 = calendar1.get(Calendar.YEAR);
		Integer year2 = calendar1.get(Calendar.YEAR);

		// 指定日期的季度
		int quarter1 = getQuarterOfYear(d1);
		int quarter2 = getQuarterOfYear(d2);
		int quarterSize = quarter2 - quarter1;// 起止日期间隔季度

		if (quarterSize > 1) {// 跨季度
			List<String> dateList = new ArrayList<String>(0);
			dateList.add(date1);
			dateList.add(DateToString(getLastDayOfQuarter(year1, quarter1), DatePattern.YYYY_MM_DD));
			String key1 = year1 + "." + quarter1 + "季";
			quarterMap.put(key1, dateList);
			quarter1++;

			for (int i = 0; i < quarterSize - 1; i++) {
				List<String> qDateList = new ArrayList<String>(0);
				qDateList.add(DateToString(getFirstDayOfQuarter(year1, quarter1), DatePattern.YYYY_MM_DD));
				qDateList.add(DateToString(getLastDayOfQuarter(year1, quarter1), DatePattern.YYYY_MM_DD));
				String key = year1 + "." + quarter1 + "季";
				quarterMap.put(key, qDateList);
				quarter1++;
			}

			List<String> dateList1 = new ArrayList<String>(0);
			dateList1.add(DateToString(getFirstDayOfQuarter(year2, quarter2), DatePattern.YYYY_MM_DD));
			dateList1.add(date2);
			String key2 = year1 + "." + quarter2 + "季";
			quarterMap.put(key2, dateList1);
		} else if (quarterSize == 1) {
			List<String> dateList = new ArrayList<String>(0);
			dateList.add(date1);
			dateList.add(DateToString(getLastDayOfQuarter(year1, quarter1), DatePattern.YYYY_MM_DD));// 起始时间所在季度的最后一天
			String key1 = year1 + "." + quarter1 + "季";
			quarterMap.put(key1, dateList);

			List<String> dateList1 = new ArrayList<String>(0);
			dateList1.add(DateToString(getFirstDayOfQuarter(year2, quarter2), DatePattern.YYYY_MM_DD));
			dateList1.add(date2);
			String key2 = year1 + "." + quarter2 + "季";
			quarterMap.put(key2, dateList1);
		} else {// 同一季度
			List<String> strDateList = new ArrayList<String>(0);
			strDateList.add(date1);
			strDateList.add(date2);
			String key = year1 + "." + quarter1 + "季";
			quarterMap.put(key, strDateList);
		}

		return quarterMap;
	}

	/**
	 * 返回指定日期的季度
	 *
	 * @param date 日期
	 * @return 返回指定日期的季度
	 */
	public static int getQuarterOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) / 3 + 1;
	}

	/**
	 * 获取某年第一天日期
	 *
	 * @param year 年份
	 * @return Date 获取某年第一天日期
	 */
	public static Date getFirstDateOfYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		return calendar.getTime();
	}

	/**
	 * 返回指定年季的季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month;
		switch (quarter) {
		case 1:
			month = 2;
			break;
		case 2:
			month = 5;
			break;
		case 3:
			month = 8;
			break;
		case 4:
			month = 11;
			break;
		default:
			month = calendar.get(Calendar.MONTH);
			break;
		}
		return getLastDayOfMonth(year, month);
	}

	/**
	 * 返回指定年季的季的第一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month;
		switch (quarter) {
		case 1:
			month = 0;
			break;
		case 2:
			month = 3;
			break;
		case 3:
			month = 6;
			break;
		case 4:
			month = 9;
			break;
		default:
			month = calendar.get(Calendar.MONTH);
			break;
		}
		return getFirstDayOfMonth(year, month);
	}

	/**
	 * 返回指定年月的月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(Integer year, Integer month) {
		Calendar calendar = Calendar.getInstance();
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		calendar.set(year, month, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 返回指定年月的月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(Integer year, Integer month) {
		Calendar calendar = Calendar.getInstance();
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		calendar.set(year, month, 1);
		return calendar.getTime();
	}
}
