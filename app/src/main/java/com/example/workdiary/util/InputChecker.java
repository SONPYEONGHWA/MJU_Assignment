package com.example.workdiary.util;

import java.util.List;

public class InputChecker {
    private static InputChecker inputChecker = null;

    public static boolean checkEmptyString(List<String> userInfo) {
        Long emptyCount = userInfo.stream().filter(String::isEmpty
        ).count();
        return Integer.parseInt(emptyCount.toString()) > 0;
    }

    public static InputChecker getInstance() {
        if (inputChecker == null) {
            inputChecker = new InputChecker();
        }
        return inputChecker;
    }
}
