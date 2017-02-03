package com.linkcm.po.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public abstract class JodaUtil {

    // 不可变对象，可复用；某种程度上是线程安全的
    private static final DateTimeFormatter yyyyMMddHH = DateTimeFormat.forPattern("yyyyMMddHH");
    private static final DateTimeFormatter yyyyMMddHH0000 = DateTimeFormat.forPattern("yyyyMMddHH0000");
    private static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter yyyy_MM_dd_HH_00_00 = DateTimeFormat.forPattern("yyyy-MM-dd HH:00:00");
    private static final DateTimeFormatter yyyy_MM_dd_HH_mm_ss = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private static final Map<String, DateTimeFormatter> patternFormatterMap = new ConcurrentHashMap<>();

    static {
        patternFormatterMap.put("yyyyMMddHH", yyyyMMddHH);
        patternFormatterMap.put("yyyyMMddHH0000", yyyyMMddHH0000);
        patternFormatterMap.put("yyyyMMddHHmmss", yyyyMMddHHmmss);
        patternFormatterMap.put("yyyy-MM-dd HH:00:00", yyyy_MM_dd_HH_00_00);
        patternFormatterMap.put("yyyy-MM-dd HH:mm:ss", yyyy_MM_dd_HH_mm_ss);
    }

    public static DateTimeFormatter getFormatter(String pattern) {
        Objects.requireNonNull(pattern);
        DateTimeFormatter formatter = patternFormatterMap.get(pattern);
        if (formatter == null) {
            formatter = DateTimeFormat.forPattern(pattern);
            patternFormatterMap.put(pattern, formatter);
        }
        return formatter;
    }

    public static String timestamp() {
        return yyyyMMddHHmmss.print(DateTime.now());
    }

    /**
     * 将日期格式化为指定格式
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static DateTime parse(String dateTime, String pattern) {
        Objects.requireNonNull(dateTime);
        DateTimeFormatter formatter = getFormatter(pattern);
        return formatter.parseDateTime(dateTime);
    }

    /**
     * 根据指定格式将日期字符串解析成 {@link DateTime} 对象
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static String format(DateTime dateTime, String pattern) {
        Objects.requireNonNull(dateTime);
        DateTimeFormatter formatter = getFormatter(pattern);
        return formatter.print(dateTime);
    }

    public static String format(Date date, String pattern) {
        Objects.requireNonNull(date);
        DateTimeFormatter format = getFormatter(pattern);
        return format.print(new DateTime(date));
    }

}
