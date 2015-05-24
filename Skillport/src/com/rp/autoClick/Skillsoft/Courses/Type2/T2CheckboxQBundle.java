package com.rp.autoClick.Skillsoft.Courses.Type2;

import java.util.Iterator;

import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import com.rp.autoClick.Skillsoft.QuestionBundles.DynamicVisualQuestionBundle;

public class T2CheckboxQBundle extends DynamicVisualQuestionBundle {

	final static Pattern	checkBox		= new Pattern(
													"img/CourseType2/Test/Checkbox/checkbox.PNG" );
	final static Pattern	answer_mark		= new Pattern(
													"img/CourseType2/Test/Checkbox/answer.PNG" );

	final static Location	offsetOptions	= new Location( 935, 145 );
	final static Location	offsetQuestion	= new Location( 85, 145 );


	public T2CheckboxQBundle( ) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getAnswers( final Region r, final int timeout )
	
	
			throws FindFailed {

		return false;
	}

	@Override
	public T2CheckboxQBundle initialAttempt( final Region r, final float timeout )
			throws FindFailed {

		// click [DONE]
		r.click( Type2Course.button_done );
		r.wait( 1f );

		// narrow region to the choices
		final Region search = r.offset( T2CheckboxQBundle.offsetOptions )
				.offset( -40, 0 ).setSize( 430, 700 );

		final Iterator<Match> matches = search
				.findAll( T2CheckboxQBundle.answer_mark.similar( 0.9f ) );

		// 3c: Get answer to left in Answers-look

		while ( matches.hasNext( ) ) {

			this.getAnswerList( )
					.add( new Pattern( r.getScreen( ).capture(
							matches.next( ).offset( 80, 0 ).setSize( 430, 50 ) ) ) );

		}

		return this;
	}

	@Override
	public boolean isAnsweredCorrect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAnswers( final Region r, final int timeout ) {
		final boolean j = r.offset( T2CheckboxQBundle.offsetOptions )
				.setSize( 500, 700 ).exists( this.getAnswerList( ).get( 0 ) ) != null;
		System.out.println( "IsAnswer = " + j );
		return j;

	}

	@Override
	public boolean isQuestion( final Region r, final int timeout ) {
		// TODO Auto-generated method stub
		final boolean j = r.offset( T2CheckboxQBundle.offsetQuestion )
				.setSize( 500, 700 )
				.exists( new Pattern( this.getQuestionVisual( ) ) ) != null;
		System.out.println( "IsQuestion = " + j );
		return j;
	}

	@Override
	public T2CheckboxQBundle newBundle() {
		return new T2CheckboxQBundle( );
	}

	@Override
	public boolean retryAnswer( final Region r, final float timeout )
			throws FindFailed {
		// TODO Auto-generated method stub

		// Answer question.

		for ( final Pattern m : this.getAnswerList( ) ) {
			// Step 2: check choices

			r.click( r.offset( T2CheckboxQBundle.offsetOptions )
					.setSize( 500, 700 ).find( m ).offset( -50, 0 )
					.setSize( 40, 40 ) );

		}

		return true;
	}

	@Override
	public boolean testForQuestionType( final Region r, final float timeout ) {
		// Narrow Look Area & search for drag_drop slot

		final boolean out = ( r.offset( T2CheckboxQBundle.offsetOptions )
				.setSize( 50, 675 )
				.exists( T2CheckboxQBundle.checkBox, timeout ) != null );

		Debug.log( "Testing for Drag & Drop" );
		return out;

	}
}
