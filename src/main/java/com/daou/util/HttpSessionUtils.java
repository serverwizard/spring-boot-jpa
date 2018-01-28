package com.daou.util;

import com.daou.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Created by hongjong-wan on 2017. 7. 2..
 */
public class HttpSessionUtils {

    public static final String USER_SESSION_KEY = "loginUser";


    public static boolean isLoginUser(HttpSession session) {

        User loginUser = (User) session.getAttribute(USER_SESSION_KEY);

        if (loginUser == null) {
            return false;
        }
        return true;
    }

    public static User getUserFromHttpSession(HttpSession session) {
        if(!isLoginUser(session)) {
            return null;
        }
        return (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
    }

}