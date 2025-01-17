package ee.energia.testassignment.price;

public class EnergyPrice {

    public EnergyPrice (int bidPrice, int askPrice, int hour, int day, int month, int year) {
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getBidPrice () {
        return bidPrice;
    }

    public int getAskPrice () {
        return askPrice;
    }

    public int getHour () {
        return hour;
    }

    public int getDay () {
        return day;
    }

    public int getMonth () {
        return month;
    }

    public int getYear () {
        return year;
    }

    private final int bidPrice;
    private final int askPrice;

    private final int hour;
    private final int day;
    private final int month;
    private final int year;
    
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[BidPrice: " + bidPrice + " AskPrice: " + askPrice + " Hour: " + hour + "]") ;
		return buffer.toString();
	}
    
    
}
