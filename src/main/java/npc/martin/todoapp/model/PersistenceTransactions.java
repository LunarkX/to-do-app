package npc.martin.todoapp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author bikathi_martin
 */
public class PersistenceTransactions {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String userHome = System.getProperty("user.home");
    
    public void saveAsJSON(TodoList todoListObject) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("todo-simple.json"), todoListObject);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public TodoList readSavedJSON() {
        TodoList readObject = null;
        objectMapper.registerModule(new JavaTimeModule());
        
        try {
            readObject = objectMapper.readValue(new File("todo-simple.json"), TodoList.class);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return readObject;
    }
}
