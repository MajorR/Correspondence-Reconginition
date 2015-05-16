package com.rp.autoClick.Skillsoft.Courses;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;

public abstract class Course {

	public Course( ) {
		// TODO Auto-generated constructor stub
	}

	protected void onStart( final Region r ) throws FindFailed {

	}

	public abstract boolean testForCourse( final Region r );

}
