package com.wydpp.pufa;

import java.util.Scanner;

public class StringMatch {
    /**
     * 在一组字符串中，找到所有具有某个字符串前缀字符串，比如application、apple、eyes、cats等。如果要匹配的字符串是app，则符合匹配条件的有application、apple。
     */
    public static void matchStr(String matchStr,String[] words){

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.next();
            System.out.println(str);
            if(str.equals("ok")){
                break;
            }
        }
    }
}
