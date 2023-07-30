package alvarengacarlos.domain.usecase;

import alvarengacarlos.domain.dto.CustomerDto;
import alvarengacarlos.domain.dto.LoanDto;
import alvarengacarlos.domain.exception.CustomerToHaveABadCreditException;

public interface LendUseCase {
    LoanDto execute(CustomerDto customerDto) throws CustomerToHaveABadCreditException;
}
