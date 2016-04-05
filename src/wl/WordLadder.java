package wl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class WordLadder {
	HashMap<String, ArrayList<String>> graph;

	public WordLadder() {
		graph = new HashMap<String, ArrayList<String>>();
	}

	public void addWord(String newWord) {
		graph.put(newWord, new ArrayList<String>());
		for (String wordInGraph : graph.keySet()) {
			if (!newWord.equals(wordInGraph)) {
				if (wordsAreConnected(newWord, wordInGraph)) {
					graph.get(newWord).add(wordInGraph);
				}
				if (wordsAreConnected(wordInGraph, newWord)) {
					graph.get(wordInGraph).add(newWord);
				}
			}
		}
	}

	private boolean wordsAreConnected(String word1, String word2){
		ArrayList<Character> w1 = new ArrayList<Character>();
		ArrayList<Character> w2 = new ArrayList<Character>();
		char replacer = 1;
		String s = word1.substring(1);
		for(int i = 0; i < 4; i++){
			w1.add(s.charAt(i));
			w2.add(word2.charAt(i));
		}
		w2.add(word2.charAt(4));
		int checker = 0;
		for(int i = 0; i < 4;i++){
			for(int j = 0; j < 5 ; j++){
				if(w1.get(i).equals(w2.get(j))){
					w2.set(j, replacer);
					checker++;
					break;
				}
			}
			if(checker < i+1){
				return false;
			}
		}
		
		
		
//		String s = word1.substring(1);
//		String temp = word2 + "";
//		int checker = 0;
//		for (int i = 0; i < 4; i++) {
//			String currentChar = Character.toString(s.charAt(i));
//			if (temp.contains(currentChar)) {
//				int index = temp.indexOf(currentChar);
//				temp = temp.substring(0, index) + temp.substring(index + 1);
//				checker++;
//			}
//		}
		if (checker > 3) {
			return true;
		}
		return false;
	}

	public void printGraph() {
		for (Entry ls : graph.entrySet()) {
			System.out.print(ls.getKey() + "\t");
			ArrayList<String> lista = (ArrayList<String>) ls.getValue();
			for (String s : lista) {
				System.out.print(s + "\t");
			}
			System.out.println();
		}
	}

	public int distanceTo(String word1, String word2) {
		if (word1.equals(word2)) {
			return 0;
		}
		LinkedList<String> queue = new LinkedList<String>();
		HashMap<String, Boolean> marked = new HashMap<String, Boolean>();
		HashMap<String, Integer> distance = new HashMap<String, Integer>();

		marked.put(word1, true);
		for (String s : graph.get(word1)) {
			if (s.equals(word2)) {
				return 1;
			}
			marked.put(s, true);
			distance.put(s, 1);
			queue.add(s);
		}

		while (!queue.isEmpty()) {
			String v = queue.poll();
			for (String wordInV : graph.get(v)) {
				if (word2.equals(wordInV)) {
					return distance.get(v) + 1;
				}
				if (marked.get(wordInV) == null) {
					distance.put(wordInV, distance.get(v) + 1);
					// System.out.println(distance.get(wordInV) + ": " +
					// distance.get(v) + ": " + wordInV + " -- " + v);
					marked.put(wordInV, true);
					queue.add(wordInV);
				}
			}
		}

		return -1;
	}
}
