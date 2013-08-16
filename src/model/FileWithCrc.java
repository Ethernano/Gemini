package model;

import java.io.File;

public class FileWithCrc implements Comparable<FileWithCrc> {
	private File file;
	private long crc;
	
	public FileWithCrc(File file, long crc) {
		this.file = file;
		this.crc = crc;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public long getCrc() {
		return crc;
	}

	public void setCrc(long crc) {
		this.crc = crc;
	}

	@Override
	public int compareTo(FileWithCrc o) {
		if (this.crc == o.crc) {
			return 0;
		}
		else if (this.crc>o.crc) {
			return 1;
		}
		return -1;
	}
	
	
	
	
}
