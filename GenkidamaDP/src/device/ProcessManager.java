package device;

import java.util.LinkedList;
import java.util.Queue;

import data.ComputationResult;
import data.FinalComputationSpec;
import data.GenkidamaResultData;
import source.GenkidamaRunnable;

public class ProcessManager {

	private Thread[] activeThreads;
	private Queue<DeviceHostOwnedObject<FinalComputationSpec>> queueComputation;
	private final Runnable activeRunnable = new Runnable() {
		
		@Override
		public void run() {
			
			while (true) {
				DeviceHostOwnedObject<FinalComputationSpec> cs;
				while ((cs = getNextComputationSpec()) == null);
				GenkidamaRunnable run = cs.get().get();
				run.run();
				GenkidamaResultData grd = run.getResult();
				ComputationResult cr = new ComputationResult(grd,cs.get().getId());
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
	
	public void addComputation(DeviceHostOwnedObject<FinalComputationSpec> computation) {
		queueComputation.add(computation);
	}
	
	private synchronized DeviceHostOwnedObject<FinalComputationSpec> getNextComputationSpec() {
		
		if (queueComputation.isEmpty())
			return null;
		
		return queueComputation.poll();
	}
}
