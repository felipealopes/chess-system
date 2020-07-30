package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		ChessMatch chessmatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		while (!chessmatch.getcheck()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessmatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessmatch.possibleMoves(source);
				UI.clearScreen();
				UI.printboard(chessmatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessmatch.performChessMove(source, target);
				
				if(capturedPiece!=null) {
					captured.add(capturedPiece);
				}
				if (chessmatch.getPromoted()!=null) {
					System.out.print("Enter piece for promotion (B/N/R/Q)");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B")&&!type.equals("N") && !type.equals("R") & !type.equals("Q") ) {
						System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q)");
						type = sc.nextLine().toUpperCase();
						
					}
					chessmatch.replacePrometedPiece(type);
				}
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.hasNextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.hasNextLine();
			}
			
			
		}
		UI.clearScreen();
		UI.printMatch(chessmatch, captured);

	}

}
