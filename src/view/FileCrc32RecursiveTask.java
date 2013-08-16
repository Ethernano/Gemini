package view;


import java.awt.List;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveTask;
import java.util.zip.CRC32;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import model.FileWithCrc;



public class FileCrc32RecursiveTask implements Callable<FileWithCrc> {
	private File file;
	private FileWithCrc results;
	private SwingWorker<Integer, String> swingWorker;
	private boolean withCrc;
	
	public FileCrc32RecursiveTask(File file, boolean withCrc) throws IOException {
		this.file = file;
		this.withCrc = withCrc;
	}
	
	public long getFileCrc(File file) throws IOException {
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		CRC32 crc = new CRC32();
		int cnt;
		while ((cnt = inputStream.read()) != -1) {
			crc.update(cnt);
		}
		return crc.getValue();
	}

	protected FileWithCrc compute() {
				try {
					Long crc = 0L;
//					System.out.println("Compute CRC for "+file.getName()+"...");
					if (withCrc == true) {
						crc = getFileCrc(file);
					}
					else {
						crc = file.length();
					}
//					Long crc = file.length();
//					System.out.println("Result CRC for "+file.getName());
					results = new FileWithCrc(file, crc);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return results;
	}

	public FileWithCrc getResults() {
		return results;
	}

	@Override
	public FileWithCrc call() throws Exception {
		return compute();
	}
}
