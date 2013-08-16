package core;

import java.io.IOException;

import model.DoublonManager;
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
				MainView view = new MainView();
				TreatmentSwingWorker sw = new TreatmentSwingWorker(view.getSwingView(),true);
				sw.execute();
				
//				System.out.println((int)(99+1)*100/100);
	}
}
