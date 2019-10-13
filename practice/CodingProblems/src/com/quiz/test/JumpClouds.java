package com.quiz.test;

public class JumpClouds {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] c = {0, 0, 1, 0, 0, 1, 0};
		System.out.println(jumpingOnClouds(c));
	}
	
	//7
	//0 0 1 0 0 1 0

	static int jumpingOnClouds(int[] c) {
        int noOfJumps = 0;
        int i = 0;
        while(i< c.length) {
        	if( (i+2 < c.length) &&  c[i+2] == 0) {
        		i=i+2;
        		noOfJumps++;
        	} else if((i+1 < c.length) &&  (c[i+1] == 0)){
        		i++;
        		noOfJumps++;
        	} else {
        		i++;
        	}
        	
        }

        return noOfJumps;
    }

}
