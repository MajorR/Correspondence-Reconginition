package com.rp.autoClick.Skillsoft;

import org.sikuli.script.App;

public class MainActivity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//TODO Process args input
		
	}

	public static App startBrowser() {
		
		return App.open(Browser.CHROME.url);
	}

	public enum Browser {

		CHROME("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe"), IE(
				""), EDGE(""), FIREFOX("");

		String url;

		Browser(String URL) {
			this.url = URL;
		}

	}

}
