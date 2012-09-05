package small.business.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import small.business.dao.entity.ComingsInvoice;

/**
 *
 * @author ihor
 */
@Repository
@Transactional
public class ComingInvoicesDAO extends GenericDAO<ComingsInvoice> {
}