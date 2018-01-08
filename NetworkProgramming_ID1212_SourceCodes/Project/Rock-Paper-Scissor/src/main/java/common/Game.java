package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
Server interface that the client uses to play the game and communicate with the server.
 */
public interface Game extends Remote {

    final String SERVER_NAME_IN_REGISTRY = "gameproj";

    void pickUsername(String username, ClientObject client) throws RemoteException;

    void deletePlayer(String username) throws RemoteException;

    void sendMove(String msg, String username) throws RemoteException;

    void playGame() throws RemoteException;

    void leaveGame() throws RemoteException;

    boolean gameInSession() throws RemoteException;
}
