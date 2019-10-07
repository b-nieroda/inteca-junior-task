package customercredit.credit.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import customercredit.credit.model.Credit;
import customercredit.credit.model.Customer;
import customercredit.credit.model.Product;

public class CreditResponseWrapper {
    @JsonProperty("credit")
    private Credit credit;
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("product")
    private Product product;

    public CreditResponseWrapper(Credit credit, Customer customer, Product product) {
        this.credit = credit;
        this.customer = customer;
        this.product = product;
    }
}
