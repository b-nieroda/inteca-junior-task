package customercredit.product.dao;

import customercredit.product.model.Product;

import java.util.List;

public interface ProductDao {

    int insertProduct(Product product);

    List<Product> selectProducts(List<Integer> creditsIds);

}
