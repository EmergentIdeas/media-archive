package com.emergentideas.mediaarchive.interfaces;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Volume {
	
	public enum VolumeType { READ_WRITE, READ }
	
	/**
	 * Returns all of the resources in this volume which is not necessarily all
	 * resources in the archive.
	 * @return
	 */
	public Map<String, ? extends ResourceDesc> getDescriptors();
	
	/**
	 * Returns a resource description of the resources in this volume. Null otherwise.
	 * @param id
	 * @return
	 */
	public ResourceDesc hasResource(String id);
	
	/**
	 * Saves a resource with a given id, mime type, and name
	 * @param resource An input stream with the resource
	 * @param id (optional) The id to save the resource with, if none is supplied, it will be calculated
	 * @param mime (optional) The type of the resource. If not given, it will try to be determined from
	 * the <code>name</code> or some other means but may be blank if it can not be determined.
	 * @param name The preferred name to use. This helps in storing things in a human readable name but
	 * is only a suggestion to the volume of how to store it.
	 */
	public ResourceDesc save(InputStream resource, String id, String mime, String name);
	
	/**
	 * Returns an {@link InputStream} with the contents of the resource if it is found in this volume.
	 * Null otherwise.
	 * @param id
	 * @return
	 */
	public InputStream get(String id);
	
	/**
	 * Deletes the resource from the volume. This doesn't mean the resource is no longer of value within
	 * the archive just that you don't want it in this particular volume any more. This could be the case
	 * if the volume is media player that you'd like to make some space on for different media.
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * Like delete, but is a signal that the resource is being removed because it is being removed from 
	 * all volumes everywhere. This is like saying, this thing is crap, or there's a new version. I don't
	 * want this taking up space anywhere.
	 * @param id
	 */
	public void expunge(String id);
	
	/**
	 * Returns a log of the changes made to the volume.
	 * @return
	 */
	public List<? extends LogEntry> getLog();
}
