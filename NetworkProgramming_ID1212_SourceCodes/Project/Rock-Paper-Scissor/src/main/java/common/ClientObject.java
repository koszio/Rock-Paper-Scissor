package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
Client interface that the server uses to communicate with one specific or all clients
 */
public interface ClientObject extends Remote {

    void recvMsg(String msg) throws RemoteException;
}
