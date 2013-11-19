package com.emergentideas.mediaarchive.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.emergentideas.mediaarchive.interfaces.Volume.VolumeType;

@Entity
public class VolumeDesc {

	public enum VolumeAccessMethod { DISK, HTTP };
	
	// Primary - This is a piece of working storage
	// Transient - a folder, removable drive, media device, archival media, etc. that we
	// 				need to be able to copy to
	// Remote - a volume someplace that we can copy from and write to, but is probably bandwidth
	//			constrained
	public enum VolumeDesignation { PRIMARY, TRANSIENT, REMOTE }
	
	@Id
	@GeneratedValue
	protected Integer id;
	
	protected String location;
	protected VolumeType type = VolumeType.READ_WRITE;
	protected VolumeAccessMethod accessMethod = VolumeAccessMethod.DISK;
	protected VolumeDesignation volumeDesignation = VolumeDesignation.PRIMARY;
	
	@Column(length = 2000)
	protected String encryptionKey;
	
	@ManyToOne
	protected Archive archive;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public VolumeType getType() {
		return type;
	}

	public void setType(VolumeType type) {
		this.type = type;
	}

	public VolumeAccessMethod getAccessMethod() {
		return accessMethod;
	}

	public void setAccessMethod(VolumeAccessMethod accessMethod) {
		this.accessMethod = accessMethod;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}

	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

	public Archive getArchive() {
		return archive;
	}

	public void setArchive(Archive archive) {
		this.archive = archive;
	}

	public VolumeDesignation getVolumeDesignation() {
		return volumeDesignation;
	}

	public void setVolumeDesignation(VolumeDesignation volumeDesignation) {
		this.volumeDesignation = volumeDesignation;
	}
	
	
}
