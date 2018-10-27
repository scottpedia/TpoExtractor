package net.babustudio.Entries;

import net.babustudio.TpoExtractor.App;
import net.babustudio.TpoExtractor.AppForWord;

import java.io.IOException;
import java.sql.SQLException;

public class Executor {
    public static void main(String[] args) {
        try {
            App app = new AppForWord();
            app.getContent();
            app.output();
            System.out.println("Program completed.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
