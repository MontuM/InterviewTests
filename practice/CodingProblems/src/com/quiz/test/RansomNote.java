package com.quiz.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

public class RansomNote {
	

	public static void main(String[] args) {
		try {
			BufferedReader bufferedWriter = new BufferedReader(new FileReader("resources/input2.txt"));
			
			Scanner scanner = new Scanner(bufferedWriter);
			String[] mn = scanner.nextLine().split(" ");

			int m = Integer.parseInt(mn[0]);

			int n = Integer.parseInt(mn[1]);

			String[] magazine = new String[m];

			String[] magazineItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int i = 0; i < m; i++) {
				String magazineItem = magazineItems[i];
				magazine[i] = magazineItem;
			}

			String[] note = new String[n];

			String[] noteItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int i = 0; i < n; i++) {
				String noteItem = noteItems[i];
				note[i] = noteItem;
			}

			checkMagazine(magazine, note);

			scanner.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void checkMagazine(String[] magazine, String[] note) {

		Hashtable<String, Integer> magzDictionary = new Hashtable<>();
		Hashtable<String, Integer> notesDictionary = new Hashtable<>();

		boolean isAvailable = true;

		for (int i = 0; i < magazine.length; i++) {
			Integer count = magzDictionary.get(magazine[i]);
			if (count == null) {
				magzDictionary.put(magazine[i], 1);
			} else {
				magzDictionary.put(magazine[i], ++count);
			}
		}
		
		/*for (int i = 0; i < note.length; i++) {
			Integer count = notesDictionary.get(note[i]);
			if (count == null) {
				notesDictionary.put(note[i], 1);
			} else {
				notesDictionary.put(note[i], count++);
			}
		}*/

		//System.out.println("aabza Count = " + magzDictionary.get("aabza"));
		for (int i = 0; i < note.length; i++) {
			Integer count = magzDictionary.get(note[i]);
			if (count == null || count <= 0) {
				System.out.println("Note word: " + note[i] + " :: Count = " + count);
				isAvailable = false;
				break;
			} else {
				magzDictionary.put(note[i], --count);
			}
		}

		if (isAvailable)
			System.out.println("Yes");
		else
			System.out.println("No");
	}

}
