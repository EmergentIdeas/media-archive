package com.emergentideas.mediaarchive.data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Tag {

	@Id
	@GeneratedValue
	protected Integer id;
	
	@Column(length = 2000)
	protected String value;
	
	@ManyToOne
	protected TagType tagType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TagType getTagType() {
		return tagType;
	}

	public void setTagType(TagType tagType) {
		this.tagType = tagType;
	}
}
