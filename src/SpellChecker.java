import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/*
 * File created on: Mar 11, 2012
 * Author: Samuel Messing <sbm2158@columbia.edu>
 */

public class SpellChecker {

	private static Map<String, Integer> knownWords = new HashMap<String, Integer>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		usage(args);

		TrainingSetGenerator trainingGenerator = new TrainingSetGenerator();
		buildKnownWords(trainingGenerator.buildWordSet(args[0]));

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
					candidates.addAll(getKnownWords(wordItself));
					Multiset<String> edits1 = getKnownWords(trainingGenerator
							.editDist1(word));
					candidates.addAll(edits1);
					candidates.addAll(getKnownWords(trainingGenerator
							.editDist2(edits1)));
					System.out.printf("Suggested correction: %s\n", max(candidates));

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
			if (knownWords.containsKey(candidate)) {
				verifiedWords.add(candidate);
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

	private static void buildKnownWords(Set<Multiset.Entry<String>> wordSet) {

		for (Multiset.Entry<String> word : wordSet) {
			knownWords.put(word.getElement(), word.getCount());
		}

	}
}
