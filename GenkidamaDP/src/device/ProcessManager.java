package device;

import java.util.LinkedList;
import java.util.Queue;

import data.ComputationResult;
import data.GenkidamaResultData;
import interfaces.GenkidamaRunnable;

public class ProcessManager {

	private Thread[] activeThreads;
	private Queue<OwnedFinalComputationSpec> queueComputation;
	private final Runnable activeRunnable = new Runnable() {
		
		@Override
		public void run() {
			
			while (true) {
				OwnedFinalComputationSpec cs;
				while ((cs = getNextComputationSpec()) == null);
				GenkidamaRunnable run = cs.getContent().getContent();
				run.run();
				GenkidamaResultData grd = run.getResult();
				ComputationResult cr = new ComputationResult(grd,cs.getContent().getObjectId());
				cs.getOwner().getComputationResult(cr);
			}
			
			
		}
	};
	
	public ProcessManager(int numThreads) {
		
		activeThreads = new Thread[numThreads];
		for (int i = 0 ; i < numThreads; i++) {
			activeThreads[i] = new Thread(activeRunnable);
		}
		
		queueComputation = new LinkedList<>();
	}
	
	public void beginProcessing() {
		for (int i = 0 ; i < activeThreads.length ; i++) {
			activeThreads[i].start();
		}
	}
	
	public void addComputation(OwnedFinalComputationSpec computation) {
		queueComputation.add(computation);
	}
	
	private synchronized OwnedFinalComputationSpec getNextComputationSpec() {
		
		if (queueComputation.isEmpty())
			return null;
		
		return queueComputation.poll();
	}
}
