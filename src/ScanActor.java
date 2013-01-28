import java.io.*;
import java.util.*;
import java.util.regex.*;

import akka.actor.*;

public class ScanActor extends UntypedActor {

	private String fileName;
	private Pattern pattern;
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
			Matcher lineMatcher = pattern.matcher(line);
			if (lineMatcher.find()) {
				results.add(lineNumber + " " + line);
			}
		}
		
		return new Found(fileName, results);
	}

}
