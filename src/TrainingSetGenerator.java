import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.base.CharMatcher;
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

	/**
	 * Generates a word count for each word found in the file specified by
	 * fileName.
	 * 
	 * @param fileName
	 *            The fileName to be opened.
	 * @return Immutable map of <word, word count> pairs.
	 * @throws UnsupportedOperationException
	 *             If file cannot be found.
	 */
	public Set<Multiset.Entry<String>> buildWordSet(String fileName)
			throws UnsupportedOperationException {

		File file = new File(fileName);
		Multiset<String> wordOccurrences = null;
		Pattern nonWord = Pattern.compile("[ \t\n\f\r-.,_;0-9?=:\\]\\[]+");

		try {
			Iterable<String> words = Splitter.on(nonWord).omitEmptyStrings().trimResults().split(
					Files.toString(file, Charsets.UTF_8));
			List<String> lowercaseWords = new ArrayList<String>();
			for (String word : words) {
				lowercaseWords.add(word.toLowerCase());
			}
			wordOccurrences = HashMultiset.create();
			wordOccurrences.addAll(lowercaseWords);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.err.printf("File not found! %s\n", fileName);
		}

		return wordOccurrences.entrySet();
	}

}
