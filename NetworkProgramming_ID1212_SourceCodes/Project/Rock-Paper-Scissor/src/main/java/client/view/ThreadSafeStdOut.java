package client.view;

/*
Synchronized printing functions for safety precautions.
 */
public class ThreadSafeStdOut {

    synchronized void print(String output) {
        System.out.print(output);
    }

    synchronized void println(String output) {
        System.out.println(output);
    }
}
