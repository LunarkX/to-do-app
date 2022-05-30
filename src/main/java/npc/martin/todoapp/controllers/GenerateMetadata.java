package npc.martin.todoapp.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 *
 * @author bikathi_martin
 * <h1>The GenerateMetadata Class</h1>
 * <p>
 * It has a number of methods to help generate the todo metadata. This includes ids,
 * date of creation and time of creation data.
 * </p>
 */
public abstract class GenerateMetadata {
    /**
     * 
     * @return String
     * <h1>generateUniqueID()</h1>
     * <p>Helps generate a unique id for each todo object.</p>
     */
    protected String generateUniqueId() {
        //we get our unique id from the UUID class
        String uuid = UUID.randomUUID().toString();
        
        //we only need the first quarter of the version 4 UUID
        //so we split the UUID and pick only the first part
        String[] uuidComponents = uuid.split("-");
        
        String uniqueTodoId = uuidComponents[0];
        return uniqueTodoId;
    }
    
    /**
     * 
     * @return LocalTime
     * <h1>generateTimeCreated()</h1>
     * <p>This method simply returns the current time when the todo object was created.</p>
     */
    protected LocalTime generateTimeCreated() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String t = timeFormatter.format(LocalTime.now());
        CharSequence timeNow = t;
        return LocalTime.parse(timeNow);
    }
    
    /**
     * 
     * @return LocalDate
     * <h1>generateDateCreated()</h1>
     * <p>This method simply returns the current date when the todo object was created</p>
     */
    protected LocalDate generateDateCreated() {
        return LocalDate.now();
    }
}
