package client.view;

import common.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class GameInterpreter implements Runnable {

    private final Scanner input = new Scanner(System.in);
    private Game game;
    private boolean loggedIn = false;
    private String username;
    private final ThreadSafeStdOut outMgr = new ThreadSafeStdOut();

    /*
    Starts the user interface.
     */
    public void start(Game gm) {
        this.game = gm;
        if (loggedIn) {
            return;
        }
        loggedIn = true;

        new Thread(this).start();
    }

    /*
    Main method that runs endlessly until exited manually by command.
     */
    @Override
    public void run() {
        outMgr.println("~~Welcome to the Game~~");
        boolean playing;
        while (true) {
            try {
                outMgr.println("");
                outMgr.println("Enter command: ('help' for list of commands)");
                CmdLine cmdLine = new CmdLine(readNextLine());

                switch (cmdLine.getCmd()) {
                    case HELP:
                        outMgr.println("~~The commands are~~");
                        for (Command command : Command.values()) {
                            if (command == Command.ILLEGAL_COMMAND) {
                                outMgr.println("~~When playing~~\n" + "quit");
                                continue;
                            }
                            outMgr.println(command.toString().toLowerCase());
                        }
                        break;
                    case PLAY:
                        outMgr.println("Choose a username: ");
                        username = readNextLine();
                        game.pickUsername(username, new NotificationHandler());

                        playing = true;
                        if (game.gameInSession()) {
                            outMgr.println("Game has already started. You will enter in the next round!");
                            while (game.gameInSession()) {
                            }
                        }
                        outMgr.println("Enter a move or 'quit' to finish the game!");
                        game.playGame();
                        while (playing) {
                            try {
                                cmdLine = new CmdLine(readNextLine());
                                switch (cmdLine.getCmd()) {
                                    case SCISSOR:
                                        game.sendMove("SCISSOR", username);
                                        break;
                                    case ROCK:
                                        game.sendMove("ROCK", username);
                                        break;
                                    case PAPER:
                                        game.sendMove("PAPER", username);
                                        break;
                                    case QUIT:
                                        game.leaveGame();
                                        game.deletePlayer(username);
                                        System.exit(0);
                                    default:
                                        outMgr.println("Invalid command! Try again!");
                                        break;
                                }
                            } catch (Exception e) {
                                outMgr.println("Ops, something happened! Be careful.");
                            }
                        }
                    case QUIT:
                        outMgr.println("Exitting the game...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid command! Try again!");
                        break;
                }
            } catch (Exception e) {
                outMgr.println("Operation failed");
                outMgr.println(e.getMessage());
            }
        }
    }

    private String readNextLine() {
        return input.nextLine();
    }

    /*
    Inner object that is then passed on as a parameter such that the server can 
    communicate with the client.
     */
    private class NotificationHandler extends UnicastRemoteObject implements ClientObject {

        public NotificationHandler() throws RemoteException {
        }

        @Override
        public void recvMsg(String msg) throws RemoteException {
            outMgr.println(msg);
        }
    }
}
