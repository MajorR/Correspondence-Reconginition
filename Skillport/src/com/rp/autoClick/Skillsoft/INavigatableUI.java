package com.rp.autoClick.Skillsoft;

/**
 * Allows the object to understand the location of an unsynchronized UI so that
 * the program can remain in-sync with the UI.
 * 
 * @author R Michael Olsen
 *
 */
public interface INavigatableUI {

	/**
	 * {@value}
	 */
	public static final int hello = 0;

	/**
	 * Determines where and what process the UI is currently in. The UI is
	 * separated from the actual program; thus, may fall out of sync. This
	 * method what set-id the UI is currently associated with. All IDs are
	 * non-zero numbers. A returning ID of <b>0</b> indicates the UI is not
	 * associated with this process set.
	 * 
	 * @return <code>0</code> if not associated; otherwise, a non-zero number
	 *         indicating process ID.
	 */
	public abstract int getNavigationalStep();

	/**
	 * Resumes the process set in accordance with its process id state.
	 * 
	 * @param id
	 *            The process id as determined by {@link #getNavigationalStep()}
	 * @return <code>true</code> if the process successfully resumed at process
	 *         id otherwise, <code>false</code>
	 * @see INavigatableUI#getNavigationalStep()
	 */
	public abstract boolean gotoNavigationalStep(int id);
}
