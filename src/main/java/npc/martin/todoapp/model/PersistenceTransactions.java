package npc.martin.todoapp.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author bikathi_martin
 */
public class PersistenceTransactions {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String userHome = System.getProperty("user.home");
    
    public void saveAsJSON(TodoList todoListObject) {
        /*//make the storage directory in a hidden folder in /home/user
        File file = new File(userHome + File.separator + ".todoapp");
        file.mkdir();
        
        //get path to the storage 
        Path path = Paths.get(userHome + File.separator + ".todoapp" + File.separator + "todo-saved.json");*/
        
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("todo-simple.json"), todoListObject);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public TodoList readSavedJSON() {
        TodoList readObject = null;
        
        //get path to the source file
        /*Path path = Paths.get(userHome + File.separator + ".todoapp" + File.separator + "todo-saved.json");*/
        
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            readObject = objectMapper.readValue(new File("todo-simple.json"), TodoList.class);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return readObject;
    }
    
    public void saveMarkedDone(List<TodoObject> doneList) {
        /*//make the storage directory in a hidden folder in /home/user
        File file = new File(userHome + File.separator + ".todoapp");
        file.mkdir();
        
        //get path to the storage 
        Path path = Paths.get(userHome + File.separator + ".todoapp" + File.separator + "todo-marked-done.json");*/
        
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("todo-markded-done.json"), doneList);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public List<TodoObject> readMarkedDone() {
        List<TodoObject> doneList = null;
        
        //get path to the source file
        /*Path path = Paths.get(userHome + File.separator + ".todoapp" + File.separator + "todo-marked-done.json");*/
        
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            doneList = Arrays.asList(objectMapper.readValue(new File("todo-marked-done.json"), TodoObject[].class));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return doneList;
    }
}
