package client.view;

import java.util.ArrayList;

class CmdLine {

    /*
    Mostly unused but returns the commands from Command.java
    */
    private static final String PARAM_DELIMETER = " ";
    private Command cmd;
    private final String enteredLine;
    private ArrayList<String> arguments = new ArrayList<>();
    private Command command;

    CmdLine(String enteredLine) {
        this.enteredLine = enteredLine;
        parseCmd(enteredLine);

    }

    Command getCmd() {
        return cmd;
    }

    String getUserInput() {
        return enteredLine;
    }

    private void parseCmd(String enteredLine) {
        int cmdNameIndex = 0;
        try {
            String[] enteredTokens = (enteredLine).split(PARAM_DELIMETER);
            cmd = Command.valueOf(enteredTokens[cmdNameIndex].toUpperCase());
        } catch (Throwable failedToReadCmd) {
            cmd = Command.ILLEGAL_COMMAND;
        }
    }

    public String getArgument(int index) {
        return arguments.get(index);
    }

    public Command getCommand() {
        return command;
    }
}
