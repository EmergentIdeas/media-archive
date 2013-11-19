package com.emergentideas.mediaarchive.volume;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.emergentideas.mediaarchive.interfaces.ResourceDesc;

public class DiskResource implements ResourceDesc {

	protected String id;
	protected String mime;
	protected File resource;
	
	public DiskResource() {}
	
	
	
	public DiskResource(String id, String mime, File resource) {
		super();
		this.id = id;
		this.mime = mime;
		this.resource = resource;
	}



	public String getId() {
		return id;
	}

	public String getMime() {
		return mime;
	}

	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public InputStream getContent() {
		try {
			return new FileInputStream(resource);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
