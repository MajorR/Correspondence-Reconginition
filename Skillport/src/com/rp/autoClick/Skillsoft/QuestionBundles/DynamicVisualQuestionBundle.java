package com.rp.autoClick.Skillsoft.QuestionBundles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

public abstract class DynamicVisualQuestionBundle implements IQuestionBundle {

	private BufferedImage				mQuestion;
	private final ArrayList<Pattern>	mAnswers;
	public boolean						answered	= false;


	public DynamicVisualQuestionBundle( ) {
		// TODO Auto-generated constructor stub
		this.mAnswers = new ArrayList<Pattern>( );
	}

	protected ArrayList<Pattern> getAnswerList() {
		return this.mAnswers;
	}

	@Override
	public boolean getAnswers( final Region r, final int timeout )
			throws FindFailed {
		// TODO Auto-generated method stub
		return false;
	}

	public BufferedImage getQuestionVisual() {
		return this.mQuestion;
	}

	@Override
	public abstract DynamicVisualQuestionBundle initialAttempt( final Region r,
			final float timeout ) throws FindFailed;

	@Override
	public boolean isAnsweredCorrect() {
		// TODO Auto-generated method stub
		return false;
	}

	public abstract boolean isAnswers( final Region r, final int timeout );

	public boolean isMatchedQuestion( final Region r, final int timeout ) {
		return this.isQuestion( r, timeout ) && this.isAnswers( r, timeout );
	}

	public abstract boolean isQuestion( final Region r, final int timeout );

	@Override
	public abstract DynamicVisualQuestionBundle newBundle();

	@Override
	public boolean retryAnswer( final Region r, final float timeout )
			throws FindFailed {
		// TODO Auto-generated method stub
		return false;
	}

	public DynamicVisualQuestionBundle setQuestionVisual(
			final BufferedImage question ) {
		this.mQuestion = question;
		return this;
	}

	@Override
	public abstract boolean testForQuestionType( final Region r,
			final float timeOut );

}
