package wl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private Scanner words;
	private Scanner wordsPair;
	private WordLadder wl;

	public Main(String[] fileName) {
		try {
			words = new Scanner(new File(fileName[0]));
			wordsPair = new Scanner(new File(fileName[1]));
			wl = new WordLadder();
//			int j = 1;
			while(words.hasNextLine()){
//				if(j % 250 == 0){
//					System.out.println("hej");
//				}
				wl.addWord(words.nextLine());
//				j++;
			}
//			System.out.println("Add finished");
			while(wordsPair.hasNextLine()){
				String line = wordsPair.nextLine();
				System.out.println(wl.distanceTo(line.substring(0, 5), line.substring(6)));
			}
		//	wl.printGraph();
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
//		String[] s = new String[2];
//		s[0] = "words-5757.txt";
//		s[1] = "words-5757-in.txt";
		new Main(args);

	}

}
