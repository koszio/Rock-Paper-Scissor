package server.model;

import static java.lang.Thread.sleep;

/*
   This thread simply broadcasts the message that the client needs to wait for more
   players
 */
public class WaitThread extends Thread {

    GameSession gamesession;
    private boolean running = true;

    public WaitThread(GameSession controller) {
        this.gamesession = controller;
    }

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            if (gamesession.getNrofplayers() > 0) {
                gamesession.broadmsg("Waiting for " + (2 - gamesession.getNrofplayers()) + " more player(s)!");
            }
            try {
                sleep(3000);
            } catch (InterruptedException ex) {
            }
        }
    }
}
