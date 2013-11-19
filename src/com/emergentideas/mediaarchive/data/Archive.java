package com.emergentideas.mediaarchive.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Archive {

	@Id
	@GeneratedValue
	protected Integer id;
	
	protected String uid;
	
	@OneToMany
	protected List<VolumeDesc> volumes = new ArrayList<VolumeDesc>();
	
	public void initUid() {
		uid = UUID.randomUUID().toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<VolumeDesc> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<VolumeDesc> volumes) {
		this.volumes = volumes;
	}
	
	
}
