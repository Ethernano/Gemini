package core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker.StateValue;

import model.DoublonManager;
import model.FileLister;
import view.MainView;
import view.TreatmentSwingWorker;


public class Main {

	public static void main(String [ ] args) throws IOException {
		//		System.out.println("Results of crc computation :");
		//		for (FileWithCrc filecrc : crc.getResults()) {
		//			System.out.println(filecrc.getFile().getName()+" : "+filecrc.getCrc());
		//		}

		//		DoublonManager dManage = new DoublonManager(crc.getResults());
		//		
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
				
//				System.out.println((int)(99+1)*100/100);
	}
}
