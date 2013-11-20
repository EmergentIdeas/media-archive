package com.emergentideas.mediaarchive.volume;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.emergentideas.mediaarchive.interfaces.LogEntry;
import com.emergentideas.mediaarchive.interfaces.LogEntry.EntryType;
import com.emergentideas.mediaarchive.interfaces.ResourceDesc;

public class DiskVolumeTest {

	File dataRoot;
	File volumeRoot;
	DiskVolume volume;
	
	public DiskVolumeTest() throws Exception {
		
		dataRoot = new File(new File("").getAbsoluteFile(), "test-data");
		volumeRoot = new File(FileUtils.getTempDirectory(), "volume" + System.currentTimeMillis());
		volumeRoot.mkdir();
		volumeRoot.deleteOnExit();
		
		volume = new DiskVolume(volumeRoot);
	}
	
	@Test
	public void testReadsAndWrites() throws Exception {
		
		volume.save(new FileInputStream(new File(dataRoot, "exp0012.jpg")), null, null, "exp0012.jpg");
		volume.save(new FileInputStream(new File(dataRoot, "blacksmithing.pdf")), null, null, "blacksmithing.pdf");
		
		assertNotNull(volume.hasResource(one));
		assertNotNull(volume.hasResource(two));
		assertNull(volume.hasResource(one + "hi"));
		
		volume.save(new FileInputStream(new File(dataRoot, "exp0012.jpg")), three, "my/mime", "exp0012.jpg");
		
		ResourceDesc rd = volume.hasResource(three); 
		assertNotNull(rd);
		assertEquals("my/mime", rd.getMime());
		
		volume.delete(one);
		assertNull(volume.hasResource(one));
		
		List<LogEntry> entries = volume.getLog();
		
		assertEquals(4, entries.size());
		
		assertEquals(EntryType.CREATE, entries.get(1).getEntryType());
		assertNotNull(entries.get(1).getDate());
		assertNotNull(entries.get(1).getId());
		assertEquals(two, entries.get(1).getId());
		
		
		
	}
	
	
	protected String one = "784f20a4d4a7f29d08705f3c60223273b1a1e72455bf8d5d1eb2a2b6ad6b7dfd1077b6cbee0525dbb220a1408c629640a1d4ac3b04c2af05fec0bb959fc3e596";
	protected String two = "505a08f5a5955fa6d3aad29afd0c284e0f99b7b0fc8089770a94ff2ba041caddc4decba296002820e2d91496807a1518bed0ca4ae532b1b092c8837e391b291c";
	protected String three = "784f20a4d4a7f29d08705f3c60223273b1a1e72455bf8d5d1eb2a2b6ad6b7dfd1077b6cbee0525dbb220a1408c629640a1d4ac3b04c2af05fec0bb959fc3e5hi";
	

}
