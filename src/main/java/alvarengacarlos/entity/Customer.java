package alvarengacarlos.entity;

import java.math.BigDecimal;

public interface Customer {
    Byte getAge();
    String getCpf();
    String getName();
    BigDecimal getIncome();
    String getLocation();
}
