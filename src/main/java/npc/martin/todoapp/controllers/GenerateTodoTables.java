package npc.martin.todoapp.controllers;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import npc.martin.todoapp.model.TodoObject;
import java.util.Arrays;
import java.util.List;
import npc.martin.todoapp.model.TodoList;

/**
 *
 * @author bikathi_martin
 */
public class GenerateTodoTables extends CreateTodo {
    protected Character[] borderStyle = AsciiTable.FANCY_ASCII;
    protected List<TodoObject> singleItemList;
    
    public void tableGenerator(List<TodoObject> dataSource) {
        System.out.println(AsciiTable.getTable(borderStyle, dataSource, Arrays.asList(
            //column 1
            new Column().header("Item ID").with(todoItem -> todoItem.getTodoId()),
                
            //column 2
            new Column().header("Item Definition").with(todoItem -> todoItem.getTodoDefinition()),

            //column 3
            new Column().header("Item Details").with(todoItem -> todoItem.getTodoDetails()),

            //column 4
            new Column().header("Date Created").with(todoItem -> todoItem.getDateCreated().toString()),

            //column 5
            new Column().header("Time Created").with(todoItem -> todoItem.getTimeCreated().toString()),

            //column 6
            new Column().header("Date to Execute").with(todoItem -> todoItem.getDateToExecute().toString()),

            //column 7
            new Column().header("Date Executed").with(todoItem -> todoItem.getDateExecuted().toString())
        )));
    }
    
    public void generateWithID(String targetTodoId) {
        listActions = transactions.readSavedJSON();
        Integer indexToPrint = new FindAndEditTodo().findTodo(targetTodoId, listActions);
        
        if(indexToPrint != null) {
            System.out.println("Populating view table... ");
            singleItemList = Arrays.asList(listActions.todoList.get(indexToPrint));
        
            this.tableGenerator(singleItemList);
        } else {
            System.out.println("Sorry, no match for that ID :(");
        }  
    }
    
    public void generateWithoutID() {
        listActions = transactions.readSavedJSON();
        System.out.println("Generating view tables...");
        this.tableGenerator(listActions.getTodoList());
    }
    
    public void generateWithIndex(Integer index, TodoList listActions) {
        singleItemList = Arrays.asList(listActions.todoList.get(index));
        System.out.println("Generating view table...");
        
        this.tableGenerator(singleItemList);
    }
}
