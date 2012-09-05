package small.business.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import small.business.dao.entity.CounterParties;

/**
 *
 * @author ihor
 */
@Repository
@Transactional
public class CounterPartiesDAO extends GenericDAO<CounterParties> {
}