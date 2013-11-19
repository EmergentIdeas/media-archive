package com.emergentideas.mediaarchive.handles;

import javax.ws.rs.Path;

import com.emergentideas.mediaarchive.data.Archive;
import com.emergentideas.webhandle.apps.oak.crud.CRUDHandle;

@Path("/archive")
public class ArchivesHandle extends CRUDHandle<Archive> {

	@Override
	public String getTemplatePrefix() {
		return "media-archive/archives/";
	}

	
}
