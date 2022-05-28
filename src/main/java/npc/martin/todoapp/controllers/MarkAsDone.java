package npc.martin.todoapp.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import npc.martin.todoapp.model.TodoObject;

/**
 *
 * @author bikathi_martin
 * <h3>The MarkAsDone Class</h3>
 * <p>
 * This class helps mark todo as done. The todos are removed from their original storage location to
 * another location where others of its kind should reside.
 * </p>
 */
public class MarkAsDone extends FindAndEditTodo {
    private final LocalDate dateToday = LocalDate.now();
    private List<TodoObject> markedDone = new ArrayList<>();
    
    /**
     * <h4>markTodoDone(String todoId)</h4>
     * <p>This is the only method of this class. It does the whole work designated to this class.</p>
     * @param todoId 
     */
    public void markTodoDone(String todoId) {
        markedDone = transactions.readMarkedDone();
        listActions = transaction.readSavedJSON();
        
        //we have to get the index of the object we're marking as todo
        Integer itemIndex = findTodo(todoId, listActions);
        
        //if the index does exist, we proceed
        if(itemIndex != null) {
            //first we change the date when the todo was to be executed to the one of the day this method is
            //called. This mean this method should be used when you're sure you wan't to mark a todo as done.
            listActions.todoList.get(itemIndex).setDateExecuted(dateToday);
            
            //we then show you the updated version of that object
            new GenerateTodoTables().generateWithIndex(itemIndex, listActions);
            
            //we add that object to a list that contains todos marked as done
            markedDone.add(listActions.todoList.get(itemIndex));
            
            //then we remove it from the original list
            listActions.todoList.remove(listActions.todoList.get(itemIndex));
            
            //we save the updated version of both lists
            transaction.saveAsJSON(listActions);
            transaction.saveMarkedDone(markedDone);
            
        //else if the index does not exist, we generate some form of error 
        } else {
            System.out.println("Sorry, no match for that index :(");
        }
    }
}
