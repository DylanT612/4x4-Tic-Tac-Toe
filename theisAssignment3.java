/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 3/30/2024

# Author: Dylan Theis
# Date: Spring 2024
# Class: CSC222
# Project: Assignment 3
# Description: 4x4 Knots and Crosses(Tic Tac Toe)
 */

// Import scanner
import java.util.Scanner;

// create class and variables
public class theisAssignment3 {
    private static final int squares = 4;
    private static char[][] board = new char[squares][squares];
    private static char sign = 'X';
    private static Scanner scnr = new Scanner(System.in);

    // main function
    public static void main(String[] args) {
        // print intro quip
        System.out.println("Welcome to 4x4 TicTacToe! Get 4 in-a-row vertically, horizontally, or diagonally!");

        // game loop
        while (true) {
            clearBoard();
            displayBoard();
            // when game is not over continue asking for moves
            while (!isGameOver()) {
                System.out.println("Player " + sign + " turn(row column): ");
                int row = scnr.nextInt() - 1;
                int col = scnr.nextInt() - 1;
                // checks valid move
                if (!isTaken(row, col)) {
                    move(row, col);
                    displayBoard();
                    // does move make you win(row, col, or diag 4 in-a-row)
                    if (checkWinner()) {
                        System.out.println("Player " + sign + " WON! Game over!");
                        break;
                    }
                    // does move make you tie(all boxes filled)
                    if (isTie()) {
                        System.out.println("It's a TIE. Game over!");
                        break;
                    }
                    // switch from X to O
                    rotatePlayer();
                // the else if not valid move
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }
            // ask user if they want to play again yes triggers new game loop
            System.out.println("Do you want to play another game?");
            System.out.print("Y/N: ");
            String playAgain = scnr.next().toLowerCase();
            if ((!playAgain.equals("yes")) && (!playAgain.equals("y"))) {
                break;
            }
            scnr.nextLine();
        }
        // Goodbye message
        System.out.println("Goodbye!");

    }

    // method for initializing board with blanks
    private static void clearBoard() {
        for (int i = 0; i < squares; i++) {
            for (int j = 0; j < squares; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // method for inputting content in my board
    private static void displayBoard() {

        for (int i = 0; i < squares; i++) {
            for (int j = 0; j < squares; j++) {
                System.out.print("[" + board[i][j] + "] ");
            }
            System.out.println();

        }
    }

    // swaps user sign from X to 0
    private static void rotatePlayer() {
        sign = (sign == 'X') ? 'O' : 'X';
    }

    // method to determine where user sign goes
    private static void move(int row, int col) {
        board[row][col] = sign;
    }

    // isTaken returns true if not valid move
    private static boolean isTaken(int row, int col) {
        return ! (row >= 0 && row < squares && col >= 0 && col < squares && board[row][col] == ' ');
    }

    // return true if you won in a row column or diagonally
    private static boolean checkWinner() {
        return checkWinningRows() || checkWinningCols() || checkWinningDiagonals();
    }

    // return true for win or tie
    private static boolean isGameOver() {
        return checkWinner() || isTie();
    }

    // seeing if row is all the same sign
    private static boolean checkWinningRows() {
        for (int i = 0; i < squares; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][2] == board[i][3]) {
                return true;
            }
        }
        return false;
    }


    // seeing if column is all the same sign
    private static boolean checkWinningCols() {
        for (int j = 0; j < squares; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[2][j] == board[3][j]) {
                return true;
            }
        }
        return false;
    }


    // seeing if diagonals are all the same sign
    private static boolean checkWinningDiagonals() {
        return (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == board[3][3]) ||
                (board[0][3] != ' ' && board[0][3] == board[1][2] && board[1][2] == board[2][1] && board[2][1] == board[3][0]);
    }

    // squares full but no winner found
    private static boolean isTie() {
        for (int i = 0; i < squares; i++) {
            for (int j = 0; j < squares; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
