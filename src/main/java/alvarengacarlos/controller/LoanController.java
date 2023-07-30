package alvarengacarlos.controller;

import alvarengacarlos.repository.InterestRateRepositoryImpl;
import alvarengacarlos.service.CustomerCreditHistoryServiceImpl;
import alvarengacarlos.dto.CustomerDto;
import alvarengacarlos.dto.LoanDto;
import alvarengacarlos.exception.CustomerToHaveABadCreditException;
import alvarengacarlos.usecase.LendUseCase;
import alvarengacarlos.usecase.LendUseCaseImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/loans")
public class LoanController {
    @PostMapping()
    public ResponseEntity lend(@RequestBody CustomerDto customerDto) {
        try {
            System.out.println(customerDto);
            LendUseCase lendUseCase = this.makeALendUseCase();
            LoanDto loanDto = lendUseCase.execute(customerDto);
            return ResponseEntity.ok().body(loanDto);

        } catch (CustomerToHaveABadCreditException customerToHaveABadCreditException) {
            return ResponseEntity.ok().body(customerToHaveABadCreditException.getMessage());
            //INFO_CUSTOMER_TO_HAVE_A_BAD_CREDIT
            //ERR_
        }
    }

    private LendUseCase makeALendUseCase() {
        return new LendUseCaseImpl(
                new InterestRateRepositoryImpl(),
                new CustomerCreditHistoryServiceImpl()
        );
    }
}
