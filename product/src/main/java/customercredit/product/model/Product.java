package customercredit.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Product {

    private int creditId;
    @NotBlank
    private String productName;
    private int value;

    public Product(@JsonProperty("creditId") int creditId, @JsonProperty("productName") String productName,@JsonProperty("value") int value) {
        this.productName = productName;
        this.value = value;
        this.creditId = creditId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public Product() {

    }

    public String getProductName() {
        return productName;
    }

    public int getValue() {
        return value;
    }

    public int getCreditId() {
        return creditId;
    }
}
