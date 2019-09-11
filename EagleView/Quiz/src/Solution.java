import java.util.LinkedList;

public class Solution {

	public int solution(String S) {
		String[] parseStr = {"B","A","L","L","O","N"};
		int passCount = 0;
		
		if(S.length() < parseStr.length) {
			return passCount;
		} else {
			int charCount = 0;
			//Decide total number of scan require
			int noOfScan = S.length() / parseStr.length;
			StringBuilder input = new StringBuilder(S);
			while(noOfScan != 0) {
				charCount = 0;
			    for(int i=0; i< parseStr.length; i++) {
					if(input.indexOf(parseStr[i]) != -1) {
						input.setCharAt(input.indexOf(parseStr[i]), '9');
						charCount++;
					}
	    		}
    			if(charCount == parseStr.length) {
    			    passCount++;
    			}
    			noOfScan--;
			}
			
		}
       
		return passCount;
    }
	
	public static void main(String args[]) {
		
	}
}
