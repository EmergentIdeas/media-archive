package com.emergentideas.mediaarchive.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TagType {

	@Id
	@GeneratedValue
	protected Integer id;
	
	@Column(length = 2000)
	protected String tagType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	
	
}
