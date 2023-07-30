package alvarengacarlos.usecase;

import alvarengacarlos.dto.CustomerDto;
import alvarengacarlos.dto.LoanDto;
import alvarengacarlos.exception.CustomerToHaveABadCreditException;

public interface LendUseCase {
    LoanDto execute(CustomerDto customerDto) throws CustomerToHaveABadCreditException;
}
