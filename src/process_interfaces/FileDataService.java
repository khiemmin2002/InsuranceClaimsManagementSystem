package process_interfaces;

public interface FileDataService {
    public void writeDataToFile();
    public String readDataToFile();
    public void deleteDataToFile();
    public void updateDataToFile();
    public boolean isFileExists();
    public boolean isFileEmpty();
    public boolean isDuplicate(String data);
}
