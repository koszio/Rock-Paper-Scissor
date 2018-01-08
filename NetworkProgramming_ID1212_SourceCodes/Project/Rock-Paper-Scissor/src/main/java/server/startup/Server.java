package server.startup;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import server.controller.Controller;

/*
   The server is started here by initializing the database
   and registering the server in a registry.
 */
public class Server {

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.startRMIServant();
        } catch (RemoteException | MalformedURLException e) {
            System.out.println("Failed to start game server!");
            e.printStackTrace();
        }
    }

    private void startRMIServant() throws RemoteException, MalformedURLException {
        try {
            LocateRegistry.getRegistry().list();
        } catch (RemoteException noRegistryRunning) {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        }
        Controller controller = new Controller();
        Naming.rebind("gameproj", controller);
        System.out.println("Game has started.");
        try {
            controller.initGame();
        } catch (InterruptedException ex) {
            System.out.println("Initialization failed.");
        }
    }

}
