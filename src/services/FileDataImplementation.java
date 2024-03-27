package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileDataImplementation implements FileDataService {
    private final String filePath;

    public FileDataImplementation(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeDataToFile(String data) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.APPEND)) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    @Override
    public String readDataToFile() {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return data.toString();
    }

    @Override
    public void deleteDataToFile() {
        // Implementation for deleting data in the file
    }

    @Override
    public void updateDataToFile() {
        // Implementation for updating data in the file
    }

    @Override
    public boolean isFileExists() {
        // Check if the file exists
        return false;
    }

    @Override
    public boolean isFileEmpty() {
        // Check if the file is empty
        return true;
    }

    @Override
    public boolean isDuplicate(String data) {
        // Check if the data is a duplicate
        return false;
    }
}
