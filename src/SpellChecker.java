import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.HashMultiset;
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
					Multiset<String> wordItself = HashMultiset.create();
					wordItself.add(word);
					Multiset<String> candidates = HashMultiset.create();
					// currently all of these are returning null:
					candidates.addAll(getKnownWords(wordItself));
					candidates.addAll(getKnownWords(trainingGenerator
							.editDist1(word)));
					// too slow:
					//candidates.addAll(getKnownWords(trainingGenerator
					//		.editDist2(trainingGenerator.editDist1(word))));
					trainingGenerator.editDist1(word);
					System.out.printf("Correction: %s\n", max(candidates));

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

	private static Multiset<String> getKnownWords(
			Multiset<String> candidateWords) {
		Multiset<String> verifiedWords = HashMultiset.create();

		for (String candidate : candidateWords) {
			for (Multiset.Entry<String> entry : knownWords) {
				if (entry.getElement().equals(candidate)) {
					verifiedWords.add(candidate);
				}
			}
		}
		
		return verifiedWords;

	}

	private static String max(Multiset<String> choices) {
		int maxCount = Integer.MIN_VALUE;
		String bestCandidate = "";

		for (Multiset.Entry<String> choice : choices.entrySet()) {
			if (choice.getCount() > maxCount) {
				maxCount = choice.getCount();
				bestCandidate = choice.getElement();
			}
		}

		return bestCandidate;
	}
}
