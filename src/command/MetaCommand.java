package command;

public abstract class MetaCommand extends Command{

	public enum MetaType {
		ACK;
	}
	
	private MetaType metaType;
	
	public MetaCommand(MetaType metaType) {
		super(RootType.META);
		
		this.metaType = metaType;
	}
	
	public MetaType getMetaType() {
		return metaType;
	}

}
