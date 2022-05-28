package npc.martin.todoapp.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import npc.martin.todoapp.model.TodoObject;

/**
 *
 * @author bikathi_martin
 */
public class MarkAsDone extends FindAndEditTodo {
    private final LocalDate dateToday = LocalDate.now();
    private List<TodoObject> markedDone = new ArrayList<>();
    
    public void markTodoDone(String todoId) {
        markedDone = transactions.readMarkedDone();
        listActions = transaction.readSavedJSON();
        
        Integer itemIndex = findTodo(todoId, listActions);
        if(itemIndex != null) {
            listActions.todoList.get(itemIndex).setDateExecuted(dateToday);
        
            markedDone.add(listActions.todoList.get(itemIndex));
            listActions.todoList.remove(listActions.todoList.get(itemIndex));
            System.out.println("Size after: " + listActions.todoList.size());

            transaction.saveAsJSON(listActions);
            transaction.saveMarkedDone(markedDone);
            new GenerateTodoTables().generateWithIndex(itemIndex, listActions);
        } else {
            System.out.println("Sorry, no match for that index :(");
        }
    }
}
