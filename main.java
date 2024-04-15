//Author: Javier Garcia Santana

package Chatbot;

import java.util.Scanner;

import Chatbot.ChatBot;

public class main {
   public static void main(String[] args) { 
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hi! What's your name?");
            String name = scanner.nextLine();

            ChatBot chatBot = new ChatBot(name);

            while (true) {
                System.out.println("You have the following options:");
                System.out.println("[0] Exit");
                System.out.println("[1] Have a conversation with the chatbot");
                System.out.println("[2] Play Battleship against the bot");
                System.out.println("[h] Help");

                String input = scanner.nextLine();

                switch (input) {
                    case "0":
                        System.out.println("Goodbye!");
                        return;
                    case "1":
                        chatBot.startConversation(name);
                        break;
                    case "2":
                        chatBot.playBattleship();
                        break;
                    case "h":
                        chatBot.displayHelp();
                        break;
                    default:
                        System.out.println("Please enter a valid option");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
