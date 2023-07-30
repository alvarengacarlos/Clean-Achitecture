package alvarengacarlos.entity;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class ConsignmentBorrowing implements Borrowing {
    private Byte interestRate;

    public ConsignmentBorrowing(Byte interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public Byte getInterestRate() {
        return this.interestRate;
    }

    @Override
    public Boolean shouldLendMoney(Customer customer) {
        Predicate<Customer> predicate0 = c -> c.getIncome().compareTo(new BigDecimal(5000)) >= 0;
        if (predicate0.test(customer)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public BorrowingEnum getType() {
        return BorrowingEnum.CONSIGNMENT;
    }
}
