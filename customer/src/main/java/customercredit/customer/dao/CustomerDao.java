package customercredit.customer.dao;

import customercredit.customer.model.Customer;

import java.util.List;

public interface CustomerDao {

    int insertCustomer(Customer product);

    List<Customer> selectCustomers(List<Integer> creditsIds);
}
