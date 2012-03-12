package spellchecker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.io.Files;

/*
 * File created on: Mar 11, 2012
 * Author: Samuel Messing <sbm2158@columbia.edu>
 */

public class TrainingSetGenerator {

	private static final char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z' };

	private static final Pattern nonWord = Pattern
			.compile("[ \t\n\f\r-.,_;0-9?=:\\]\\[]+");

	/**
	 * Generates a word count for each word found in the file specified by
	 * fileName.
	 * 
	 * @param fileName
	 *            The fileName to be opened.
	 * @return Set<Multi.Entry<String>> Bag of words for lazy counting.
	 * @throws UnsupportedOperationException
	 *             If file cannot be found.
	 */
	public Set<Multiset.Entry<String>> buildWordSet(String fileName)
			throws UnsupportedOperationException {

		File file = new File(fileName);
		Multiset<String> wordOccurrences = null;

		try {
			Iterable<String> words = Splitter.on(nonWord).omitEmptyStrings()
					.trimResults().split(Files.toString(file, Charsets.UTF_8));
			List<String> lowercaseWords = new ArrayList<String>();
			for (String word : words) {
				lowercaseWords.add(word.toLowerCase());
			}
			wordOccurrences = HashMultiset.create();
			wordOccurrences.addAll(lowercaseWords);
		} catch (IOException ioe) {
			throw new UnsupportedOperationException("Training file not found! "
					+ fileName + "\n", ioe);
		}

		return wordOccurrences.entrySet();
	}

	public Set<Multiset.Entry<String>> buildBigramSet(String fileName)
			throws UnsupportedOperationException {

		File file = new File(fileName);
		Multiset<String> wordOccurrences = null;

		try {
			Iterable<String> words = Splitter.on(nonWord).omitEmptyStrings()
					.trimResults().split(Files.toString(file, Charsets.UTF_8));
			List<String> lowercaseWords = new ArrayList<String>();
			for (String word : words) {
				lowercaseWords.add(word.toLowerCase());
			}
			wordOccurrences = HashMultiset.create();
			for (int i = 0; i < lowercaseWords.size() - 1; i++) {
				wordOccurrences.add(new String(lowercaseWords.get(i).toString()
						+ " " + lowercaseWords.get(i + 1).toString()));
			}

		} catch (IOException ioe) {
			throw new UnsupportedOperationException("Training file not found! "
					+ fileName + "\n", ioe);
		}

		return wordOccurrences.entrySet();

	}

	/**
	 * Computes all strings of edit distance 1 away from input string.
	 * 
	 * @param word
	 *            the input string
	 * @return a set of all strings one edit distance away.
	 */
	public Multiset<String> editDist1(String word) {

		Set<Tuple<String, String>> splits = buildSplits(word);

		Multiset<String> combined = HashMultiset.create();
		combined.addAll(buildDeletes(splits));
		combined.addAll(buildTransposes(splits));
		combined.addAll(buildReplaces(splits));
		combined.addAll(buildInserts(splits));

		return combined;

	}

	/**
	 * Computes all strings of edit distance 1 from a set of strings.
	 * 
	 * @param editsOne
	 *            a set of strings all within 1 edit distance from each other.
	 * @return a set of strings all within 2 edit distances from each other.
	 */
	public Multiset<String> editDist2(Multiset<String> editsOne) {

		Multiset<String> editsTwo = HashMultiset.create();

		for (String editOne : editsOne) {
			editsTwo.addAll(editDist1(editOne));
		}

		return editsTwo;

	}

	private Multiset<String> buildInserts(Set<Tuple<String, String>> splits) {

		Multiset<String> inserts = HashMultiset.create();

		for (Tuple<String, String> tuple : splits) {
			for (char letter : alphabet) {
				inserts
						.add(new String(tuple.getOne() + letter
								+ tuple.getTwo()));
			}
		}

		return inserts;

	}

	private Multiset<String> buildReplaces(Set<Tuple<String, String>> splits) {

		Multiset<String> replaces = HashMultiset.create();

		for (Tuple<String, String> tuple : splits) {
			for (char letter : alphabet) {
				if (tuple.getTwo().length() > 0) {
					replaces.add(new String(tuple.getOne() + letter
							+ tuple.getTwo().substring(1)));
				}
			}
		}

		return replaces;

	}

	private Multiset<String> buildTransposes(Set<Tuple<String, String>> splits) {

		Multiset<String> transposes = HashMultiset.create();

		for (Tuple<String, String> tuple : splits) {
			if (tuple.getTwo().length() > 1) {
				transposes.add(new String(tuple.getOne()
						+ tuple.getTwo().substring(1, 2)
						+ tuple.getTwo().substring(0, 1)
						+ tuple.getTwo().substring(2)));
			}
		}

		return transposes;

	}

	private Multiset<String> buildDeletes(Set<Tuple<String, String>> splits) {

		Multiset<String> deletes = HashMultiset.create();

		for (Tuple<String, String> tuple : splits) {
			if (tuple.getTwo().length() > 0) {
				deletes.add(new String(tuple.getOne()
						+ tuple.getTwo().substring(1)));
			}
		}

		return deletes;
	}

	private Set<Tuple<String, String>> buildSplits(String word) {
		Set<Tuple<String, String>> splits = new HashSet<Tuple<String, String>>();

		for (int i = 0; i < word.length() + 1; i++) {
			splits.add(new Tuple<String, String>(word.substring(0, i), word
					.substring(i, word.length())));
		}

		return splits;
	}

	protected class Tuple<E, T> {

		private E one;
		private T two;

		Tuple(E one, T two) {
			this.one = one;
			this.two = two;
		}

		public E getOne() {
			return this.one;
		}

		public T getTwo() {
			return this.two;
		}

		@Override
		public String toString() {
			return "[" + one.toString() + "," + two.toString() + "]";
		}
	}

}
