package customercredit.customer.dao;

import customercredit.customer.model.Customer;
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
public class CustomerDataAccessService implements CustomerDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setPesel(rs.getString("pesel"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setSurname(rs.getString("surname"));
            customer.setCreditId(rs.getInt("creditId"));

            return customer;
        }

    }


    @Override
    public int insertCustomer(Customer customer) {
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("pesel", customer.getPesel());
        argMap.put("firstName", customer.getFirstName());
        argMap.put("surname", customer.getSurname());
        argMap.put("creditId", customer.getCreditId());

        try {
            return jdbcTemplate.update("INSERT INTO Customer (pesel, firstName, surname, creditId) " + "values(:pesel,:firstName,:surname,:creditId)",
                    argMap);
        }catch(DataAccessException e){
            return 0;
        }

    }

    @Override
    public List<Customer> selectCustomers(List<Integer> creditsIds) {
        Map idsMap = Collections.singletonMap("ids", creditsIds);
        return jdbcTemplate.query("SELECT * FROM Customer WHERE creditId IN (:ids)",idsMap, new CustomerRowMapper());
    }
}
