package com.emergentideas.mediaarchive.data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ResourceAssociation {

	@Id
	@GeneratedValue
	protected Integer id;
	
	@Column(length = 2000)
	protected String resourceId;
	
	@Column(length = 200)
	protected String associationName;
	
}
