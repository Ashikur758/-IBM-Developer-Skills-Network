// Event.java
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    // Step 1: Declare private variables for event name, dateTime, duration, and timezone
    private String name;
    private ZonedDateTime dateTime;
    private Duration duration;
    
    // Step 2: Create a constructor to initialize these variables
    public Event(String name, ZonedDateTime dateTime, Duration duration) {
        this.name = name;
        this.dateTime = dateTime;
        this.duration = duration;
    }
    
    // Step 3: Create getter methods for each variable
    public String getName() {
        return name;
    }
    
    public ZonedDateTime getDateTime() {
        return dateTime;
    }
    
    public Duration getDuration() {
        return duration;
    }
    
    // Step 4: Create a method to format and display the event date using a specified pattern
    public String formatDateTime(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }
    
    // Step 5: Create a method to calculate time remaining until the event
    public Duration timeUntilEvent() {
        ZonedDateTime now = ZonedDateTime.now();
        if (now.isAfter(dateTime)) {
            return Duration.ZERO; // Event has already occurred
        }
        return Duration.between(now, dateTime);
    }
    
    // Step 6: Create a method to convert the event time to a different timezone
    public ZonedDateTime convertToTimezone(String timezoneId) {
        return dateTime.withZoneSameInstant(ZoneId.of(timezoneId));
    }
    
    // Override toString method for easy printing
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        return name + " - " + dateTime.format(formatter) + " (Duration: " + 
               duration.toHours() + "h " + duration.toMinutesPart() + "m)";
    }
}