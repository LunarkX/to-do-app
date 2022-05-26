package npc.martin.todoapp.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import npc.martin.todoapp.model.TodoObject;

/**
 *
 * @author bikathi_martin
 */
public class FindAndEditTodo extends CreateTodo {
    private Integer positionInList;
    
    public Integer findTodo(String targetTodoId) {
        for(TodoObject todoItem : listActions.todoList) {
            String todoItemId = todoItem.getTodoId();
            if(targetTodoId.equals(todoItemId)) {
                positionInList = listActions.todoList.indexOf(todoItem);
                System.out.println("Match found! Populating result table... ");
                new GenerateTodoTables().generateWithID(positionInList);
                
                //TBD
                System.out.println("Index of search: " + positionInList);
                break;
            } else {
                System.out.println("Sorry, no match for that ID :(");
            }
        }
        
        return positionInList;
    }
    
    public void editTodo(String targetTodoId) {
        Integer itemToEdit = this.findTodo(targetTodoId);
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
    }
}
