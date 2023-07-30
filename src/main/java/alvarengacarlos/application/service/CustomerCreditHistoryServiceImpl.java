package alvarengacarlos.application.service;

import alvarengacarlos.domain.entity.Customer;
import alvarengacarlos.domain.service.CustomerCreditHistoryService;

public class CustomerCreditHistoryServiceImpl implements CustomerCreditHistoryService {
    @Override
    public Boolean toHaveABadCredit(Customer customer) {
        //Code to request to other service like a rest api to discovery
        return Boolean.FALSE;
    }
}
