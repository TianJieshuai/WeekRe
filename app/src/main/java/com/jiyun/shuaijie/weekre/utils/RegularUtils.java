package com.jiyun.shuaijie.weekre.utils;

/**
 * Created by shuaiJie on 2017/9/12.
 */

public class RegularUtils {
    public static boolean isCheckName(String name) {
        String phomeRegularString = "1[3589][0-9]{9,9}";
        String E_mainRegularString = "[\\w]{0,}@[\\w\\.]{0,}";
        if ((name.matches(phomeRegularString) | name.matches(E_mainRegularString))) {
            return true;
        }
        return false;
    }

    public static boolean isCheckPassword(String password) {
        String regularPassword = "\\w{6,16}";
        if (password.matches(regularPassword)) {
            return true;
        }
        return false;
    }

    public static boolean isCheckPhone(int phome) {

        return false;
    }
}
