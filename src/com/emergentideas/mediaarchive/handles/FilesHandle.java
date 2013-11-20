package com.emergentideas.mediaarchive.handles;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.emergentideas.mediaarchive.services.ArchiveService;
import com.emergentideas.webhandle.Location;
import com.emergentideas.webhandle.output.Template;

@Path("/files/{archiveId:[0-9\\-]+}")
public class FilesHandle {

	@Resource
	protected ArchiveService archiveService;
	
	@Path("/all-files")
	@GET
	@Template
	public Object allFiles(String archiveId, Location location) {
		
		location.put("files", archiveService.allFiles(archiveId));
		
		return "media-archive/files/all-files";
	}
	
	@Path("/all-files")
	@GET
	@Template
	public Object uploadFileForm(String archiveId, Location location) {
		
		location.put("archiveId", archiveId);
		return "media-archive/files/upload-file";
		
	}
}
