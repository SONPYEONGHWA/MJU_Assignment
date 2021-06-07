package com.example.workdiary.util;

import android.text.TextUtils;

import java.util.List;
import java.util.Objects;

public class InputChecker {
    private static InputChecker inputChecker = null;

    public static int checkEmptyString(List<String> userInfo) {
        Long emptyCount = userInfo.stream().filter(Objects::isNull).count();
        return Integer.parseInt(emptyCount.toString());
    }

    public static InputChecker getInstance() {
        if (inputChecker == null) {
            inputChecker = new InputChecker();
        }
        return inputChecker;
    }
}
