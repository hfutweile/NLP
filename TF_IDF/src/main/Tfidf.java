package main;
import tools.*;
import java.util.*;

public class Tfidf {
	public static void main(String args[]) {
		String dir="../../data/";
		HashMap<String,Map> tfidfs=TF_IDF.getTFIDF(dir);
		for(Map.Entry<String,Map> entry:tfidfs.entrySet()) {
			String filename=entry.getKey();
			System.out.println(filename);
			HashMap<String,Double> tfidf=(HashMap<String, Double>) entry.getValue();
			for(Map.Entry<String, Double> en:tfidf.entrySet()) {
				System.out.println(en.getKey()+":"+en.getValue());
			}
			System.out.println("\n");
		}
	}
}
