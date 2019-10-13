import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

	public static void main(String[] args) {
		String[] orders = {"223:456:334:1934","234:shampoo:soap","456:898:333:123","144:biscuit:soap","864:abcd:uhgt"};
		sortOrders(orders);
	}
	
	/*
	 * Orders eg: 444:345:237:259  - Regular
	 * 458:abcd:Defgtg:ukij  -- Gold
	 */
	static void sortOrders(String[] orders) {
		// Segregate the regular and gold customer orders
		List<String> goldCustomers = new ArrayList<>();
		List<String> regularCustomers = new ArrayList<>();
		
		Pattern gPattern = Pattern.compile("x[0-9]+[:][a-z]+");
		
		
		for(int i=0; i<orders.length; i++) {
			Matcher matchGold = gPattern.matcher(orders[i]);
			if(matchGold.matches()) {
				goldCustomers.add(orders[i]);
			} else  {
				regularCustomers.add(orders[i]);
			}
		}
		
		System.out.println("Regular Customers : " + regularCustomers.size());
		System.out.println("Gold Customers : " + goldCustomers.size());
		
		// We can do first round with comparable and then optimize for Merge sort? 
	/*	goldCustomers.sort(new Comparator<String >() {
			@Override
			public int compare(String o1, String o2) {
				
				String s1 = o1.split(":")[1];
				String s2 = o2.split(":")[1];
				
				int result = s1.compareTo(s2);
				if(result == 0) { // If both are same
					return o1.compareTo(o2);
				}
				return result;
			}
		});
		
		regularCustomers.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				Integer int1 =  new Integer( o1.split(":")[0]);
				Integer int2 = new Integer(o2.split(":")[0]);
				
				if(int1 < int2 ) {
					return -1;
				} else if(int1 > int2) {
					return 1;
				} else {	
					// Should we consider the second digit after ":"
					return 0;
				}		
		
			}
		});
		
		List<String> sortedList = new ArrayList<>();
		sortedList.addAll(goldCustomers);
		sortedList.addAll(regularCustomers);
		
		// List sorted... 
		for(int i=0;i<sortedList.size();i++) {
			System.out.println(sortedList.get(i));
		}*/
		
	}

}
