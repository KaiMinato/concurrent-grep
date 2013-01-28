import akka.actor.Actors;
import akka.actor.UntypedActor;

public class CollectionActor extends UntypedActor {

	private int fileCount;
	// Current number of Found message processed
	private int numFoundMessage = 0;
	
	@Override
	public void onReceive(Object message) throws Exception {
		// If message is a file count
		if (message instanceof Integer) {
			fileCount = ((Integer) message).intValue();
		}
		
		if (message instanceof Found) {
			if (((Found) message).getFileName() == null) {
				System.out.println("-");
			} else {
				System.out.println(((Found) message).getFileName());
			}

			for (String line : ((Found) message).getMatchingLines()) {
				System.out.println("\t" + line);
			}
			
			System.out.println();
			numFoundMessage++;
		}
		
		if (numFoundMessage == fileCount) {
			Actors.registry().shutdownAll();
		}
	}

}
