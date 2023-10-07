package edu.wm.cs.cs301.memorygame;

public class CyrillicAlphabet implements Alphabet {
	char[] chars = new char[]{'\u0400','\u0410','\u0420','\u0430','\u0440','\u0450','\u0460','\u0470','\u0480','\u0490','\u04a0','\u04b0','\u04c0','\u04e0','\u04F0','\u0411','\u0421','\u0431','\u0441','\u04c1','\u0402','\u0412','\u0422','\u0452','\u0462','\u0472','\u0482','\u04a2'};


	public char[] toCharArray() {
		return this.chars;
	}
}
