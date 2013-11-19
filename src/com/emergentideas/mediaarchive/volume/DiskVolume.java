package com.emergentideas.mediaarchive.volume;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.emergentideas.mediaarchive.interfaces.ResourceDesc;
import com.emergentideas.mediaarchive.interfaces.Volume;
import com.emergentideas.mediaarchive.services.CryptoService;

public class DiskVolume implements Volume {
	
	protected VolumeType type = VolumeType.READ_WRITE;
	protected File root;
	protected File index;
	
	public DiskVolume(File root) {
		this.root = root;
		this.index = new File(root, ".arkindex");
	}

	
	public Map<String, DiskResource> getDescriptors() {
		Map<String, DiskResource> result = new HashMap<String, DiskResource>();
		try {
			for(String line : FileUtils.readLines(index)) {
				if(StringUtils.isBlank(line) || line.trim().startsWith("#")) {
					continue;
				}
				DiskResource rd = parseEntry(line);
				result.put(rd.getId(), rd);
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public DiskResource hasResource(String id) {
		try {
			String indexContent = FileUtils.readFileToString(index);
			return hasResource(indexContent, id);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected DiskResource hasResource(String indexContent, String id) {
		int i1 = indexContent.indexOf(id);
		if(i1 < 0) {
			return null;
		}
		int i2 = indexContent.indexOf('\n', i1);
		if(i2 < 0) {
			// this is the last entry but was not terminated
			i2 = indexContent.length();
		}
		String line = indexContent.substring(i1, i2);
		return parseEntry(line);
	}

	public ResourceDesc save(InputStream resource, String id, String mime, String name) {
		try {
			File temp = null;
			if(StringUtils.isBlank(id)) {
				// Generate an id
				temp = File.createTempFile("digest", "temp");
				FileOutputStream fos = new FileOutputStream(temp);
				IOUtils.copy(resource, fos);
				fos.flush();
				fos.close();
				id = CryptoService.createDigest(temp);
				resource = new FileInputStream(temp);
			}
			
			mime = determineMime(mime, name);
			name = determineName(id, name);
			
			File dest = new File(root, determinePath(id, name));
			dest.getParentFile().mkdirs();
			dest.createNewFile();
			FileOutputStream fos = new FileOutputStream(dest);
			IOUtils.copy(resource, fos);
			fos.flush();
			fos.close();
			
			if(temp != null) {
				resource.close();
				temp.delete();
			}
			
			DiskResource dr = new DiskResource(id, mime, dest);
			FileWriter fw = new FileWriter(index, true);
			fw.append(createResourceString(dr));
			fw.flush();
			fw.close();
			
			return dr;
		}
		catch(Exception e ) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void delete(String id) {
		Map<String, DiskResource> resources = getDescriptors();
		if(resources.containsKey(id)) {
			DiskResource focus = resources.get(id);
			File focusFile = focus.getResource();
			File parent = focusFile.getParentFile();
			focusFile.delete();
			parent.delete();
			resources.remove(id);
			try {
				File temp = new File(root, ".arkindex~");
				FileWriter fw = new FileWriter(temp);
				for(DiskResource dr : resources.values()) {
					fw.append(createResourceString(dr));
				}
				fw.flush();
				fw.close();
				FileUtils.copyFile(temp, index);
				temp.delete();
			}
			catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}

	protected String createResourceString(DiskResource dr) {
		return dr.getId() + ";" + dr.getMime() + ";" + getRelativePath(dr.getResource()) + "\n";
	}

	protected String getRelativePath(File file) {
		String relative = root.toURI().relativize(file.toURI()).getPath();
		return relative;
	}
	
	protected String determinePath(String id, String name) {
		return id + File.separatorChar + name;
	}
	
	
	protected String determineName(String id, String name) {
		if(StringUtils.isNotBlank(name)) {
			if(name.lastIndexOf('.') < 1) {
				// This name doesn't have an extension
				name = name + ".bin";
			}
			return name;
		}
		
		return id + ".bin";
	}
	
	protected String determineMime(String mime, String name) {
		if(StringUtils.isNotBlank(mime)) {
			return mime;
		}
		if(StringUtils.isNotBlank(name)) {
			// Also, from Java 7, I hear tell of Files.probeContentType(path) which examines the content
			return URLConnection.guessContentTypeFromName(name);
		}
		
		return "";
	}
	
	protected String getMimeForExtension(String ext) {
		
		return "";
	}

	public InputStream get(String id) {
		DiskResource dr = hasResource(id);
		if(dr == null) {
			return null;
		}
		return dr.getContent();
	}

	
	protected DiskResource parseEntry(String line) {
		line = line.trim();
		int i1 = line.indexOf(';');
		int i2 = line.indexOf(';', i1 + 1);
		
		String id = line.substring(0, i1);
		String mime = line.substring(i1 + 1, i2);
		String path = line.substring(i2 + 1);
		File resource = new File(path);
		if(resource.isAbsolute() == false) {
			resource = new File(root, path);
		}
		
		return new DiskResource(id, mime, resource);
	}
}
