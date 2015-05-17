package com.rp.autoClick.Skillsoft.Courses;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;

public abstract class Test {

	public Test( ) {
		// TODO Auto-generated constructor stub
	}

	protected abstract void answerQuestion();

	protected abstract void nextQuestion() throws FindFailed;

	public void onStart( final Region r ) {

	}

	public abstract void runStragity() throws FindFailed;

}
