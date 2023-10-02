package org.example;

import org.example.service.SearchService;
import org.example.service.StatService;
import java.io.File;

public class Main {
    public static void main(String[] args)  {
        String action = args[0];
        String filePathInput = args[1];
        String filePathOutput = args[2];
        if (action.equals("search"))
        {
            SearchService searchService = new SearchService();
            searchService.search(new File(filePathInput), new File(filePathOutput));
        } else if (action.equals("stat")) {
            StatService statService = new StatService();
            statService.stat(new File(filePathInput), new File(filePathOutput));
        }
    }
}