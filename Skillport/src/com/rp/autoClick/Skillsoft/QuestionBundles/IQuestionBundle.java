/**
 * 
 */
package com.rp.autoClick.Skillsoft.QuestionBundles;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;

/**
 * @author RMichael
 * 
 */
public interface IQuestionBundle {

	public abstract boolean getAnswers( Region r, int timeout )
			throws FindFailed;

	/**
	 * <b>initialAttempt(<i> Region </i>, <i>timeout</i>)</b><br>
	 * Attempts to answer question if operation is necessary. This should be
	 * called after the question type is already confirmed, before the answer is
	 * checked. The attempt will continue until <i>timeout</i> expires. If the
	 * operation was successful, this will return <i>true</i>. Any errors will
	 * result in <i>false</i>.
	 * 
	 * @param r
	 *            The region to search in
	 * @param timeout
	 *            Time in seconds for attempt.
	 * @return returns if attempt was successful.
	 */
	public abstract IQuestionBundle initialAttempt( Region r, float timeout )
			throws FindFailed;

	public abstract boolean isAnsweredCorrect();

	/**
	 * Returns copy of question's contents for additive purposes.
	 * 
	 * @return
	 */
	public abstract IQuestionBundle newBundle();

	public abstract boolean retryAnswer( Region r, float timeout )
			throws FindFailed;

	/**
	 * <b>testForQuestion(<i> Region </i>)</b><br>
	 * Attempts to detect for signs of the question type in the current region
	 * <i>r</i?.
	 * 
	 * @param r
	 * @return If question is detected.
	 */
	public boolean testForQuestionType( Region r, float timeout );
}
