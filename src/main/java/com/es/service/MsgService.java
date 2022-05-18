package com.es.service;

import javax.ejb.Stateful;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Stateful
public class MsgService {
    private final Path PATH = Paths.get("E:\\Programs\\glassfish5\\file.txt");

    private final List<String> messages = new ArrayList<>();

    public void addMessage(String message) throws IOException {
        if (messages.size() < 10) {
            messages.add(message);
        } else {
            StringBuilder res = new StringBuilder();
            res.append("Word with max num of vowels: ")
                    .append(findMaxVowelsQty())
                    .append('\n')
                    .append("Word with min num of vowels: ")
                    .append(findMinVowelsQty())
                    .append('\n');

            Files.write(PATH, res.toString().getBytes());
            messages.clear();
        }
    }

    private String findMinVowelsQty() {
        return messages.stream()
                .min(Comparator.comparing(this::getVowelQty))
                .orElse("1");
    }

    private String findMaxVowelsQty() {
        return messages.stream()
                .max(Comparator.comparing(this::getVowelQty))
                .orElse("2");
    }

    private int getVowelQty(String s) {
        int res = 0;
        for (char c : s.toCharArray()) {
            if (Character.toString(c)
                    .matches("[aoiuye]")) {
                res++;
            }
        }
        return res;
    }
}
