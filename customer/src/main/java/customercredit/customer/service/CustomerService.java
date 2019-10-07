package customercredit.customer.service;

import customercredit.customer.dao.CustomerDao;
import customercredit.customer.model.Customer;
import customercredit.customer.model.request.CreditsIdsRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;

    @Autowired
    public CustomerService(@Qualifier("H2") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public int createCustomer(Customer customer) {
        return customerDao.insertCustomer(customer);
    }

    public List<Customer> getCustomers(@Valid @NotNull CreditsIdsRequestWrapper creditsIds) {
        return customerDao.selectCustomers(creditsIds.getIds());
    }
}
