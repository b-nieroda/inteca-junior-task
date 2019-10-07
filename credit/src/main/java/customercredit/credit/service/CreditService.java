package customercredit.credit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import customercredit.credit.dao.CreditDao;
import customercredit.credit.model.Credit;
import customercredit.credit.model.Customer;
import customercredit.credit.model.Product;
import customercredit.credit.model.request.CreditRequestWrapper;
import customercredit.credit.model.request.CreditsIdsRequestWrapper;
import customercredit.credit.model.response.CreditResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditService {
    private final static String CUSTOMER_URL = "http://localhost:8082/api/v1/customer";
    private final static String PRODUCT_URL = "http://localhost:8081/api/v1/product";
    private final CreditDao creditDao;

    @Autowired
    public CreditService(@Qualifier("H2") CreditDao creditDao) {
        this.creditDao = creditDao;
    }

    public int createCredit(@Valid @NotNull CreditRequestWrapper creditRequestWrapper) {
        int creditId = creditDao.getAvailableId();

        Product product = creditRequestWrapper.getProduct();
        product.setCreditId(creditId);

        Customer customer = creditRequestWrapper.getCustomer();
        customer.setCreditId(creditId);

        Credit credit = creditRequestWrapper.getCredit();
        credit.setId(creditId);

        try {
            createProduct(product);
            createCustomer(customer);
        } catch (JsonProcessingException | DataCreationException e) {
            e.printStackTrace();
            return -1;
        }

        return creditDao.insertCredit(credit) > 0 ? credit.getId() : -1;
    }

    private void createCustomer(Customer customer) throws JsonProcessingException, DataCreationException {
        HttpEntity<String> request = new HttpEntity<>(createJSONBody(customer), getHttpHeaders());
        Integer response =
                new RestTemplate().postForObject(
                        CUSTOMER_URL, request, Integer.class);
        if (response == null || response == 0)
            throw new DataCreationException("Customer creation failed");
    }

    private void createProduct(Product product) throws JsonProcessingException, DataCreationException {
        HttpEntity<String> request = new HttpEntity<>(createJSONBody(product), getHttpHeaders());
        Integer response =
                new RestTemplate().postForObject(
                        PRODUCT_URL, request, Integer.class);
        if (response == null || response == 0)
            throw new DataCreationException("Product creation failed");
    }

    public List<CreditResponseWrapper> getAllCredits() {
        List<Credit> credits = creditDao.selectAllCredits();
        List<Integer> creditsIds = credits.stream().mapToInt(Credit::getId).boxed().collect(Collectors.toList());
        CreditsIdsRequestWrapper creditsIdsRequestWrapper = new CreditsIdsRequestWrapper(creditsIds);
        List<Customer> customers = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        try {
            customers = getCustomers(creditsIdsRequestWrapper);
            products = getProducts(creditsIdsRequestWrapper);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return createCreditResponse(credits, products, customers);
    }

    private List<CreditResponseWrapper> createCreditResponse(List<Credit> credits, List<Product> products, List<Customer> customers) {
        List<CreditResponseWrapper> creditResponseWrappers = new ArrayList<>();

        for (Credit credit : credits) {
            Product product = products.stream().filter(product1 -> product1.getCreditId() == credit.getId()).findFirst().orElse(null);
            Customer customer = customers.stream().filter(customer1 -> customer1.getCreditId() == credit.getId()).findFirst().orElse(null);
            if (product != null && customer != null) {
                creditResponseWrappers.add(new CreditResponseWrapper(credit, customer, product));
            }
        }
        return creditResponseWrappers;
    }

    private List<Product> getProducts(CreditsIdsRequestWrapper creditsIds) throws JsonProcessingException {
        HttpHeaders httpHeaders = getHttpHeaders();
        httpHeaders.add("action", "get-products-by-credits-ids");
        HttpEntity<String> request = new HttpEntity<>(createJSONBody(creditsIds), httpHeaders);
        ResponseEntity<Product[]> response =
                new RestTemplate().postForEntity(PRODUCT_URL, request, Product[].class);
        return response.getBody() == null ? new ArrayList<>() : Arrays.asList(response.getBody());
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private List<Customer> getCustomers(CreditsIdsRequestWrapper creditsIds) throws JsonProcessingException {
        HttpHeaders httpHeaders = getHttpHeaders();
        httpHeaders.add("action", "get-customers-by-credits-ids");

        HttpEntity<String> request = new HttpEntity<>(createJSONBody(creditsIds), httpHeaders);
        ResponseEntity<Customer[]> response =
                new RestTemplate().postForEntity(CUSTOMER_URL, request, Customer[].class);
        return response.getBody() == null ? new ArrayList<>() : Arrays.asList(response.getBody());
    }

    private String createJSONBody(Object creditsIds) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(creditsIds);
    }

    private class DataCreationException extends Exception {
        public DataCreationException(String message) {
            super(message);
        }
    }
}
