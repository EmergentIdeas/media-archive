package com.emergentideas.mediaarchive.interfaces;

public interface LogEntry {
	/**
	 * The types of actions that can be taken on a volume: <br/>
	 * 
	 * CREATE: Added a resource to the volume. It may not be the first time the volume has recorded this
	 * 			resource but it was not currently in the volume when the resource was saved. <br/>
	 *  
	 * UPDATE: A resource has been saved which already had an entry. <br/>
	 *  
	 * DELETE: Deletes the resource from the volume. This doesn't mean the resource is no longer of value within
	 * the archive just that you don't want it in this particular volume any more. This could be the case
	 * if the volume is media player that you'd like to make some space on for different media. <br/>
	 * 
	 * EXPUNGE: Like delete, but is a signal that the resource is being removed because it is being removed from 
	 * all volumes everywhere. This is like saying, this thing is crap, or there's a new version. I don't
	 * want this taking up space anywhere.
	 * 
	 * @author kolz
	 *
	 */
	public enum EntryType { CREATE, UPDATE, DELETE, EXPUNGE }
	
	/**
	 * The type of the change.
	 * @return
	 */
	public EntryType getEntryType();
	
	/**
	 * The identifier of the resource changed.
	 * @return
	 */
	public String getId();
	
	/**
	 * The time which the change was made.
	 * @return
	 */
	public Long getDate();

}
