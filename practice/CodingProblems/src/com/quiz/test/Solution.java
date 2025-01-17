package com.quiz.test;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/*
 * 
 * John works at a clothing store. He has a large pile of socks that he must pair by color for sale. Given an array of integers representing the color of each sock, determine how many pairs of socks with matching colors there are.
For example, there are  socks with colors . There is one pair of color  and one of color . There are three odd socks left, one of each color. The number of pairs is .
Function Description
Complete the sockMerchant function in the editor below. It must return an integer representing the number of matching pairs of socks that are available.
sockMerchant has the following parameter(s):
n: the number of socks in the pile
ar: the colors of each sock
Input Format
The first line contains an integer , the number of socks represented in . 
The second line contains  space-separated integers describing the colors  of the socks in the pile.
Constraints

 where 
Output Format
Return the total number of matching pairs of socks that John can sell.

Sample input
9
10 20 20 10 10 30 50 10 20

Sample Output
3
 *
 */

public class Solution {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        int noOfPairs = 0;
//        int temp = -1;
//        
//        for(int i=0; i<n; i++) {
//            temp = ar[i];
//            int colour = 0;
//            for(int j = i+1; j < n; j++) {
//                if((ar[j] != -99) && (temp == ar[j])) {
//                    colour++;
//                    ar[j]=-99;
//                }
//                
//            }
//            
//                if(colour != 0) {
//                    noOfPairs += ((colour+1)/2);
//                   
//                }
//
//        }
    	
    	Hashtable<Integer, Integer> duplicates = new Hashtable<>();
    	
    	for(int i=0; i< n; i++ ) {
    		Integer temp = duplicates.get(ar[i]);
    		if(temp == null) {
    			duplicates.put(ar[i], 1);
    		} else {
    			duplicates.put(ar[i], ++temp);
    		}
    	}
    	
    	Set<Entry<Integer, Integer>> entrySet =  duplicates.entrySet();
    	
    	for (Entry<Integer, Integer> entry : entrySet) {
			if(entry.getValue() > 1) {
				noOfPairs += (entry.getValue()/2);
			}
			
		}

        return noOfPairs;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);
        

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
