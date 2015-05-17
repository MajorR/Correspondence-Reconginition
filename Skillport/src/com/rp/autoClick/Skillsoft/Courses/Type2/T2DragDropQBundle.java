package com.rp.autoClick.Skillsoft.Courses.Type2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import com.rp.autoClick.Skillsoft.QuestionBundles.DynamicVisualQuestionBundle;

public class T2DragDropQBundle extends DynamicVisualQuestionBundle {

	final static Pattern	drag_drop_slot	= new Pattern(
													"img/CourseType2/Test/DragDrop/slot.PNG" );

	final static Location	offsetQuestion	= new Location( 85, 145 );
	final static Location	offsetChoice	= new Location( 710, 148 );
	final static Location	offsetOptions	= new Location( 1245, 145 );


	public static boolean testForQuestion( final Region r, final int timeout ) {
		return false;

	}


	private final ArrayList<Pattern>	e_answers	= new ArrayList<Pattern>( );


	public T2DragDropQBundle( ) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getAnswers( final Region r, final int timeout )
			throws FindFailed {

		return false;
	}

	@Override
	public T2DragDropQBundle initialAttempt( final Region r, final float timeout )
			throws FindFailed {

		// narrow region to the choices
		final Region search = r.offset( 1245, 148 ).setSize( 430, 700 );

		final Iterator<Match> matches = search
				.findAll( T2DragDropQBundle.drag_drop_slot.similar( 0.9f ) );

		// place in list
		final ArrayList<Match> temp = new ArrayList<Match>( );
		while ( matches.hasNext( ) ) {
			temp.add( matches.next( ) );
		}

		// Sort
		temp.sort( new Comparator<Match>( ) {
			@Override
			public int compare( final Match arg0, final Match arg1 ) {
				// Sort by Y coordinates
				return arg0.getY( ) - arg1.getY( );
			}
		} );

		// 3b: Get Choice Regions in Choices
		for ( final Match m : temp ) {
			this.getAnswerList( )
					.add( new Pattern( r.getScreen( ).capture(
							m.setSize( 400, 50 ) ) ) );
		}

		// click [DONE]
		r.click( Type2Course.button_done );
		r.wait( 2f );

		// 3c: Get answer to left in Answers-look

		for ( final Match m : temp ) {
			final Match k2 = r
					.offset( 710, 145 )
					.setSize( 60, 700 )

					.find( new Pattern( r.getScreen( ).capture(
							m.offset( -43, 0 ).setSize( 40, 40 ) ) )
							.similar( .9f ) );

			this.e_answers.add( new Pattern( r.getScreen( ).capture(
					k2.offset( 50, 0 ).setSize( 340, 50 ) ) ) );

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
		final boolean j = r.offset( T2DragDropQBundle.offsetOptions )
				.setSize( 500, 700 ).exists( this.getAnswerList( ).get( 0 ) ) != null;
		System.out.println( "IsAnswer = " + j );
		return j;

	}

	@Override
	public boolean isQuestion( final Region r, final int timeout ) {
		// TODO Auto-generated method stub
		final boolean j = r.offset( T2DragDropQBundle.offsetQuestion )
				.setSize( 500, 700 )
				.exists( new Pattern( this.getQuestionVisual( ) ) ) != null;
		System.out.println( "IsQuestion = " + j );
		return j;
	}

	@Override
	public T2DragDropQBundle newBundle() {
		return new T2DragDropQBundle( );
	}

	@Override
	public boolean retryAnswer( final Region r, final float timeout )
			throws FindFailed {
		// TODO Auto-generated method stub

		// Answer question.

		for ( int i = 0; i < this.getAnswerList( ).size( ); i++ ) {
			// Step 2: check choices

			r.mouseMove( r.offset( 710, 145 ).setSize( 500, 700 )
					.find( this.e_answers.get( i ) ).offset( -50, 0 )
					.setSize( 40, 40 ) );

			r.mouseDown( Mouse.LEFT );

			r.mouseMove( ( r.offset( 1245, 145 ).setSize( 430, 700 )
					.find( this.getAnswerList( ).get( i ) ).offset( -1, 0 )
					.setSize( 40, 40 ) ) );

			r.mouseUp( );

		}

		return false;
	}

	@Override
	public boolean testForQuestionType( final Region r, final float timeout ) {
		// Narrow Look Area & search for drag_drop slot

		final boolean out = ( r.offset( T2DragDropQBundle.offsetOptions )
				.setSize( 50, 700 )
				.exists( T2DragDropQBundle.drag_drop_slot, timeout ) != null );

		Debug.log( "Testing for Drag & Drop" );
		return out;

	}
}
