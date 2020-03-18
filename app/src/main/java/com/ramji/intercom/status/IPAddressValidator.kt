package com.ramji.intercom.status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

object IPAddressValidator {

    private lateinit var matcher: Matcher;

    private final var IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";


    public fun validate(ip: String): Boolean {
        matcher = Pattern.compile(IPADDRESS_PATTERN).matcher(ip);
        return matcher.matches();
    }
}