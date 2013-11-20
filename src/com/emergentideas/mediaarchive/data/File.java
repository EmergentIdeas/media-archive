package com.emergentideas.mediaarchive.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public abstract class File {
	
	public enum FileContentType { IMAGE, VIDEO, DOCUMENT, AUDIO, PLAYLIST, DIRECTORY }
	
	@ManyToOne
	protected Archive archive;
	
	@Column(length = 200)
	protected String uid;

	@Column(length = 2000)
	protected String name;
	
	@ManyToMany
	List<Tag> tags = new ArrayList<Tag>();
	
	protected Long created;
	
	protected Long lastUpdated;
	
	@OneToMany
	protected List<ResourceAssociation> resourceAssociations = new ArrayList<ResourceAssociation>();
	
	protected FileContentType fileType;
	
	public void assignUID() {
		if(uid == null) {
			uid = UUID.randomUUID().toString();
		}
	}


	public abstract Integer getId();


	public abstract void setId(Integer id);


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Tag> getTags() {
		return tags;
	}


	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}


	public Long getCreated() {
		return created;
	}


	public void setCreated(Long created) {
		this.created = created;
	}


	public Long getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


	public List<ResourceAssociation> getResourceAssociations() {
		return resourceAssociations;
	}


	public void setResourceAssociations(
			List<ResourceAssociation> resourceAssociations) {
		this.resourceAssociations = resourceAssociations;
	}


	public FileContentType getFileType() {
		return fileType;
	}


	public void setFileType(FileContentType fileType) {
		this.fileType = fileType;
	}


	public Archive getArchive() {
		return archive;
	}


	public void setArchive(Archive archive) {
		this.archive = archive;
	}
	
	
	
}
