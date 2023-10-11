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

	private int matches;

	
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
		System.out.print("\n");
		while ( !alphabet_selection.equals("greek") && !alphabet_selection.equals("cyrillic") ){
			System.out.println("!!!invalid symbol set!!! - please enter 'greek', or 'cyrillic' exactly.");
			System.out.print("\n");
			System.out.print("choose a symbol set (greek, cyrillic): ");
			alphabet_selection = stdin.nextLine().trim();
			System.out.print("\n");
		}
		switch (alphabet_selection) {
			case "greek" -> alphabet = new GreekAlphabet();
			case "cyrillic" -> alphabet = new CyrillicAlphabet();
		}

		// get difficulty, check that difficulty is valid
		System.out.print("choose a difficulty level (easy, medium, hard): ");
		difficulty = stdin.nextLine().trim();
		System.out.print("\n");
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

		int turn = 1; // turn number 
		 matches = 0; // number of matches


		while (true) {
			turn = nextTurn(turn); // returns 0 for quit, or next turn number 
			
			if (matches == num_matches_to_win) { 
				int score = turn - 1;//decrement turn to get score
				//updateLeaderboard(player_name, difficulty, score)
				// print win message
				System.out.println("Congrats " + player_name + "! you beat the memory game in " + score + " turns on " + difficulty + " difficulty. \nThanks for playing! \n");
				board.drawBoard();
				System.out.print("\n");
				return;
				// System.out.print("play again (yes, no): ");
				// String play_again = stdin.nextLine().trim();
			}
			//if turn = 0, the quit condition, quit
		  if (turn == 0) { 
				System.out.print("\n");
				System.out.println("You are a failure!");
				System.out.print("\n");
				board.revealBoard();
				board.drawBoard();
				return;
			}
			// hit enter to continue
			System.out.print("hit enter to continue ");
			String k = stdin.nextLine();
			System.out.print("\n");
		}
		
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
		System.out.println("Turn # " + turn);
		System.out.println("---------------------------");
		System.out.print("\n");

		// print board
		board.drawBoard();

		// prompt for input, set guess1 - if quit return 0
		int[] guess1 = new int[2];
		boolean is_valid_guess = false;
		while (!is_valid_guess) {
			// get guess array
			guess1 = getGuess();
			if (guess1[0] == 0) { return 0; }
			is_valid_guess = board.isValidGuess(guess1[0], guess1[1]);
		}
		board.makeGuess(guess1[0], guess1[1]);

		// redraw board
		board.drawBoard();

		// prompt for guess 2 - if quit return 0
		int[] guess2 = new int[2];
		is_valid_guess = false;
		while (!is_valid_guess) {
			guess2 = getGuess();
			if (guess2[0] == 0) { return 0; }
			is_valid_guess = board.isValidGuess(guess2[0], guess2[1]);
		}
		board.makeGuess(guess2[0], guess2[1]);

		// draw board
		board.drawBoard();

		// check match
		boolean is_match = board.isMatch(guess1[0], guess1[1], guess2[0], guess2[1]);
		// if match, return turn ++
		if (is_match) { 
			System.out.print("\n");
			System.out.println("Good guess!");
			System.out.print("\n");
			matches++;
			return turn + 1; 
		}
		// if not match, mutate board and return turn++
		System.out.print("\n");
		System.out.println("No dice!");
		System.out.print("\n");
		board.resetGuess(guess1[0], guess1[1]);
		board.resetGuess(guess2[0], guess2[1]);
		return turn + 1;
	}



	private int[] getGuess() {
		System.out.print("\n");
		System.out.print("select your guess [R C] (input 'quit' to exit): ");
		
		int row_limit = board.getRows();
		int col_limit = board.getColumns();
		String raw_input = stdin.nextLine().trim();
		if (raw_input.equals("quit")) { return new int[]{0, 0}; }

		
		String[] split_input = raw_input.split(" ");
		int row = Integer.parseInt(split_input[0]);
		int col = Integer.parseInt(split_input[1]);
		



		while (row < 1 || row > row_limit || col < 1 || col > col_limit) {
			System.out.print("\n");
			System.out.println("Invalid guess, please select row from 1 to " + row_limit + "and column from 1 to " + col_limit);
			System.out.print("\n");
			System.out.print("select your guess (input 'quit' to exit): ");
			raw_input = stdin.nextLine().trim();
			if (raw_input.equals("quit")) { return new int[]{0, 0}; }
			split_input = raw_input.split(" ");
			row = Integer.parseInt(split_input[0]);
			col = Integer.parseInt(split_input[1]);
		}
		System.out.print("\n");
		return new int[]{row, col};
	}
}
