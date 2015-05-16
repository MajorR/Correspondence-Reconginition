package com.rp.autoClick.Skillsoft.Courses.Type2;

import java.util.ArrayList;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import com.rp.autoClick.Skillsoft.Courses.Course;
import com.rp.autoClick.Skillsoft.QuestionBundles.DynamicVisualQuestionBundle;

class Test extends com.rp.autoClick.Skillsoft.Courses.Test {

	private final Region									contentArea;
	private final Region									windowArea;
	private final ArrayList<DynamicVisualQuestionBundle>	questionTypes;


	public Test( final Region contentArea, final Region windowArea ) {
		this.contentArea = contentArea;
		this.windowArea = windowArea;
		this.questionTypes = new ArrayList<DynamicVisualQuestionBundle>( );

		// Add question types
		this.questionTypes.add( new T2DragDropQBundle( ) );

	}

	@Override
	protected void answerQuestion() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void nextQuestion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void runStragity() {

		// initial run
		final boolean initialRun = true; // true on first run
		final boolean unique = true; // if a question is unique

		final DynamicVisualQuestionBundle questionType = null;

		// Test for question Type
		for ( final DynamicVisualQuestionBundle qt : this.questionTypes )
			if ( qt.testForQuestionType( this.contentArea,0 ) ) {
				// questionType = qt.newBundle( );
				break;
			}

	}

}

public class Type2Course extends Course {

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


	final static Pattern	tableContentsButton	= new Pattern(
														"img/CourseType2/tableContentsButton.PNG" );
	private Region			contentRegion;

	private Region			windowRegion;


	public Type2Course( ) {
		// TODO Auto-generated constructor stub
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
				.find( Type2Course.tableContentsButton ).offset( -45,-20 )
				.setSize( 1790,1045 );

	};

	public void runTest() {
		final Test courseTest = new Test( this.contentRegion, this.windowRegion );

	}

	@Override
	public boolean testForCourse( final Region r ) {
		// TODO Auto-generated method stub
		return false;
	}

}
