package com.valora.memo;

import java.util.regex.Pattern;

public class Tool {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || Pattern.matches("^\\s*$", str);
    }
}
