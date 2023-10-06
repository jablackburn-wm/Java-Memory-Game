package edu.wm.cs.cs301.memorygame;

// import file reader/writer and input scanner
import java.util.Scanner;

public class MemoryGame {

	private String leaderboard;

	private String player_name;
	private String difficulty; // easy, medium, hard

	//private GameBoard board;

	
	public MemoryGame() {
		// print welcome message
		System.out.println("Welcome to Jakes Memory Game Project!");
		System.out.println("-------------------------------------");

		// player name
		Scanner stdin = new Scanner(System.in);
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

		// get difficulty, check that difficulty is valid
		System.out.print("choose a difficulty level (easy, medium, hard): ");
		difficulty = stdin.nextLine().trim();
		while ( !difficulty.equals("easy") && !difficulty.equals("medium") && !difficulty.equals("hard") ) {
			System.out.println("!!!invalid difficulty!!! - please enter 'easy', 'medium', or 'hard'.");
			System.out.print("\n");
			System.out.print("choose a difficulty level (easy, medium, hard): ");
			difficulty = stdin.nextLine().trim();
		}
		
		// create gameboard
		// board = new GameBoard(difficulty);

		int turn = 0; // turn number 
		int matches = 0; // number of matches

		int num_matches_to_win;
		switch (difficulty) {
			case "easy" -> num_matches_to_win = 6;
			case "medium" -> num_matches_to_win = 14;
			case "hard" -> num_matches_to_win = 28;
		}

		//while matches < num_matches_to_win {
		//	turn = nextTurn(); // returns 0 for quit, next turn, or the final score if win condition
		// if turn = 0, the quit condition, break out of loop
		//}
		// check game was not quit
		int score = turn--;//decrement turn to get score
		//updateLeaderboard(player_name, difficulty, score)
		// print win message
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


	private int nextTurn() {
		// print turn number 
		// print board
		// prompt for input, set guess1 - if quit return 0
		// mutate board
		// redraw board
		// prompt for guess 2 - if quit return 0
		// mutate board
		// draw board
		// check match
		// if match, return turn ++
		// if not match, mutate board and return turn++
	}
}
