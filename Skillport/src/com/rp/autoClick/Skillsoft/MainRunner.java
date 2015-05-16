package com.rp.autoClick.Skillsoft;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

import org.sikuli.basics.Debug;
import org.sikuli.basics.Settings;
import org.sikuli.natives.Vision;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.rp.autoClick.Skillsoft.QuestionBundles.CheckboxQBundle;
import com.rp.autoClick.Skillsoft.QuestionBundles.IQuestionBundle;
import com.rp.autoClick.Skillsoft.QuestionBundles.RadioQBundle;

class idMatch {
	Match	match;
	String	id;


	public idMatch( final Match match, final String id ) {
		this.match = match;
		this.id = id;
	}
}

public class MainRunner {

	// Load Images
	final static Pattern	button_ok				= new Pattern(
															"img/OkButton.PNG" );
	final static Pattern	button_done				= new Pattern(
															"img/button_done.png" );
	final static Pattern	button_endTest			= new Pattern(
															"img/button_endTest.png" );
	final static Pattern	button_redoTest			= new Pattern(
															"img/button_incompleteFailed.png" );
	final static Pattern	button_next				= new Pattern(
															"img/button_next.png" );
	final static Pattern	select_radio			= new Pattern(
															"img/button_radio.png" );
	final static Pattern	select_checkbox			= new Pattern(
															"img/checkbox.png" );
	final static Pattern	ans_Incorrect			= new Pattern(
															"img/xmark.png" );
	final static Pattern	ans_correct				= new Pattern(
															"img/checkmark.png" );
	final static Pattern	start_test				= new Pattern(
															"img/button_startTeste.PNG" );
	final static Pattern	close_test				= new Pattern(
															"img/button_closeTest.PNG" );
	final static Pattern	exit_yes				= new Pattern(
															"img/button_exit_yes.PNG" );
	final static Pattern	take_test				= new Pattern(
															"img/button_take_test.PNG" );
	final static Pattern	arrow_right				= new Pattern(
															"img/Arrow_right.PNG" );
	final static Pattern	continue_course			= new Pattern(
															"img/button_continueCourse.PNG" );
	final static Pattern	label_student			= new Pattern(
															"img/label_studentName.PNG" );
	final static Pattern	button_home				= new Pattern(
															"img/Home.PNG" );
	final static Pattern	browser_addressBar		= new Pattern(
															"img/browser_addressbar.PNG" );
	final static Pattern	browser_signin			= new Pattern(
															"img/course_signon.PNG" );
	final static Pattern	browser_logo1			= new Pattern(
															"img/course_elearninglogo.PNG" );
	final static Pattern	browser_catalog			= new Pattern(
															"img/course_catalog_button.PNG" );
	final static Pattern	course_complete			= new Pattern(
															"img/course_complete.PNG" );
	final static Pattern	course_computer			= new Pattern(
															"img/course_computer.PNG" );
	final static Pattern	course_launch			= new Pattern(
															"img/course_launch.PNG" );
	final static Pattern	course_leave			= new Pattern(
															"img/test_leave.PNG" );
	final static Pattern	course_loaded_complete	= new Pattern(
															"img/course_completed.PNG" );
	final static Pattern	button_skip				= new Pattern(
															"img/skip_button.PNG" );
	final static Pattern	folder_closed			= new Pattern(
															"img/folder_closed.PNG" );
	final static Pattern	start_play				= new Pattern(
															"img/startPlay.PNG" );
	final static Pattern	drag_drop_slot			= new Pattern(
															"img/CourseType2/Test/DragDrop/slot.PNG" );

	final static Pattern	tableContentsButton		= new Pattern(
															"img/CourseType2/tableContentsButton.PNG" );

	final static Pattern	windowButtons			= new Pattern(
															"img/CourseType2/windowButtons.PNG" );


	public static Match crossPatchMouse( final Region r, int splices,
			final Pattern... pattern ) {
		Debug.log( "Cross-patching Cursor. " + splices + " Splices." );
		final float vSpace = r.getH( ) / splices;
		final float hSpace = r.getW( ) / splices;
		Match out = null;
		try {
			for ( ; splices > 0; splices-- ) {
				// Move to horizontal
				r.mouseMove( new Location( r.getX( ),
						(int) ( r.getY( ) + ( splices * vSpace ) ) ) );
				r.mouseMove( new Location( r.getBottomRight( ).getX( ),
						(int) ( r.getY( ) + ( splices * vSpace ) ) ) );

				// Move Vertical
				r.mouseMove( new Location(
						(int) ( r.getX( ) + ( hSpace * splices ) ), r.getY( ) ) );
				r.mouseMove( new Location(
						(int) ( r.getX( ) + ( hSpace * splices ) ), r
								.getBottomRight( ).getY( ) ) );

				for ( final Pattern p : pattern )
					if ( ( out = r.exists( p,0 ) ) != null ) return out;
			}
		} catch ( final FindFailed e ) {
			e.printStackTrace( );
			return out;
		}
		return out;

	}

	/**
	 * @param args
	 * @throws FindFailed
	 * @throws InterruptedException
	 */
	public static void main( final String[] args ) throws FindFailed,
			InterruptedException {
		// TODO Auto-generated method stub

		final Screen s = new Screen( );

		// final Region r = new Region(825, 459, 1577, 1056);

		App.focusedWindow( ).click(
				MainRunner.windowButtons.targetOffset( -10,0 ) );
		s.wait( 1f );
		final Region r = App.focusedWindow( );

		Settings.OcrTextRead = true;
		Settings.OcrTextRead = true;
		// Settings.Highlight = true;
		Vision.setParameter( "minTargetSize",6 );

		final Region f = r.find( MainRunner.tableContentsButton )
				.offset( -45,-20 ).setSize( 1790,1045 );

		f.highlight( 3 );

		s.wait( 8f );

		// Content Area :

		//@formatter:off
		/**
		 * Content Area: @(190, 250) #(1526, 827)
		 * Question Area: @(215, 322) #(550, 530)
		 * 
		 * Drag & Drop 
		 * 	Options-look: @(780, 322) #(60, 530)
		 * 	Option: #(430, 50)
		 * 	Choices-look: @(1270, 322) #(50, 530)
		 * 	Choice: #(430, 50)
		 * 	Answers-look: @Choice:(-47, 0) #(45, 530)
		 * 
		 * Done Button:
		 * 	*@(ContentArea.W() / 2, ContentArea.H() - 20)
		 */
		//@formatter:on

		final Region l;
		/**
		 * // highlight question area r.offset( 215,322 ).setSize( 550,530
		 * ).highlight( 3 );
		 * 
		 * // highlight Options-Look l = r.offset( 780,322 ).setSize( 60,530
		 * ).highlight( 3 ); System.out.println( "Options-Look [ " + l.text( ) +
		 * " ]" );
		 * 
		 * // highlight Options l = l.setSize( 430,50 ).highlight( 3 );
		 * System.out.println( "Options [ " + l.text( ) + " ]" );
		 * 
		 * // highlight Choices-look l = r.offset( 1270,322 ).setSize( 50,530
		 * ).highlight( 3 );
		 * 
		 * // highlight Choices l.setSize( 430,50 ).highlight( 3 );
		 * 
		 * // highlight Answers-look l = l.offset( -47,0 ).setSize( 45,530
		 * ).highlight( 3 ); System.out.println( "Answers-Look [ " + l.text( ) +
		 * " ]" );
		 **/
		final BufferedImage e_Question;
		final ArrayList<Pattern> e_choices = new ArrayList<Pattern>( );
		final ArrayList<Pattern> e_answers = new ArrayList<Pattern>( );

		// Step 1: Get Question
		e_Question = s.capture(
				f.offset( 85,145 ).setSize( 550,710 ).highlight( 2 ) )
				.getImage( );

		// Step 2: Click [DONE]

		// Step 3: Get Choices
		// 3a: Get list of boxes in Choices-look.
		final Iterator<Match> match;
		match = f.offset( 1245,145 ).setSize( 50,700 ).highlight( 2 )
				.findAll( MainRunner.drag_drop_slot );
		// 3b: Get Choice Regions in Choices
		while ( match.hasNext( ) ) {
			final Match m = match.next( );
			e_choices.add( new Pattern( s.capture( m.setSize( 400,50 )
					.highlight( 1 ) ) ) );
			// );
			// 3c: Get answer to left in Answers-look

			final Match k2 = f
					.offset( 710,145 )
					.setSize( 60,700 )
					.highlight( 1 )
					.find( new Pattern( s.capture( m.offset( -43,0 ).setSize(
							40,40 ) ) ).similar( .9f ) );

			e_answers.add( new Pattern( s.capture( k2.offset( 50,0 ).setSize(
					340,50 ) ) ) );

		}

		System.out.println( "Scan Complete. Waiting for User..." );
		new Scanner( System.in ).nextLine( );
		Thread.sleep( 2000 );

		// Answer question.

		// Step 1: Check question match.
		if ( r.offset( 215,322 ).setSize( 550,530 ).highlight( 1 )
				.exists( new Pattern( e_Question ) ) != null ) {

			for ( int i = 0; i < e_choices.size( ); i++ ) {
				// Step 2: check choices
				;
				r.mouseMove( r.offset( 780,322 ).setSize( 430,530 )
						.find( e_answers.get( i ) ).setSize( 40,40 ) );

				r.mouseDown( Mouse.LEFT );

				r.mouseMove( r.offset( 1270,322 ).setSize( 430,530 )
						.find( e_choices.get( i ) ).setSize( 40,40 ) );

				r.mouseUp( );

			}

		}

		// =========================================

		// Debug.setDebugLevel(1);
		// MainRunner.olderStyleTest(r);
		// MainRunner.rerun(s, r);
		if ( r != null ) return;

		System.out.println( "STARTED" );

		// MainRunner.olderStyleCourse(r);

		// If the eLearning site is offline, start chrome.
		if ( App.focus( "Army eLearning" ) == null ) {
			App.open( "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe" );
			s.click( );
			s.click( s.exists( MainRunner.browser_addressBar,120 ).offset( 100,
					0 ) );
			s.type( "https://usarmy.skillport.com/skillportfe/index.action" );
			s.keyDown( org.sikuli.script.Key.ENTER );
			s.click( s.wait( MainRunner.browser_signin,120 ) );
			s.wait( MainRunner.browser_logo1,120 );
			s.click( s.exists( MainRunner.browser_catalog,120 ) );
		}

		s.hover( new Location( 4, 4 ) );

		Match exist = null;
		// find courses. Search by computer icon.
		exist = s.exists( MainRunner.course_computer,120 );
		if ( exist != null ) {
			// found a course. Get all.
			final Iterator<Match> co = s.findAll( MainRunner.course_computer );
			final ArrayList<Match> courses = new ArrayList<Match>( );
			// Store courses in ArrayList
			while ( co.hasNext( ) )
				courses.add( co.next( ) );

			// Sort courses
			courses.sort( new Comparator<Match>( ) {
				@Override
				public int compare( final Match arg0, final Match arg1 ) {
					// Sort by Y coordinates
					return arg0.getY( ) - arg1.getY( );
				}
			} );

			Debug.log( courses.size( ) + " courses found!" );
			// Debug.setLogFile("C:/Users/RMichael/Desktop/corclass_log.log");

			int complete = 0;
			int skipped = 0;
			int errored = 0;

			// Iterate through courses.
			for ( final Match c : courses ) {

				s.wait( 10f );
				// If not complete, do it.
				if ( App.focus( "Army eLearning" ) == null ) {
					Debug.error( "Courses not found. Was it closed?" );
					break;
				}

				s.hover( c );
				// hover over.
				if ( ( ( exist = c.grow( 0,s.getW( ) - c.getX( ) - c.getW( ),0,
						0 ).exists( MainRunner.course_launch,5 ) ) != null )
						&& ( c.grow( 0,s.getW( ) - c.getX( ) - c.getW( ),0,0 )
								.exists( MainRunner.course_complete,0 ) == null ) ) {
					// Launch course
					s.click( exist );
					if ( MainRunner.runCourse( r ) ) {
						// Course Completed Successfully
						complete++;
						Debug.log( "Course Complete." );
					} else {
						// Error in course.
						Debug.log( "Error in Course. See Log." );
						errored++;
					}
				} else
					if ( c.grow( 0,s.getW( ) - c.getX( ) - c.getW( ),0,0 )
							.exists( MainRunner.course_complete ) != null ) {
						Debug.log( "Course already Completed. Skipping...." );
						skipped++;
					} else {
						Debug.error( "Courses not found. Was it closed?" );
						break;
					}
				Debug.log( "Next Course." );
				Debug.log( "Completed: " + complete + " | Skipped " + skipped
						+ " | Errored: " + errored );
				Debug.log( "Stat: " + ( complete + skipped + errored ) + " / "
						+ courses.size( ) );

			}
			Debug.log( "--------------------------------" );
			Debug.log( "END OF COURSES" );
			Debug.log( "Completed: " + complete + " | Skipped " + skipped
					+ " | Errored: " + errored );
			Debug.log( "Stat: " + ( complete + skipped + errored ) + " / "
					+ courses.size( ) );

		}

		// MainRunner.olderStyleCourse(r);

	}

	public static boolean olderStyleCourse( final Region r ) throws FindFailed {

		// Take Module.

		// Take test.

		return MainRunner.olderStyleModules( r )
				&& MainRunner.olderStyleTest( r );
	}

	public static boolean olderStyleModules( Region r ) throws FindFailed {

		int retry = 0;
		final float accr = .4f;
		final float maxTest = 2;
		int wait = 0;
		// final ImgChangeObeserver observer = new ImgChangeObeserver();
		// Start screen watch
		final int test = 0;
		// start course
		Debug.log( "Start Module" );
		r.wait( 10f );
		r = App.focusedWindow( );
		r.highlight( 2 );
		// Wait for start test.
		if ( r.exists( MainRunner.take_test,120 ) == null ) {
			Debug.error( "Waiting for Module to load timed out. Aborting...." );
			throw new FindFailed( "Waiting for Module timed out. Aborted." );
		}

		if ( r.exists( MainRunner.course_loaded_complete.exact( ),5 ) != null ) {
			Debug.log( "Course is already complete." );
			// close
			r.keyDown( Key.ALT + Key.F4 );
			r.keyUp( );
			r.click( r.exists( MainRunner.course_leave,25 ) );
			return false;
		}
		r.wait( MainRunner.arrow_right,120 );
		Match clickEnd = null;
		while ( ( clickEnd = r.exists( MainRunner.continue_course,0 ) ) == null ) {
			// Check for [skip]
			if ( ( clickEnd = r.exists( MainRunner.button_skip,0 ) ) != null )
				r.click( clickEnd );

			// observer.startCompare(r);
			for ( int i = 0; i < 10; i++ ) {
				clickEnd = r.exists( MainRunner.arrow_right.similar( 0.78f ),5 );

				if ( ( clickEnd == null ) && ( wait < 12 ) ) wait++;
				else
					if ( clickEnd == null ) {
						wait = 0;
						// Go home.
						Debug.error( "Module froze. Go Home and retry. Retry count: + "
								+ ( ++retry ) );
						r.click( clickEnd = r.exists( MainRunner.button_home,
								120 ) );
						if ( ( clickEnd == null ) || ( retry > 2 ) ) {
							Debug.error( "Module Error. Failure on Lesson." );
							return false;
						}
						Debug.log( "Retrying." );
					} else r.click( clickEnd );
				// r.click(MainRunner.arrow_right.similar(0.78f));
				r.mouseMove( r.getCenter( ) );
				// r.mouseMove(r.getX() + (r.getW() / 2),
				// r.getBottomRight().getY());
			}

			// Test if screen changed.
			Debug.log( "Testing for change." );
			// (!observer.isComplete())
			;
			// if (!observer.isChanged()) {
			// // if not final test, move mouse.
			// if (test < maxTest) {
			// Debug.error("Module may have froze. Move Mouse");
			// MainRunner.crossPatchMouse(r, 10,
			// MainRunner.arrow_right.similar(0.78f));
			// test++;
			// } else {
			// // Failed to change within timing.
			// // Go home.
			// Debug.error("Module froze. Go Home and retry. Retry count: + "
			// + (++retry));
			// r.click(clickEnd = r.exists(MainRunner.button_home, 120));
			// if ((clickEnd == null) || (retry > 2)) {
			// Debug.error("Module Error. Failure on Lesson.");
			// return false;
			// }
			// Debug.log("Retrying.");
			// }
			// } else {
			// test = 0;
			// Debug.log("Screen changed.");
			// }

			// r.click();
		}

		r.click( clickEnd );
		Debug.log( "End of Module" );
		return true;
	}

	public static boolean olderStyleTest( final Region r ) throws FindFailed {

		Debug.log( "Preparing to take test." );
		r.click( r.wait( MainRunner.take_test,120 ) );

		// Move mouse to bottom of region. This should expose [Start Test] if
		// it is hidden.
		Debug.log( "Waiting for [Start Test]" );

		final long time = System.currentTimeMillis( );
		while ( r.exists( MainRunner.start_test ) == null ) {
			if ( ( System.currentTimeMillis( ) - time ) > 120000 ) {
				Debug.error( "Unable to start test. Aborting...." );
				throw new FindFailed(
						"Stuck 'Attempting to find [Start Test]. Aborted." );
			}
			r.hover( new Location( r.getCenter( ).x, r.getTopLeft( ).y ) );
			r.hover( new Location( r.getCenter( ).x, r.getBottomLeft( ).y ) );

		}

		//

		// final boolean runTest = true;
		// Start the test. Click [Start Test]
		r.click( r.wait( MainRunner.start_test,120 ) );
		Debug.log( "Starting Test." );

		while ( !MainRunner.olderStyleTestQuestion( r,0 ) )
			;
		r.click( r.wait( MainRunner.close_test,120 ) );
		r.click( r.wait( MainRunner.exit_yes,120 ) );

		return true;
	}

	public static boolean olderStyleTestQuestion( final Region r,
			final int optNum ) throws FindFailed {

		final ArrayList<IQuestionBundle> questions = new ArrayList<IQuestionBundle>( );
		questions.add( new CheckboxQBundle( ) );
		questions.add( new RadioQBundle( ) );

		IQuestionBundle question = null;

		// wait for [done]
		r.wait( MainRunner.button_done,120 );

		if ( r.exists( MainRunner.button_ok,2 ) != null ) {
			r.click( MainRunner.button_ok );
			return true;
		}
		// If there are radio buttons, find those; otherwise, find checkboxes.

		long time = System.currentTimeMillis( );
		do {
			Debug.log( "Checking for type." );
			for ( final IQuestionBundle q : questions )
				if ( ( question = q ).testForQuestionType( r,0 ) ) break;
			if ( r.exists( MainRunner.button_ok,0 ) != null ) {
				r.click( MainRunner.button_ok );
				return true;
			}

		} while ( ( question == null )
				&& ( ( System.currentTimeMillis( ) - time ) <= 120000 ) );

		Debug.log( "Detected " + question.getClass( ).getSimpleName( ) );

		// Attempt question.
		time = System.currentTimeMillis( );
		while ( !question.initialAttempt( r,2 ) )
			if ( ( System.currentTimeMillis( ) - time ) > 120000 ) {
				Debug.error( "Stuck attempting question. Aborting...." );
				throw new FindFailed( "Stuck 'Attempting Question. Aborted." );
			}
		;

		// Click [Done]
		r.click( r.exists( MainRunner.button_done,120 ) );

		// Wait for [Next]
		Debug.log( "Waiting... [Next]" );
		r.wait( MainRunner.button_next,120 );

		Debug.log( "Getting Answers." );
		// Obtain answers.
		question.getAnswers( r,120 );

		// continue to attempt question until it is correct.
		while ( !question.isAnsweredCorrect( ) ) {
			// The question isn't correct. Reattempt.
			r.click( r.wait( MainRunner.button_endTest,120 ) );
			r.click( r.wait( MainRunner.button_ok,120 ) );
			r.wait( MainRunner.label_student,120 );
			Match n;
			if ( ( n = r.exists( MainRunner.button_redoTest,8 ) ) == null ) {
				// Click 8 times at 1/4 seconds with 1/2 seconds between every
				// two.
				for ( int i = 0; i < 4; i++ ) {
					r.keyDown( Key.CTRL + "A" );
					r.keyUp( );
					r.doubleClick( );
				}
				if ( MainRunner.crossPatchMouse( r,15,
						MainRunner.button_redoTest ) != null ) {
					Debug.error( "Can't find [Retake Test] button." );
					throw new FindFailed(
							"Unable to find retake button. Aborted." );
				}
			} else r.click( n );

			r.wait( MainRunner.button_done,120 );
			question.retryAnswer( r,120 );
			r.click( r.exists( MainRunner.button_done,120 ) );
			question.getAnswers( r,120 );
		}
		Debug.log( "Correct Answer." );
		r.click( r.wait( MainRunner.button_next,120 ) );
		Debug.log( "Next Question." );

		// Not end of question.
		return false;
	}

	public static void rerun( final Screen s, final Region r )
			throws FindFailed {
		Match exist = null;
		// find courses. Search by computer icon.
		exist = s.exists( MainRunner.start_play,120 );
		System.out.println( "Here" );
		if ( exist != null ) {
			// found a course. Get all.
			final Iterator<Match> co = s.findAll( MainRunner.start_play );
			final ArrayList<Match> courses = new ArrayList<Match>( );
			// Store courses in ArrayList
			while ( co.hasNext( ) )
				courses.add( co.next( ) );

			// Sort courses
			courses.sort( new Comparator<Match>( ) {
				@Override
				public int compare( final Match arg0, final Match arg1 ) {
					// Sort by Y coordinates
					return arg0.getY( ) - arg1.getY( );
				}
			} );

			for ( final Match m : courses ) {
				try {
					App.focus( "Army eLearning" );
					m.click( );
					final long time = System.currentTimeMillis( );
					while ( ( ( System.currentTimeMillis( ) - time ) <= 120000 )
							&& ( MainRunner.crossPatchMouse( r,15,
									MainRunner.take_test ) == null ) )
						;

					MainRunner.olderStyleTest( r );
					r.wait( 60f );
				} catch ( final Exception e ) {
				}

			}
		}
	}

	public static boolean runCourse( final Region r ) {

		// Close previous course if existing.
		if ( r.exists( MainRunner.course_leave,15 ) != null ) {
			Debug.error( "Closed Previous Course. See Log." );
			r.keyDown( org.sikuli.script.Key.ENTER );
			r.wait( 15f );
		}
		try {
			return MainRunner.olderStyleCourse( r );
		} catch ( final FindFailed e ) {
			// TODO Auto-generated catch block
			e.printStackTrace( );
			Debug.error( "Course crashed!" );
			return false;
		}
	}

}