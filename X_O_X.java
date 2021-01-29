package xox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class X_O_X 
{
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> computerPositions = new ArrayList<Integer>();

	public static void main(String[] args) 
	{
		
		char[][] board = { 
				{' ','|',' ','|',' '}, 
				{'-','+','-','+','-'}, 
				{' ','|',' ','|',' '}, 
				{'-','+','-','+','-'}, 
				{' ','|',' ','|',' '} };
		
		printBoard(board);
		
		
		
		while(true) 
		{
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Hamle yapmak istediginiz alani secin (1-9) : ");
			int playerPosition = scan.nextInt();
			
			while(playerPositions.contains(playerPosition) || computerPositions.contains(playerPositions)) {
				System.out.println("Bu alan dolu! Bos bir alan secin.s");
				playerPosition = scan.nextInt();
			}
				
			placeXO(board, playerPosition, "player");
			String result = winner();
			if(result.length() > 0) 
			{
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int computerPosition = rand.nextInt(9) + 1;
			while(playerPositions.contains(computerPositions) || computerPositions.contains(computerPositions)) {
				computerPosition = rand.nextInt(9) + 1;
			}
			placeXO(board, computerPosition, "computer");
			
			printBoard(board);	
			result = winner();
			if(result.length() > 0) 
			{
				System.out.println(result);
				break;
			}
		}
	}
	
	
	//prints game board
	public static void printBoard(char[][] board) 
	{
		for(char[] row : board) 
		{
			for(char c : row) 
			{
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	//puts X or O in scanned place
	public static void placeXO(char[][] board, int alan, String user) 
	{
		char symbol = ' ';
	
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(alan);
		}
		else if(user.equals("computer")) {
			symbol = 'O';
			computerPositions.add(alan);
		}
		else 
			System.out.println("error");
		
		switch(alan) {
			case 1:
				board[0][0] = symbol;
				break;
			case 2:
				board[0][2] = symbol;
				break;
			case 3:
				board[0][4] = symbol;
				break;
			case 4:
				board[2][0] = symbol;
				break;
			case 5:
				board[2][2] = symbol;
				break;
			case 6:
				board[2][4] = symbol;
				break;
			case 7:
				board[4][0] = symbol;
				break;
			case 8:
				board[4][2] = symbol;
				break;
			case 9:
				board[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	public static String winner() 
	{
		List toprow = Arrays.asList(1,2,3);
		List midrow = Arrays.asList(4,5,6);
		List botrow = Arrays.asList(7,8,9);
		List leftcol = Arrays.asList(1,4,7);
		List midcol = Arrays.asList(2,5,8);
		List rigthcol = Arrays.asList(3,6,9);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(3,5,7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(toprow);
		winning.add(midrow);
		winning.add(botrow);
		winning.add(leftcol);
		winning.add(midcol);
		winning.add(rigthcol);
		winning.add(cross1);
		winning.add(cross2);
		
		for(List l : winning) 
		{
			if(playerPositions.containsAll(l))
				return "You WON!";
			else if(computerPositions.containsAll(l))
				return "Computer wins.";
			else if(playerPositions.size() + computerPositions.size() == 9)
				return"GAME OVER";
		}
		
		return "";
	}
}
