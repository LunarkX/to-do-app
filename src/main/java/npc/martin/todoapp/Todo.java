package npc.martin.todoapp;

import java.io.File;
import npc.martin.todoapp.controllers.CreateTodo;
import npc.martin.todoapp.controllers.FindAndEditTodo;
import npc.martin.todoapp.controllers.GenerateTodoTables;
import npc.martin.todoapp.controllers.MarkAsDone;
import npc.martin.todoapp.model.InitSample;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Spec;

@Command(name = "todo", description = "Start the Todo command line utility.", mixinStandardHelpOptions = true, 
        version = "Todo-1.0-STABLE")
public class Todo implements Runnable {
    @Spec CommandSpec spec;
    
    @Command(name = "new-interactive", description = "Create a new todo non-interactively.")
    public void newTodo() {
        new CreateTodo().createATodo();
    }
    
    @Command(name = "new", description = "Create a new todo interactively. ")
    public void newTodo(@Option(names = "--definition", description = "Upto three words describing the todo.", paramLabel = "short description") String definition, 
            @Option(names = "--details", description = "A longer paragraph describing the todo.", paramLabel = "longer details") String details, 
            @Option(names = "--date", description = "Date to execute the todo. Format dd MMM yyyy e.g 21 Feb 2021.", paramLabel = "date to execute") CharSequence dateToExecute) {
        new CreateTodo().createATodo(definition, details, dateToExecute);
    }
    
    @Command(name = "search", description = "Search for a todo by the id.")
    void searchTodo(@Option(names = "--id", description = "The id of the todo to search for.", paramLabel = "ID") String id) {
        new FindAndEditTodo().findSpecTodo(id);
    }
    
    @Command(name = "edit", description = "Edit data of an existing todo.")
    void editTodo(@Option(names = "--id", description = "The id of the todo to edit.", paramLabel = "ID") String id) {
        new FindAndEditTodo().editTodo(id);
    }
    
    @Command(name = "view", description = "View data of an existing todo.")
    void viewTodo(@Option(names = "--id", description = "The id of the todo you want to view.", paramLabel = "ID") String id) {
        new GenerateTodoTables().generateWithID(id);
    }
    
    @Command(name = "mark-done", description = "Mark a todo as done.")
    void markDone(@Option(names = "--id", description = "The id of the todo to mark as done.", paramLabel = "ID") String id) {
        new MarkAsDone().markTodoDone(id);
    }
    
    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Specify a command.");
    }
    
    public static void main(String[] args) {
        //when the code starts, we always look for the storage directory
        File storageFolder = new File(System.getProperty("user.home") + File.separator + ".todoappdata");
        
        //if it does exist we simply proceed running the user's commands
        if(storageFolder.exists()) {
            //for deployment
            Integer exitCode = new CommandLine(new Todo()).execute(args);
            System.exit(exitCode);

            //for testing
            //new CommandLine(new Todo()).execute("view", "--id", "cb325677");
        
        //else we create it and fill it with sample data first, then proceed executing user commands
        } else {
            new InitSample();
            System.out.println("Created new folder coz it does not exist.");
            //for deployment
            Integer exitCode = new CommandLine(new Todo()).execute(args);
            System.exit(exitCode);

            //for testing
            //new CommandLine(new Todo()).execute("");
        }
    }
}
