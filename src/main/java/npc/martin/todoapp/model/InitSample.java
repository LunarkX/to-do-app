package npc.martin.todoapp.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import npc.martin.todoapp.controllers.GenerateMetadata;

/**
 *
 * @author bikathi_martin
 */
public final class InitSample extends GenerateMetadata {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    private final List<TodoObject> initDoneTodos = new ArrayList<>();
    private final TodoList initNewTodos = new TodoList();
    private final TodoObject sampleTodoObject = new TodoObject();
    private final String userHome = System.getProperty("user.home");
    
    public InitSample() {
        this.createSampleTodo();
    }
    
    public void createSampleTodo() {
        //create the sample object
        sampleTodoObject.setTodoID(this.generateUniqueId());
        sampleTodoObject.setTodoDefinition("A sample definition.");
        sampleTodoObject.setTodoDetails("A sample detailed description. It can be a paragraph long.");
        sampleTodoObject.setDateCreated(LocalDate.parse("01 Jan 1970", DateTimeFormatter.ofPattern("dd MMM yyyy")));
        sampleTodoObject.setTimeCreated(this.generateTimeCreated());
        sampleTodoObject.setDateToExecute(LocalDate.parse("03 Jan 1970", DateTimeFormatter.ofPattern("dd MMM yyyy")));
        sampleTodoObject.setDateExecuted(LocalDate.parse("02 Jan 1970", DateTimeFormatter.ofPattern("dd MMM yyyy")));
        
        //add the sample object to the list, and to the TodoList object
        initDoneTodos.add(sampleTodoObject); //for the list of done todos
        initNewTodos.todoList.add(sampleTodoObject); //for the new todos object
        
        //then call the method to save data
        this.saveSampleTodo();
    }
    
    public void saveSampleTodo() {
        //make the storage directory in a hidden folder in /home/user
        File file = new File(userHome + File.separator + ".todoapp");
        file.mkdir();
        
        //get path to the storage 
        Path pathToNewTodos = Paths.get(userHome + File.separator + ".todoapp" + File.separator + "new-todos.json");
        Path pathToSavedTodos = Paths.get(userHome + File.separator + ".todoapp" + File.separator + "todos-marked-done.json");
        
        try {
            //first save the sample new todos;
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(pathToNewTodos.toString()), initNewTodos);
            
            //then save the sample todos marked done;
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(pathToSavedTodos.toString()), initDoneTodos);
            
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
