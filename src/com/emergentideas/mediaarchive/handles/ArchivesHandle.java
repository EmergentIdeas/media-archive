package com.emergentideas.mediaarchive.handles;

import java.util.List;

import javax.ws.rs.Path;

import com.emergentideas.mediaarchive.data.Archive;
import com.emergentideas.webhandle.InvocationContext;
import com.emergentideas.webhandle.Location;
import com.emergentideas.webhandle.apps.oak.crud.CRUDHandle;
import com.emergentideas.webhandle.assumptions.oak.RequestMessages;

@Path("/archive")
public class ArchivesHandle extends CRUDHandle<Archive> {

	@Override
	public String getTemplatePrefix() {
		return "media-archive/archives/";
	}

	
	@Override
	protected void addAssociatedData(InvocationContext context, Archive focus,
			Location location) {
		if(focus != null) {
			location.put("archive", focus);
		}
		super.addAssociatedData(context, focus, location);
	}


	@Override
	public boolean validateCreate(Archive focus, RequestMessages messages) {
		focus.initUid();
		return super.validateCreate(focus, messages);
	}


	@Override
	protected String getPostCreateURL(InvocationContext context, Archive focus,
			Location location, RequestMessages messages) {
		return "/archive/" + focus.getId();
	}

	@Override
	protected List<String> determinePropertyNames() {
		List<String> l = super.determinePropertyNames();
		l.add(0, "id");
		return l;
	}

	
}
