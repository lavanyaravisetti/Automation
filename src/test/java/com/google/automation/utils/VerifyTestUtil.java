package com.google.automation.utils;

import org.testng.Assert;

import java.util.ArrayList;


public class VerifyTestUtil {
    private ArrayList<String> results = new ArrayList<String>();

    /**
     * method to verify one condition and add err message ot err list
     * @param condition
     * @param message
     */
    public void verify(boolean condition, String message) {
        if (!condition) results.add(message);
    }

    /**
     * final verification to check wether the tes is passed or not
     * @return
     */
    public boolean isPassed() {
        return results.size() == 0;
    }

    /**
     * Method to read messages
     * @return
     */
    public String getMessage() {
        return results.toString();
    }

    /**
     * final method toe exectute
     * @return
     */
    public boolean assertTestResult() {
        for(String str :results)
        System.out.println(str);
        Assert.assertTrue(results.size() == 0, results.toString());
        return results.size() == 0;
    }

    /**
     * verifies result
     * @param message
     * @return
     */
    public boolean assertTestResult(String message) {
        for(String str :results)
            System.out.println(str);
        Assert.assertTrue(results.size() == 0, message + " " + results.toString());
        return results.size() == 0;
    }

    /**
     * compare two strings and append err message if any
     * @param one
     * @param two
     * @param message
     * @return
     */
    public boolean assertEqual(String one, String two,String message) {
        if (one.contentEquals(two))
            return true;
        else {
            results.add(message);
            return false;
        }
    }

    /**
     * comares two integers
     * @param one
     * @param two
     * @param message
     * @return
     */
    public boolean assertEqual(int one, int two,String message) {
        if (one==two)
            return true;
        else {
            results.add(message);
            return false;
        }
    }

    public void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }
}

