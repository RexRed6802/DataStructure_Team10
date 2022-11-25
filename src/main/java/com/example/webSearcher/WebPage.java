package com.example.webSearcher;

import java.io.IOException;
import java.util.ArrayList;

public class WebPage implements Runnable{
	public String url;
	public String name;
	public WordCounter counter = new WordCounter(url);
	public String content;
	public double score = 0;
	//private ArrayList<Thread> threadList = new ArrayList<Thread>(); 
	//private ArrayList<WordCounter> wcList = new ArrayList<WordCounter>();
	
	public WebPage(String url,String name){
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);	
	}

	public void run(){
		try {
			//this.content = counter.fetchContent();
			if(this.setScore()){
				//continue;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean setScore() throws IOException{
		ArrayList<Keyword> k = new ArrayList<Keyword>();
		k.add(new Keyword("拉麵",500));
        k.add(new Keyword("豚骨",300));
        k.add(new Keyword("叉燒",300));
        k.add(new Keyword("雞白湯",200));
        k.add(new Keyword("醬油", 200));
        k.add(new Keyword("台北",200));
        k.add(new Keyword("排隊",50));
        k.add(new Keyword("好吃",30));
        k.add(new Keyword("湯底",30));
        k.add(new Keyword("激辛", 30));
        k.add(new Keyword("日本", 30));
        k.add(new Keyword("日式", 30));
		k.add(new Keyword("美食", 30));
		
		for(Keyword key : k){
			score += key.weight*counter.countKeyword(key.name);	
		}
		
	//	k.add(new Keyword("湯", 5));
	//	k.add(new Keyword("麵", 5));
	/* 
		for(int i = 0; i < k.size(); i++){		
			//score += key.weight*counter.countKeyword(key.name);	
			WordCounter wc = new WordCounter(url, k.get(i).name);
			Thread counterthread = new Thread(wc);
			threadList.add(counterthread);
			wcList.add(wc);
		}
		
		for(Thread element : threadList){
			element.start();
		}

		for(Thread element : threadList){
			try {
				element.join();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(WordCounter wc : wcList){
			score += wc.getScore();
		}
*/
		System.out.println("finishing set score");
		return true;
	}
}