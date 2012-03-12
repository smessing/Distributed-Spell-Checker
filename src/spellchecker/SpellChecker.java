package spellchecker;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import spellchecker.SpellCheckerEvaluator.TestType;
import spellchecker.SpellCheckerEvaluator.Verboseness;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/*
 * File created on: Mar 11, 2012
 * Author: Samuel Messing <sbm2158@columbia.edu>
 */

public class SpellChecker {

	protected static Map<String, Integer> knownWords = new HashMap<String, Integer>();
	protected static Map<String, Integer> bigrams = new HashMap<String, Integer>();
	protected static TrainingSetGenerator trainingGenerator = new TrainingSetGenerator();
	protected static SpellCheckerEvaluator modelEvaluator;

	protected static void evaluateModel() {
		if (modelEvaluator == null)
			modelEvaluator = new SpellCheckerEvaluator(knownWords);
		
		Iterator<String> testSet = SpellCheckerEvaluator
				.getTestErrors(TestType.DEVELOPMENT);

		Map<String, String> proposedCorrections = new HashMap<String, String>();

		Date startTime = new Date();

		while (testSet.hasNext()) {
			String misspelling = testSet.next();
			String correction = getCorrection(misspelling);
			proposedCorrections.put(misspelling, correction);
		}

		Date endTime = new Date();

		long seconds = endTime.getTime() - startTime.getTime();

		double accuracy = modelEvaluator.evaluateCorrections(
				proposedCorrections, TestType.DEVELOPMENT, Verboseness.VERBOSE);

		System.out
				.printf(
						"Words tested: %d, percent correct: %f, time taken: %d milliseconds, %f ms/word\n",
						proposedCorrections.keySet().size(), accuracy, seconds,
						proposedCorrections.keySet().size() / (float) seconds);

	}

	protected static void usage(String[] args) {
		if (args.length != 1) {
			System.err.println("Length of args: " + args.length);
			System.err.printf("Didn't specify just a training set!\n");
			System.exit(1);
		}
	}

	protected static String getCorrection(String misspelling) {

		Multiset<String> wordItself = HashMultiset.create();
		Multiset<String> candidates = HashMultiset.create();

		wordItself.add(misspelling);
		candidates.addAll(getKnownWords(wordItself));
		Multiset<String> edits1 = getKnownWords(trainingGenerator
				.editDist1(misspelling));
		candidates.addAll(edits1);
		candidates.addAll(getKnownWords(trainingGenerator.editDist2(edits1)));

		return max(candidates);

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

	protected static void buildKnownWords(Set<Multiset.Entry<String>> wordSet) {

		for (Multiset.Entry<String> word : wordSet) {
			knownWords.put(word.getElement(), word.getCount());
		}

	}
	
	protected static void buildBigramModel(Set<Multiset.Entry<String>> bigramSet) {
		
		for (Multiset.Entry<String> bigram : bigramSet) {
			bigrams.put(bigram.getElement(), bigram.getCount());
		}
		
	}
}
