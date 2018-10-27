package net.babustudio.TpoExtractor;

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
