import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class ScanActor extends UntypedActor {

	private String fileName;
	private String pattern;
	private ActorRef collector;
	
	@Override
	public void onReceive(Object message) throws Exception {
		Configure config = (Configure) message;
		fileName = config.getFileName();
		pattern = config.getPattern();
		collector = config.getCollectionActor();
		
		Found foundMessage = constructFoundMessage();
		collector.tell(foundMessage);
	}
	
	private Found constructFoundMessage() throws IOException {
		BufferedReader in = null;
		
		if (fileName == null) {
			in = new BufferedReader(new InputStreamReader(System.in));
		} else {
			try {
				in = new BufferedReader(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		List<String> results = new ArrayList<String>();
		String line;
		int lineNumber = 0;
		while ((line = in.readLine()) != null) {
			lineNumber++;
			if (line.matches(pattern)) {
				results.add(lineNumber + " " + line);
			}
		}
		
		return new Found(fileName, results);
	}

}
