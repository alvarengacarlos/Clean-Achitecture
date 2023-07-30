package alvarengacarlos.domain.entity;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class PersonalBorrowing implements Borrowing {
    private Byte interestRate;

    public PersonalBorrowing(Byte interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public Byte getInterestRate() {
        return this.interestRate;
    }

    @Override
    public Boolean shouldLendMoney(Customer customer) {
        Predicate<Customer> earnLessThanOrEquals3000 = c -> c.getIncome().compareTo(new BigDecimal(3000)) <= 0;
        if (earnLessThanOrEquals3000.test(customer)) {
            return Boolean.TRUE;
        }

        Predicate<Customer> earnBetween3000And5000 = c -> (c.getIncome().compareTo(new BigDecimal(3000)) >= 0) &&
                (c.getIncome().compareTo(new BigDecimal(5000)) <= 0);
        Predicate<Customer> toBeLessThan30Years = c -> c.getAge() < 30;
        Predicate<Customer> liveInSP = c -> c.getLocation().equals("SP");
        if (earnBetween3000And5000.and(toBeLessThan30Years).and(liveInSP).test(customer)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public BorrowingEnum getType() {
        return BorrowingEnum.PERSONAL;
    }
}
