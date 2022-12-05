package forum.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2022-11-21 10:49
 *
 * @author Xia Jiayi
 */
public class DateTimeUtil {
    /**
     * 以yyyy-MM-dd HH:mm:SS格式返回当前时间字符串
     *
     * @return String
     */
    public static String getNowStr() {
        String resultStr = null;
        String pattern = "yyyy-MM-dd HH:mm:ss";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            resultStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }
}
