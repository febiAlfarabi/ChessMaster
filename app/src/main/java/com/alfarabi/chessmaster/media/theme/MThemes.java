package com.alfarabi.chessmaster.media.theme;

import java.util.HashMap;

import com.alfarabi.chessmaster.R;

public class MThemes {
	
	public static final char DEFAULT_THEME = 'D';
	private HashMap<Character, Design> themes = new HashMap<Character, Design>();
	private char currentTheme = DEFAULT_THEME;
	
	public MThemes() {
		// TODO Auto-generated constructor stub
		Design design = new Design();
		design.setBgWalpaper(R.drawable.bg_wall);
		design.setBgPlayButton(R.drawable.bg_play);
		design.setBgUndoButton(R.drawable.bg_undo_button);
		design.setBgUndoButtonSelected(R.drawable.bg_undo_button_selected);
		design.setBgPauseButton(R.drawable.bg_pause_button);
		design.setBgPauseButtonSelected(R.drawable.bg_pause_button_selected);
		design.setBgPlayerIcon(R.drawable.icon_android);
		themes.put(DEFAULT_THEME, design);
		
	}

	public HashMap<Character, Design> getThemes() {
		return themes;
	}

	public void setThemes(HashMap<Character, Design> themes) {
		this.themes = themes;
	}

	public char getCurrentTheme() {
		return currentTheme;
	}

	public void setCurrentTheme(char currentTheme) {
		this.currentTheme = currentTheme;
	}
	
}
