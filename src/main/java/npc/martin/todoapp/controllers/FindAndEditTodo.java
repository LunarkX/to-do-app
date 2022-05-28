package npc.martin.todoapp.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import npc.martin.todoapp.model.TodoList;
import npc.martin.todoapp.model.TodoObject;
import npc.martin.todoapp.model.PersistenceTransactions;

/**
 *
 * @author bikathi_martin
 */
public class FindAndEditTodo extends CreateTodo {
    private Integer positionInList = null;
    PersistenceTransactions transaction = new PersistenceTransactions();
    
    //works when you're not sure of the ID you're providing
    public void findSpecTodo(String targetTodoId) {
        listActions = transaction.readSavedJSON();
        
        for(TodoObject todoItem : listActions.getTodoList()) {
            String todoItemId = todoItem.getTodoId();
            if(todoItemId.equals(targetTodoId)) {
               positionInList = listActions.getTodoList().indexOf(todoItem);
               System.out.println("Match found! Populating results table... ");
               new GenerateTodoTables().generateWithIndex(positionInList, listActions);
               break;
            } else if(!(todoItemId.equals(targetTodoId)) && 
                    (listActions.getTodoList().indexOf(todoItem) == listActions.getTodoList().size() - 1)) {
                System.out.println("Sorry, no match for that ID :(");
            }
        }
    }
    
    //works when you know that the ID you're providing actually exists
    public Integer findTodo(String targetTodoId, TodoList list) {
        
        for(TodoObject todoItem : list.getTodoList()) {
            String todoItemId = todoItem.getTodoId();
            if(todoItemId.equals(targetTodoId)) {
                positionInList = list.getTodoList().indexOf(todoItem);
                break;
            }
        }
        
        return positionInList;
    }
    
    public void editTodo(String targetTodoId) {
        listActions = transaction.readSavedJSON();
        Integer itemToEdit = this.findTodo(targetTodoId, listActions);
        
        if(itemToEdit != null) {
            System.out.println("Starting editor in interractive mode... ");
        
            System.out.print("Enter new simple todo definition[3 words max]: ");
            this.todoDefinition = s1.nextLine();
            this.listActions.todoList.get(itemToEdit).setTodoDefinition(todoDefinition);

            System.out.print("Enter new indepth todo definition[upto a paragraph]: ");
            this.todoDetails = s1.nextLine();
            this.listActions.todoList.get(itemToEdit).setTodoDetails(todoDetails);

            System.out.print("Enter new date to execute todo[Format: dd MM yyyy e.g 21 Mar 2021]: ");
            this.date = s1.nextLine();
            CharSequence dateAsCharSequence = this.date;
            this.dateToExecute = LocalDate.parse(dateAsCharSequence, DateTimeFormatter.ofPattern("dd MMM yyyy"));
            this.listActions.todoList.get(itemToEdit).setDateToExecute(dateToExecute);

            System.out.println("Viable parameters have been editted successfully :)");
            transactions.saveAsJSON(listActions);
        } else {
            System.out.println("Sorry. No match found for that ID.");
        }
    }
}
