package edu.wm.cs.cs301.memorygame;

public class GreekAlphabet implements Alphabet {
	char[] chars = new char[]{'\u03b3','\u03b4','\u03b5','\u03b6','\u03b7','\u03b8','\u03b9','\u03ba','\u03bb','\u03bc','\u03bd','\u03be','\u03bf','\u03c0','\u03c1','\u03c2','\u03c3','\u03c4','\u03c5','\u03c6','\u03c7','\u03c8','\u03c9','\u0393','\u0394','\u0395','\u0396','\u0397'};


	public char[] toCharArray() {
		return this.chars;
	}
}
