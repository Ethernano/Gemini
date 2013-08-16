package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class FileLister {

	private ArrayList<File> folders;
	private ArrayList<File> files;
	
	public FileLister (ArrayList<File> folders) throws IOException {
		this.folders = folders;
		files = new ArrayList<File>();
		loadAllFiles();
	}
	
	public void loadAllFiles() {
		for (File file : folders) {
			if (file.isDirectory()) {
				loadAllFilesFromFolder(file);
			}
			else if (file.isFile()) {
				files.add(file);
			}
		}
	}
	
	public void loadAllFilesFromFolder(File folder) {
		if (folder.isDirectory()) {
			for (File file : folder.listFiles()) {
				loadAllFilesFromFolder(file);
			}
		}
		else if (folder.isFile()) {
			files.add(folder);
		}
	}

	public ArrayList<File> getFolders() {
		return folders;
	}

	public ArrayList<File> getFiles() {
		return files;
	}
	
	

	
	
}
