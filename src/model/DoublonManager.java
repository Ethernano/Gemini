package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;


public class DoublonManager {
	private ArrayList<FileWithCrc> files;
	private HashMap<Integer, ArrayList<FileWithCrc>> groupMap = new HashMap<>();

	public DoublonManager(ArrayList<FileWithCrc> files) {
		this.files = files;
		Collections.sort(files);
		
		long prevCrc = -1L;
		int actualGroup = 0;
		//groupMap.put(actualGroup, new ArrayList<FileWithCrc>());
		
		for (FileWithCrc file : files) {
			System.out.println(file.getFile().getName()+" : "+file.getCrc());
			if (file.getCrc() != prevCrc) {
				actualGroup++;
				groupMap.put(actualGroup, new ArrayList<FileWithCrc>());
				groupMap.get(actualGroup).add(file);
			}
			else {
				groupMap.get(actualGroup).add(file);
			}
			prevCrc = file.getCrc();
		}
		
		System.out.println("Groups : ");
		for (int n = 1;n<=groupMap.size();n++) {
			System.out.println("Group "+n);
			for (FileWithCrc file : groupMap.get(n)) {
				System.out.println(file.getFile()+" "+file.getCrc());
			}
		}
	}

	
}
