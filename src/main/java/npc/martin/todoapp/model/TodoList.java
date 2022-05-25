package npc.martin.todoapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bikathi_martin
 * <h3>TODO OBJECTS</h3>
 */
public class TodoList {
    List<TodoObject> todoList = new ArrayList<>();
    
    public void addTodo(TodoObject todo) {
        this.todoList.add(todo);
    }
    
    public void removeTodo() {
        
    }
    
    public Integer getNumberOfTodos() {
        return this.todoList.size();
    }
    
    public TodoObject getTodo(Integer index) {
        return todoList.get(index);
    }
}
