package npc.martin.todoapp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author bikathi_martin
 */
public class PersistenceTransactions {
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public void saveAsJSON(TodoList todoListObject) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("todo-simple.json"), todoListObject);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public TodoList readSavedJSON() {
        TodoList readObject = null;
        
        try {
            readObject = objectMapper.readValue(new File("todo-simple.json"), TodoList.class);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return readObject;
    }
    
    /**public static void main(String[] args) {
        TodoList testWriteObject = new TodoList();
        new PersistenceTransactions().saveAsJSON(testWriteObject);
        
        TodoList testReadObject = new PersistenceTransactions().readSavedJSON();
        System.out.println("Read object: " + testReadObject.toString());
    }*/
}
