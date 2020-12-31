package com.ssomai.myjavalibraries.java;

import org.junit.gen5.api.DisplayName;
import org.junit.gen5.api.Test;

class Tests {

    @Test
//    @DisplayName("1 + 1 = 2")
    void addsTwoNumbers() {
//        Calculator calculator = new Calculator();
//        assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");

        // TODO 안됨
        System.out.println(" "+Tool_Java.dateformat_Day_UTC(Tool_Java.getOldBaseTimestamp_Upper()));
    }

//    @ParameterizedTest(name = "{0} + {1} = {2}")
//    @CsvSource({
//            "0,    1,   1",
//            "1,    2,   3",
//            "49,  51, 100",
//            "1,  100, 101"
//    })
//    void add(int first, int second, int expectedResult) {
//        Calculator calculator = new Calculator();
//        assertEquals(expectedResult, calculator.add(first, second),
//                () -> first + " + " + second + " should equal " + expectedResult);
//    }
}