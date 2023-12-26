package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import model.Account;
import model.Score;

public class DataHandler {

  public static ArrayList<Account> accountList = new ArrayList<>();
  public static ArrayList<Score> scoreList = new ArrayList<>();

  public boolean readFile(String url) {
    try {
      FileReader fr = new FileReader(url);
      BufferedReader br = new BufferedReader(fr);
      String line = "";
      while ((line = br.readLine()) != null) {
        StringTokenizer stk = new StringTokenizer(line, " ");
        String username = stk.nextToken();
        String password = stk.nextToken();
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
      FileReader fr = new FileReader(url);
      BufferedReader br = new BufferedReader(fr);
      String line = "";
      while ((line = br.readLine()) != null) {
        StringTokenizer stk = new StringTokenizer(line, " ");
        String username = stk.nextToken();
        int score = Integer.parseInt(stk.nextToken());
        scoreList.add(new Score(username, score));
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
      File file = new File(url);
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fileWriter = new FileWriter(file);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      for (Score item : scoreList) {
        bufferedWriter.write(item.getUsername() + " " + item.getScore());
        bufferedWriter.newLine();
      }
      bufferedWriter.close();
      return true;
    } catch (Exception e) {
      System.out.println("Error write file Score.txt: " + e);
    }
    return false;
  }

  @Override
  public String toString() {
    return "[scoreList=" + scoreList + "]";
  }
}
