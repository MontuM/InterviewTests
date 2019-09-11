class Solution {
    public static int solution(int A, int B) {
        // write your code in Java SE 8
    	int decimalNumber = A*B;
    	System.out.println(decimalNumber);
    	//Convert decimalNumber to binary equivalent
    	int[] bNumSequence = new int[1000];
    	int i=0;
    	while(decimalNumber > 0) {
    		bNumSequence[i] = decimalNumber % 2;
    		decimalNumber = decimalNumber / 2;
    		i++;
    	}
    	//Binary sequence is 
    	for (int j = i - 1; j >= 0; j--) {
			System.out.print(bNumSequence[j]);
		}
    	
    	return 0;
    	
    }
    
    public static void main(String[] args) {
    	solution(76574,872);
    }
}