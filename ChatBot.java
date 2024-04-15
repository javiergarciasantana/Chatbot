package Chatbot;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ChatBot {
    private static final String[] angryResponses = {"Watch your language!", "That was rude!", "Be respectful!"};
    private static final String[] sadResponses = {"That's unfortunate", "I'm sorry to hear that", "Cheer up!"};
    private static final String[] happyResponses = {"That's great!", "Good to hear!", "Congratulations!"};
    private static final String[] eagerResponses = {"Tell me more!", "I'm excited to hear!", "Keep it coming!"};
    private static final String[] hitResponses = {"You hit me!", "Nice shot!", "Direct hit!", "Oh shit you hit me"};
    private static final String[] missResponses = {"Missed me!", "Better luck next time!", "Haha, missed!", "Haha looser, i bet you still live with your mom"};
  

    private String name;

    public ChatBot(String name) {
        this.name = name;
    }

    public void startConversation(String answer) {
        int mad_counter = 0;
        int sad_counter = 0;
        String happy_words[] = {"fun", "happy", "cute", "handsome", "pretty", "beautifull", "like you", "baby"};
        String sad_words[] = {"dead", "die", "killed", "shot", "homocide", "sucicide", "manslaughter", "murder", "kill", "horrible", "useless"};
        String bad_words[] = {"fuck", "studpid", "reatard", "bitch", "communist", "twat"};
        String emotional_state = "eager";

        while (true) {
                
        if (answer.equals("exit")) {
            System.out.println("Bye!");
            break;
        }
        
        if (answer.contains("Javier")) {
            System.out.println("Yo i gotta tell you, your name is badass.");
        }
        for (String i : happy_words) {
            if (answer.contains(i)) {
            emotional_state = "happy";
            }
        }
        for (String i : sad_words) {
            if (answer.contains(i)) {
            emotional_state = "sad";
            }
        }
        for (String i : bad_words) {
            if (answer.contains(i)) {
            emotional_state = "angry";
            ++mad_counter;
            }
        }
        if (mad_counter >= 6) {
            System.exit(0);
        }
        
        if (emotional_state.equals("angry")) {
            System.out.println(getRandomResponse(angryResponses));
        } else if (emotional_state.equals("sad")) {
            System.out.println(getRandomResponse(sadResponses));
            if (sad_counter >= 5) {
                System.out.println(":(");
            }
        } else if (emotional_state.equals("happy")) {
            System.out.println(getRandomResponse(happyResponses));
        } else if (emotional_state.equals("eager")) {
            System.out.println(getRandomResponse(eagerResponses));
        }
        
        Scanner sc = new Scanner(System.in);
        answer = sc.nextLine();
      }
    }

    public void playBattleship() {
        final char kWater = '-';
        final char kShip = 's';
        final char kHit = 'X';
        final char kMiss = '0';

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the desired board size (the ship number will always be 6)");
        int gameBoardLength = Integer.parseInt(scanner.nextLine());

        char[][] gameboard_user;
        char[][] gameboard_bot = createGameboard(gameBoardLength, kWater, kShip, 6);

        String move;
        int desired_row;
        int desired_col;
        int[] bot_coordinates;

        while (true) {
            gameboard_user = createGameboard(gameBoardLength, kWater, kShip, 6);
            System.out.println("This is your board. Would you like to change it?");
            printGameBoard(gameboard_user, kWater, kShip);
            System.out.println("Yes[Y]    No[N]");
            String alterBoard = scanner.nextLine();
            if (alterBoard.contains("N")) {
                break;
            }
        }

        printGameBoards(gameboard_user, gameboard_bot, kWater, kShip);

        while (true) {
            System.out.println("Make a move!");
            move = scanner.nextLine();
            if (move.equals("exit")) {
                break;
            }
            desired_row = convertRow(move);
            desired_col = convertCol(move, gameBoardLength);
            if (desired_col != '0') {
                if (gameboard_bot[desired_row][desired_col] == kShip) {
                    System.out.println(getRandomResponse(hitResponses));
                    gameboard_bot[desired_row][desired_col] = kHit;

                } else if (gameboard_bot[desired_row][desired_col] == kHit) {
                    System.out.println("What are you? Blind? DO NOT REPEAT YOUR MOVES!");

                } else {
                    System.out.println(getRandomResponse(missResponses));
                    gameboard_bot[desired_row][desired_col] = kMiss;
                }
                printGameBoards(gameboard_user, gameboard_bot, kWater, kShip);
                if (noShips(gameboard_bot, kShip)) {
                    System.out.println("You won!");
                    return;
                }

                bot_coordinates = generateShipCoordinates(gameBoardLength);
                if (gameboard_user[bot_coordinates[0]][bot_coordinates[1]] == kShip) {
                    System.out.println("You've been hit!");
                    gameboard_user[bot_coordinates[0]][bot_coordinates[1]] = kHit;
                } else if (gameboard_user[bot_coordinates[0]][bot_coordinates[1]] == kHit) {
                    System.out.println("The bot repeated a previous move.");

                } else {
                    System.out.println("The bot missed!");
                    gameboard_user[bot_coordinates[0]][bot_coordinates[1]] = kMiss;
                }

                if (noShips(gameboard_user, kShip)) {
                    System.out.println("The bot has won!   :(");
                    return;
                }
                printGameBoards(gameboard_user, gameboard_bot, kWater, kShip);
            }
        }
    }

    private String getRandomResponse(String[] responses) {
        Random rand = new Random();
        return responses[rand.nextInt(responses.length)];
    }

    private void printGameBoard(char[][] gameboard_user, char kWater, char kShip) {
        int gameboardLength = gameboard_user.length;
        System.out.print("  ");
        for (char i = 'A'; i <= ('A' + gameboardLength) - 1; ++i) {
            System.out.print(i + " ");
        }
        System.out.print('\n');
        for (int row = 0; row < gameboardLength; ++row) {
            System.out.print(row + " ");
            for (int col = 0; col < gameboardLength; ++col) {
                System.out.print(gameboard_user[row][col] + " ");
            }
            System.out.print("\n");
        }
    }

    private void printGameBoards(char[][] gameboard_user, char[][] gameboard_bot, char kWater, char kShip) {
        int gameboardLength = gameboard_user.length;
        System.out.print("  Your table");
        for (int i = 0; i < gameboardLength + 1; ++i) {
            System.out.print("  ");
        }
        System.out.println("Bot's table");
        System.out.print("  ");
        for (char i = 'A'; i <= ('A' + gameboardLength) - 1; ++i) {
            System.out.print(i + " ");
        }
        System.out.print("            ");
        for (char i = 'A'; i <= ('A' + gameboardLength) - 1; ++i) {
            System.out.print(i + " ");
        }
        System.out.print('\n');
        for (int row = 0; row < gameboardLength; ++row) {
            System.out.print(row + " ");
            for (int col = 0; col < gameboardLength; ++col) {
                System.out.print(gameboard_user[row][col] + " ");
            }
            System.out.print("          ");
            System.out.print(row + " ");
            for (int col = 0; col < gameboardLength; ++col) {
                if (gameboard_bot[row][col] == kShip) {
                    System.out.print(kWater + " ");
                } else {
                    System.out.print(gameboard_bot[row][col] + " ");
                }
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    private boolean noShips(char[][] gameboard_bot, char ship) {
        for (char[] row : gameboard_bot) {
            for (char cell : row) {
                if (cell == ship) {
                    return false;
                }
            }
        }
        return true;
    }

    private char[][] createGameboard(int gameBoardLength, char water, char ship, int shipNumber) {
        char[][] gameBoard = new char[gameBoardLength][gameBoardLength];
        for (char[] row : gameBoard) {
            Arrays.fill(row, water);
        }
        return placeShips(gameBoard, shipNumber, water, ship);
    }

    private char[][] placeShips(char[][] gameBoard, int shipNumber, char water, char ship) {
        int placedShips = 0;
        final int kGameBoardLength = gameBoard.length;
        while (placedShips < shipNumber) {
            int[] location = generateShipCoordinates(kGameBoardLength);
            char possiblePlacement = gameBoard[location[0]][location[1]];
            if (possiblePlacement == water) {
                gameBoard[location[0]][location[1]] = ship;
                ++placedShips;
            }
        }
        return gameBoard;
    }

    private int[] generateShipCoordinates(int kGameBoardLength) {
        Random rand = new Random();
        int[] coordinates = new int[2];
        for (int i = 0; i < coordinates.length; ++i) {
            coordinates[i] = rand.nextInt(kGameBoardLength);
        }
        return coordinates;
    }

    private int convertCol(String move, int gameboardLength) {
        char letter = move.charAt(0);
        if (letter < 'A' || letter > ('A' + gameboardLength)) {
            System.out.print("Input letter must be between A and ");
            System.out.println((char) (('A' + gameboardLength) - 1));
            return '0';
        }
        return letter - 'A';
    }

    private int convertRow(String move) {
        char result = move.charAt(1);
        return result - '0';
    }

    public void displayHelp() {
        System.out.println("How does the chatbot work?\n");
        System.out.println("It is very easy, you just tell him whatever you want. But you need to be careful, it has different responses " +
                "depending on the words you use.\nIf you use bad words more than 6 times, the program will exit unexpectedly due to " +
                "your bad behavior.\n");
        System.out.println("How can I play battleships?\n");
        System.out.println("You will first need to select the size of the board and then you will be prompted with a random ship allocation.\n" +
                "If you like it, press continue, if not, select change. After this you only need to make moves until you or the bot wins\n" +
                "To make a move, first input the letter and then the number, so that the program can understand the coordinates.\n");
        System.out.println("You may enter the keyword 'exit' every time you want to return to the main menu.\n");
    }
}

