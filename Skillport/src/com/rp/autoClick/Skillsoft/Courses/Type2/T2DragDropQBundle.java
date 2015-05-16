package com.rp.autoClick.Skillsoft.Courses.Type2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
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

	public T2DragDropQBundle( ) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getAnswers( final Region r, final int timeout )
			throws FindFailed {

		return false;
	}

	@Override
	public boolean initialAttempt( final Region r, final int timeout )
			throws FindFailed {

		// narrow reagin to the choices
		final Region search = r.offset( 1245,148 ).setSize( 430,700 );

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

		// Convert to Pattern Answers
		for ( final Match m : temp )
			this.getAnswerList( ).add(
					new Pattern( r.getScreen( ).capture(
							m.setSize( 400,50 ).highlight( 1 ) ) ) );

		return false;
	}

	@Override
	public boolean isAnsweredCorrect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isQuestion( final Region r, final int timeout ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T2DragDropQBundle newBundle() {
		return new T2DragDropQBundle( );
	}

	@Override
	public boolean retryAnswer( final Region r, final int timeout )
			throws FindFailed {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean testForQuestionType( final Region r, final int timeout ) {
		// Narrow Look Area & search for drag_drop slot

		final boolean out = ( r.offset( T2DragDropQBundle.offsetChoice )
				.setSize( 50,675 )
				.exists( T2DragDropQBundle.drag_drop_slot,timeout ) != null );

		Debug.log( "Testing for Drag & Drop" );
		return out;

	}
}
