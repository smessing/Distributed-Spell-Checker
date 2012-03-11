import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Multiset;

/*
 * File created on: Mar 11, 2012
 * Author: Samuel Messing <sbm2158@columbia.edu>
 */

public class SpellChecker {

	private static Set<Multiset.Entry<String>> knownWords;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		usage(args);

		TrainingSetGenerator trainingGenerator = new TrainingSetGenerator();
		knownWords = trainingGenerator.buildWordSet(args[0]);

		boolean quit = false;
		while (!quit) {
			BufferedReader inputText = new BufferedReader(
					new InputStreamReader(System.in));
			System.out.print("Enter misspelled word: ");
			try {
				String word = inputText.readLine();
				if (word.equals("quit")) {
					quit = true;
				} else {
					//Set<String> candidates = 
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
				System.exit(1);
			}
		}
		
		System.exit(1);

	}

	private static void usage(String[] args) {
		if (args.length != 1) {
			System.err.println("Length of args: " + args.length);
			System.err.printf("Didn't specify just a training set!\n");
			System.exit(1);
		}
	}

	private Set<String> getKnownWords(Set<String> candidateWords) {
		Set<String> verifiedWords = new HashSet<String>();

		for (String candidate : candidateWords) {
			if (knownWords.contains(candidate)) {
				verifiedWords.add(candidate);
			}
		}

		return verifiedWords;

	}
}
