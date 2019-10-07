package customercredit.product.dao;

import customercredit.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("H2")
public class ProductDataAccessService implements ProductDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setCreditId(rs.getInt("creditId"));
            product.setProductName(rs.getString("productName"));
            product.setValue(rs.getInt("value"));
            return product;
        }

    }


    @Override
    public int insertProduct(Product product) {
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("creditId", product.getCreditId());
        argMap.put("productName", product.getProductName());
        argMap.put("value", product.getValue());
        try {
            return jdbcTemplate.update("INSERT INTO Product (creditId, productName, value) " + "values(:creditId,:productName,:value)",
                    argMap);
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<Product> selectProducts(List<Integer> creditsIds) {
        Map idsMap = Collections.singletonMap("ids", creditsIds);
        return jdbcTemplate.query("SELECT * FROM Product WHERE creditId IN (:ids)",idsMap, new ProductRowMapper());
    }
}
