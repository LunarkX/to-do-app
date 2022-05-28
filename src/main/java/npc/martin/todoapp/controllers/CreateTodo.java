package npc.martin.todoapp.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import npc.martin.todoapp.model.TodoObject;
import npc.martin.todoapp.model.TodoList;
import npc.martin.todoapp.model.PersistenceTransactions;
import java.util.Scanner;

/**
 *
 * @author bikathi_martin
 * <h3>The CreateTodo Class</h3>
 * <p>
 * Is a class that has the methods that facilitate creating a todo. It has two methods:
 * <ul>
 *      <li>createATodo():helps create a todo in the interactive manner </li>
 *      <li>createATodo(String def, String details, CharSequence toExecute): overridden form of the former. 
 *          Helps create the todo directly i.e non-interactively 
 *      </li>
 * </ul>
 * </p>
 */
public class CreateTodo extends GenerateMetadata {
    String todoId;
    String todoDefinition;
    String todoDetails;
    LocalDate dateCreated;
    LocalTime timeCreated;
    LocalDate dateToExecute;
    
    String date;
    
    Scanner s1 = new Scanner(System.in);
    TodoList listActions = new TodoList();
    PersistenceTransactions transactions = new PersistenceTransactions();
    
    /**
     * <h4>createATodo()</h4>
     * <p>Helps create a todo in the interactive mode.</p>
     */
    public void createATodo() {
        //first load the list of already saved todos
        listActions = transactions.readSavedJSON();
        
        //create an id and a date and time for when a todo was created.
        this.todoId = this.generateUniqueId();
        this.dateCreated = this.generateDateCreated();
        this.timeCreated = this.generateTimeCreated();
        
        System.out.println("Starting interactive mode...");
        
        //in the interractive mode, get the simple definition, the in-depth details and the date to execute the todo
        System.out.print("Simple todo definition[3 words max]: ");
        this.todoDefinition = s1.nextLine();
        
        System.out.print("Indepth todo definition[upto a paragraph]: ");
        this.todoDetails = s1.nextLine();
        
        //the date should be formatted to be of type LocalDate
        //it is accepted as input in the format dd MMM yyyy
        System.out.print("Date to execute todo[Format: dd MMM yyyy e.g 21 Mar 2021]: ");
        this.date = s1.nextLine();
        CharSequence dateAsCharSequence = this.date;
        dateToExecute = LocalDate.parse(dateAsCharSequence, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        
        //using this information we build a TodoObject
        TodoObject todo = new TodoObject(todoId, todoDefinition, todoDetails, dateCreated, timeCreated, dateToExecute);
        Integer previousListSize = listActions.todoList.size();
        
        //then we add the new object to the list
        listActions.addTodo(todo);
        Integer postAddListSize = listActions.todoList.size();
        
        //if the size of the list successfully changes by 1, then the creation was correct
        if(postAddListSize - previousListSize == 1) {
            System.out.println("Successfully added new todo :) ");
            System.out.println("Saving todo... ");
            
            //then save the modified list
            transactions.saveAsJSON(listActions);
        } else {
            
            //else if the size of the list does not change, there was an error adding things to it
            System.out.println("Error adding new todo :(");
        }
    }
    
    /**
     * <h4>createATodo(String def, String details, CharSequence toExecute)</h4>
     * <p>Helps create a todo in the non-interactive mode.</p>
     * @param def
     * @param details
     * @param toExecute 
     */
    public void createATodo(String def, String details, CharSequence toExecute) {
        //as usual, we read from the storage file and assign all the other parameters to the todo object
        //remember the date takes the format dd MMM yyyy
        listActions = transactions.readSavedJSON();
        
        this.todoId = this.generateUniqueId();
        this.todoDefinition = def;
        this.todoDetails = details;
        this.dateCreated = this.generateDateCreated();
        this.timeCreated = this.generateTimeCreated();
        this.dateToExecute = LocalDate.parse(toExecute, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        
        //we then form an object using these parameters and try to add it to the list
        TodoObject todo = new TodoObject(todoId, todoDefinition, todoDetails, dateCreated, timeCreated, dateToExecute);
        Integer previousListSize = listActions.todoList.size();
        listActions.addTodo(todo);
        Integer postAddListSize = listActions.todoList.size();
        
        //if the size of the list grows by one, then the creation and addition process was successful
        if(postAddListSize - previousListSize == 1) {
            System.out.println("Successfully added new todo :) ");
            System.out.println("Saving todo... ");
            
            //save the updated list
            transactions.saveAsJSON(listActions);
        } else {
            
            //else if the list size does not change, something went wrong when adding the object
            System.out.println("Error adding new todo :(");
        }
    }
}
