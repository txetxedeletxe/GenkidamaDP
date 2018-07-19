package host;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RemoteDeviceView {

	private Set<String> sourceIdentifiers;
	private Map<Long,Object> pendingComputations;
	
	
	public RemoteDeviceView() {
		sourceIdentifiers = new HashSet<String>();
		pendingComputations = new HashMap<>();
	}
	
	public void addResult(Object content, long objectId) {
		pendingComputations.put(objectId, content);
		
	}

	public void addSource(String sourceIdentifier) {
		sourceIdentifiers.add(sourceIdentifier);
		
	}

	public void addPendingComputation(long computationId) {
		pendingComputations.put(computationId, null);
		
	}

	public Object getComputationResult(long computationIdentifier) {
		return pendingComputations.get(computationIdentifier);
		
	}
	
	public boolean computationIdExists(long computationId) {
		
		return pendingComputations.containsKey(computationId);
	}

}
