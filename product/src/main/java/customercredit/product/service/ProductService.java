package customercredit.product.service;

import customercredit.product.dao.ProductDao;
import customercredit.product.model.Product;
import customercredit.product.model.request.CreditsIdsRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(@Qualifier("H2") ProductDao productDao) {
        this.productDao = productDao;
    }

    public int createProduct(Product credit) {
        return productDao.insertProduct(credit);
    }

    public List<Product> getProducts(@Valid @NotNull CreditsIdsRequestWrapper creditsIds) {
        return productDao.selectProducts(creditsIds.getIds());
    }
}
