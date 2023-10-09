package edu.wm.cs.cs301.memorygame;

// import file reader/writer and input scanner
import java.util.Scanner;

public class MemoryGame {

	private String leaderboard;

	private String player_name;
	private Alphabet alphabet; // greek, cyrillic
	private String difficulty; // easy, medium, hard

	private GameBoard board;
	private Scanner stdin = new Scanner(System.in);

	
	public MemoryGame() {
		// print welcome message
		System.out.println("Welcome to Jakes Memory Game Project!");
		System.out.println("-------------------------------------");

		// player name
		System.out.print("Please register your username: ");
		player_name = stdin.nextLine().trim();
		System.out.print("\n");

		// leaderboard = getLeaderBoard();
		leaderboard = "difficulty | player_name | score  \ndifficulty | player_name | score \ndifficulty | player_name | score"; // faux leaderboard

		// print leaderboard message
		System.out.println("-------------------------------------");
		System.out.println("\tCURRENT LEADERBOARD:");
		System.out.println(leaderboard);
		System.out.println("-------------------------------------");
		System.out.print("\n");


		// get alphabet (greek, cyrillic)
		String alphabet_selection;
		System.out.print("choose a symbol set (greek, cyrillic): ");
		alphabet_selection = stdin.nextLine().trim();
		while ( !alphabet_selection.equals("greek") && !alphabet_selection.equals("cyrillic") ){
			System.out.println("!!!invalid symbol set!!! - please enter 'greek', or 'cyrillic' exactly.");
			System.out.print("\n");
			System.out.print("choose a symbol set (greek, cyrillic): ");
			alphabet_selection = stdin.nextLine().trim();
		}
		System.out.print("\n");
		switch (alphabet_selection) {
			case "greek" -> alphabet = new GreekAlphabet();
			case "cyrillic" -> alphabet = new CyrillicAlphabet();
		}

		// get difficulty, check that difficulty is valid
		System.out.print("choose a difficulty level (easy, medium, hard): ");
		difficulty = stdin.nextLine().trim();
		while ( !difficulty.equals("easy") && !difficulty.equals("medium") && !difficulty.equals("hard") ) {
			System.out.println("!!!invalid difficulty!!! - please enter 'easy', 'medium', or 'hard'.");
			System.out.print("\n");
			System.out.print("choose a difficulty level (easy, medium, hard): ");
			difficulty = stdin.nextLine().trim();
			System.out.print("\n");
		}
		
		// create gameboard
		int rows = 0;
		int columns = 0;
		int num_matches_to_win = 0;
		switch (difficulty) {
			case "easy" -> {
					rows = 4;
					columns = 3;
					num_matches_to_win = 6;
				}
			case "medium" -> {
					rows = 4;
					columns = 7;
					num_matches_to_win = 14;
				}
			case "hard" -> {
					rows = 7;
					columns = 8;
					num_matches_to_win = 28;
				}
		}
		board = new GameBoard(rows, columns, alphabet);

		int turn = 0; // turn number 
		int matches = 0; // number of matches


		while (matches < num_matches_to_win) {
			turn = nextTurn(turn); // returns 0 for quit, or next turn number 

			//if turn = 0, the quit condition, quit
		  if (turn == 0) { 
				System.out.println("You are a failure!");
				return;
			}
		}
		
		int score = turn--;//decrement turn to get score
		//updateLeaderboard(player_name, difficulty, score)
		// print win message
		System.out.println("Congrats! you beat the memory game in " + score + " turns on " + difficulty + " difficulty. \n Thanks for playing! \n");
		board.drawBoard();
	}

	private String getLeaderBoard() {
		//check file
		//if no file, return a response string
		//if file, load data and return string
	}

	private void updateLeaderBoard(String player_name, String difficulty, int score) {
	
		//String[] leaderboard_data = leaderboard.split(" | ", 0);
		
		//check leaderboard data exists, if not create file and add score 
		//if data exists
		//check for corresponding difficulty
		//if same difficulty exists, check that score is lower and update as needed
		//if same difficulty does not exist, add line in proper location
		//overwrite file with new data
	}


	private int nextTurn(int turn) {
		// print turn number 
		System.out.println("Turn #" + turn);
		System.out.println("---------------------------");
		System.out.print("\n");
		// print board
		board.drawBoard();

		// prompt for input, set guess1 - if quit return 0
		int row_guess1 = 0;
		int col_guess1 = 0;
		boolean is_valid_guess = false;
		while (!is_valid_guess) {
			row_guess1 = getRowGuess();
			col_guess1 = getColumnGuess();
			if (row_guess1 == 0 || col_guess1 == 0) { return 0; }
			is_valid_guess = board.isValidGuess(row_guess1, col_guess1);
		}
		board.makeGuess(row_guess1, col_guess1);

		// redraw board
		board.drawBoard();

		// prompt for guess 2 - if quit return 0
		int row_guess2 = 0;
		int col_guess2 = 0;
		is_valid_guess = false;
		while (!is_valid_guess) {
		  row_guess2 = getRowGuess();
			col_guess2 = getColumnGuess();
			if (row_guess2 == 0 || col_guess2 == 0) { return 0; }
			is_valid_guess = board.isValidGuess(row_guess2, col_guess2);
		}
		board.makeGuess(row_guess2, col_guess2);

		// draw board
		board.drawBoard();

		// check match
		boolean is_match = board.isMatch(row_guess1, col_guess1, row_guess2, col_guess2);
		// if match, return turn ++
		if (is_match) { 
			System.out.println("Good guess!");
			return turn++; }
		// if not match, mutate board and return turn++
		System.out.println("No dice!");
		board.resetGuess(row_guess1, col_guess1);
		board.resetGuess(row_guess2, col_guess2);
		return turn++;
	}



	private int getRowGuess() {
		System.out.print("\n");
		System.out.print("select the row of your guess (input 'quit' to exit): ");
		
		int row_limit = board.getRows();
		int row = 0;
		row = stdin.nextLine().trim();
		if (row.equals("quit")) { return 0; }
		while (row < 1 && row > row_limit) {
			System.out.print("\n");
			System.out.println("Invalid row number, please select from 1 to " + row_limit);
			System.out.print("\n");
			System.out.print("select the row of your guess (input 'quit' to exit): ");
			row = stdin.nextLine().trim();
			if (row.equals("quit")) { return 0; }
		}
		return row;
	}


	private int getColumnGuess() {
		System.out.print("\n");
		System.out.print("select the column of your guess (input 'quit' to exit): ");

		int col_limit = board.getColumns();
		int col = 0;
		col = stdin.nextLine().trim();
		if (col.equals("quit")) { return 0; }
		while (col < 1 && col > col_limit) {
			System.out.print("\n");
			System.out.println("Invalid column number, please select from 1 to " + col_limit);
			System.out.print("\n");
			System.out.print("select the column of your guess (input 'quit' to exit): ");
			col = stdin.nextLine().trim();
			if (col.equals("quit")) { return 0; }
		}
		return col;
	}
}
