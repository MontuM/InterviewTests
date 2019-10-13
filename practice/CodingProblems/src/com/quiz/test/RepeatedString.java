package com.quiz.test;

public class RepeatedString {
	
	public static void main(String[] args) {
		System.out.println(repeatedString("ababa", 3));
		//System.out.println(11%3);
		//System.out.println(11/3);
		
	}
	static long repeatedString(String s, long n) {
		long count = 0;
		int sLen = s.length();
		
		if((sLen == 1) && (s.equals("a")) ) {
			return n;
		} else if((sLen == 1) && (!s.equals("a")) ) {
			return count;
		}
		
		StringBuffer searchStringBuffer = new StringBuffer(s);
		if(sLen < n) {
			//This needs to re-written
			int num = (int) (n%sLen);
			if(num == 0) {
				count = findRepeatCharSequence(new StringBuffer(s));
				count = (count * (n/sLen));
			} else {
				count = findRepeatCharSequence(new StringBuffer(s));
				count = (count * (n/sLen));
				count = count + findRepeatCharSequence(new StringBuffer(s.substring(0, (int)(num))));
			}
			
		} else if(sLen > n) {
			searchStringBuffer = new StringBuffer(s.substring(0, ((int)n))); 
			count = findRepeatCharSequence(searchStringBuffer);
		} 
		
		return count;
	}
	
	
/*	static long repeatedString(String s, long n) {
		int sLen = s.length();
		StringBuffer searchStringBuffer = new StringBuffer(s);
		if((sLen == 1) && (s.equals("a")) ) {
			return n;
		} else if((sLen == 1) && (!s.equals("a")) ) {
			return 0;
		}
		
		if(sLen < n) {
			int rLen = (int) (n/sLen);
			for(int i=1;i< rLen+1;i++) {
				searchStringBuffer.append(s);
			}
			//N is Long. Can get bug here for n>integer range
			searchStringBuffer = new StringBuffer(searchStringBuffer.substring(0, ((int)n))); 
		} else if(sLen > n) {
			searchStringBuffer = new StringBuffer(searchStringBuffer.substring(0, ((int)n))); 
		} 
		
		return findRepeatCharSequence(searchStringBuffer);
    } */
	
	static long findRepeatCharSequence(StringBuffer sbr) {
		int length = sbr.length();
		int i = 0;
		long count=0;
		while(i < length) {
			int index =0;
			if((index = sbr.indexOf("a", i)) != -1) {
				count++;
				i = index+1;
			} else {
				break;
			}
		}
		
		return count;
	}
	
	
}
