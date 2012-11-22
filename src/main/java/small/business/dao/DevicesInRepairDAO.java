package small.business.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import small.business.dao.entity.DeviceInRepair;

/**
 *
 * @author ihor
 */
@Repository
@Transactional
public class DevicesInRepairDAO extends GenericDAO<DeviceInRepair> {
}