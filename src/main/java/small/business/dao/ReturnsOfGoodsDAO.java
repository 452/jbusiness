package small.business.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import small.business.dao.entity.ReturnsOfGoodsInvoice;

/**
 *
 * @author root
 */
@Repository
@Transactional
public class ReturnsOfGoodsDAO extends GenericDAO<ReturnsOfGoodsInvoice> {
}