package customercredit.credit.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import customercredit.credit.model.Credit;
import customercredit.credit.model.Customer;
import customercredit.credit.model.Product;

public class CreditRequestWrapper {
    @JsonProperty("credit")
    private Credit credit;
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("product")
    private Product product;

    public Credit getCredit() {
        return credit;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }
}
