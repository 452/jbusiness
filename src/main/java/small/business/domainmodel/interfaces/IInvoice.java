package small.business.domainmodel.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import small.business.dao.entity.CounterParties;
import small.business.dao.entity.StoreHouse;

/**
 *
 * @author root
 */
public interface IInvoice extends Serializable {

    public Integer getId();

    public Date getDate();

    public CounterParties getCounterParty();

    public StoreHouse getStoreHouse();

    public Set<IGoods> getGoods();

    public String getCounterPartyTitle();

    public String getStoreHouseTitle();
}
