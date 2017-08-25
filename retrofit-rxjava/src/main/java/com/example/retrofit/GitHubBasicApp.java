package com.example.retrofit;

import java.io.IOException;
import java.util.List;

public class GitHubBasicApp {
    
    public static void main(String[] args) throws IOException {
        String userName = "eugenp";
        List<String> topContributors = new GitHubBasicService().getTopContributors(userName);
        topContributors.stream().forEach(System.out::println);
    }

}
