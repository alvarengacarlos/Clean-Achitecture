package alvarengacarlos.entity;

public interface Borrowing {
    Byte getInterestRate();
    Boolean shouldLendMoney(Customer customer);
    BorrowingEnum getType();
}
