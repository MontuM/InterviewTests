package com.quiz.test;

public class FindSubstring {
	
	// Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
    	boolean isSubstring = false;
    	
    	char[] chars = s1.toCharArray();
    	
    	for(int i=0;i<chars.length;i++) {
    		if(s2.indexOf(chars[i]) != -1) {
    			return "Yes";
    		}
    	}
    	
        return "NO";
    }
    
    public static void main(String[] args) {
    	System.out.println(twoStrings("hello", "world"));
    }

}
