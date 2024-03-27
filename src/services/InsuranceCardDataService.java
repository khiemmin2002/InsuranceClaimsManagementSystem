package services;

import models.InsuranceCard;

public class InsuranceCardDataService {
    private FileDataService fileDataService;

    public InsuranceCardDataService(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    public void saveInsuranceCard(InsuranceCard card) {
        // Convert the InsuranceCard to a string format (e.g., JSON or CSV)
        // Use fileDataService.writeDataToFile() to save it
    }

    public InsuranceCard loadInsuranceCard() {
        // Use fileDataService.readDataToFile() to get the data
        // Convert the string data back to an InsuranceCard object
        return null; // placeholder
    }

    // Additional methods for delete, update, etc., utilizing fileDataService methods
}
