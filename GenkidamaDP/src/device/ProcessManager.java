package device;

import java.util.LinkedList;
import java.util.Queue;

import utils.GenkidamaRunnable;

public class ProcessManager {

	private Thread[] activeThreads;
	private Queue<ComputationSpecFinal> queueComputation;
	private final Runnable activeRunnable = new Runnable() {
		
		@Override
		public void run() {
			
			while (true) {
				ComputationSpecFinal cs = getNextComputationSpec();
				GenkidamaRunnable run = cs.getContent();
				run.run();
				cs.getOwner().getComputationResult(run);
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
	
	public void addComputation(ComputationSpecFinal computation) {
		queueComputation.add(computation);
	}
	
	private synchronized ComputationSpecFinal getNextComputationSpec() {
		
		while (queueComputation.isEmpty()) {}
		return queueComputation.poll();
	}
}
