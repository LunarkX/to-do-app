package npc.martin.todoapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author bikathi_martin
 */

public class TodoObject {
    private String todoId;
    private String todoDefinition;
    private String todoDetails;
    private LocalDate dateCreated;
    private LocalTime timeCreated;
    private LocalDate dateToExecute;
    private LocalDate dateExecuted = LocalDate.now();

    public String getTodoId() {
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

    public LocalTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalTime timeCreated) {
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
    
    public TodoObject(String todoId, String todoDefinition, String todoDetails, LocalDate dateCreated, LocalTime timeCreated, 
            LocalDate dateToExecute) {
        this.todoId = todoId;
        this.todoDefinition = todoDefinition;
        this.todoDetails = todoDetails;
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;
        this.dateToExecute = dateToExecute;
    }
    
    public TodoObject() {
        //parameterless constructor
    }
}
