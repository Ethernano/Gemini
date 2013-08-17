package controler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingWorker.StateValue;

import model.FileLister;
import view.MainView;
import view.TreatmentSwingWorker;

public class Actions {

	
	public static void computeCrcOnly() throws IOException {
		final MainView view = new MainView();
		
		ArrayList<File> folders = new ArrayList<File>();
		folders.add(new File("C:\\Users\\Guida\\Videos"));

		FileLister fileLister = new FileLister(folders);
		final TreatmentSwingWorker sw = new TreatmentSwingWorker(view.getSwingView(),true,fileLister.getFiles());
		sw.execute();
	}
	
	public static void computeSizeOnly() throws IOException {
		final MainView view = new MainView();
		
		ArrayList<File> folders = new ArrayList<File>();
		folders.add(new File("C:\\Users\\Guida\\Videos"));

		FileLister fileLister = new FileLister(folders);
		final TreatmentSwingWorker sw = new TreatmentSwingWorker(view.getSwingView(),false,fileLister.getFiles());
		sw.execute();
	}
	
	public static void computeSizeThenCrc() throws IOException {
		final MainView view = new MainView();
		
		ArrayList<File> folders = new ArrayList<File>();
		folders.add(new File("C:\\Users\\Guida\\Videos"));

		FileLister fileLister = new FileLister(folders);
		final TreatmentSwingWorker sw = new TreatmentSwingWorker(view.getSwingView(),false,fileLister.getFiles());
		sw.execute();

		sw.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				if (arg0.getNewValue().equals(StateValue.DONE)) {
					TreatmentSwingWorker sw2 = new TreatmentSwingWorker(view.getSwingView(),true,sw.getdManage().getDoublonFileList());
					sw2.execute();
				}
			}
		});
	}
}
