package com.springboot.controller;
import java.util.*;

import java.util.HashMap;
public class Solution {
    
    
     static final HashMap<Character,String> map = new HashMap<Character,String>(){{
        put('0',"0");
         put('1',"1");
        put('2',"2abc");
        put('3',"3def");
        put('4',"4ghi");
        put('5',"5jkl");
        put('6',"6mno");
        put('7',"7pqrs");
        put('8',"8tuv");
        put('9',"9wxyz");
    }} ;
      
    public static ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        ArrayList<String> preres = new ArrayList<String>();
        res.add("");

        for(int i=0;i<digits.length();i++){
            for(String str: res){
                String letters = map.get(digits.charAt(i));
                for(int j=0;j<letters.length();j++)
                    preres.add(str+letters.charAt(j));
            }
            res = preres;
            preres = new ArrayList<String>();
        }      
        return res;
    }

   
}
