package edu.wm.cs.cs301.memorygame;

public class CharacterGamePiece implements GamePiece {
	private final Character symbol;
	private boolean is_visible = false;
	
	public CharacterGamePiece(char s) {
		this.symbol = s;
	}

	public Character getSymbol() {
		return symbol;
	}
	
	public void setVisible(boolean v) {
		this.is_visible = v;
	}
	
	public boolean isVisible() {
		return this.is_visible;
	}
	
	public boolean equals(GamePiece other) {
		return (other.getSymbol().equals(this.symbol));
	}
	
}
