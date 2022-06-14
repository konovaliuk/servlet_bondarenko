package kpi.iasa.mmsa.taxiservice.entities;

import java.io.Serializable;
import java.util.Objects;

public class Passenger implements Serializable {

    private Long id;
    private Long numberOfRides;
    private String cardNumber;
    private String cardCVV;
    private Long userId;

    public Passenger(Long id, Long numberOfRides, String cardNumber, String cardCVV, Long userId) {
        this.id = id;
        this.numberOfRides = numberOfRides;
        this.cardNumber = cardNumber;
        this.cardCVV = cardCVV;
        this.userId = userId;
    }

    public Long getNumberOfRides() {
        return numberOfRides;
    }

    public void setNumberOfRides(Long numberOfRides) {
        this.numberOfRides = numberOfRides;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(id, passenger.id) && Objects.equals(numberOfRides, passenger.numberOfRides) &&
                Objects.equals(cardNumber, passenger.cardNumber) && Objects.equals(cardCVV, passenger.cardCVV) &&
                Objects.equals(userId, passenger.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfRides, cardNumber, cardCVV, userId);
    }
}
