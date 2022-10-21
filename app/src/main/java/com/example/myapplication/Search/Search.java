package com.example.myapplication.Search;

import java.io.IOException;
import java.util.Scanner;

public class Search implements Runnable{

    public String result;
    private String keyword;

    public Search(String key){
        this.keyword = key;
    }

    @Override
    public void run() {
        try {
            WordCounter counter = new WordCounter("https://mao-code.github.io");
            this.result = this.keyword + " appears " + counter.countKeyword(this.keyword) + " times.";
        }catch(IOException e){
            e.printStackTrace();
        }
        // return "未收到訊息";
    }
}