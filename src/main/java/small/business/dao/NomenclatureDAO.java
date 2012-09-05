package small.business.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import small.business.dao.entity.Nomenclature;

/**
 *
 * @author ihor
 */
@Repository
@Transactional
public class NomenclatureDAO extends GenericDAO<Nomenclature> {
}