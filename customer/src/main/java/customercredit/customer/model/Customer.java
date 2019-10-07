package customercredit.customer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Customer {

    private int creditId;
    @NotBlank
    private String pesel;
    @NotBlank
    private String firstName;
    @NotBlank
    private String surname;


    public Customer(@JsonProperty("creditId") int creditId, @JsonProperty("pesel") String pesel, @JsonProperty("firstName") String firstName, @JsonProperty("surname") String surname) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.surname = surname;
        this.creditId = creditId;
    }

    public Customer() {

    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public int getCreditId() {
        return creditId;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }
}
