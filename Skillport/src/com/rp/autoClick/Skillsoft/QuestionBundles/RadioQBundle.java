package com.rp.autoClick.Skillsoft.QuestionBundles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

public class RadioQBundle implements IQuestionBundle {

	class idElement<T> {
		int id = 0;
		T element;

		public idElement(final T element, final int id) {
			this.id = id;
			this.element = element;
		}
	}

	final static Pattern select_radio = new Pattern("img/button_radio.png");
	final static Pattern ans_incorrect = new Pattern("img/xmark.png");

	final static Pattern ans_correct = new Pattern("img/checkmark.png");
	final private ArrayList<idElement<Match>> answers = new ArrayList<idElement<Match>>() {
		@Override
		public String toString() {
			String x = "[";
			for (final idElement<Match> m : this)
				x += m.id + ", ";
			return x + "]";
		}
	};
	private final ArrayList<Match> radio_buttons = new ArrayList<Match>();

	private boolean isCorrect = false;
	private int option = 0;
	private int rightAnswers = 0;
	private int wrongAnswers = 0;

	@Override
	public boolean getAnswers(final Region r, final int timeout)
			throws FindFailed {
		// Get the checkboxes
		Iterator<Match> n;

		// Get incorrect marks
		if (r.exists(RadioQBundle.ans_incorrect.similar(0.9f), 5) != null) {
			n = r.findAll(RadioQBundle.ans_incorrect.similar(0.9f));
			while (n.hasNext()) {
				this.answers.add(new idElement<Match>(n.next(), 0));
				this.wrongAnswers++;
			}
		}
		// Get correct marks
		if (r.exists(RadioQBundle.ans_correct, 5) != null) {
			n = r.findAll(RadioQBundle.ans_correct);
			while (n.hasNext()) {
				this.answers.add(new idElement<Match>(n.next(), 1));
				this.rightAnswers++;
			}
		}
		// sort by y values.
		this.answers.sort(new Comparator<idElement<Match>>() {
			@Override
			public int compare(final idElement<Match> arg0,
					final idElement<Match> arg1) {
				// Sort by Y coordinates
				return arg0.element.getY() - arg1.element.getY();
			}
		});

		Debug.log("ANS: " + this.rightAnswers + "/" + this.wrongAnswers + " : "
				+ this.answers);

		if (this.answers.get(this.option).id == 1)
			this.isCorrect = true;
		else if (this.rightAnswers == 0)
			this.option++;
		else
			for (; this.option < this.answers.size(); this.option++)
				if (this.answers.get(this.option).id == 1)
					break;
		return true;

	}

	@Override
	public boolean initialAttempt(final Region r, final int timeout)
			throws FindFailed {
		if (r.exists(RadioQBundle.select_radio) == null)
			return false;
		Iterator<Match> n;
		n = r.findAll(RadioQBundle.select_radio);
		// Get checkboxes.
		while ((n != null) && n.hasNext()) {
			this.radio_buttons.add(n.next());
		}
		this.radio_buttons.sort(new Comparator<Match>() {
			@Override
			public int compare(final Match arg0, final Match arg1) {
				// Sort by Y coordinates
				return arg0.getY() - arg1.getY();
			}
		});
		r.click(this.radio_buttons.get(this.option));
		return true;
	}

	@Override
	public boolean isAnsweredCorrect() {
		return this.isCorrect;
	}

	@Override
	public boolean retryAnswer(final Region r, final int timeout)
			throws FindFailed {
		if (this.answers.isEmpty())
			return false;
		r.click(this.radio_buttons.get(this.option));
		// TODO Fix.
		this.isCorrect = true;
		return true;
	}

	@Override
	public boolean testForQuestionType(final Region r, final int timeout) {
		final boolean out = (r.exists(RadioQBundle.select_radio, timeout) != null);
		Debug.log("Testing For Radio Buttons ... " + out);
		return out;
	}

}
