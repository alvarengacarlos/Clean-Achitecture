package alvarengacarlos.domain.usecase;

import alvarengacarlos.domain.dto.CustomerDto;
import alvarengacarlos.domain.dto.LoanDto;
import alvarengacarlos.domain.entity.Borrowing;
import alvarengacarlos.domain.entity.ConsignmentBorrowing;
import alvarengacarlos.domain.entity.Customer;
import alvarengacarlos.domain.entity.CustomerImpl;
import alvarengacarlos.domain.entity.GuaranteedBorrowing;
import alvarengacarlos.domain.entity.PersonalBorrowing;
import alvarengacarlos.domain.exception.CustomerToHaveABadCreditException;
import alvarengacarlos.domain.repository.InterestRateRepository;
import alvarengacarlos.domain.service.CustomerCreditHistoryService;

import java.util.ArrayList;
import java.util.List;

public class LendUseCaseImpl implements LendUseCase {
    private InterestRateRepository interestRateRepository;
    private CustomerCreditHistoryService customerCreditHistoryService;

    public LendUseCaseImpl(InterestRateRepository interestRateRepository, CustomerCreditHistoryService customerCreditHistoryService) {
        this.interestRateRepository = interestRateRepository;
        this.customerCreditHistoryService = customerCreditHistoryService;
    }

    public LoanDto execute(CustomerDto customerDto) throws CustomerToHaveABadCreditException {
        Customer customer = new CustomerImpl(
                customerDto.age(),
                customerDto.cpf(),
                customerDto.name(),
                customerDto.income(),
                customerDto.location()
        );

        this.checkCustomerCreditHistory(customer);
        List<Borrowing> borrowings = this.getAvailableLoansTo(customer);

        List<LoanDto.Loan> loans = borrowings.stream().map(borrowing -> new LoanDto.Loan(borrowing.getType().name(), borrowing.getInterestRate())).toList();
        return new LoanDto(
            customer.getName(),
            loans
        );
    }

    private void checkCustomerCreditHistory(Customer customer) throws CustomerToHaveABadCreditException {
        Boolean toHaveABadCredit = this.customerCreditHistoryService.toHaveABadCredit(customer);
        if (toHaveABadCredit) {
            throw new CustomerToHaveABadCreditException();
        }
    }

    private List<Borrowing> getAvailableLoansTo(Customer customer) {
        List<Borrowing> borrowings = new ArrayList<>();

        Byte personalInterestRate = this.interestRateRepository.findPersonalInterestRate();
        Borrowing personalBorrowing = new PersonalBorrowing(personalInterestRate);
        if (personalBorrowing.shouldLendMoney(customer)) {
            borrowings.add(personalBorrowing);
        }

        Byte consignmentInterestRate = this.interestRateRepository.findConsignmentInterestRate();
        Borrowing consignmentBorrowing = new ConsignmentBorrowing(consignmentInterestRate);
        if (consignmentBorrowing.shouldLendMoney(customer)) {
            borrowings.add(consignmentBorrowing);
        }

        Byte guaranteedInterestRate = this.interestRateRepository.findGuaranteedInterestRate();
        Borrowing guaranteedBorrowing = new GuaranteedBorrowing(guaranteedInterestRate);
        if (guaranteedBorrowing.shouldLendMoney(customer)) {
            borrowings.add(guaranteedBorrowing);
        }

        return borrowings;
    }
}
