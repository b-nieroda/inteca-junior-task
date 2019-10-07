package customercredit.customer.api;

import customercredit.customer.model.Customer;
import customercredit.customer.model.request.CreditsIdsRequestWrapper;
import customercredit.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public int createCustomer(@RequestBody @Valid @NotNull Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PostMapping(headers = "action=get-customers-by-credits-ids")
    public List<Customer> getCustomers(@RequestBody @Valid @NotNull CreditsIdsRequestWrapper creditsIds) {
        return customerService.getCustomers(creditsIds);
    }
}
