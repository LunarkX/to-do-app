package npc.martin.todoapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bikathi_martin
 */
public class TodoList {
    public List<TodoObject> todoList = new ArrayList<>();
    
    public List<TodoObject> getTodoList() {
        return todoList;
    }
    
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
