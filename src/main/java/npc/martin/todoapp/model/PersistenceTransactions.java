package npc.martin.todoapp.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author bikathi_martin
 */
public class PersistenceTransactions {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    private final String userHome = System.getProperty("user.home");
    
    public List<TodoObject> temporaryList = new ArrayList<>();
    
    public void saveAsJSON(TodoList todoListObject) {
        //make the storage directory in a hidden folder in /home/user
        File file = new File(userHome + File.separator + ".todoappdata");
        file.mkdir();
        
        //get path to the storage 
        Path path = Paths.get(userHome + File.separator + ".todoappdata" + File.separator + "todo-saved.json");
        
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path.toString()), todoListObject);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public TodoList readSavedJSON() {
        TodoList readObject = null;
        
        //get path to the source file
        Path path = Paths.get(userHome + File.separator + ".todoappdata" + File.separator + "todo-saved.json");
        
        try {
            readObject = objectMapper.readValue(new File(path.toString()), TodoList.class);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return readObject;
    }
    
    public void saveMarkedDone(List<TodoObject> doneList) {
        //make the storage directory in a hidden folder in /home/user
        File file = new File(userHome + File.separator + ".todoappdata");
        file.mkdir();
        
        //get path to the storage 
        Path path = Paths.get(userHome + File.separator + ".todoappdata" + File.separator + "todo-marked-done.json");
        
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path.toString()), doneList);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public List<TodoObject> readMarkedDone() {
        List<TodoObject> doneTodosList = null;
        
        //get path to the source file
        Path path = Paths.get(userHome + File.separator + ".todoappdata" + File.separator + "todo-marked-done.json");
        
        try {
            temporaryList = Arrays.asList(objectMapper.readValue(new File(path.toString()), TodoObject[].class));
            doneTodosList = new ArrayList<>(temporaryList);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return doneTodosList;
    }
}
