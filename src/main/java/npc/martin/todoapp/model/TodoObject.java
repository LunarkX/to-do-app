package npc.martin.todoapp.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author bikathi_martin
 */

public class TodoObject {
    private String todoId;
    private String todoDefinition;
    private String todoDetails;
    private LocalDate dateCreated;
    private CharSequence timeCreated;
    private LocalDate dateToExecute;
    private LocalDate dateExecuted;

    public String getToDo() {
        return todoId;
    }

    public void setToDo(String toDo) {
        this.todoId = toDo;
    }

    public String getTodoDefinition() {
        return todoDefinition;
    }

    public void setTodoDefinition(String todoDefinition) {
        this.todoDefinition = todoDefinition;
    }

    public String getTodoDetails() {
        return todoDetails;
    }

    public void setTodoDetails(String todoDetails) {
        this.todoDetails = todoDetails;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public CharSequence getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(CharSequence timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDate getDateToExecute() {
        return dateToExecute;
    }

    public void setDateToExecute(LocalDate dateToExecute) {
        this.dateToExecute = dateToExecute;
    }

    public LocalDate getDateExecuted() {
        return dateExecuted;
    }

    public void setDateExecuted(LocalDate dateExecuted) {
        this.dateExecuted = dateExecuted;
    }
    
    public TodoObject(String todoDefinition, String todoDetails, LocalDate dateCreated, CharSequence timeCreated, 
            LocalDate dateToExecute, LocalDate dateExecuted) {
        this.todoDefinition = todoDefinition;
        this.todoDetails = todoDetails;
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;
        this.dateToExecute = dateToExecute;
        this.dateExecuted = dateExecuted;
    }
    
    public TodoObject() {
        //parameterless constructor
    }
    
    public static void main(String[] args) {
        CharSequence exampleDate = "2021 Dec 21";
        LocalDate date = LocalDate.parse(exampleDate, DateTimeFormatter.ofPattern("uuuu MMM dd")); //yyyy-MM-dd
        System.out.println("Date: " + date);
    }
}
