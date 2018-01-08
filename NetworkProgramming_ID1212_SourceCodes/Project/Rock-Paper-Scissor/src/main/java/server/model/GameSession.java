package server.model;

import static java.lang.Thread.sleep;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.integration.PlayerDAO;

/*
The core of the server.
This is where the game is run.
 */
public class GameSession extends Thread {

    private final PlayerDAO playerDAO;
    private WaitThread wt;
    private boolean isSessiongoing;
    private boolean gameRunning;
    private int answers;
    private int NrOfPlayers;
    private List<Player> listPlayers;

    public GameSession(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
        this.wt = new WaitThread(this);
        this.isSessiongoing = false;
        this.answers = 0;
        this.NrOfPlayers = 0;
        getPlayers();
    }

    /*
    Return the number of players waiting to play or currently playing
     */
    public int getNrofplayers() {
        return NrOfPlayers;
    }

    /*
    Sets
     */
    public void setNrOfPlayers(int NrOfPlayers) {
        this.NrOfPlayers = NrOfPlayers;
        if (this.NrOfPlayers < 0) {
            this.NrOfPlayers = 0;
        }
    }

    /*
    Increments
     */
    public void incrementNrOfPlayers() {
        this.NrOfPlayers++;
    }

    /*
    Decrements
     */
    public void decrementNrOfPlayers() {
        this.NrOfPlayers--;
        if (this.NrOfPlayers < 0) {
            this.NrOfPlayers = 0;
        }
    }

    /*
    Returns the player object of the specific username (client)
     */
    public Player getPlayer(String username) {
        if (isSessiongoing) {
            for (Player player : listPlayers) {
                if (player.getUsername().equals(username)) {
                    return player;
                }
            }
        }
        return null;
    }

    /*
    Sets the move that the client made
     */
    public Player setPlayer(String username, String move) {
        if (isSessiongoing) {
            for (int i = 0; i < listPlayers.size(); i++) {
                if (listPlayers.get(i).getUsername().equals(username)) {
                    listPlayers.get(i).setMove(move);
                    answers++;
                    broadmsg(username + " has made their move!");
                }
            }
        }
        return null;
    }

    /*
    Checks if the player has already made their move. 
    If not(and it is valid), the move is registered
     */
    public void playerMove(String msg, String username) throws RemoteException {
        Player player = getPlayer(username);
        if (isSessiongoing) {
            if (player.getMove().equals("")) {
                setPlayer(username, msg);
            } else {
                player.getPlayerObj().recvMsg("You have already answered!");
            }
        } else {
            player.getPlayerObj().recvMsg("Round has not started yet!");
        }
    }

    /*
    Returns the current state of the game
    Either ongoing or waiting
     */
    public boolean gameInSession() {
        return isSessiongoing;
    }

    /*
    Terminates the game session if a client exits
     */
    public void terminate() {
        gameRunning = false;
        wt.terminate();
    }

    /*
    Runs endlessly until a client exits
    The inner loop starts as soon as there are two clients
    Clients who then join afterwards has to wait until the round has ended
     */
    @Override
    public void run() {
        gameRunning = true;
        int gamecounter = 1;
        wt.start();
        while (gameRunning) {
            try {
                sleep(200);
            } catch (InterruptedException ex) {
            }

            while (gameRunning && getNrofplayers() >= 2) {

                if (!isSessiongoing) {
                    wt.terminate();
                    getPlayers();
                    isSessiongoing = true;
                }
                if (isSessiongoing) {
                    //all players give their moves
                    broadmsg("Round " + gamecounter);
                    broadmsg("Players! Enter your guesses");
                    while (answers < getNrofplayers()) {
                        try {
                            sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameSession.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    gamecounter++;
                    answers = 0;
                    try {
                        new GameLogic().game(listPlayers);
                    } catch (RemoteException ex) {
                        Logger.getLogger(GameSession.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    isSessiongoing = false;
                }
            }
        }
    }

    /*
    Returns the player list from the database
     */
    public final void getPlayers() {
        this.listPlayers = playerDAO.listPlayers();
    }

    /*
    Broadcasts a message to all players
     */
    public void broadmsg(String msg) {
        listPlayers.forEach((p) -> {
            try {
                p.getPlayerObj().recvMsg(msg);
            } catch (RemoteException ex) {
            }
        });
    }
}
