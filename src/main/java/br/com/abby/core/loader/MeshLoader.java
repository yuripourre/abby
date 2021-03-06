package br.com.abby.core.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.abby.core.loader.mesh.Max3DLoader;
import br.com.abby.core.loader.mesh.OBJLoader;
import br.com.abby.core.model.Model;
import br.com.etyllica.loader.LoaderImpl;
import br.com.etyllica.util.StringUtils;
import br.com.etyllica.util.io.IOHelper;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class MeshLoader extends LoaderImpl {

	private static MeshLoader instance = null;

	private Map<String, VBOLoader> loaders = new HashMap<String, VBOLoader>();

	public static final String OBJ = "obj";
	public static final String MAX3D = "3ds";

	public static MeshLoader getInstance() {
		if(instance==null){
			instance = new MeshLoader();
		}

		return instance;
	}

	private MeshLoader() {
		super();

		folder = "assets/models/";

		loaders.put(OBJ, new OBJLoader());
		loaders.put(MAX3D, new Max3DLoader());
	}
	
	public Model loadModel(String path) {
		return loadModel(path, false);
	}

	public Model loadModel(String path, boolean absolutePath) {

		URL dir = null;
		try {
			if(!absolutePath) {
				dir = getFullURL(path);
			} else {
				dir = new URL(IOHelper.FILE_PREFIX+path);
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		String ext = StringUtils.fileExtension(path);
		VBOLoader loader = getLoader(ext);

		if(loader == null) {
			System.out.println("Abby can't load "+ext+" files.");
		} else {
			try {
				return loader.loadModel(dir, path);
			} catch (FileNotFoundException e) {
				System.err.println("Asset "+path+" not found.");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Asset "+path+" not found.");
			}
		}

		return null;
	}

	public VBOLoader getLoader(String extension) {
		return loaders.get(extension);
	}
	
	public void addLoader(String extension, VBOLoader loader) {
		loaders.put(extension, loader);
	}

	public Set<String> supportedExtensions() {
		return loaders.keySet();
	}

}