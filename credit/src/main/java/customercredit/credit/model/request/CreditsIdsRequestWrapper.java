package customercredit.credit.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import customercredit.credit.model.Credit;
import customercredit.credit.model.Customer;
import customercredit.credit.model.Product;

import java.util.List;

public class CreditsIdsRequestWrapper {
    @JsonProperty("ids")
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public CreditsIdsRequestWrapper(List<Integer> ids) {
        this.ids = ids;
    }
}
