import java.io.File;

import com.emergentideas.entityclasstools.EntityGenerator;
import com.emergentideas.entityclasstools.GenerateClassDescriptions;
import com.emergentideas.mediaarchive.data.Archive;
import com.emergentideas.mediaarchive.data.VolumeDesc;
import com.emergentideas.mediaarchive.interfaces.Volume;
import com.emergentideas.mediaarchive.volume.DiskVolume;


public class GenerateFields {
	
	public static void main(String[] args) throws Exception {
		EntityGenerator eg = new EntityGenerator();
		eg.generateTemplates(new File("/data/repositories/media-archive/templates/media-archive"), VolumeDesc.class);
	}

}
