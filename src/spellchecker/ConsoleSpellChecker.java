package spellchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * File created on: Mar 12, 2012
 * Author: Samuel Messing <sbm2158@columbia.edu>
 */

public class ConsoleSpellChecker extends SpellChecker {

	
	
	public static void main(String[] args) {
		usage(args);
		
		buildKnownWords(trainingGenerator.buildWordSet(args[0]));
		buildBigramModel(trainingGenerator.buildBigramSet(args[0]));
		
		evaluateModel();
		
		boolean quit = false;
		while (!quit) {
			BufferedReader inputText = new BufferedReader(
					new InputStreamReader(System.in));
			System.out.print("Enter misspelled word: ");
			try {
				String word = inputText.readLine();
				if (word.equals("quit")) {
					quit = true;
					System.out.println("Quitting spellchecker.");
				} else {
					System.out.printf("Suggested correction: %s\n",
							getCorrection(word));

				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
				System.exit(1);
			}
		}

		System.exit(1);
		
	}
	
}
