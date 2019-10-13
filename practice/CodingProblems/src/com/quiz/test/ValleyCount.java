package com.quiz.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ValleyCount {

	  // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
    	char[] chars = s.toCharArray();
    	int vCount = 0;
    	int hCount = 0;
    	int seaLevel = 0;
    	for (int i = 0; i < chars.length; i++) {
			if(chars[i] == 'U') 
				seaLevel++;
			if(chars[i] == 'D')
				seaLevel--;
			if(seaLevel == -1)
				vCount++;
		}
    	//System.out.println("Valley count: " + vCount);		
    	System.out.println("seaLevel : " + hCount);		
    	
    	return seaLevel;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/input.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();
        System.out.println(s);
        int result = countingValleys(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

}
