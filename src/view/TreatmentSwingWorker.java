package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import com.sun.org.apache.bcel.internal.generic.DMUL;

import model.DoublonManager;
import model.FileLister;
import model.FileWithCrc;

public class TreatmentSwingWorker extends SwingWorker<Integer, String>{
	public SwingView view;
	public boolean withCrc;
	public ArrayList<FileWithCrc> results;
	private DoublonManager dManage;
	private ArrayList<File> files;
	
	public TreatmentSwingWorker(SwingView swingView, boolean withCrc, ArrayList<File> files) {
		this.view = swingView;
		this.withCrc = withCrc;
		this.files = files;
		this.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				 if("progress".equals(evt.getPropertyName())) {
	                    view.getProgressBar().setValue((Integer) evt.getNewValue());
	                    view.getProgressBar().setString(Integer.toString((Integer) evt.getNewValue())+"%");
	                }
			}
		});
	}
	
	@Override
	protected Integer doInBackground() throws Exception {
		view.getProgressBar().setIndeterminate(true);
		

		
		System.out.println("processing "+files.size());

		List<Callable<FileWithCrc>> tasks = new ArrayList<Callable<FileWithCrc>>();

		for (File file : files) {
			tasks.add( new FileCrc32RecursiveTask(file,withCrc) );
		}

		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		CompletionService<FileWithCrc> completionService = new ExecutorCompletionService<>(executor);

		List<Future<FileWithCrc>> futures = new ArrayList<Future<FileWithCrc>>();

		try {
			for(Callable<FileWithCrc> t : tasks){
				futures.add(completionService.submit(t));
			}
			
			results = new ArrayList<FileWithCrc>();
			FileWithCrc res;
			for (int i = 0; i < tasks.size(); ++i) {
				try {
					res = completionService.take().get();
					if (res != null) {
//						System.out.println(res.getFile()+" "+res.getCrc());
//						setProgress((int)nb*100/nbMax);
						int progress = (int)(i+1)*100/tasks.size();
						view.getProgressBar().setIndeterminate(false);
						setProgress(progress);
						publish("Processed "+res.getFile().getName()+" : "+res.getCrc() );
						results.add(res);
					}
				} 
				catch(ExecutionException ignore) {}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			executor.shutdown();
		}
		
		return tasks.size();

	}
	
	@Override
	protected void process(List<String> chunks) {
		for(String s : chunks)
			view.getProgressTextField().setText(s);
	}
	
	@Override
	protected void done() {
		super.done();
		if (getResults() != null) {
			dManage = new DoublonManager(getResults());
		}
	}

	public ArrayList<FileWithCrc> getResults() {
		return results;
	}

	public DoublonManager getdManage() {
		return dManage;
	}
	
}
