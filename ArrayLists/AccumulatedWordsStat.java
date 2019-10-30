
/**
 * CMSI Assignment 1
 * 
 * @author <Law, Raymond>
 *
 */
public class AccumulatedWordsStat extends SimpleWordsStat {

	public AccumulatedWordsStat() {
		super();
	}

	/**
	 * Updates the words and counts lists with an accumulated total for each word
	 * and its count
	 */
	@Override
	public void update(String input) {
		updateStat(input);
	}
}
