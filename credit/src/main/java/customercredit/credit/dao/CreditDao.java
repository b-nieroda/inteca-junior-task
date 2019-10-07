package customercredit.credit.dao;



import customercredit.credit.model.Credit;

import java.util.List;

public interface CreditDao {

    int insertCredit(Credit credit);
    int getAvailableId();
    List<Credit> selectAllCredits();
}
