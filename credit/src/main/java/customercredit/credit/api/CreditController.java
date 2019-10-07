package customercredit.credit.api;

import customercredit.credit.model.request.CreditRequestWrapper;
import customercredit.credit.model.response.CreditResponseWrapper;
import customercredit.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController("api/v1/credit")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    public int createCredit(@RequestBody @Valid @NotNull CreditRequestWrapper creditRequestWrapper){
        return creditService.createCredit(creditRequestWrapper);
    }

    @GetMapping
    public List<CreditResponseWrapper> getCredits(){
        return creditService.getAllCredits();
    }
}
