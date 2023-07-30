package alvarengacarlos.service;

import alvarengacarlos.entity.Customer;

public class CustomerCreditHistoryServiceImpl implements CustomerCreditHistoryService {
    @Override
    public Boolean toHaveABadCredit(Customer customer) {
        //Code to request to other service like a rest api to discovery
        return Boolean.FALSE;
    }
}
