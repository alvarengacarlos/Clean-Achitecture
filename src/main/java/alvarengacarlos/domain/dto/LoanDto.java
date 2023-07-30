package alvarengacarlos.domain.dto;

import java.util.List;

public record LoanDto(String customerName, List<Loan> loans) {
    public record Loan(String borrowingType, Byte interestRate) {}
}
