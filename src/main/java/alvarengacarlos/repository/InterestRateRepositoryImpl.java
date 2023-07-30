package alvarengacarlos.repository;

import alvarengacarlos.model.InterestRateModel;
import alvarengacarlos.entity.BorrowingEnum;

import java.util.UUID;

public class InterestRateRepositoryImpl implements InterestRateRepository {
    //Create the sql statements to be executed
    @Override
    public Byte findPersonalInterestRate() {
        InterestRateModel interestRateModel = new InterestRateModel(
                UUID.randomUUID(),
                (byte) 4,
                BorrowingEnum.PERSONAL
        );
        return interestRateModel.getInterestRate();
    }

    @Override
    public Byte findGuaranteedInterestRate() {
        InterestRateModel interestRateModel = new InterestRateModel(
                UUID.randomUUID(),
                (byte) 3,
                BorrowingEnum.GUARANTEED
        );
        return interestRateModel.getInterestRate();
    }

    @Override
    public Byte findConsignmentInterestRate() {
        InterestRateModel interestRateModel = new InterestRateModel(
                UUID.randomUUID(),
                (byte) 2,
                BorrowingEnum.CONSIGNMENT
        );
        return interestRateModel.getInterestRate();
    }
}
