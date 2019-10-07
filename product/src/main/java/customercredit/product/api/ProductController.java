package customercredit.product.api;

import customercredit.product.model.Product;
import customercredit.product.model.request.CreditsIdsRequestWrapper;
import customercredit.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService creditService) {
        this.productService = creditService;
    }

    @PostMapping
    public int createProduct(@RequestBody @Valid @NotNull Product product) {
        return productService.createProduct(product);
    }

    @PostMapping(headers = "action=get-products-by-credits-ids")
    public List<Product> getProducts(@RequestBody @Valid @NotNull CreditsIdsRequestWrapper creditsIds) {
        return productService.getProducts(creditsIds);
    }
}
