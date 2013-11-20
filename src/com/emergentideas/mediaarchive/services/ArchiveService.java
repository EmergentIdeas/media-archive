package com.emergentideas.mediaarchive.services;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import com.emergentideas.mediaarchive.data.Archive;
import com.emergentideas.mediaarchive.data.AudioFile;
import com.emergentideas.mediaarchive.data.VolumeDesc;
import com.emergentideas.mediaarchive.data.File.FileContentType;
import com.emergentideas.mediaarchive.data.VolumeDesc.VolumeAccessMethod;
import com.emergentideas.mediaarchive.data.VolumeDesc.VolumeDesignation;
import com.emergentideas.mediaarchive.interfaces.Volume;
import com.emergentideas.mediaarchive.interfaces.Volume.VolumeType;
import com.emergentideas.mediaarchive.volume.DiskVolume;

public class ArchiveService {
	
	@Resource
	protected EntityManager entityManager;
	
	public Volume getDefaultValueForWrite(Archive archive) {
		for(VolumeDesc desc : archive.getVolumes()) {
			if(desc.getVolumeDesignation() == VolumeDesignation.PRIMARY && desc.getType() == VolumeType.READ_WRITE) {
				return createVolume(desc);
			}
		}
		return null;
	}
	
	protected Volume createVolume(VolumeDesc desc) {
		if(desc.getAccessMethod() == VolumeAccessMethod.DISK) {
			return new DiskVolume(new File(desc.getLocation()));
		}
		throw new IllegalArgumentException("Access method is not supported.");
	}
	
	public com.emergentideas.mediaarchive.data.File createFile(Archive archive, String name, com.emergentideas.mediaarchive.data.File.FileContentType type) {
		long now = System.currentTimeMillis();
		if(type == FileContentType.AUDIO) {
			AudioFile f = new AudioFile();
			f.setArchive(archive);
			f.setName(name);
			f.assignUID();
			f.setCreated(now);
			f.setLastUpdated(now);
			
			entityManager.persist(f);
			return f;
		}
		
		throw new IllegalArgumentException("Could not create file of type: " + type.toString());
	}
	
	public List<com.emergentideas.mediaarchive.data.File> allFiles(String archiveId) {
		return entityManager.createQuery("select f from AudioFile f where f.archive.uid = :archiveId").setParameter("archiveId", archiveId).getResultList();
	}
		

}
