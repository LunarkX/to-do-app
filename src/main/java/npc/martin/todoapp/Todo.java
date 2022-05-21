package npc.martin.todoapp;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine;

@Command(name = "td", description = "Start the ToDo command line utility.")
public class Todo implements Runnable {
    @Override
    public void run() {
        System.out.println("hello world");
    }
    
    public static void main(String[] args) {
        //for deployment
        Integer exitCode = new CommandLine(new Todo()).execute(args);
        System.exit(exitCode);
        
        //for testing
        new CommandLine(new Todo()).execute("-h");
    }
}
