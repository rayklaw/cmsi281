
import java.util.ArrayList;

/**
 * CMSI Assignment 1
 * 
 * @author <Law, Raymond>
 *
 */
public class SimpleWordsStat implements WordsStat {

	private final String SPLIT_REGEX = "\\W+";
	private final String EMPTY = "";

	ArrayList<String> words = new ArrayList<String>();
	ArrayList<Integer> counts = new ArrayList<Integer>();

	public SimpleWordsStat() {
	}

	/**
	 * Tokenizes the input String
	 *
	 * @param input
	 * @return String array containing the tokenized input
	 */
	protected String[] parseString(String input) {
		if (input == null || input.equals(EMPTY)) {
			return new String[0];
		}
		return input.split(SPLIT_REGEX);
	}

	/**
	 * Clears the words and counts lists
	 */
	protected void clear() {
		words.clear();
		counts.clear();
	}

	/**
	 * Updates the words and counts lists where the count of each word in words at
	 * index i corresponds to the same index in counts
	 */
	protected void updateStat(String input) {
		String[] inputArray = parseString(input);
		int wordCount;
		String uniqueWord;

		for (int i = 0; i < inputArray.length; i++) {
			if (!words.contains(inputArray[i])) {
				words.add(inputArray[i]);
			}
			if (words.size() > counts.size()) {
				counts.add(0);
			}
		}
		for (int i = 0; i < words.size(); i++) {
			uniqueWord = words.get(i);
			wordCount = 0;

			for (int j = 0; j < inputArray.length; j++) {
				if (uniqueWord.equals(inputArray[j])) {
					wordCount++;
				}
			}
			counts.set(i, wordCount + counts.get(i));
		}
	}

	/**
	 * Clears the existing stats contained within words and counts and then
	 * recompute the stats
	 */
	@Override
	public void update(String input) {
		clear();
		updateStat(input);
	}

	/**
	 * Prints a formatted text showing each word's usage in counts
	 */
	@Override
	public void showStat() {
		System.out.println("Words Usage:");

		if (words.size() == 0) {
			System.out.println("No stats found!");
		}

		for (int i = 0; i < words.size(); i++) {
			System.out.println(words.get(i) + " : " + counts.get(i));
		}
	}
}
