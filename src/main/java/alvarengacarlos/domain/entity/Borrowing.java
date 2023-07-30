package alvarengacarlos.domain.entity;

public interface Borrowing {
    Byte getInterestRate();
    Boolean shouldLendMoney(Customer customer);
    BorrowingEnum getType();
}
