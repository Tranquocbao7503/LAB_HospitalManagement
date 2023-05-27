/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestSave {
    public static void saveDataToBinaryFile(Object data, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
            System.out.println("Data saved to " + fileName + " successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving data to " + fileName + ": " + e.getMessage());
        }
    }
    
  
    public static void main(String[] args) {
        // Example usage
        String data = "Hello, World!";
        String fileName = "C:\\Users\\PC\\Desktop\\data.dat\\";
        saveDataToBinaryFile(data, fileName);
    }
}



    

