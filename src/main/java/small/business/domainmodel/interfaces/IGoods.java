package small.business.domainmodel.interfaces;

import java.io.Serializable;
import small.business.dao.entity.Nomenclature;

/**
 *
 * @author root
 */
public interface IGoods extends Serializable {

    public Long getId();
    
    public Nomenclature getNomenclature();
    
    public void setNomenclature(Nomenclature nomenclature);

    public Integer getQuantity();
    
    public void setQuantity(Integer quantity);
    
    public Double getPrice();
    
    public void setPrice(Double price);
    
    public Double getSum();
}
