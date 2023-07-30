package alvarengacarlos.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("ConsignmentBorrowing")
public class ConsignmentBorrowingTests {
    private ConsignmentBorrowing consignmentBorrowing;
    private Customer customer;

    @BeforeEach
    void beforeEach() {
        this.customer = mock(Customer.class);
        this.consignmentBorrowing = new ConsignmentBorrowing((byte) 2);
    }

    @Test
    @DisplayName("should lend money because the customer earn more than or equals 5000")
    void shouldLendMoneyBecauseTheCustomerEarnMoreThanOrEquals5000() {
        when(this.customer.getIncome()).thenReturn(new BigDecimal(5000));

        Boolean result = this.consignmentBorrowing.shouldLendMoney(this.customer);

        assertEquals(result, Boolean.TRUE);
    }

    @Test
    @DisplayName("should get a borrowing type")
    void shouldGetABorrowingType() {
        String result = this.consignmentBorrowing.getType().name();

        assertEquals(result, "CONSIGNMENT");
    }
}
