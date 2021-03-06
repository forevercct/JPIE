package uitox.eric.ch7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Anagrams {
	private static final Logger logger = LoggerFactory
			.getLogger(Anagrams.class);

	final Map<String, List<String>> lookup = new HashMap<String, List<String>>();

	public static void main(String[] args) {
		final List<String> source = Arrays.asList("word", "boat", "rowd",
				"abot");
		Anagrams anagram = new Anagrams(source);
		List<String> result = anagram.getAnagrams("drow");

		logger.info("The result of checked functions: {}",
				Arrays.toString(result.toArray()));
	}

	public Anagrams(final List<String> words) {
		for (final String word : words) {
			final String signature = alphabetize(word);
			if (lookup.containsKey(signature)) {
				lookup.get(signature).add(word);
			} else {
				final List<String> anagramList = new ArrayList<String>();
				anagramList.add(word);
				lookup.put(signature, anagramList);
			}
		}
	}

	private String alphabetize(final String word) {
		final byte[] bytes = word.getBytes();
		Arrays.sort(bytes);
		return new String(bytes);
	}

	public List<String> getAnagrams(final String word) {
		final String signature = alphabetize(word);
		final List<String> anagrams = lookup.get(signature);

		return anagrams == null ? new ArrayList<String>() : anagrams;
	}
}
