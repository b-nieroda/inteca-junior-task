package customercredit.product.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreditsIdsRequestWrapper {
    @JsonProperty("ids")
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }
}
