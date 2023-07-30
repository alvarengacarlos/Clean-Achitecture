package alvarengacarlos.service;

import alvarengacarlos.entity.Customer;

public interface CustomerCreditHistoryService {
    Boolean toHaveABadCredit(Customer customer);
}
