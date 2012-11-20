package small.business.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import small.business.domainmodel.interfaces.IElement;
import small.business.domainmodel.interfaces.IGroup;

//@Entity
public class DeviceInRepair implements Serializable {//, IElement<DeviceInRepair>, IGroup {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private Long parentId;
	@Column
	private String isGroup;
	@Column
	@Lob
	private String title;
	@Column
	@Lob
	private String info;
	private List<PartsForRepairDevices> partsForRepairDevices;
}