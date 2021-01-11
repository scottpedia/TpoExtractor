/*
Copyright (C) 2018-2021 Scott X. Liang <me@theanonymity.de>

This file is part of TpoExtractor.

TpoExtractor is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

TpoExtractor is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with TpoExtractor.  If not, see <https://www.gnu.org/licenses/>.
*/


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
