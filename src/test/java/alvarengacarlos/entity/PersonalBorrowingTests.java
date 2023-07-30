package alvarengacarlos.entity;

import alvarengacarlos.entity.Customer;
import alvarengacarlos.entity.PersonalBorrowing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("PersonalBorrowing")
public class PersonalBorrowingTests {
    private PersonalBorrowing personalBorrowing;
    private Customer customer;

    @BeforeEach
    void beforeEach() {
        this.customer = mock(Customer.class);
        this.personalBorrowing = new PersonalBorrowing((byte) 4);
    }

    @Test
    @DisplayName("should lend money because the customer earn less than or equals 3000")
    void shouldLendMoneyBecauseTheCustomerEarnLessThanOrEquals3000() {
        when(this.customer.getIncome()).thenReturn(new BigDecimal(3000));

        Boolean result = this.personalBorrowing.shouldLendMoney(this.customer);

        assertEquals(result, Boolean.TRUE);
    }

    @Test
    @DisplayName("should lend money because the customer earn between 3000 and 5000, to be less than 30 years and live in SP state")
    void shouldLendMoneyBecauseTheCustomerEarnBetween3000And5000ToHaveLessThan30YearsAndLiveInSPState() {

        when(this.customer.getIncome()).thenReturn(new BigDecimal(4000));
        when(this.customer.getAge()).thenReturn((byte) 29);
        when(this.customer.getLocation()).thenReturn("SP");

        Boolean result = this.personalBorrowing.shouldLendMoney(this.customer);

        assertEquals(result, Boolean.TRUE);
    }

    @Test
    @DisplayName("should get a borrowing type")
    void shouldGetABorrowingType() {
        String result = this.personalBorrowing.getType().name();

        assertEquals(result, "PERSONAL");
    }
}
