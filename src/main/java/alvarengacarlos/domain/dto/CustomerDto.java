package alvarengacarlos.domain.dto;

import java.math.BigDecimal;

public record CustomerDto(Byte age, String cpf, String name, BigDecimal income, String location) {}
