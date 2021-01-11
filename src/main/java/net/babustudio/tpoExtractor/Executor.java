package net.babustudio.tpoExtractor;

import net.babustudio.tpoExtractor.extractors.App;
import net.babustudio.tpoExtractor.extractors.AppAncestor;
import net.babustudio.tpoExtractor.extractors.AppForWord;
import net.babustudio.tpoExtractor.utils.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Executor {

    public static void execute(AppAncestor app, boolean isTestScope) {
        try {
            app.getContent();
            app.output();
            System.out.println("Program completed.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isTestScope) {
                pause();
                Utils.cleanup(app.getOutputDirectory());
            }
        }
    }

    private static void pause() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Test Scope Executed, please press enter to release the pause and do the cleanup.");
        scanner.hasNextLine();
    }

    public static void main(String[] args) {
        execute(new AppForWord(), false);
    }
}
