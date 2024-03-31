package system;

import models.InsuranceCard;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InsuranceList {
    private List<InsuranceCard> insuranceCards;
    private String filePath;

    public InsuranceList(String filePath) {
        this.insuranceCards = new ArrayList<>();
        this.filePath = filePath;
    }

    public List<InsuranceCard> getInsuranceCards() {
        return insuranceCards;
    }

    public void loadFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    InsuranceCard card = parseLineToInsuranceCard(line);
                    if (card != null) {
                        insuranceCards.add(card);
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while loading insurance cards from the file: " + e.getMessage());
            }
        }
    }

    private InsuranceCard parseLineToInsuranceCard(String line) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String[] parts = line.split(",");

        if (parts.length == 4) {
            try {
                Date expiredDate = dateFormat.parse(parts[3]);
                return new InsuranceCard(parts[0], parts[1], parts[2], expiredDate);
            } catch (ParseException e) {
                System.err.println("Failed to parse the date: " + parts[3]);
                return null;
            }
        }
        return null;
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (InsuranceCard card : insuranceCards) {
                writer.write(cardToFileFormat(card));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving insurance cards to the file: " + e.getMessage());
        }
    }

    private String cardToFileFormat(InsuranceCard card) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String expiredDateString = card.getExpiredDate() != null ? dateFormat.format(card.getExpiredDate()) : "N/A";
        // Assuming the format "cardNum,cardHolder,policyOwner,expiredDate"
        return card.getCardNum() + "," + card.getCardHolder() + "," + card.getPolicyOwner() + "," + expiredDateString;
    }

    public void addInsuranceCard(InsuranceCard card) {
        insuranceCards.add(card);
        // Here you can also write the logic to save the updated list to the file if needed
    }

    public void updateInsuranceCard(String cardNumber, InsuranceCard updatedCard) {
        for (int i = 0; i < insuranceCards.size(); i++) {
            if (insuranceCards.get(i).getCardNum().equals(cardNumber)) {
                insuranceCards.set(i, updatedCard);
                break;
            }
        }
        // After updating the list, you would also write the updated list back to the file
        saveToFile();
    }

    public void deleteInsuranceCard(String cardNumber) {
        insuranceCards.removeIf(card -> card.getCardNum().equals(cardNumber));
        // After deleting the card, you would also update the file
        saveToFile();
    }

    public InsuranceCard getInsuranceCard(String cardNumber) {
        for (InsuranceCard card : insuranceCards) {
            if (card.getCardNum().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }
}
