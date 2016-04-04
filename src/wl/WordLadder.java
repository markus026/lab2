package wl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class WordLadder {
	HashMap<String, ArrayList<String>> graph;

	public WordLadder() {
		graph = new HashMap<String, ArrayList<String>>();
	}

	public void addWord(String newWord) {
		graph.put(newWord, new ArrayList<String>()); //skapar en lista för ordet
		
		for (String wordInGraph : graph.keySet()) { //itererar över grafen för att se om det nya ordet ska kopplas till något
			
			if (!newWord.equals(wordInGraph)) { //utesluter det ordet vi precis la till eftersom det inte ska kopplas till sig själv
				
				if (wordsAreConnected(newWord, wordInGraph)) { //kollar om det nya ordets fyra sista finns i något annat ord i grafen
					graph.get(newWord).add(wordInGraph);
				}
				if (wordsAreConnected(wordInGraph, newWord)) { //kollar om något annat ords fyra sista finns i det nya ordet
					graph.get(wordInGraph).add(newWord);
				}
			}
		}
	}

	private boolean wordsAreConnected(String word1, String word2) {// true om fyra sista bokstäver i word1 finns i word2
		String s = word1.substring(1);
		String temp = word2 + "";
		int checker = 0;
		
		for(int i = 0; i < 4; i++){
			String currentChar = Character.toString(s.charAt(i));
			if(temp.contains(currentChar)){
				int index = temp.indexOf(currentChar);
				temp = temp.substring(0, index) + temp.substring(index + 1);
				checker++;
			}
		}
		
		if(checker > 3){
			return true;
		}
		return false;
	}
	
	public void printGraph(){
		for(Entry ls:graph.entrySet()){
			System.out.print(ls.getKey() + "\t");
			ArrayList<String> lista = (ArrayList<String>) ls.getValue();
			for(String s : lista){
				System.out.print(s + "\t");
			}
			System.out.println();
		}
	}
	
	public int distanceTo(String word1, String word2){
		PriorityQueue<String> queue = new PriorityQueue<String>();
		HashMap<String, Boolean> marked = new HashMap<String, Boolean>();
		HashMap<String, Integer> distance = new HashMap<String, Integer>();
		
		for(String graphKey: graph.keySet()){
			marked.put(graphKey, false);
			distance.put(graphKey, -1);
		}
		for(String s:graph.get(word1)){
			marked.put(s, true);
			distance.put(s, 1);
			queue.add(s);
		}
			
		while(!queue.isEmpty()){
			String v = queue.poll();
			for(String wordInV: graph.get(v)){
				if(!marked.get(wordInV)){
					distance.put(wordInV, distance.get(v) + 1);
					marked.put(wordInV, true);
					queue.add(wordInV);
				}
			}
		}
		
		return distance.get(word2);
	}
	
}
