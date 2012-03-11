import java.util.Set;

import com.google.common.collect.Multiset;

/*
 * File created on: Mar 11, 2012
 * Author: Samuel Messing <sbm2158@columbia.edu>
 */

public class SpellChecker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		usage(args);

		TrainingSetGenerator trainingGenerator = new TrainingSetGenerator();
		Set<Multiset.Entry<String>> numWords = trainingGenerator
				.buildWordSet(args[0]);
		for (Multiset.Entry<String> entry : numWords) {
			System.out.printf("Word: %s, count: %d\n", entry.getElement(),
					entry.getCount());
		}

	}

	private static void usage(String[] args) {
		if (args.length != 1) {
			System.err.println("Length of args: " + args.length);
			System.err.printf("Didn't specify just a training set!\n");
			System.exit(1);
		}
	}

}
