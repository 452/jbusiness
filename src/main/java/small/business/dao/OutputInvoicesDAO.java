package small.business.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import small.business.dao.entity.OutputInvoice;

/**
 *
 * @author ihor
 */
@Repository
@Transactional
public class OutputInvoicesDAO extends GenericDAO<OutputInvoice> {
}