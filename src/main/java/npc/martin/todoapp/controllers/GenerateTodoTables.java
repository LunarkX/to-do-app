package npc.martin.todoapp.controllers;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import java.util.ArrayList;
import npc.martin.todoapp.model.TodoObject;
import java.util.Arrays;
import java.util.List;
import npc.martin.todoapp.model.TodoList;

/**
 *
 * @author bikathi_martin
 * <h3>The GeneraeTodoTables Class</h3>
 * <p>
 * Is the class housing the methods that will help generate the view tables for the app. 
 * It extends the CreateTodo class so that it can inherit the listActions field.
 * </p>
 */
public class GenerateTodoTables extends CreateTodo {
    protected Character[] borderStyle = AsciiTable.FANCY_ASCII;
    protected List<TodoObject> singleItemList;
    private List<TodoObject> markedDone = new ArrayList<>();
    
    /**
     * <h4>tableGenerator(List<TodoObject> dataSource)</h4>
     * <p>
     * This is the method doing actual generate of tables. It takes a source and uses Freva's 
     * AsciiTable code to build all the tables.
     * </p>
     * @param dataSource 
     */
    public void tableGenerator(List<TodoObject> dataSource) {
        System.out.println(AsciiTable.getTable(borderStyle, dataSource, Arrays.asList(
            //column 1
            new Column().header("Item ID").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT)
                    .with(todoItem -> todoItem.getTodoId()),
                
            //column 2
            new Column().header("Simple Definition").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT)
                    .with(todoItem -> todoItem.getTodoDefinition()),

            //column 3
            new Column().header("Longer Details").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT)
                    .maxColumnWidth(50).with(todoItem -> todoItem.getTodoDetails()),

            //column 4
            new Column().header("Date Created").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT)
                    .with(todoItem -> todoItem.getDateCreated().toString()),

            //column 5
            new Column().header("Time Created (24 HRS)").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT)
                    .with(todoItem -> todoItem.getTimeCreated().toString()),

            //column 6
            new Column().header("Date to Execute").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT)
                    .with(todoItem -> todoItem.getDateToExecute().toString()),

            //column 7
            new Column().header("Date Executed").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT)
                    .with(todoItem -> todoItem.getDateExecuted().toString())
        )));
    }
    
    /**
     * <h4>generateWithID(String targetTodoId)</h4>
     * <p>
     * This method can be directly invoked by the user to generate a table based on the ID that a user provides.
     * The ID is taken in as a parameter.
     * </p>
     * @param targetTodoId 
     */
    public void generateWithID(String targetTodoId) {
        listActions = transactions.readSavedJSON();
        
        //we get the index of the item with that ID with the help of the FindAndEditClass.
        Integer indexToPrint = new FindAndEditTodo().findTodo(targetTodoId, listActions);
        
        //if the index is found, we print a table.
        if(indexToPrint != null) {
            System.out.println("Populating view table... ");
            singleItemList = Arrays.asList(listActions.todoList.get(indexToPrint));
        
            this.tableGenerator(singleItemList);
            
        //else if no index is found, we generate an error message
        } else {
            System.out.println("Sorry, no match for that ID :(");
        }  
    }
    
    /**
     * <h4>generateWithoutID()</h4>
     * <p>
     * This method will generate the a table showing all the un-executed todos. It can be invoked directly
     * by the user but it takes no argument.
     * </p>
     */
    public void generateWithoutID(String[] targetTables) {
        for(String tableName: targetTables) {
            if(tableName.equals("not-done")) {
                listActions = transactions.readSavedJSON();
                System.out.println("Generating view tables...");

                //we will generate the table using the entire list
                this.tableGenerator(listActions.getTodoList());
            } else if(tableName.equals("done")) {
                markedDone = transactions.readMarkedDone();
                System.out.println("Generating view tables...");
                
                //we will generate a tabel using the entire list
                this.tableGenerator(markedDone);
            } else {
                System.out.println("Table non-existent, please provide a valid table name. Use 'done' or 'not-done'.");
            }
        }
    }
    
    /**
     * <h4>generateWithIndex(Integer index, TodoList listActions)</h4>
     * <p>
     * This method generates a table based on a specific index. It cannot be invoked directly by the user
     * rather is invoked by code in the FindAndEditTodo class.
     * </p>
     * @param index
     * @param listActions 
     */
    public void generateWithIndex(Integer index, TodoList listActions) {
        //we build an list of one item which is decided by the provided index
        singleItemList = Arrays.asList(listActions.todoList.get(index));
        System.out.println("Generating view table...");
        
        //then pass that list to the table generator method.
        this.tableGenerator(singleItemList);
    }
}
