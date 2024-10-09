package reconnect.server.global.util;

import org.springframework.util.StringUtils;

public class StringUtil {
    public static boolean isEmpty(String check) {
        return !StringUtils.hasText(check);
    }

    public static boolean isNotEmpty(String check) {
        return StringUtils.hasText(check);
    }
}
