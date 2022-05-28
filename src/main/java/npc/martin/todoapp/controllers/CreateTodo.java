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
    
    public void createATodo() {
        listActions = transactions.readSavedJSON();
        
        this.todoId = this.generateUniqueId();
        this.dateCreated = this.generateDateCreated();
        this.timeCreated = this.generateTimeCreated();
        
        System.out.println("Starting interactive mode...");
        
        System.out.print("Simple todo definition[3 words max]: ");
        this.todoDefinition = s1.nextLine();
        
        System.out.print("Indepth todo definition[upto a paragraph]: ");
        this.todoDetails = s1.nextLine();
        
        System.out.print("Date to execute todo[Format: dd MM yyyy e.g 21 Mar 2021]: ");
        this.date = s1.nextLine();
        CharSequence dateAsCharSequence = this.date;
        dateToExecute = LocalDate.parse(dateAsCharSequence, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        
        TodoObject todo = new TodoObject(todoId, todoDefinition, todoDetails, dateCreated, timeCreated, dateToExecute);
        Integer previousListSize = listActions.todoList.size();
        listActions.addTodo(todo);
        Integer postAddListSize = listActions.todoList.size();
        
        if(postAddListSize - previousListSize == 1) {
            System.out.println("Successfully added new todo :) ");
            System.out.println("Saving todo... ");
            transactions.saveAsJSON(listActions);
        } else {
            System.out.println("Error adding new todo :(");
        }
        
        System.out.println("todo list size: " + listActions.todoList.size());
        
    }
    
    public void createATodo(String def, String details, CharSequence toExecute) {
        listActions = transactions.readSavedJSON();
        
        this.todoId = this.generateUniqueId();
        this.todoDefinition = def;
        this.todoDetails = details;
        this.dateCreated = this.generateDateCreated();
        this.timeCreated = this.generateTimeCreated();
        this.dateToExecute = LocalDate.parse(toExecute, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        
        TodoObject todo = new TodoObject(todoId, todoDefinition, todoDetails, dateCreated, timeCreated, dateToExecute);
        Integer previousListSize = listActions.todoList.size();
        listActions.addTodo(todo);
        Integer postAddListSize = listActions.todoList.size();
        
        if(postAddListSize - previousListSize == 1) {
            System.out.println("Successfully added new todo :) ");
            System.out.println("Saving todo... ");
            transactions.saveAsJSON(listActions);
        } else {
            System.out.println("Error adding new todo :(");
        }
        System.out.println("todo list size: " + listActions.todoList.size());
    }
}
