package com.ssomai.myjavalibraries.java;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


public class Tool_Java {
    public static boolean isBooleanFalse(Boolean val) {
        if (val == null)
            return true;
        return val.booleanValue() == false;
    }

    public static boolean isBooleanTrue(Boolean val) {
        if (val == null)
            return false;
        return val.booleanValue() != false;
    }

    public static boolean isLongBlank(Long val) {
        return val == null || val <= 0;
    }

    public static boolean isIntegerBlank(Integer val) {
        return val == null || val <= 0;
    }

    public static boolean isStringBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isListBlank(List<?> pList) {
        if (pList == null) {
            return true;
        }
        return pList.size() <= 0;
    }


    private static Timestamp sTimestamp_OldBaseLine_Lower = null;

    public static Timestamp getOldBaseTimestamp_Lower() {
        if (sTimestamp_OldBaseLine_Lower == null) {
            sTimestamp_OldBaseLine_Lower = new Timestamp(-31532339000L);
        }
        return sTimestamp_OldBaseLine_Lower;
    }

    private static Timestamp sTimestamp_OldBaseLine = null;

    public static Timestamp getOldBaseTimestamp() {
        if (sTimestamp_OldBaseLine == null) {
            sTimestamp_OldBaseLine = new Timestamp(3661000L);
        }
        return sTimestamp_OldBaseLine;
    }

    private static Timestamp sTimestamp_OldBaseLine_Upper = null;

    public static Timestamp getOldBaseTimestamp_Upper() {
        if (sTimestamp_OldBaseLine_Upper == null) {
            sTimestamp_OldBaseLine_Upper = new Timestamp(31539661000L);
        }
        return sTimestamp_OldBaseLine_Upper;
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(getCurrentTime());
    }

    //    public static boolean isDateTimeBlank(Timestamp val) {
//        if(val == null) {
//            return true;
//        }
//        return val.getTime() <= sTimestamp_BlankBaseLine.getTime();
//
//    }
    public static boolean isNumber(String str) {
        if (str == null) {
            return false;
        }
        String st = str.trim();
        if (st.length() <= 0) {
            return false;
        }
        // return st.matches("[0-9]+");
        return st.matches("^(-?)\\d+$");
    }

    public static int parseInteger(String str) {
//        if(isNumber(str)) {
//            return -1;
//        }
        return Integer.parseInt(str.replaceAll("\\D+", ""));
    }

    public static long parseLong(String str) {
//        if(isNumber(str)) {
//            return -1;
//        }
        return Long.parseLong(str.replaceAll("\\D+", ""));
    }

    public static boolean isInInterval(long pDateTime_Target, long pIntervalInMilliSecond) {
        return isInInterval(pDateTime_Target, pIntervalInMilliSecond, Tool_Java.getCurrentTime());
    }

    public static boolean isInInterval(long pDateTime_Target, long pIntervalInMilliSecond, long pDateTime_Compare) {
        return pDateTime_Compare - pIntervalInMilliSecond <= pDateTime_Target && pDateTime_Target <= pDateTime_Compare + pIntervalInMilliSecond;
    }

    private static Long sTimezoneOffset = null;

    public static long getTimezonOffset() {
        if (sTimezoneOffset == null) {
            TimeZone tz = TimeZone.getDefault();
            sTimezoneOffset = Long.valueOf(tz.getRawOffset());
        }
        return sTimezoneOffset;
    }
//    // UTC 문제는 레알 빡침
//    public static long getCurrentTimeOnUTC() {
//        return new Date().getTime() - getTimezonOffset();
//    }

    public static long getCurrentTime() {
        return new Date().getTime();
    }


    public static final SimpleDateFormat FORMATTER_UTC_Month;
    public static final SimpleDateFormat FORMATTER_UTC_Day;

    public static final String dateformat_Day_UTC(long pTime) {
        return dateformat_Day_UTC(new Date(pTime));
    }

    public static final String dateformat_Day_UTC(Date pDate) {
        synchronized (FORMATTER_UTC_Day) {
            return FORMATTER_UTC_Day.format(pDate);
        }
    }

    public static final SimpleDateFormat FORMATTER_UTC_Full;

    public static String dateformat_Full_UTC(long pTime) {
        return dateformat_Full_UTC(new Date(pTime));
    }

    public static String dateformat_Full_UTC(Date pDate) {
        synchronized (FORMATTER_UTC_Full) {
            return FORMATTER_UTC_Full.format(pDate);
        }
    }

    public static final SimpleDateFormat FORMATTER_UTC_Millisec;

    public static String dateformat_Millisec_UTC(long pTime) {
        return dateformat_Full_UTC(new Date(pTime));
    }

    public static String dateformat_Millisec_UTC(Date pDate) {
        synchronized (FORMATTER_UTC_Millisec) {
            return FORMATTER_UTC_Millisec.format(pDate);
        }
    }

    private static final SimpleDateFormat FORMATTER_KR_Day;

    public static String dateformat_Day_KR(long pTime) {
        return dateformat_Day_KR(new Date(pTime));
    }

    public static String dateformat_Day_KR(Date pDate) {
        synchronized (FORMATTER_KR_Day) {
            return FORMATTER_KR_Day.format(pDate);
        }
    }

    private static final SimpleDateFormat FORMATTER_KR_Full;

    public static String dateformat_Full_KR(long pTime) {
        return dateformat_Full_KR(new Date(pTime));
    }

    public static String dateformat_Full_KR(Date pDate) {
        synchronized (FORMATTER_KR_Full) {
            return FORMATTER_KR_Full.format(pDate);
        }
    }

    public static boolean compareString(String pStr1, String pStr2) {
        if(isStringBlank(pStr1) == true) {
            return isStringBlank(pStr2) != false;
        }
        if(isStringBlank(pStr1) == false) {
            if(isStringBlank(pStr2) == true) {
                return false;
            }
        }
        return pStr1.compareTo(pStr2) == 0;
    }


    static {
        FORMATTER_UTC_Month = new SimpleDateFormat("yyyy-MM");
        FORMATTER_UTC_Month.setTimeZone(getTimeZone_UTC());
        FORMATTER_UTC_Day = new SimpleDateFormat("yyyy-MM-dd");
        FORMATTER_UTC_Day.setTimeZone(getTimeZone_UTC());
        FORMATTER_UTC_Full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FORMATTER_UTC_Full.setTimeZone(getTimeZone_UTC());
        FORMATTER_UTC_Millisec = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        FORMATTER_UTC_Millisec.setTimeZone(getTimeZone_UTC());

        FORMATTER_KR_Day = new SimpleDateFormat("yyyy-MM-dd");
        FORMATTER_KR_Day.setTimeZone(getTimeZone_Korea());
        FORMATTER_KR_Full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FORMATTER_KR_Full.setTimeZone(getTimeZone_Korea());

    }

//    public static String format_hourmin(int pMin) {
//        String time = "";
//        if(pMin >= 60) {
//            time += (pMin / 60) +"시간";
//        }
//        if(pMin % 60 > 0) {
//            if(time.length() > 0) {
//                time += " " + (pMin % 60) + "분";
//            }
//            else {
//                time += (pMin % 60) + "분";
//            }
//        }
//        return time;
//    }

    public static TimeZone getTimeZone_UTC() {
        return TimeZone.getTimeZone("UTC");
    }

    public static TimeZone getTimeZone_Korea() {
        return TimeZone.getTimeZone("Asia/Seoul");
    }
//    public static Calendar getCalendar_Korea() {
//        return Calendar.getInstance(getTimeZone_Korea());
//    }

    public static String getCurrentThreadStackTrace() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Thread.currentThread().getStackTrace().length; i++) {
            sb.append(Thread.currentThread().getStackTrace()[i].toString()).append("\n");
        }
        return sb.toString();
    }

    public static String makeStackTrace(Throwable t) {
        if (t == null)
            return "";
//        try {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        sw.flush();
        return sw.toString();
//        } catch (Throwable e) {
//            return "";
//        }
    }

}
