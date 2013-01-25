import java.util.List;

public class Found {
	private final String fileName;
	private final List<String> matchingLines;
	
	public Found(String fName, List<String> matched) {
		this.fileName = fName;
		this.matchingLines = matched;
	}

	public String getFileName() {
		return fileName;
	}

	public List<String> getMatchingLines() {
		return matchingLines;
	}
}
