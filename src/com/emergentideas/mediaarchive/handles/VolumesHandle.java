package com.emergentideas.mediaarchive.handles;

import java.util.List;

import javax.ws.rs.Path;

import com.emergentideas.mediaarchive.data.Archive;
import com.emergentideas.mediaarchive.data.VolumeDesc;
import com.emergentideas.webhandle.Inject;
import com.emergentideas.webhandle.InvocationContext;
import com.emergentideas.webhandle.Location;
import com.emergentideas.webhandle.NotNull;
import com.emergentideas.webhandle.apps.oak.crud.CRUDHandle;
import com.emergentideas.webhandle.assumptions.oak.RequestMessages;
import com.emergentideas.webhandle.assumptions.oak.interfaces.User;
import com.emergentideas.webhandle.handlers.Handle;
import com.emergentideas.webhandle.handlers.HttpMethod;
import com.emergentideas.webhandle.output.Template;
import com.emergentideas.webhandle.output.Wrap;

@Path("/archive/{archiveId:\\d+}/volume")
public class VolumesHandle extends CRUDHandle<VolumeDesc> {

	@Override
	public String getTemplatePrefix() {
		return "media-archive/volumes/";
	}
	
	

	@Override
	protected String getPostCreateURL(InvocationContext context,
			VolumeDesc focus, Location location, RequestMessages messages) {
		return "/archive/" + focus.getArchive().getId();
	}



	@Override
	protected String getPostEditURL(InvocationContext context,
			VolumeDesc focus, Location location, RequestMessages messages) {
		return "/archive/" + focus.getArchive().getId();
	}



	@Override
	protected String getPostDeleteURL(InvocationContext context,
			VolumeDesc focus, Location location, RequestMessages messages) {
		return "/archive/" + focus.getArchive().getId();
	}



	@Handle(value = "/create", method = HttpMethod.GET)
	@Template
	@Wrap("app_page")
	public Object createGet(InvocationContext context, User user, Integer archiveId, Location location) {
		Archive ark = entityManager.find(Archive.class, archiveId);
		location.put("archive", ark);
		return super.createGet(context, user, location);
	}



	@Handle(value = "/create", method = HttpMethod.POST)
	@Template
	@Wrap("app_page")
	public Object createPost(InvocationContext context, @NotNull @Inject VolumeDesc focus, Integer archiveId,
			Location location, RequestMessages messages) {
		Archive ark = entityManager.find(Archive.class, archiveId);
		focus.setArchive(ark);
		ark.getVolumes().add(focus);
		return super.createPost(context, focus, location, messages);
	}

	@Override
	protected List<String> determinePropertyNames() {
		List<String> l = super.determinePropertyNames();
		l.add(0, "id");
		return l;
	}
	
	

}
