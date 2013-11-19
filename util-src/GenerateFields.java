import java.io.File;

import com.emergentideas.entityclasstools.EntityGenerator;
import com.emergentideas.entityclasstools.GenerateClassDescriptions;
import com.emergentideas.mediaarchive.data.Archive;


public class GenerateFields {
	
	public static void main(String[] args) throws Exception {
		EntityGenerator eg = new EntityGenerator();
		eg.generateTemplates(new File("/data/repositories/media-archive/templates/media-archive"), Archive.class);
	}

}
