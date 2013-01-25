import akka.actor.ActorRef;

public class Configure {
	private final String fileName;
	private final String pattern;
	private final ActorRef collector;
	
	public Configure(String fName, String pattern, ActorRef collectActor) {
		this.fileName = fName;
		this.pattern = pattern;
		this.collector = collectActor;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getPattern() {
		return pattern;
	}
	
	public ActorRef getCollectionActor() {
		return collector;
	}
}
