package client.startup;

import client.view.*;
import common.Game;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {

    /*
    Acquires the remote object from the server and then starts communicating. 
    Exception is thrown if not possible 
    */
    public static void main(String[] args) {
        try {
            Game game = (Game) Naming.lookup(Game.SERVER_NAME_IN_REGISTRY);
            new GameInterpreter().start(game);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

}
