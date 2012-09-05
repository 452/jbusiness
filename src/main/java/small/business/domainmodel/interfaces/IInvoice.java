package small.business.domainmodel.interfaces;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public interface IInvoice extends Serializable{
    public Integer getId();
    public Date getDate();
    public String getCounterPartyTitle();
    public String getStoreHouseTitle();
}
