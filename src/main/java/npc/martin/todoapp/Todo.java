package npc.martin.todoapp;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine;

@Command(name = "td", description = "Start the ToDo command line utility.", version = "Todo-1.0-BETA")
public class Todo implements Runnable {
    @Override
    public void run() {
        
    }
    
    public static void main(String[] args) {
        //for deployment
        //Integer exitCode = new CommandLine(new Todo()).execute(args);
        //System.exit(exitCode);
        
        //for testing
        new CommandLine(new Todo()).execute("-h");
    }
}
