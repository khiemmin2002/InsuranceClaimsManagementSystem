import java.text.SimpleDateFormat;
import java.util.Date;

public class InsuranceCard {
    private String cardNum;
    private String cardHolder;
    private String policyOwner;
    private Date expiredDate;

    public InsuranceCard() {
    }

    public InsuranceCard(String cardHolder, String policyOwner, Date expiredDate) {
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expiredDate = expiredDate;
        this.cardNum = generateCardNum();
    }


    public String getCardNum() {
        return cardNum;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    // Set the card number whenever the card is created
    // The card number is 10 digits long, generate randomly, and unique (The num can start with 0)
    // This method now returns a String that represents the card number
    public String generateCardNum() {
        StringBuilder cardNumBuilder = new StringBuilder();
        // Ensure the first digit can be '0'
        cardNumBuilder.append((int) (Math.random() * 10));

        // Now proceed with the rest of the digits
        for (int i = 1; i < 10; i++) { // Start from 1 since we've already added the first digit
            cardNumBuilder.append((int) (Math.random() * 10));
        }
        return cardNumBuilder.toString();
    }

    // Format the expired date to mm/dd/yyyy
    public String getFormattedExpiredDate() {
        return new SimpleDateFormat("MM/dd/yyyy").format(expiredDate);
    }

    @Override
    public String toString() {
        return String.format ("Card Number: %s, Card Holder: %s, Policy Owner: %s, Expired Date: %s", cardNum, cardHolder, policyOwner, getFormattedExpiredDate());
    }
}
