package com.highpeak.parksmart.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by saralmohan on 3/1/18.
 */
public class DateUtil {

    public static final String DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";

    public static final String DD_MM_YYYY = "dd-MM-yyyy";

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String DATE_FORMAT_RECURRENCE_PATTERN = "yyyyMMdd'T'HHmmss'Z'";

    public static final Long MLLISECONDS_IN_DAY = 86400000l;

    /**
     * Private constructor
     */
    private DateUtil() {
        super();
    }

    /**
     * Get {@link DateTime} object for given calendar
     *
     * @param calendar calender
     * @return DateTime
     */
    public static DateTime getDateTime(final Calendar calendar) {
        return new DateTime(calendar);
    }

    /**
     * Compare Calendar with current date irrespective of time
     *
     * @param dateToCompare date to compare
     * @return returns true if the current date is after passed date
     **/
    public static Boolean currentDateComparator(final Long dateToCompare) {
        final DateTime currentDate = DateTime.now().withTimeAtStartOfDay().withZone(DateTimeZone.UTC);
        final DateTime dateTime = new DateTime(dateToCompare).withTimeAtStartOfDay().withZone(DateTimeZone.UTC);

        return dateTime.isAfter(currentDate);
    }

    /**
     * Return current UTC time in milliseconds
     *
     * @return current utc time
     */

    public static Long currentTimeMillis() {
        return new DateTime().withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * function to convert time in milli sec to UTC format
     *
     * @param dateInMillis date in millis
     * @return long
     */
    public static Long convertToUTC(Long dateInMillis) {
// while passing dateInMillis append L to it
        final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS zzz";
        final SimpleDateFormat sdf1 = new SimpleDateFormat(ISO_FORMAT);
        final TimeZone utc1 = TimeZone.getTimeZone("UTC");
        sdf1.setTimeZone(utc1);
        return (new Date(dateInMillis).toInstant().toEpochMilli());
    }


    /**
     * Return current UTC time in milliseconds
     *
     * @return current time with time at start of day
     */
    public static Long currentTimeMillisWithTimeAtStartOfDay() {
        return new DateTime().withTimeAtStartOfDay().withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * compare passed in dates
     *
     * @param startDate start date
     * @param endDate   end date
     * @return returns true if startdate is after enddate
     **/
    public static Boolean dateComparator(final Calendar startDate, final Calendar endDate) {
        return startDate.after(endDate);
    }

    /**
     * Return future UTC time in milliseconds for given number of days with time at start Of day
     *
     * @param numberOfDays number of days
     * @return future time
     */
    public static Long futureTimeMillisWithTimeAtStartOfDay(final Integer numberOfDays) {
        return new DateTime().withTimeAtStartOfDay().plusDays(numberOfDays).withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * Return future UTC time in milliseconds for given number of days
     *
     * @param numberOfDays number of days
     * @return future time
     */
    public static Long futureTimeMillis(final Integer numberOfDays) {
        return new DateTime().plusDays(numberOfDays).withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * Get UTC calendar for given milliseconds date with offset
     *
     * @param millisecondsTime millisecond time
     * @param offsetHour       offset hour
     * @param offsetMinute     offset minute
     * @return utc calender
     */
    public static Calendar getCalendarWithTimeAtEndOfDay(final Long millisecondsTime, final Integer offsetHour,
                                                         final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).withTime(23, 59, 59, 999).withZone(DateTimeZone.UTC)
                .toGregorianCalendar();
    }

    /**
     * Get calendar with time at start of day
     *
     * @param calendar calender
     * @return calender with start of day
     */
    public static synchronized Calendar getCalendarWithTimeAtStartOfDay(final Calendar calendar) {
        return new DateTime(calendar).withTimeAtStartOfDay().withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * Get UTC calendar for given milliseconds date with offset
     *
     * @param millisecondsTime millisecondsTime
     * @param offsetHour       offsetHour
     * @param offsetMinute     offsetMinute
     * @return UTC calendar
     */
    public static synchronized Calendar getCalendarWithTimeAtStartOfDay(final Long millisecondsTime,
                                                                        final Integer offsetHour, final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toGregorianCalendar();
    }

    /**
     * Gives the Calendar object at start-time of the day according to offset and adds/subtracts the
     * number of days passed
     *
     * @param offsetHour   offsetHour
     * @param offsetMinute offsetMinute
     * @param numberOfDays numberOfDays
     * @return Calendar object at start-time of the day
     */
    public static synchronized Calendar getCalendarWithTimeAtStartOfDayBasedOnNumberOfDays(final Integer offsetHour,
                                                                                           final Integer offsetMinute, final Integer numberOfDays) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(currentTimeMillis(), zone).withTimeAtStartOfDay().plusDays(numberOfDays)
                .withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * Gives the Calendar object at start-time of the day according to offset and adds/subtracts the
     * number of days passed
     *
     * @param offsetHour   offsetHour
     * @param offsetMinute offsetMinute
     * @param numberOfDays numberOfDays
     * @return the Calendar object at start-time of the day
     */
    public static synchronized Calendar getCalendarWithTimeAtStartOfDayBasedOnNumberOfDays(final Calendar calendar,
                                                                                           final Integer offsetHour, final Integer offsetMinute, final Integer numberOfDays) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(calendar, zone).withTimeAtStartOfDay().plusDays(numberOfDays).withZone(DateTimeZone.UTC)
                .toGregorianCalendar();
    }

    /**
     * Return sql timestamp for sent date if not return timestamp for current day
     *
     * @param calendar calendar
     * @return timestamp for sent date
     */
    public static Timestamp getCurrentSQLTimeStamp(final Calendar calendar) {
        if (!NullEmptyUtils.isNull(calendar)) {
            return new Timestamp(calendar.getTimeInMillis());
        }
        return new Timestamp(DateUtil.getUTCCalenderInstance(null).getTimeInMillis());
    }

    /**
     * For given date in milliseconds, return string date in specified string format
     *
     * @param dateMilliseconds dateMilliseconds
     * @return string date
     */
    public static synchronized String getDateString(final Long dateMilliseconds, final String format) {
        return new DateTime(dateMilliseconds).toString(DateTimeFormat.forPattern(format));
    }

    /**
     * Get calendar for today with DB_DAT_FORMAT
     **/

    public static synchronized String getDateStringForToday() {
        return DateTime.now().toString(DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT));
    }

    /**
     * For given date in milliseconds, return string date in UTC DB_DATE_FORMAT string format
     *
     * @param dateMilliseconds dateMilliseconds
     * @return string date
     */
    public static synchronized String getDateStringInUTC(final Long dateMilliseconds) {
        return new DateTime(dateMilliseconds).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gives the date string at start-time of the day according to offset and adds/subtracts the
     * number of days passed
     *
     * @param offsetHour   offsetHour
     * @param offsetMinute offsetMinute
     * @param numberOfDays numberOfDays
     * @return date string
     */
    public static synchronized String getDateStringWithTimeAtStartOfDayBasedOnNumberOfDays(final Integer offsetHour,
                                                                                           final Integer offsetMinute, final Integer numberOfDays) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(currentTimeMillis(), zone).withTimeAtStartOfDay().plusDays(numberOfDays)
                .withZone(DateTimeZone.UTC).toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }


    /**
     * Gives the date string at start-time of the day according to offset and adds/subtracts the
     * number of days passed
     *
     * @param date         date
     * @param numberOfDays numberOfDays
     * @return date string
     */

    public static synchronized String getDateStringWithTimeAtStartOfDayBasedOnNumberOfDays(final Long date,
                                                                                           final Integer numberOfDays) {
        return new DateTime(date).withTimeAtStartOfDay().plusDays(numberOfDays).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Parse given string date with specified format, add specified number of days and return in UTC
     * with time at start of the day in DB_DATE_FORMAT string format
     *
     * @param date         date
     * @param numberOfDays numberOfDays
     * @return Calendar
     */
    public static synchronized Calendar getFutureDateStringWithTimeAtStartOfDay(final Long date,
                                                                                final int numberOfDays) {

        return new DateTime(date).withTimeAtStartOfDay().plusDays(numberOfDays).withZone(DateTimeZone.UTC)
                .toGregorianCalendar();
    }

    /**
     * Parse given string date with specified format, add specified number of days and return in UTC
     * with time at start of the day in DB_DATE_FORMAT string format
     *
     * @param date         date
     * @param numberOfDays numberOfDays
     * @param offsetHour   offsetHour
     * @param offsetMinute offsetMinute
     * @return UTC time at start of the day
     */
    public static synchronized String getFutureDateStringWithTimeAtStartOfDay(final Long date, final int numberOfDays,
                                                                              final int offsetHour, final int offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(date, zone).withTimeAtStartOfDay().plusDays(numberOfDays).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * get local calendar instance using offset values
     *
     * @param millisecondsTime millisecondsTime
     * @param offsetHour       offsetHour
     * @param offsetMinute     offsetMinute
     * @return calendar
     **/
    public static synchronized Calendar getLocalCalendarInstanceUsingOffset(final Long millisecondsTime,
                                                                            final Integer offsetHour, final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).toGregorianCalendar();

    }

    /**
     * get local calendar instance using offset values
     *
     * @param millisecondsTime millisecondsTime
     * @param offsetHour       offsetHour
     * @param offsetMinute     offsetMinute
     * @return calendar
     **/
    public static synchronized Calendar getFutureLocalCalendarInstanceUsingOffset(final Long millisecondsTime,
                                                                                  final Integer numberOfDays, final Integer offsetHour, final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).plusDays(numberOfDays).toGregorianCalendar();
    }

    /**
     * @param date         date
     * @param offsetHour   offsetHour
     * @param offsetMinute offsetMinute
     * @return utc date
     */
    public static synchronized String getUTCDateString(final Long date, final Integer offsetHour,
                                                       final Integer offsetMinute, final String format) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(date, zone).withZone(DateTimeZone.UTC).toString(DateTimeFormat.forPattern(format));
    }

    /**
     * @param date         date
     * @param offsetHour   offsetHour
     * @param offsetMinute offsetMinute
     * @return get utc long
     */
    public static synchronized Long getUTCDateLong(final Long date, final Integer offsetHour,
                                                   final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(date, zone).withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * @param date         date
     * @param offsetHour   offsetHour
     * @param offsetMinute offsetMinute
     * @return local date
     */
    public static synchronized String getLocalDateString(final Long date, final Integer offsetHour,
                                                         final Integer offsetMinute, final String format) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(date, zone).toString(DateTimeFormat.forPattern(format));
    }

    public static synchronized long getMillisWithTimeAtStartOfDay(final Long millisecondsTime,
                                                                  final Integer offsetHour, final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).withTimeAtStartOfDay().withZone(DateTimeZone.UTC).getMillis();
    }


    /**
     * Parse given string date with specified format and return in UTC with time at start of the day
     * in DB_DATE_FORMAT string format
     *
     * @param date
     * @return start date string with time at the start of the day
     */

    public static synchronized String getStartDateStringWithTimeAtStartOfDay(final Long date) {
        return new DateTime(date).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Parse given string date with specified format and return in UTC with time at start of the day
     * in DB_DATE_FORMAT string format
     *
     * @param date         date
     * @param offsetHour   offsetHour
     * @param offsetMinute offsetMinute
     * @return start date string
     */
    public static synchronized String getStartDateStringWithTimeAtStartOfDay(final Long date, final int offsetHour,
                                                                             final int offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(date, zone).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Parse given string date with specified format and return in UTC with time at start of the day
     * in DB_DATE_FORMAT string format
     *
     * @param date         date
     * @param offsetHour   offsetHour
     * @param offsetMinute offsetMinute
     * @return start date string
     */
    public static synchronized String getStartDateStringWithTimeAtStartOfDay(final Long date, final int offsetHour,
                                                                             final int offsetMinute, final String format) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(date, zone).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(format));
    }

    public static synchronized String getStringAtStartTimeOfDayAccordingToOffset(final Long millisecondsTime,
                                                                                 final Integer offsetHour, final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).withTimeAtStartOfDay().withZone(zone)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    public static synchronized String getStringAtStartTimeOfDayAccordingToOffset(final Long millisecondsTime,
                                                                                 final Integer offsetHour, final Integer offsetMinute, final String dateFormat) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).withTimeAtStartOfDay().withZone(zone)
                .toString(DateTimeFormat.forPattern(dateFormat));
    }

    /**
     * Get UTC calendar for given milliseconds date with offset
     *
     * @param millisecondsTime millisecondsTime
     * @param offsetHour       offsetHour
     * @param offsetMinute     offsetMinute
     * @return string calender
     */
    public static synchronized String getStringCalendarWithTimeAtStartOfDay(final Long millisecondsTime,
                                                                            final Integer offsetHour, final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    public static synchronized String getStringDateAtEndOfDay() {
        return new DateTime(currentTimeMillis()).withTimeAtStartOfDay().plusDays(1).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    public static synchronized String getStringDateAtEndOfMonth() {
        return new DateTime(currentTimeMillis()).withDayOfMonth(1).plusMonths(1).withTimeAtStartOfDay()
                .withZone(DateTimeZone.UTC).toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    public static synchronized String getStringDateAtEndofTheWeek() {
        return new DateTime(currentTimeMillis()).weekOfWeekyear().roundCeilingCopy().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    public static synchronized String getStringDateAtStartOfDay() {
        return new DateTime(currentTimeMillis()).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    public static synchronized String getStringDateAtStartOfMonth() {
        return new DateTime(currentTimeMillis()).withDayOfMonth(1).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    public static synchronized String getStringDateAtStartofTheWeek() {
        return new DateTime(currentTimeMillis()).weekOfWeekyear().roundFloorCopy().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Get current time utc calendar instance if external vaue is not passed
     *
     * @param millisecondsTime millisecondsTime
     * @return utc calender
     */
    public static synchronized String getStringUTCCalenderInstance(final Long millisecondsTime) {
        return new DateTime(millisecondsTime).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Get current time utc calendar instance if external vaue is not passed using provided offset
     *
     * @param millisecondsTime millisecondsTime
     * @param offsetHour       offsetHour
     * @param offsetMinute     offsetMinute
     * @return calendar
     */
    public static synchronized String getStringUTCCalenderInstanceUsingOffset(final Long millisecondsTime,
                                                                              final Integer offsetHour, final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gives the hour and minute time as a float for example if time is 02:45 then it is represented
     * as 2.45
     *
     * @param millisecondsTime millisecondsTime
     * @return time
     */
    public static synchronized float getTimeInFloat(final Long millisecondsTime) {
        final Calendar cal = getUTCCalenderInstance(millisecondsTime);
        final float timeSlot = cal.get(Calendar.HOUR_OF_DAY);
        return timeSlot + cal.get(Calendar.MINUTE) / 100f;
    }

    /**
     * Get current time utc calendar instance if external vaue is not passed
     *
     * @param millisecondsTime millisecondsTime
     * @return calender
     */
    public static synchronized Calendar getUTCCalenderInstance(final Long millisecondsTime) {
        return new DateTime(millisecondsTime).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * Get current time utc calendar instance if external vaue is not passed using provided offset
     *
     * @param millisecondsTime millisecondsTime
     * @param offsetHour       offsetHour
     * @param offsetMinute     offsetMinute
     * @return calendar
     */
    public static synchronized Calendar getUTCCalenderInstanceUsingOffset(final Long millisecondsTime,
                                                                          final Integer offsetHour, final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(millisecondsTime, zone).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }


    /**
     * Get current time utc calendar instance if external vaue is not passed using provided offset
     *
     * @param millisecondsTime millisecondsTime
     * @param timeZone         timeZone
     * @return calender
     */

    public static synchronized Calendar getUTCCalenderInstanceUsingOffset(final Long millisecondsTime,
                                                                          final String timeZone) {
        final DateTimeZone zone = DateTimeZone.forID(timeZone);

        return new DateTime(millisecondsTime, zone).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * @param date date
     * @return date
     */
    public static synchronized String getUTCDateStringWithOffset(final String date, final Integer offsetHour,
                                                                 final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        final DateTimeFormatter formatter = DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT);
        final DateTime dt = formatter.withZone(zone).parseDateTime(date).withTimeAtStartOfDay();

        return dt.withZone(DateTimeZone.UTC).toString(DateTimeFormat.forPattern(DateUtil.DB_DATE_FORMAT));
    }

    /**
     * @param date date
     * @return future date
     */
    public static synchronized String getFutureUTCDateStringWithOffset(final String date, final Integer numberOfDays,
                                                                       final Integer offsetHour, final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        final DateTimeFormatter formatter = DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT);
        final DateTime dt = formatter.withZone(zone).parseDateTime(date).withTimeAtStartOfDay().plusDays(numberOfDays);

        return dt.withZone(DateTimeZone.UTC).toString(DateTimeFormat.forPattern(DateUtil.DB_DATE_FORMAT));
    }

    /**
     * @param date date
     * @return zone date
     */
    public static long getZoneDateWithOffset(final String date, final Integer offsetHour, final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        final DateTimeFormatter formatter = DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT);
        return formatter.withZone(zone).parseDateTime(date).withTimeAtStartOfDay().getMillis();
    }

    /**
     * increment by specified number of days
     *
     * @param date         date
     * @param numberOfDays numberOfDays
     * @return incremented calendar instance
     **/
    public static Calendar incrementDate(final Long date, final int numberOfDays) {
        return new DateTime(date).withTimeAtStartOfDay().withZone(DateTimeZone.UTC).plusDays(numberOfDays)
                .toGregorianCalendar();
    }

    /**
     * Check if the passed date is after current date
     *
     * @param startDateTime startDateTime
     * @return date after current date
     */
    public static boolean isDateAfterCurrentDate(final Calendar startDateTime) {
        final DateTime startDate = new DateTime(startDateTime.getTimeInMillis());
        return startDate.toLocalDate().isAfter(DateTime.now().toLocalDate());
    }

    /**
     * Check if the passed date is before current date
     *
     * @param startDateTime startDateTime
     * @return date before current date
     */
    public static boolean isDateBeforeCurrentDate(final Calendar startDateTime) {
        final DateTime startDate = new DateTime(startDateTime.getTimeInMillis());
        return startDate.toLocalDate().isBefore(DateTime.now().toLocalDate());
    }

    /**
     * check if both are on same day
     *
     * @param startDate startDate
     * @param endDate   endDate
     * @return returns true if both passed in dates are on same day
     **/
    public static Boolean isSameDay(final Calendar startDate, final Calendar endDate) {
        return startDate.get(Calendar.YEAR) == endDate.get(Calendar.YEAR)
                && startDate.get(Calendar.MONTH) == endDate.get(Calendar.MONTH)
                && startDate.get(Calendar.DAY_OF_MONTH) == endDate.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param date         date
     * @param format       format
     * @param offsetHour   offsetHour
     * @param offsetMinute offsetMinute
     * @return date format for all the day
     */
    public static String dateFormatForAllDayTask(final Long date, final String format, final Integer offsetHour,
                                                 final Integer offsetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);

        return new DateTime(date).withZone(zone).toString(DateTimeFormat.forPattern(format));

    }

    /**
     * @param offSetHour   offSetHour
     * @param offSetMinute offSetMinute
     * @return time zone
     */
    public static String getTimeZone(final Integer offSetHour, final Integer offSetMinute) {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offSetHour, offSetMinute);
        return zone.getID();
    }

    /**
     * @param date   date
     * @param format format
     * @return date
     * @throws ParseException ParseException
     */
    public static Date getDateObject(final Calendar date, final String format) throws ParseException {
        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter
                .parse(new DateTime(date).toString(DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT)));
    }

    /**
     * @param taskTimeStamp     taskTimeStamp
     * @param scheduleTimeStamp scheduleTimeStamp
     * @param timeZone          timeZone
     * @return start and end date
     */
    public static Calendar getStartAndEndDate(final Long taskTimeStamp, final Long scheduleTimeStamp,
                                              final String timeZone) {
        final Calendar taskTime = Calendar.getInstance();
        final Calendar scheduleTime = Calendar.getInstance();
        final TimeZone tz = TimeZone.getTimeZone(timeZone);
        taskTime.setTimeInMillis(taskTimeStamp);
        scheduleTime.setTimeInMillis(scheduleTimeStamp);
        scheduleTime.setTimeZone(tz);
        final Calendar actualScheduleTime = Calendar.getInstance();
        actualScheduleTime.setTimeZone(tz);
        actualScheduleTime.set(taskTime.get(Calendar.YEAR), taskTime.get(Calendar.MONTH),
                taskTime.get(Calendar.DAY_OF_MONTH), scheduleTime.get(Calendar.HOUR_OF_DAY),
                scheduleTime.get(Calendar.MINUTE), scheduleTime.get(Calendar.SECOND));
        return actualScheduleTime;
    }

    /**
     * @param dob dob
     * @return date format
     * @throws ParseException ParseException
     */
    public static java.sql.Date dateFormatForDOB(String dob) throws ParseException {
        final DateFormat formatter = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        return new java.sql.Date(formatter.parse(dob).getTime());
    }


    /**
     * @param startDate startDate
     * @param endDate   endDate
     * @param timeZone  timeZone
     * @return boolean value
     */

    public static Boolean compareDates(final Long startDate, final Long endDate, String timeZone) {
        final TimeZone tz = TimeZone.getTimeZone(timeZone);
        final DateTime start = new DateTime(startDate).withZone(DateTimeZone.forTimeZone(tz)).plusDays(7);
        final DateTime end = new DateTime(endDate).withZone(DateTimeZone.forTimeZone(tz));
        return (start.getMillis() == end.getMillis());
    }

}
