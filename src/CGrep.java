import static akka.actor.Actors.*;
import akka.actor.*;

public class CGrep {
	
	public static void main(String[] args) {
		String pattern = null;
		try {
			pattern = args[0];
		} catch (Exception e) {
			System.out.println("Please specify the pattern to search for.");
			System.exit(0);
		}
		
		int fileCount = args.length - 1;
		
		ActorRef collector = actorOf(CollectionActor.class);
		collector.start();
		collector.tell(new Integer(fileCount));
		
		int curFileCount = 1;
		while (curFileCount <= fileCount) {
			String fName = args[curFileCount];
			Configure config = new Configure(fName, pattern, collector);
			
			ActorRef scanner = actorOf(ScanActor.class);
			scanner.start();
			scanner.tell(config);
			curFileCount++;
		}
		
		// A Configure for standard input
		Configure config = new Configure(null, pattern, collector);
		ActorRef scanner = actorOf(ScanActor.class);
		scanner.start();
		scanner.tell(config);
	}

}
