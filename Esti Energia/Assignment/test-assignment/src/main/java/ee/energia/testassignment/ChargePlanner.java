package ee.energia.testassignment;

import ee.energia.testassignment.planning.ChargePlan;
import ee.energia.testassignment.price.EnergyPrice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;

public class ChargePlanner {

    // the capability of the charger to charge certain amount of energy into the battery in 1 hour
    public final static int CHARGER_POWER = 50;

    // maximum battery level possible
    public final static int MAX_LEVEL = 100;

    // battery level required by the end of the charging
    public final static int REQUIRED_LEVEL = 100;

    /**
     * Method calculates the optimal hourly charge plan.
     * Method finds the cheapest hour to charge the battery (if multiple then the earliest)
     * and uses it to charge the battery up to the {@link ChargePlanner#REQUIRED_LEVEL}.
     * If {@link ChargePlanner#CHARGER_POWER} limitation does not allow to do this in one hour,
     * then method finds the next cheapest opportunities and uses them until {@link ChargePlanner#REQUIRED_LEVEL} is met.
     *
     * Method returns the array of {@link ChargePlan} objects that represent the hourly time slot
     * and the capacity that we need to charge during that hour to charge the battery.
     *
     * @param batteryLevel initial battery level when the charger is connected
     * @param energyPrices the list of the energy prices from the moment when charger is connected until the moment when battery needs to be charged
     *                     there is an assumption that battery is connected the first second of the first given hour and disconnected the last second of the last given hour
     * @return
     */
    public static ArrayList<ChargePlan> calculateChargePlan(int batteryLevel, ArrayList<EnergyPrice> energyPrices) {
        // todo: implement the function that will be calculating the optimal hourly charge plan
    	
    	TreeMap<Integer, ChargePlan> sortedPlan = new TreeMap<>();

		
    	/*
    	 * Sort the array list buying price and then hour
    	 */
    	
    	energyPrices.sort(new Comparator<EnergyPrice>() {

			@Override
			public int compare(EnergyPrice price1, EnergyPrice price2) {
				if(price1.getBidPrice() < price2.getBidPrice()) {
					if(price1.getHour() < price2.getHour()) {
						return 1;
					} else {
						return -1;
					}
				}
				return 0;
			}
		});
    	
    	
    	
    	for (Iterator<EnergyPrice> iterator = energyPrices.iterator(); iterator.hasNext();) {
			EnergyPrice energyPrice = (EnergyPrice) iterator.next();
			//System.out.println(energyPrice.toString());
			int capacity = Math.min(ChargePlanner.REQUIRED_LEVEL - batteryLevel, ChargePlanner.CHARGER_POWER);
			if(batteryLevel < ChargePlanner.REQUIRED_LEVEL ) {
				sortedPlan.put(new Integer(energyPrice.getHour()), new ChargePlan(capacity, energyPrice.getHour(), energyPrice.getMonth(), energyPrice.getYear()));
							
				batteryLevel = capacity + batteryLevel;
			} else {

				sortedPlan.put(energyPrice.getHour(), new ChargePlan(0, energyPrice.getHour(), energyPrice.getMonth(), energyPrice.getYear()));
			}
			
		}
    	
    	
    	//System.out.println("======================================================");
    	ArrayList<ChargePlan> definedPlan = new ArrayList<>(sortedPlan.values());
    	/*for (ChargePlan chargePlan : definedPlan) {
			System.out.println(chargePlan.toString());
		}*/
    	
        return definedPlan;
    }
    
    /**
     * Method calculates the amount of saved money that the {@link ChargePlan} generates comparing with classical way to charge
     * when the changing would start straight away when connected to the charger, and will charge until charged to maximum.
     * 
     * @param batteryLevel
     * @param energyPrices
     * @return Difference of amount between classical way of charging to @link ChargePlan}
     */
    public static int calculateAmountSaved(int batteryLevel, ArrayList<EnergyPrice> energyPrices) {
    	int actualAmount = 0;
    	int optimizedAmount = 0;
    	int tmpBatteryLevel = batteryLevel;
    	
    	for (Iterator<EnergyPrice> iterator = energyPrices.iterator(); iterator.hasNext() && (tmpBatteryLevel != ChargePlanner.REQUIRED_LEVEL);) {
			EnergyPrice energyPrice = (EnergyPrice) iterator.next();
			int capacity = Math.min(ChargePlanner.REQUIRED_LEVEL - tmpBatteryLevel, ChargePlanner.CHARGER_POWER);
			if(tmpBatteryLevel < ChargePlanner.REQUIRED_LEVEL ) {
				actualAmount = actualAmount + energyPrice.getBidPrice();
				tmpBatteryLevel = capacity + tmpBatteryLevel;
			} 
			
		}
    	
    	calculateChargePlan(batteryLevel,energyPrices);
    	
    	for (Iterator<EnergyPrice> iterator = energyPrices.iterator(); iterator.hasNext() && (batteryLevel != ChargePlanner.REQUIRED_LEVEL);) {
			EnergyPrice energyPrice = (EnergyPrice) iterator.next();
			int capacity = Math.min(ChargePlanner.REQUIRED_LEVEL - batteryLevel, ChargePlanner.CHARGER_POWER);
			if(batteryLevel < ChargePlanner.REQUIRED_LEVEL ) {
				optimizedAmount = optimizedAmount + energyPrice.getBidPrice();
				batteryLevel = capacity + batteryLevel;
			} 
			
		}
    	System.out.println("\n\n Actual Amount :: " + actualAmount);
    	System.out.println("\n\n Optimized Amount :: " + optimizedAmount);
    	
    	return (actualAmount-optimizedAmount);
    }
}
