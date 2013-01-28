import java.util.regex.*;

import akka.actor.ActorRef;

public class Configure {
	private final String fileName;
	private final Pattern pattern;
	private final ActorRef collector;
	
	public Configure(String fName, String pattern, ActorRef collectActor) {
		this.fileName = fName;
		this.pattern = Pattern.compile(pattern);
		this.collector = collectActor;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public Pattern getPattern() {
		return pattern;
	}
	
	public ActorRef getCollectionActor() {
		return collector;
	}
}
