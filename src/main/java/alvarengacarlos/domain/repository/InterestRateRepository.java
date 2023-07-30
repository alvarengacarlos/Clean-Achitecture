package alvarengacarlos.domain.repository;

public interface InterestRateRepository {
    Byte findPersonalInterestRate();
    Byte findGuaranteedInterestRate();
    Byte findConsignmentInterestRate();
}
