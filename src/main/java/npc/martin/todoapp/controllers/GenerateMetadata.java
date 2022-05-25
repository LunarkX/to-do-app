package npc.martin.todoapp.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author bikathi_martin
 */
abstract class GenerateMetadata {
    protected String generateUniqueId() {
        String uuid = UUID.randomUUID().toString();
        String[] uuidComponents = uuid.split("-");
        
        String uniqueTodoId = uuidComponents[0];
        return uniqueTodoId;
    }
    
    protected LocalTime generateTimeCreated() {
        return LocalTime.now();
    }
    
    protected LocalDate generateDateCreated() {
        return LocalDate.now();
    }
}
