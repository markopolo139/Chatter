package com.chatter.chatter.web.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    public static String getBaseUrl(HttpServletRequest httpServletRequest) {

        String url = httpServletRequest.getRequestURL().toString();

        return url.replace(httpServletRequest.getServletPath(), "")
                .replace(httpServletRequest.getQueryString(),"");

    }

}
