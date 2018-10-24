package com.baeldung.string.searching;

import java.util.ArrayList;
import java.util.List;

public class WordIndexer {

    public List<Integer> findWord(String textString, String word) {
        int index = -1;
        List<Integer> indexes = new ArrayList<Integer>();
        boolean done = false;
        String lowerCaseTextString = textString.toLowerCase();
        String lowerCaseWord = word.toLowerCase();

        while(!done){
            index = lowerCaseTextString.indexOf(lowerCaseWord, index + 1);
            if (index != -1) {
                indexes.add(index);
            }else{
                done = true;
            }
        }
        return indexes;
    }

    public List findWordUpgrade(String textString, String word) {
        int index = -1;
        List<Integer> indexes = new ArrayList<Integer>();
        StringBuilder output = new StringBuilder();
        boolean done = false;
        String lowerCaseTextString = textString.toLowerCase();
        String lowerCaseWord = word.toLowerCase();
        int wordLength = word.length();

        while(!done){
            index = lowerCaseTextString.indexOf(lowerCaseWord, index + wordLength);  // Slight improvement
            if (index != -1) {
                indexes.add(index);
            }else{
                done = true;
            }
        }
        return indexes;
    }
}
