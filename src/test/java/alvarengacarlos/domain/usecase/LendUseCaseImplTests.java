package alvarengacarlos.domain.usecase;

import alvarengacarlos.domain.dto.CustomerDto;
import alvarengacarlos.domain.dto.LoanDto;
import alvarengacarlos.domain.entity.Customer;
import alvarengacarlos.domain.exception.CustomerToHaveABadCreditException;
import alvarengacarlos.domain.repository.InterestRateRepository;
import alvarengacarlos.domain.service.CustomerCreditHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("LendImpl")
public class LendUseCaseImplTests {
    private LendUseCaseImpl lend;
    private CustomerCreditHistoryService customerCreditHistoryService;
    private InterestRateRepository interestRateRepository;

    @BeforeEach
    void beforeEach() {
        this.interestRateRepository = mock(InterestRateRepository.class);
        when(this.interestRateRepository.findPersonalInterestRate()).thenReturn((byte) 4);
        when(this.interestRateRepository.findConsignmentInterestRate()).thenReturn((byte) 2);
        when(this.interestRateRepository.findGuaranteedInterestRate()).thenReturn((byte) 3);

        this.customerCreditHistoryService = mock(CustomerCreditHistoryService.class);

        this.lend = new LendUseCaseImpl(interestRateRepository, customerCreditHistoryService);
    }

    @Test
    @DisplayName("should allow personal, consignment and guaranteed borrowings types")
    void shouldAllowPersonalConsignmentAndGuaranteedBorrowingsTypes() throws CustomerToHaveABadCreditException {
        when(this.customerCreditHistoryService.toHaveABadCredit(any(Customer.class))).thenReturn(Boolean.FALSE);
        CustomerDto customerDto = new CustomerDto(
                (byte) 29,
                "000.000.000-00",
                "John Doe",
                new BigDecimal(5000),
                "SP"
        );

        LoanDto loanDto = this.lend.execute(customerDto);

        assertEquals(loanDto.customerName(), "John Doe");
        assertEquals(loanDto.loans().size(), 3);
        assertEquals(loanDto.loans().get(0).borrowingType(), "PERSONAL");
        assertEquals(loanDto.loans().get(0).interestRate(), (byte) 4);
        assertEquals(loanDto.loans().get(1).borrowingType(), "CONSIGNMENT");
        assertEquals(loanDto.loans().get(1).interestRate(), (byte) 2);
        assertEquals(loanDto.loans().get(2).borrowingType(), "GUARANTEED");
        assertEquals(loanDto.loans().get(2).interestRate(), (byte) 3);
    }

    @Test
    @DisplayName("should not allow personal, consignment and guaranteed borrowings types")
    void shouldNotAllowPersonalConsignmentAndGuaranteedBorrowingsTypes() throws CustomerToHaveABadCreditException {
        when(this.customerCreditHistoryService.toHaveABadCredit(any(Customer.class))).thenReturn(Boolean.FALSE);
        CustomerDto customerDto = new CustomerDto(
                (byte) 30,
                "000.000.000-00",
                "John Doe",
                new BigDecimal(4000),
                "RJ"
        );

        LoanDto loanDto = this.lend.execute(customerDto);

        assertEquals(loanDto.customerName(), "John Doe");
        assertEquals(loanDto.loans().size(), 0);
    }

    @Test
    @DisplayName("should not allow borrowings and throws CustomerToHaveABadCreditException")
    void shouldNotAllowBorrowingsAndThrowsCustomerToHaveABadCreditException() {
        when(this.customerCreditHistoryService.toHaveABadCredit(any(Customer.class))).thenReturn(Boolean.TRUE);
        CustomerDto customerDto = new CustomerDto(
                (byte) 29,
                "000.000.000-00",
                "John Doe",
                new BigDecimal(5000),
                "SP"
        );

        assertThrows(CustomerToHaveABadCreditException.class, () -> {
            this.lend.execute(customerDto);
        });
    }
}
