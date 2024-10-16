package com.dbs;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void shouldAnswerWithTrue(){
         try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("src\\test\\java\\com\\dbs\\Testdata");
            // Read JSON file and convert to HashMap
            List<Map<String, Object>> list = objectMapper.readValue(jsonFile,new TypeReference<List<Map<String,Object>>>() {});
            
            // Print the List of Maps
            for (int i = 0; i < list.size(); i++) {
                System.out.println("Item " + i + ":");
                for (Map.Entry<String, Object> entry : list.get(i).entrySet()) {
                    System.out.println("  " + entry.getKey() + " : " + entry.getValue());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
            String currentDir = Paths.get("").toAbsolutePath().toString(); 
            System.out.println("Current working directory: " + currentDir);
    }
}
