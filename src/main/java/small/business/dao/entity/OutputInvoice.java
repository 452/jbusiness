package small.business.dao.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.eclipse.persistence.annotations.Cache;

import small.business.domainmodel.interfaces.IElement;

/**
 * 
 * @author root
 */
@Entity
@Cache
@Table(name = "outputofinvoicesongoods")
@NamedQueries({ @NamedQuery(name = "OutputInvoice.findAll", query = "SELECT o FROM OutputInvoice o ORDER BY o.createdDate DESC"), @NamedQuery(name = "OutputInvoice.findById", query = "SELECT o FROM OutputInvoice o WHERE o.id = :id") })
public class OutputInvoice implements Serializable, IElement<OutputInvoice> {

	private static final long	serialVersionUID	= 1L;
	@Id
	@Basic(optional = false)
	@TableGenerator(name = "outputofinvoicesongoods", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", allocationSize = 1, initialValue = 0)
	@GeneratedValue(generator = "outputofinvoicesongoods", strategy = GenerationType.TABLE)
	@Column(name = "id", unique = true, nullable = false)
	private Long				id;
	@Basic(optional = false)
	@Column(name = "date", nullable = false)
	private String				createdDate			= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	@Basic(optional = false)
	@Column(name = "statusofpayment", nullable = false)
	private Integer				statusOfPayment		= 0;
	@Basic(fetch = FetchType.LAZY, optional = false)
	@Column(name = "paidamount", nullable = false)
	private Double				paidAmount			= 0.0;
	@Column(name = "info", nullable = true)
	@Basic(fetch = FetchType.LAZY)
	private String				info;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "counterpartyid", nullable = false)
	private CounterParties		counterParty;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "storehouse", nullable = false)
	private StoreHouse			storeHouse			= new StoreHouse(1L, "Основний склад");
	@OneToMany(targetEntity = OutputGoods.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "invoiceid", referencedColumnName = "id", unique = true, updatable = true)
	@OrderBy("price ASC")
	private Set<OutputGoods>	goods				= new HashSet<OutputGoods>();

	public OutputInvoice() {
	}

	public OutputInvoice(Long id) {
		this.id = id;
	}

	public OutputInvoice(Long id, String createdDate) {
		this.id = id;
		this.createdDate = createdDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date result = null;
		try {
			if (createdDate != null) {
				result = df.parse(createdDate);
			}
		}
		catch (ParseException ex) {
			Logger.getLogger(ComingsInvoice.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	public void setCreatedDate(Date createdDate) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createdDate = dateFormat.format(createdDate);
	}

	public Integer getStatusOfPayment() {
		if (statusOfPayment == null) {
			statusOfPayment = 0;
		}
		return statusOfPayment;
	}

	public void setStatusOfPayment(Integer statusOfPayment) {
		this.statusOfPayment = statusOfPayment;
	}

	public Double getPaidAmount() {
		if (paidAmount == null) {
			paidAmount = 0.0;
		}
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof OutputInvoice)) {
			return false;
		}
		OutputInvoice other = (OutputInvoice) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public CounterParties getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(CounterParties counterParty) {
		this.counterParty = counterParty;
	}

	public StoreHouse getStoreHouse() {
		return storeHouse;
	}

	public void setStoreHouse(StoreHouse storeHouse) {
		this.storeHouse = storeHouse;
	}

	public Set<OutputGoods> getGoods() {
		return goods;
	}

	@Deprecated
	public void setGoods(Set<OutputGoods> goods) {
		this.goods = goods;
	}

	/**
	 * This method for add goods to invoice
	 * 
	 * @param OutputGoods
	 * @return true if this Goods did not already contained in the Invoice
	 */
	public boolean addGoods(OutputGoods goods) {
		return this.goods.add(goods);
	}
}