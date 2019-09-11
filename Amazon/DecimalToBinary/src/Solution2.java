import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

public class Solution2 {
	
	public static void main(String[] S) {
        // write your code in Java SE 8
		String sch = "Sun 10:00-20:00\n" + 
				"Fri 05:00-10:00\n" + 
				"Fri 16:30-23:50\n" +
				"Sat 10:00-24:00\n" +
				"Sun 01:00-04:00\n" ;
		// split the dates string
		String[] meetings = sch.split("\\n");
		System.out.println(meetings.length);
		 long restTime = 0;
		 
	
		
//		String[] meeting = days[0].split("-");
//		System.out.println(meeting[0] + meeting[1]);
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("E hh:mm");
		SortedSet<Date> meetingDayTime = new ConcurrentSkipListSet<Date>();
		try {
			
			for(int i=0; i<meetings.length; i++) {
				String[] day = meetings[i].split("-");
				Date date1 = sdf.parse(day[0]);
				Date date2 = sdf.parse(day[0].split(" ")[0] + " " + day[1]);
				meetingDayTime.add(sdf.parse(day[0]));
				meetingDayTime.add(sdf.parse(day[0].split(" ")[0] + " " + day[1]));
				
			//	System.out.println(date1.toLocaleString());
			//	System.out.println(date2.toLocaleString());
			}
			
			 
			 for (Iterator iterator = meetingDayTime.iterator(); iterator.hasNext();) {
				Date date = (Date) iterator.next();
				System.out.println(date.toLocaleString());
			}
			//Need to do the operation from set or convert set to array and then do the operation.
			 Date[] meetingTime = new Date[meetingDayTime.size()];	
			 for(int k=0; k< meetingDayTime.size() -1; k++) {
				 long diffInMillies = Math.abs(meetingTime[k].getTime() - meetingTime[k+1].getTime());
				 long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
				 if(diff > restTime) {
				     restTime = diff;
				 }
			 }
			 
			System.out.println("Maximum rest time is : " + restTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

}
