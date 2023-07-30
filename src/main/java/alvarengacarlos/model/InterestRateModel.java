package alvarengacarlos.model;

import alvarengacarlos.entity.BorrowingEnum;

import java.util.UUID;

public class InterestRateModel {
    //Represent a ORM object
    private UUID id;
    private Byte interestRate;
    private BorrowingEnum type;

    public InterestRateModel() {}

    public InterestRateModel(UUID id, Byte interestRate, BorrowingEnum type) {
        this.id = id;
        this.interestRate = interestRate;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Byte getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Byte interestRate) {
        this.interestRate = interestRate;
    }

    public BorrowingEnum getType() {
        return type;
    }

    public void setType(BorrowingEnum type) {
        this.type = type;
    }
}
