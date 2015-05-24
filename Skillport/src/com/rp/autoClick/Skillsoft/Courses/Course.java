package com.rp.autoClick.Skillsoft.Courses;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;

import com.rp.autoClick.Skillsoft.INavigatableUI;

public abstract class Course implements INavigatableUI {

	public abstract class Test implements INavigatableUI{

		protected abstract void answerQuestion();

		/**
		 * Called when the question is first established or unrecognized.
		 * 
		 * @return <code>true</code> if operation was successful;
		 *         <code>false</code> otherwise
		 */
		protected abstract boolean initialAttmpt();

		/**
		 * Navigates to the next question.
		 * 
		 * @return <code>true</code> if operation was successful;
		 *         <code>false</code> otherwise
		 */
		protected abstract boolean nextQuestion();

		public void onStart(final Region r) {

		}

		/**
		 * Skips the current question if available.
		 * 
		 * @param r
		 * @return code>true</code> if operation was successful;
		 *         <code>false</code> otherwise
		 */
		public boolean skipQuestion(Region r) {
			return false;
		}

		/**
		 * Runs through test by calling the following methods: <br>
		 * </br>
		 * <ol>
		 * <li> {@link #gotoTest(Region)}</li>
		 * <li> {@link #startTest(Region)}</li>
		 * <li> {@link #runStragity()}</li>
		 * </ol>
		 * The Test will continue until all units are complete. If a section
		 * call should fail (<b>return</b> <code>false</code>), the method will
		 * attempt to continue to call the next step. Should all steps fail, the
		 * method will reattempt starting from the first step. If the last step
		 * succeeds, this method will return <code>true</code>.
		 * 
		 * @param r
		 *            The content region to search for main elements.
		 * @param attempts
		 *            The number of attempts that should be tried.
		 * @see #takeTest(Region)
		 * @see #takeTest(Region, float)
		 * @see #gotoTest(Region)
		 * @see #startTest(Region)
		 * @see #runStragity(Region)
		 * @return <code>true</code> if operation was successful;
		 *         <code>false</code> otherwise
		 */
		public boolean takeTest(Region r, int attempts) {

			// Repeat operation until number of attempts or success
			for (; attempts > 0; attempts--) {
				gotoTest(r); // Attempt to go to test section
				startTest(r); // Attempt to go start test
				// Run through questions. If success, return true
				if (runStragity(r))
					return true;
			}
			// Operation failed to complete within limits
			return false;
		}

		/**
		 * <i>Calls {@link #takeTest(Region, int) takeTest(Region, 3)}</i><br>
		 * <br>
		 * Runs through test by calling the following methods: </br>
		 * 
		 * <ol>
		 * <li> {@link #gotoTest(Region)}</li>
		 * <li> {@link #startTest(Region)}</li>
		 * <li> {@link #runStragity()}</li>
		 * </ol>
		 * The Test will continue until all units are complete. If a section
		 * call should fail (<b>return</b> <code>false</code>), the method will
		 * attempt to continue to call the next step. Should all steps fail, the
		 * method will reattempt starting from the first step for up to three
		 * (3) attempts. If the last step succeeds, this method will return
		 * <code>true</code>.
		 * 
		 * @param r
		 *            The content region to search for main elements.
		 * @see #takeTest(Region, int)
		 * @see #takeTest(Region, float)
		 * @see #gotoTest(Region)
		 * @see #startTest(Region)
		 * @see #runStragity(Region)
		 * @return <code>true</code> if operation was successful;
		 *         <code>false</code> otherwise
		 */
		public boolean takeTest(Region r) {
			return takeTest(r, 3);
		}

		/**
		 * <i>Calls {@link #takeTest(Region, int) takeTest(Region, 1)}</i><br>
		 * <br>
		 * Runs through test by calling the following methods: </br>
		 * 
		 * <ol>
		 * <li> {@link #gotoTest(Region)}</li>
		 * <li> {@link #startTest(Region)}</li>
		 * <li> {@link #runStragity()}</li>
		 * </ol>
		 * The Test will continue until all units are complete. If a section
		 * call should fail (<b>return</b> <code>false</code>), the method will
		 * attempt to continue to call the next step. Should all steps fail, the
		 * method will reattempt starting from the first step until allotted
		 * time as been reached.. If the last step succeeds, this method will
		 * return <code>true</code>.
		 * 
		 * @param r
		 *            The content region to search for main elements.
		 * @param timeOut
		 *            The amount of time allotted for this operation in seconds.
		 * @see #takeTest(Region, int)
		 * @see #takeTest(Region, float)
		 * @see #gotoTest(Region)
		 * @see #startTest(Region)
		 * @see #runStragity(Region)
		 * @return <code>true</code> if operation was successful;
		 *         <code>false</code> otherwise
		 */
		public boolean takeTest(Region r, float timeOut) {
			long startTime = System.currentTimeMillis();

			while (System.currentTimeMillis() - startTime < timeOut * 100)
				if (takeTest(r, 1))
					return true;
			return false;

		}

		/**
		 * Navigates to the test start page of the course. This method does not
		 * actually begin the test and is part of a 3-step process as defined by
		 * {@link #takeTest(Region)}:
		 * <ol>
		 * <li> {@link #gotoTest(Region)}</li>
		 * <li> {@link #startTest(Region)}</li>
		 * <li> {@link #runStragity()}</li>
		 * </ol>
		 * 
		 * @param r
		 *            The content region to search for main elements.
		 * @see #takeTest(Region)
		 * @see #takeTest(Region, float)
		 * @see #takeTest(Region, int)
		 */
		public abstract boolean gotoTest(Region r);

		/**
		 * Starts the test portion. This method does not navigate through the
		 * questions and is part of a 3-step process as defined by
		 * {@link #takeTest(Region)}:
		 * <ol>
		 * <li> {@link #gotoTest(Region)}</li>
		 * <li> {@link #startTest(Region)}</li>
		 * <li> {@link #runStragity()}</li>
		 * </ol>
		 * 
		 * @param r
		 *            The content region to search for main elements
		 * @see #takeTest(Region)
		 * @see #takeTest(Region, float)
		 * @see #takeTest(Region, int)
		 * @return <code>true</code> if operation was successful;
		 *         <code>false</code> otherwise
		 */
		public abstract boolean startTest(Region r);

		public abstract boolean runStragity(Region r);

		public abstract boolean restartTest(Region r);
	}

	private Test courseTest;
	
	public abstract boolean testForCourse(final Region r);

	public abstract boolean closeCourse(final Region r);

	protected abstract void onStart(final Region r) throws FindFailed;

	
	public boolean start(final Region r) {
		try {
			this.onStart(r);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public Test getTest(){
		return this.courseTest;
	}
	
	public void createTest(){
		
	}

}
