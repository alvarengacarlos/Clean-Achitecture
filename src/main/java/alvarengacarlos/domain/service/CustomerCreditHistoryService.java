package alvarengacarlos.domain.service;

import alvarengacarlos.domain.entity.Customer;

public interface CustomerCreditHistoryService {
    Boolean toHaveABadCredit(Customer customer);
}
