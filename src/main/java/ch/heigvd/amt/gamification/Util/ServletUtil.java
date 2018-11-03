package ch.heigvd.amt.gamification.Util;

public class ServletUtil {

    public static String getString(String field, String def) {
        return field == null || field.isEmpty() ? def : field;
    }

    public static Integer getInt(String field, Integer def) {
        return field == null || field.isEmpty() ? def : Integer.valueOf(field);
    }
}
