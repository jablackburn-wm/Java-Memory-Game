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
		GamePiece[] randomized_chars = randomizeChars(chars);

		board = populateBoard(rows, cols, randomized_chars);
	}

	public void makeGuess(int row, int col) {
		GamePiece guess = board[row - 1][col - 1];	
		guess.setVisible(true);
	}

	public boolean isValidGuess(int row, int col) {
		GamePiece guess = board[row - 1][col - 1];
		if (guess.isVisible()) {
			System.out.println("Invalid Guess - this character is already visible!");
			return false;
		}	
		return true;
	}

	public boolean isMatch(int row1, int col1, int row2, int col2) {
		GamePiece guess1 = board[row1 - 1][col1 - 1];
		GamePiece guess2 = board[row2 - 1][col2 - 1];
		if (guess1.getSymbol().equals(guess2.getSymbol())) { return true; }
		return false;	
	}

	public void resetGuess(int row, int col) {
		GamePiece guess = board[row - 1][col - 1];
		guess.setVisible(false);
	}	

	public void drawBoard() {
		//print column list 
		System.out.print("# | ");
		for (int i = 1; i <= cols; i++) {
			System.out.print(i + " | ");
		}
		System.out.print("\n");

		//print delimiting lines
		System.out.print("---");
		for (int i = 1; i <= cols; i++) {
			System.out.print("----");
		}
		System.out.print("\n");

		//print row 
		int row_number = 1;
		for (GamePiece[] row : board) {
			System.out.print(row_number + " | ");
			for (GamePiece c : row) {
				if (c.isVisible()) {
					System.out.print(c.getSymbol() + " | ");
					continue;
				}
				System.out.print("  | ");
			}
			System.out.print("\n");
			//delimiting lines
			System.out.print("---");
			for (int i = 1; i <= cols; i++) {
				System.out.print("----");
			}
		System.out.print("\n");
			row_number++;
		}
		
	}
	
	private GamePiece[][] populateBoard(int rows, int columns, GamePiece[] randomized_chars) {
		GamePiece[][] board = new GamePiece[rows][];
		int random_char_index = 0;
		int row_index = 0;
		int column_index = 0;
		while (row_index < rows) {
			board[row_index] = new GamePiece[columns];
			while (column_index < columns) {
				board[row_index][column_index] = randomized_chars[random_char_index];
				random_char_index++;
				column_index++;
			}
			row_index++;
			column_index = 0;
		}
		return board;
		
	}

  private GamePiece[] randomizeChars(char[] chars) {
		int chars_len = chars.length;
		GamePiece[] doubled_chars = new GamePiece[chars_len * 2];
		int i = 0;
		for (char c : chars) {
			doubled_chars[i] = new CharacterGamePiece(c);
			doubled_chars[i + 1] = new CharacterGamePiece(c);
			i = i + 2;
		}
		List<GamePiece> char_list = Arrays.asList(doubled_chars);
		Collections.shuffle(char_list);
		char_list.toArray(doubled_chars);
		return doubled_chars;
	}

	public void revealBoard() {
		for (GamePiece[] row : board) {
			for (GamePiece item : row) {
				item.setVisible(true);
			}
		}
	}
		

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return cols;
	}
}
