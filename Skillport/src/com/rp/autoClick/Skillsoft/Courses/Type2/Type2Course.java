package com.rp.autoClick.Skillsoft.Courses.Type2;

import java.util.ArrayList;
import java.util.Iterator;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import com.rp.autoClick.Skillsoft.Courses.Course;
import com.rp.autoClick.Skillsoft.QuestionBundles.DynamicVisualQuestionBundle;

public class Type2Course extends Course {

	public class Test extends com.rp.autoClick.Skillsoft.Courses.Test {

		class MultiplePartQuestion {
			Iterator<Match>	parts	= null;


			public boolean checkForMultiple( final float timeOut )
					throws FindFailed {
				if ( ( this.parts != null ) && this.parts.hasNext( ) )
					return true;

				if ( Test.this.contentArea
						.exists(
								Type2Course.partQuestionButton.similar( 0.7f ),
								timeOut ) != null ) {

					this.parts = Test.this.contentArea
							.findAll( Type2Course.partQuestionButton
									.similar( 0.7f ) );

					return true;
				}

				this.parts = null;
				return false;
			}

			public void gotoNext() throws FindFailed {
				Test.this.contentArea.click( this.parts.next( ) );
			}
		}


		private final Region									contentArea;
		private final Region									windowArea;
		private final ArrayList<DynamicVisualQuestionBundle>	questionTypes;
		private final ArrayList<DynamicVisualQuestionBundle>	questions;
		MultiplePartQuestion									mq;


		public Test( final Region contentArea, final Region windowArea ) {
			this.contentArea = contentArea;
			this.windowArea = windowArea;
			this.questionTypes = new ArrayList<DynamicVisualQuestionBundle>( );
			this.questions = new ArrayList<DynamicVisualQuestionBundle>( );

			// Add question types
			this.questionTypes.add( new T2DragDropQBundle( ) );
			this.questionTypes.add( new T2CheckboxQBundle( ) );
			this.questionTypes.add( new T2RadioQBundle( ) );
			this.mq = new MultiplePartQuestion( );

		}

		@Override
		protected void answerQuestion() {
			// TODO Auto-generated method stub

		}

		protected DynamicVisualQuestionBundle getQuestionFromList() {
			// Scan through questions for matches. Skip over used matches.
			for ( final DynamicVisualQuestionBundle q : this.questions )
				if ( !q.answered && q.isMatchedQuestion( this.contentArea, 0 ) )
					return q;

			return null;

		}

		protected DynamicVisualQuestionBundle getQuestionTypeBundle(
				final float timeOut ) {

			for ( int i = 0; i < 5; i++ )
				for ( final DynamicVisualQuestionBundle qt : this.questionTypes )
					if ( qt.testForQuestionType( this.contentArea, timeOut ) )
						return qt.newBundle( );

			return null;
		}

		@Override
		public void nextQuestion() throws FindFailed {

			// Click [Next Page]
			this.windowArea.click( new Location(
					this.windowArea.getCenter( ).x + 50, this.windowArea.find(
							Type2Course.button_home ).getCenter( ).y ) );

			this.windowArea.wait( 2f );
			if ( this.mq.checkForMultiple( 1f ) ) {
				this.mq.gotoNext( );
				this.windowArea.wait( 2f );
			}
		}

		@Override
		public void runStragity() throws FindFailed {

			// initial run
			boolean initialRun = true; // true on first run
			boolean unique; // if a question is unique
			Match retakeButton = null;

			// Run Test
			do {

				// Reset answered flag.
				for ( final DynamicVisualQuestionBundle q : this.questions )
					q.answered = false;

				this.nextQuestion( );

				unique = false; // Assume reanswering questions each run.
				// Iterate through questions
				while ( ( retakeButton = this.contentArea.exists(
						Type2Course.button_retake, 1f ) ) == null ) {
					// pull question from screen.

					DynamicVisualQuestionBundle q;

					// If initial run or first encounter of question, add to
					// list.
					if ( initialRun
							|| ( ( q = this.getQuestionFromList( ) ) == null ) ) {

						// Get question bundle and add to list of questions.
						q = this.getQuestionTypeBundle( 0f );
						if ( q != null ) {

							this.questions.add( q.setQuestionVisual(
									this.contentArea
											.getScreen( )
											.capture(
													this.contentArea.offset(
															100, 150 ).setSize(
															450, 50 ) )
											.getImage( ) ).initialAttempt(
									this.contentArea, 0 ) );
							unique = true; // Question is unique; thus, test set
											// is
											// unique.
						}
					} else
						// Answer the question promptly.
						if ( q != null ) {
							// Answer question only if this is not a unique set.
							if ( !unique ) {
								q.retryAnswer( this.contentArea, 0 ); // Answer
																		// quuestion
								this.contentArea
										.click( Type2Course.button_done );
							}
							q.answered = true; // Flag to skip over question in
												// scanner.
						}

					// Go to next question
					this.nextQuestion( );

					// System.out.println( "WAITING USER" );
					// new Scanner( System.in ).nextLine( );
					// break;

				}

				retakeButton.click( );
				initialRun = false;
			} while ( unique );

		}
	}



	public static enum WindowButtons {

		MAXIMIZE( -10, 0 ), MINIMIZE( -50, 0 ), CLOSE( 10, 0 );

		public static Pattern getPattern() {
			return new Pattern( "img/CourseType2/windowButtons.PNG" );
		}


		private Location	offset;


		WindowButtons( final int offsetX, final int offsetY ) {
			this.offset = new Location( offsetX, offsetY );
		}

		public Location location() {
			return this.offset;
		}
	}


	final static Pattern		button_done			= new Pattern(
															"img/CourseType2/Test/button_done.PNG" );

	public static final Pattern	partQuestionButton	= new Pattern(
															"img/CourseType2/Test/multipleQuestion.PNG" );

	final static Pattern		button_home			= new Pattern(
															"img/CourseType2/home.PNG" );

	final static Pattern		tableContentsButton	= new Pattern(
															"img/CourseType2/tableContentsButton.PNG" );
	public static final Pattern	button_retake		= new Pattern(
															"img/CourseType2/Test/retake.PNG" );

	public final Test			CourseTest;
	private Region				contentRegion;

	private Region				windowRegion;


	public Type2Course( final Region contentRegion, final Region windowRegion ) {
		this.contentRegion = contentRegion;
		this.windowRegion = windowRegion;
		this.CourseTest = new Test( contentRegion, windowRegion );
	}

	@Override
	protected void onStart( final Region r ) throws FindFailed {

		// Maximize Course
		r.click( r.exists( r.offset( WindowButtons.MAXIMIZE.location( ) ) ) );
		// Wait for 1 second to maximize window
		r.wait( 1f );
		// Set window Region
		this.windowRegion = App.focusedWindow( );
		// Set content Region
		this.contentRegion = this.windowRegion
				.find( Type2Course.tableContentsButton ).offset( -45, -20 )
				.setSize( 1790, 1045 );

	}

	public void runTest() {
		final Test courseTest = new Test( this.contentRegion, this.windowRegion );

	};

	@Override
	public boolean testForCourse( final Region r ) {
		// TODO Auto-generated method stub
		return false;
	}

}
