package customercredit.credit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Credit {

    private int id;
    @NotBlank
    private String creditName;


    public Credit(@JsonProperty("creditName") String creditName) {
        this.creditName = creditName;
    }

    public Credit() {

    }

    public int getId() {
        return id;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }
}
