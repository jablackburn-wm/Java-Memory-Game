package edu.wm.cs.cs301.memorygame;

import java.util.Collections;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
	private final GamePiece[][] board;
	private final int rows;
	private final int cols;
	
	public GameBoard(int rows, int cols, Alphabet a) {
		this.rows = rows;
		this.cols = cols;

		int num_chars = (rows * cols) / 2;

		char[] chars = Arrays.copyOfRange(a.toCharArray(), 0, num_chars);
		char[] randomized_chars = randomizeChars(chars);

		board = populateBoard(rows, columns, randomized_chars);
	}

	public void drawBoard() {
		
	}
	
	private GamePiece[][] populateBoard(int rows, int columns, char[] randomized_chars) {
		GamePiece[][] board = new GamePiece[rows][];
		int random_char_index = 0;
		int row_index = 0;
		int column_index = 0;
		while (row_index < rows) {
			board[row_index] = new GamePiece[columns];
			while (column_index < columns) {
				board[row_index][column_index] = new GamePiece(randomized_chars[random_char_index]);
				random_char_index++;
				column_index++;
			}
			column_index = 0;
		}
		
	}

  private char[] randomizeChars(char[] chars) {
		int chars_len = chars.length;
		char[] doubled_chars = new char[chars_len * 2];
		int i = 0;
		for (char c : chars) {
			doubled_chars[i] = c;
			doubled_chars[i + 1] = c;
			i = i + 2;
		}
		List<char> char_list = Arrays.asList(doubled_chars);
		Collections.shuffle(doubled_chars);
		char_list.toArray(doubled_chars);
		return doubled_chars;
	}
}
