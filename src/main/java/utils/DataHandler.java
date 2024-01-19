package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import models.Account;
import models.Score;

public class DataHandler {

  public static ArrayList<Account> accountList = new ArrayList<>();
  public static Map<String, Score> scoreList = new HashMap<>();

  public DataHandler() {
    // accountList.add(new Account("admin", "admin"));
  }

  public boolean readFile(String url) {
    try {
      FileReader fr = new FileReader(url);
      BufferedReader br = new BufferedReader(fr);
      String line = "";
      while ((line = br.readLine()) != null) {
        StringTokenizer stk = new StringTokenizer(line, " ");
        String username = stk.nextToken().trim();
        String password = stk.nextToken().trim();
        accountList.add(new Account(username, password));
      }
      br.close();
      fr.close();
      return true;
    } catch (Exception e) {
      System.out.println("Error read file Account.txt: " + e);
    }
    return false;
  }

  public static boolean writeFile(String url) {
    try {
      File file = new File(url);
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fileWriter = new FileWriter(file);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      for (Account item : accountList) {
        bufferedWriter.write(item.getUsername() + " " + item.getPassword());
        bufferedWriter.newLine();
      }
      bufferedWriter.close();
      return true;
    } catch (Exception e) {
      System.out.println("Error write file Account.txt: " + e);
    }
    return false;
  }

  public static boolean readScore(String url) {
    try {
      scoreList.clear();
      FileReader fr = new FileReader(url);
      BufferedReader br = new BufferedReader(fr);
      String line = "";
      while ((line = br.readLine()) != null) {
        StringTokenizer stk = new StringTokenizer(line, " ");
        String username = stk.nextToken().trim();
        String username_id = username; // id = username
        int score = Integer.parseInt(stk.nextToken().trim());
        scoreList.put(username_id, new Score(username, score));
      }
      br.close();
      fr.close();
      return true;
    } catch (Exception e) {
      System.out.println("Error read file Score.txt: " + e);
    }
    return false;
  }

  public static boolean writeScore(String url) {
    try {
      // scoreList.clear();
      File file = new File(url);
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fileWriter = new FileWriter(file);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      for (Map.Entry<String, Score> entry : scoreList.entrySet()) {
        bufferedWriter.write(entry.getValue().getUsername() + " " + entry.getValue().getScore());
        bufferedWriter.newLine();
      }
      bufferedWriter.close();
      return true;
    } catch (Exception e) {
      System.out.println("Error write file Score.txt: " + e);
    }
    return false;
  }
}
