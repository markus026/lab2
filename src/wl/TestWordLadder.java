package wl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestWordLadder {
	private WordLadder wl;
	@Before
	public void setUp() throws Exception {
		wl = new WordLadder();
	}

	@Test
	public void test() {
		wl.addWord("words");
		wl.addWord("these");
		wl.addWord("other");
		wl.addWord("there");
		wl.addWord("about");
		wl.addWord("their");
		wl.addWord("write");
		wl.addWord("could");
		wl.addWord("which");
		wl.addWord("would");
		 wl.printGraph();
		 System.out.println( wl.distanceTo("their", "write"));
		// wl.distanceTo("other", "there");
//		String[] s = new String[2];
//		s[0] = "words-5757.txt";
//		s[1] = "words-5757-in.txt";
//		Main m = new Main(s);
	}

}
