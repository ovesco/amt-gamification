package ch.heigvd.amt.gamification.Util;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

    public static Long getDevId(HttpServletRequest request) {
        return (Long)request.getSession().getAttribute("token");
    }

    public static String getString(String field, String def) {
        return field == null || field.isEmpty() ? def : field;
    }

    public static Integer getInt(String field, Integer def) {
        return field == null || field.isEmpty() ? def : Integer.valueOf(field);
    }

    public static Long getLong(String field, Long def) {
        return field == null || field.isEmpty() ? def : Long.valueOf(field);
    }
}
