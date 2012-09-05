package small.business.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import small.business.dao.entity.History;

/**
 *
 * @author root
 */
@Repository
@Transactional
public class HistoryDAO extends GenericDAO<History> {
}