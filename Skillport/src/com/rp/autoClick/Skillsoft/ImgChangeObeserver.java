package com.rp.autoClick.Skillsoft;

import java.util.ArrayList;

import org.sikuli.basics.Debug;
import org.sikuli.script.Finder;
import org.sikuli.script.ObserveEvent;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.ScreenImage;
import org.sikuli.script.SikuliEventObserver;

public class ImgChangeObeserver implements SikuliEventObserver {

	private boolean complete;
	private boolean isChanged;

	private final ArrayList<ScreenImage> screens = new ArrayList<ScreenImage>();

	public ImgChangeObeserver() {
		this.complete = false;
		this.isChanged = false;
		this.screens.add(null);
		this.screens.add(null);
	}

	public void clear() {
		this.screens.set(0, null);
		this.screens.set(1, null);
	}

	public boolean isChanged() {
		return this.isChanged;
	}

	public boolean isComplete() {
		return this.complete;
	}

	public boolean isValid() {
		return (this.screens.get(0) != null) && (this.screens.get(1) != null);
	}

	public boolean startCompare(final Region r) {

		// r.observe();

		// The images are valid and should be compared.
		ImgChangeObeserver.this.complete = false;
		final Thread k = new Thread(new Runnable() {
			// Move last image to initial and the new one to the final.

			@Override
			public void run() {

				System.out.println("In Here");
				// Set last final as inital and new as final.

				// if screens are valid, start compare.
				Debug.log("Observer: Comparing regions in background.");

				// Compare images.
				final Finder f = new Finder(
						ImgChangeObeserver.this.screens.get(0));
				f.findAll(new Pattern(ImgChangeObeserver.this.screens.get(1))
						.similar(0.95f));

				// If final is found in initial, mark as 'Not Changed'
				ImgChangeObeserver.this.isChanged = !f.hasNext();
				// Set complete
				ImgChangeObeserver.this.complete = true;
			}
		});
		// Run comparer in background
		k.run();

		// Return valid compare.
		return true;
	}

	@Override
	public void targetAppeared(final ObserveEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void targetChanged(final ObserveEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void targetVanished(final ObserveEvent arg0) {
		// TODO Auto-generated method stub

	}
}
