package ee.energia.testassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import ee.energia.testassignment.planning.ChargePlan;
import ee.energia.testassignment.price.EnergyPrice;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAssignmentApplicationTests {

	private ArrayList<EnergyPrice> energyPrices = new ArrayList<>();
	private ArrayList<ChargePlan> definedPlan = new ArrayList<>();
	private int batteryLevel = 0;
	
	@Test
	public void contextLoads() {
		System.out.println("#####	Testing Charge Planner	 #####");
		File[] testFiles = null;
		
		File testFolder;
		try {
			testFolder = ResourceUtils.getFile("classpath:tests");
			if(testFolder.isDirectory()) {
				testFiles = testFolder.listFiles();
			} else {
				System.out.println("Could not find input test files. Exiting......");
				System.exit(-1);
			}
			
			for (File testFile : testFiles) {
				System.out.println("Testing file : " + testFile.getName());
				
				resetData();
				initDataFromFile(testFile);
				
				final ArrayList<ChargePlan> chargePlan = ChargePlanner.calculateChargePlan(batteryLevel, energyPrices);
				
				Assert.assertEquals(definedPlan, chargePlan);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Could not find input test files. Exiting......");
			e.printStackTrace();
			System.exit(-1);
			
		}
		
	}
	

	@Test
	public void testAmountSaved() {
		File[] testFiles = null;
		File testFolder;
		try {
			testFolder = ResourceUtils.getFile("classpath:tests");
			if(testFolder.isDirectory()) {
				testFiles = testFolder.listFiles();
			} else {
				System.out.println("Could not find input test files. Exiting......");
				System.exit(-1);
			}
			
			for (File testFile : testFiles) {
				System.out.println("Testing file : " + testFile.getName());
				
				resetData();
				initDataFromFile(testFile);
				System.out.println(ChargePlanner.calculateAmountSaved(batteryLevel, energyPrices));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Could not find input test files. Exiting......");
			e.printStackTrace();
			System.exit(-1);
		}
		
		
	}
	
	private void getDefinedPlan(String[] chargePlanStr) throws NumberFormatException {
		int tempLevel = batteryLevel;
		int capacity = 0;
		
		for(int i=0; i< chargePlanStr.length; i++) {
			int hour = Integer.parseInt(chargePlanStr[i]);
			if(hour != 0) {
				capacity = Math.min(ChargePlanner.REQUIRED_LEVEL - tempLevel, ChargePlanner.CHARGER_POWER);
				definedPlan.add(new ChargePlan(capacity, hour, 1, 2019));
				tempLevel = capacity + tempLevel;
			} else {
				definedPlan.add(new ChargePlan(0, i+1, 1, 2019));
			}
		}
		
	}

	private ArrayList<EnergyPrice> getEnergyPrices(String[] energyPriceStr) throws NumberFormatException {
		
		for(int j=0; j<energyPriceStr.length;j++) {
			int price = Integer.parseInt(energyPriceStr[j]);
			energyPrices.add(new EnergyPrice(price, price -2 , j+1, 1, 1, 2019));
		}
		
		return energyPrices;
	}
	
	private void resetData() {
		energyPrices = new ArrayList<>();
		definedPlan = new ArrayList<>();
		batteryLevel = 0;
	}
	
	private void initDataFromFile(File file) {
		
		String[] energyPriceStr = null;
		String[] chargePlanStr = null;
		
		try {
			Scanner sc = new Scanner(file);
			
			int i = 1;
			while (sc.hasNext()) {
				String str = sc.nextLine();
				switch(i ) {
				case 1:
					energyPriceStr = str.split(" ");
					i++;
					break;
				case 2:
					batteryLevel = Integer.parseInt(str);
					i++;
					break;
				case 3:
					chargePlanStr = str.split(" ");
					i++;
					break;
				}
			}
			sc.close();
			
			if(energyPriceStr != null && chargePlanStr != null) {
				getEnergyPrices(energyPriceStr);
				
				getDefinedPlan(chargePlanStr);
				
			} else {
				System.out.println("Could not load test data. Please check the input.txt file in resources folder.");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the test data \"input.txt\" file in resources folder. ");
			e.printStackTrace();
			System.exit(-1);
		} catch(NumberFormatException nfe) {
			System.out.println("Could not read number from \\\"input.txt\\\" file");
			nfe.printStackTrace();
			System.exit(-1);
		}
		
		 
	}

	
	

}
