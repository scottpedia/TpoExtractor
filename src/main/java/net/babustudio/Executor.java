package net.babustudio;

import net.babustudio.extractors.App;
import net.babustudio.extractors.AppJson;
import net.babustudio.extractors.AppMD;

import java.io.IOException;
import java.sql.SQLException;

public class Executor {
    public static void main(String[] args) {
        try {
            App app = new AppMD();
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
