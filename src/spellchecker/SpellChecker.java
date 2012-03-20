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
						(float) seconds / proposedCorrections.keySet().size());

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

		Map<String, Integer> proposedCount = getProposedCount(candidates);

		return max(proposedCount);

	}

	protected static String getCorrection(String context, String misspelling) {

		Multiset<String> wordItself = HashMultiset.create();
		Multiset<String> candidates = HashMultiset.create();

		wordItself.add(misspelling);
		candidates.addAll(getKnownWords(wordItself));
		Multiset<String> edits1 = getKnownWords(trainingGenerator
				.editDist1(misspelling));
		candidates.addAll(edits1);
		candidates.addAll(getKnownWords(trainingGenerator.editDist2(edits1)));

		Map<String, Integer> proposedCount = getProposedCount(candidates);

		for (String proposed : proposedCount.keySet()) {

			Set<Multiset.Entry<String>> matchedBigrams = matchFirstInBigram(proposed);
			addBigramWeight(proposed, proposedCount, matchedBigrams);

		}

		return max(proposedCount);
	}

	private static void addBigramWeight(String proposed,
			Map<String, Integer> proposedCount,
			Set<Multiset.Entry<String>> matchedBigrams) {

		for (Multiset.Entry<String> bigram : matchedBigrams) {
			proposedCount.put(proposed, new Integer(bigram.getCount()
					+ proposedCount.get(proposed)));
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

	private static String max(Map<String, Integer> choices) {
		int maxCount = Integer.MIN_VALUE;
		String bestCandidate = "";

		for (String choice : choices.keySet()) {
			if (choices.get(choice) > maxCount) {
				maxCount = choices.get(choice);
				bestCandidate = choice;
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

	protected static Set<Multiset.Entry<String>> matchFirstInBigram(String first) {

		Multiset<String> matchedBigrams = HashMultiset.create();

		for (String bigram : bigrams.keySet()) {
			String[] split = bigram.split(" ");
			if (split.length == 2) {
				if (split[0].equals(first)) {
					matchedBigrams.add(bigram);
				}
			}
		}

		return matchedBigrams.entrySet();
	}

	private static Map<String, Integer> getProposedCount(
			Multiset<String> proposals) {

		Map<String, Integer> proposalCounts = new HashMap<String, Integer>();

		for (String proposal : proposals) {
			proposalCounts.put(proposal, proposals.count(proposal));
		}

		return proposalCounts;

	}

}
