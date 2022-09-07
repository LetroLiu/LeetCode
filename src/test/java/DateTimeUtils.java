import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 默认标题
 * 默认描述
 *
 * @author Letro Liu
 * @date 2022-09-06
 */
public class DateTimeUtils {
    /**
     * 获取指定毫秒数在格林威治时区时间当天的开始时间的Calendar对象
     * @param millis
     * @return
     */
    public static Calendar getCalendarForTheDayBeginOfGreenwichBy(long millis) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT00:00"));
        calendar.setTime(new Date(millis));
        //calendar.set(Calendar.HOUR_OF_DAY, 0);
        int min = (calendar.get(Calendar.MINUTE) / 10) * 10;
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
    /**
     * 根据时间戳获取该时间戳对应的格林威治年月日字符串
     * @param millis
     * @return
     */
    public static String getGreenwichYyMmDd(long millis) {
        Calendar calendar = getCalendarForTheDayBeginOfGreenwichBy(millis);
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH) + 1;
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int i = calendar.get(Calendar.MINUTE);
        return String.format("%d%02d%02d%02d%02d", y,  m, d, h, i);
    }

    /**
     * 根据时间戳获取该时间戳对应的格林威治0点时间戳
     * @param millis
     * @return
     */
    public static long getGreenwichTimestampCurrentDayZero(long millis) {
        Calendar calendar = getCalendarForTheDayBeginOfGreenwichBy(millis);
        return calendar.getTimeInMillis();
    }

    /**
     * 根据时间戳获取该时间戳对应的后一天格林威治0点时间戳
     * @param millis
     * @return
     */
    public static long getGreenwichTimestampNextDayZero(long millis) {
        Calendar calendar = getCalendarForTheDayBeginOfGreenwichBy(millis);
        calendar.add(Calendar.MINUTE, 10);
        return calendar.getTimeInMillis();
    }

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println(now + " : " + getGreenwichYyMmDd(now));
        System.out.println("1662451055000 : " + getGreenwichYyMmDd(1662451055000L));
        System.out.println("=================");
        System.out.println("1626922800000 : " + getGreenwichTimestampCurrentDayZero(1626922800000L));
        System.out.println("1626940800000 : " + getGreenwichTimestampCurrentDayZero(1626940800000L));
        System.out.println("1626922800000 : " + getGreenwichTimestampNextDayZero(1626922800000L));
        System.out.println("1626940800000 : " + getGreenwichTimestampNextDayZero(1626940800000L));
        System.out.println("1626973200000 : " + getGreenwichYyMmDd(1626973200000L));
        System.out.println("1630537199000 : " + getGreenwichTimestampCurrentDayZero(1630537199000L));
        System.out.println("1630537199000 : " + getGreenwichTimestampNextDayZero(1630537199000L));
        System.out.println("1630537199000 : " + getGreenwichYyMmDd(1630537199000L));
    }
}
