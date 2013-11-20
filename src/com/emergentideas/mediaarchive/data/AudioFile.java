package com.emergentideas.mediaarchive.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AudioFile extends File {

	@Id
	@GeneratedValue
	protected Integer id;
	

	public AudioFile() {
		fileType = FileContentType.AUDIO;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
