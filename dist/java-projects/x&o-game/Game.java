/* Tic-tac-Toe game 
 * 2 Players only!.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Game {
	//two import statements for player1 and player2.
	 static ArrayList<Integer>player1Positions=new ArrayList<Integer>();
	 static ArrayList<Integer>player2Positions=new ArrayList<Integer>();
	
	public static void main(String[]args) {
		//Creating the game board and printing the game board
		char [][] gameBoard= {{' ','|', ' ', '|',' '},
				{'-','+', '-', '+','-'},
				{' ','|', ' ', '|',' '},
				{'-','+', '-', '+','-'},
				{' ','|', ' ', '|',' '}};
		printGameBoard(gameBoard);
		/*Create the game loop , get input from user1 using scanner on a position from 1-9,
		 * we store it and then check if their's a winner. Next user2 moves we store it and check for a winner,
		 * with help from printGameBoard method,placePiece method and the checkWinner method.
		 */
		
		while(checkWinner()==false) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Player1(X): enter your placement number from 1-9!");
			int playerPos1 = sc.nextInt();
			/*Here we make sure that user1 input is between 1-9 otherwise keep 
			  prompting user to enter the correct input range.
			*/
			while(playerPos1>=10||playerPos1<=0) {
				System.out.println("Player1 please enter a number between 1-9!!");
				playerPos1 = sc.nextInt();
			}
			//Check if the inputed position entered already exists or not for player 1.
			while(player2Positions.contains(playerPos1)||player1Positions.contains(playerPos1)){
				System.out.println("Position "+playerPos1+" is Taken!");
				playerPos1=sc.nextInt();
				}
			placePiece(gameBoard,playerPos1,"player1");
			printGameBoard(gameBoard);
			//if we find winner stop the game and print result ,else continue.
			if(checkWinner()==false)
				System.out.println("Player2(O): enter your placement number from 1-9!");
				int playerPos2 = sc.nextInt();	
				/*Here we make sure that user2 input is between 1-9 otherwise keep 
				  prompting user to enter the correct input range.
				*/
				while(playerPos2>9||playerPos2<1) {
					System.out.println("Player2 please enter a number between 1-9!!");
					playerPos2 = sc.nextInt();
					}
				//Check if the inputed position entered already exists or not for player 2.
				while(player1Positions.contains(playerPos2)||player2Positions.contains(playerPos2)){
					System.out.println("Position "+playerPos2+" is Taken!");
					playerPos2=sc.nextInt();
				}
				placePiece(gameBoard,playerPos2,"player2");
				printGameBoard(gameBoard);
			//sc.close();
		}
		
	}
	public static void printGameBoard(char[][]gameBoard){
		/*Print the board each time a move is made and keeps updating the new board
		 *until the final move or result is reached.
		*/
		for(char[] row:gameBoard) {
			for(char col:row) {
				System.out.print(col);
			}
			System.out.println();
		}
	}
	public static void placePiece(char[][]gameBoard,int pos,String user) {
		/* when user1 or user2 input (position) equals to the case number execute the case
		 * command.
		 */
		char symbol = ' ';
		if(user.equals("player1")) {
			symbol='X';						//assign player 1 to the symbol X.
			player1Positions.add(pos);
		}else if(user.equals("player2")) {
			symbol='O';						//assign player 2 to the symbol O.
			player2Positions.add(pos);
		}
		//These are the 9 empty spaces that are going to be matched and filled.
		switch(pos) {
		case 1:
			gameBoard[0][0]=symbol;
			break;
		case 2:
			gameBoard[0][2]=symbol;
			break;
		case 3:
			gameBoard[0][4]=symbol;
			break;
		case 4:
			gameBoard[2][0]=symbol;
			break;
		case 5:
			gameBoard[2][2]=symbol;
			break;
		case 6:
			gameBoard[2][4]=symbol;
			break;
		case 7:
			gameBoard[4][0]=symbol;
			break;
		case 8:
			gameBoard[4][2]=symbol;
			break;	
		case 9:
			gameBoard[4][4]=symbol;
			break;	
		default:
			break;
		}
	}
	public static boolean checkWinner() {
		/*As we check for a winner we stored all the win conditions and we check if,
		 * any of the stored positions has it and we keep checking and placing till
		 * we get a winner and we print.
		 */
		
		//The 9 different win conditions with their position
		List topRow=Arrays.asList(1, 2, 3);
		List midRow=Arrays.asList(4, 5, 6);
		List botRow=Arrays.asList(7, 8, 9);
		List leftCol=Arrays.asList(1, 4, 7);
		List midCol=Arrays.asList(2, 5, 8);
		List rightCol=Arrays.asList(3, 6, 9);
		List cross1=Arrays.asList(1, 5, 9);
		List cross2=Arrays.asList(7, 5, 3);
		
		//The link that links to arrayList above^
		List<List>winning=new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		//3 different ways the game can end, either player 1 or 2 wins or a draw.
		for(List l:winning) {
			if(player1Positions.containsAll(l)) {
				System.out.println("Player1 Wins! :)");
				return true;
			}else if(player2Positions.containsAll(l)) {
				System.out.println("Player2 Wins! :)");
				return true;
			}else if(player1Positions.size()+player2Positions.size()==9) {
				System.out.println("It's a Draw! ;/");
				return true;
			}
		}
		return false;
	}
}
