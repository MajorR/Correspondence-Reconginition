package com.rp.autoClick.Skillsoft.QuestionBundles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

public class CheckboxQBundle implements IQuestionBundle {

	class idElement<T> {
		int	id	= 0;
		T	element;


		public idElement( final T element, final int id ) {
			this.id = id;
			this.element = element;
		}
	}


	final static Pattern						select_checkbox	= new Pattern(
																		"img/checkbox.png" );
	final static Pattern						ans_incorrect	= new Pattern(
																		"img/xmark.png" );

	final static Pattern						ans_correct		= new Pattern(
																		"img/checkmark.png" );
	final private ArrayList<idElement<Match>>	answers			= new ArrayList<idElement<Match>>( ) {
																	@Override
																	public String toString() {
																		String x = "[";
																		for ( final idElement<Match> m : this )
																			x += m.id
																					+ ", ";
																		return x
																				+ "]";
																	}
																};
	private final ArrayList<Match>				checkboxes		= new ArrayList<Match>( );

	private boolean								isCorrect		= false;


	@Override
	public boolean getAnswers( final Region r, final int timeout )
			throws FindFailed {

		Iterator<Match> n = null;
		// Get incorrect marks
		if ( r.exists( CheckboxQBundle.ans_incorrect.similar( 0.9f ) ) != null ) {
			n = r.findAll( CheckboxQBundle.ans_incorrect.similar( 0.9f ) );
			while ( ( n != null ) && n.hasNext( ) ) {
				this.answers.add( new idElement<Match>( n.next( ), 0 ) );
			}
		}
		// Get correct marks
		if ( r.exists( CheckboxQBundle.ans_correct,4 ) != null ) {
			n = r.findAll( CheckboxQBundle.ans_correct );
			while ( ( n != null ) && n.hasNext( ) ) {
				this.answers.add( new idElement<Match>( n.next( ), 1 ) );
			}

			// sort by y values.
			this.answers.sort( new Comparator<idElement<Match>>( ) {
				@Override
				public int compare( final idElement<Match> arg0,
						final idElement<Match> arg1 ) {
					// Sort by Y coordinates
					return arg0.element.getY( ) - arg1.element.getY( );
				}
			} );
			this.checkboxes.sort( new Comparator<Match>( ) {
				@Override
				public int compare( final Match arg0, final Match arg1 ) {
					// Sort by Y coordinates
					return arg0.getY( ) - arg1.getY( );
				}
			} );
		}

		return false;
	}

	@Override
	public boolean initialAttempt( final Region r, final int timeout )
			throws FindFailed {
		if ( r.exists( CheckboxQBundle.select_checkbox ) == null )
			return false;
		Iterator<Match> n;
		n = r.findAll( CheckboxQBundle.select_checkbox );
		// Get checkboxes.
		while ( ( n != null ) && n.hasNext( ) ) {
			this.checkboxes.add( n.next( ) );
		}
		this.checkboxes.sort( new Comparator<Match>( ) {
			@Override
			public int compare( final Match arg0, final Match arg1 ) {
				// Sort by Y coordinates
				return arg0.getY( ) - arg1.getY( );
			}
		} );
		// Don't attempt this question without the answers.
		return true;
	}

	@Override
	public boolean isAnsweredCorrect() {
		return this.isCorrect;
	}

	@Override
	public boolean retryAnswer( final Region r, final int timeout )
			throws FindFailed {
		if ( this.answers.isEmpty( ) ) return false;
		for ( int i = 0; i < this.checkboxes.size( ); i++ )
			if ( this.answers.get( i ).id == 1 )
				r.click( this.checkboxes.get( i ) );
		this.isCorrect = true;
		return true;
	}

	@Override
	public boolean testForQuestionType( final Region r, final int timeout ) {
		final boolean out = ( r
				.exists( CheckboxQBundle.select_checkbox,timeout ) != null );
		Debug.log( "Testing For Checkboxes ... " + out );
		return out;
	}

}
