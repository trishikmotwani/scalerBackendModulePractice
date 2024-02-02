package com.javabasics;

import java.time.LocalDateTime;

public interface Greeting {

    public static String greet() {
        var currHour = LocalDateTime.now().toLocalTime().getHour();
        System.out.println("currHour::" + currHour);
        if(currHour  >= 17) {
            return "Good Evening";
        } else if(currHour >= 12) {
            return "Good Afternoon";
        } else {
            return "Good Morning";
        }
    }
}
