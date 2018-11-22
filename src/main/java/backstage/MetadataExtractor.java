package backstage;

import model.Publication;

public interface MetadataExtractor  {

    Publication extract() throws Exception;
}
