package core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker.StateValue;

import controler.Actions;
import model.DoublonManager;
import model.FileLister;
import view.MainView;
import view.TreatmentSwingWorker;


public class Main {

	public static void main(String [ ] args) throws IOException {
		Actions.computeSizeThenCrc();
	}
}
