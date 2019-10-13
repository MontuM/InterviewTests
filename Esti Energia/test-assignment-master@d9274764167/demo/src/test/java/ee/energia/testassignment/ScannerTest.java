package ee.energia.testassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.util.ResourceUtils;

public class ScannerTest {

	public static void main(String[] args) {
		System.out.println("Reading a text file line by line: ");
		Scanner sc;
		String[] energyPriceStr = null;
		int battreyLevel = 0;
		String[] chargePlanStr = null;
		File[] testFiles = null;
		
		try {
			File file = ResourceUtils.getFile("classpath:tests");
			if(file.isDirectory()) {
				testFiles = file.listFiles();
			}
			for(int k=0; k< testFiles.length; k++) {
				System.out.println("##### File : " + testFiles[k].getName());
				sc = new Scanner(testFiles[k]);
				int i = 1;
				while (sc.hasNext()) {
					String str = sc.nextLine();
					switch(i ) {
					case 1:
						energyPriceStr = str.split(" ");
						i++;
						break;
					case 2:
						battreyLevel = Integer.parseInt(str);
						i++;
						break;
					case 3:
						chargePlanStr = str.split(" ");
						i++;
						break;
					}
					//System.out.println(str);
				}
				System.out.println(energyPriceStr.length);
				System.out.println(battreyLevel);
				System.out.println(chargePlanStr.length);
				sc.close();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		

	}

}
