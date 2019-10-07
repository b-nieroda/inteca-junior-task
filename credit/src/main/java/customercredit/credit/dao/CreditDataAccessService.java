package customercredit.credit.dao;

import customercredit.credit.model.Credit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("H2")
public class CreditDataAccessService implements CreditDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class CreditRowMapper implements RowMapper<Credit> {
        @Override
        public Credit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Credit credit = new Credit();
            credit.setId(rs.getInt("id"));
            credit.setCreditName(rs.getString("creditName"));
            return credit;
        }

    }


    @Override
    public int insertCredit(Credit credit) {
        return jdbcTemplate.update("INSERT INTO Credit (id, creditName) " + "values(?,?)",
                credit.getId(), credit.getCreditName());
    }

    @Override
    public int getAvailableId() {
        Integer availableId = jdbcTemplate.queryForObject("SELECT MIN(t1.id + 1) AS nextID\n" +
                "FROM Credit t1\n" +
                "   LEFT JOIN Credit t2\n" +
                "       ON t1.id + 1 = t2.id\n" +
                "WHERE t2.id IS NULL", Integer.class);
        return availableId == null ? 1 : availableId;
    }

    @Override
    public List<Credit> selectAllCredits() {
        return jdbcTemplate.query("SELECT * FROM Credit", new CreditRowMapper());
    }
}
