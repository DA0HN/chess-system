package app;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        ChessMatch match = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
        while(!match.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(match, captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = match.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(match.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);
                Optional<ChessPiece> capturedPiece = Optional.ofNullable(match.performChessMove(source, target));
                capturedPiece.ifPresent(captured::add);
            }
            catch(ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        // terminou a partida
        UI.clearScreen();
        UI.printMatch(match, captured);
    }
}