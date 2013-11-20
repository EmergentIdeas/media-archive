package com.emergentideas.mediaarchive.volume;

import com.emergentideas.mediaarchive.interfaces.LogEntry;

public class SimpleLogEntry implements LogEntry {

	protected EntryType entryType;
	protected String id;
	protected Long date;
	
	public SimpleLogEntry() {}
	
	public SimpleLogEntry(EntryType entryType, String id, Long date) {
		super();
		this.entryType = entryType;
		this.id = id;
		this.date = date;
	}

	public EntryType getEntryType() {
		return entryType;
	}

	public String getId() {
		return id;
	}

	public Long getDate() {
		return date;
	}

}
