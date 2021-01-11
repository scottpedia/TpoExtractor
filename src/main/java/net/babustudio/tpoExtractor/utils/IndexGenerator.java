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


package net.babustudio.tpoExtractor.utils;

import java.io.*;
import java.util.ArrayList;

class IndexGenerator {

    private final String[] directories = {"/Users/Billy/coda/TpoExtractor/listenings", "/Users/Billy/coda/TpoExtractor/passages"};
    private ArrayList<File> listenings = null;
    private ArrayList<File> passages = null;
    private String md_fragment_listenings = "";
    private String md_fragment_passages = "";

    private IndexGenerator() {
        this.getPassages();
        this.getListening();
    }

    public static void main(String[] args) throws IOException {
        IndexGenerator IG = new IndexGenerator();
        String lis_out = IG.getMd_fragment_listenings();
        String pas_out = IG.getMd_fragment_passages();
        File README = new File("/Users/Billy/coda/TpoExtractor/README.md");
        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter(README, true);
            fileWriter.write(lis_out);
            fileWriter.write("\n");
            fileWriter.write(pas_out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }
    }

    private void getListening() {
        this.listenings = new ArrayList<File>();

        for (File listening : new File(directories[0]).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        })) {
            listenings.add(listening);
        }
    }

    private void getPassages() {
        this.passages = new ArrayList<File>();

        for (File passage : new File(directories[1]).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        })) {
            passages.add(passage);
        }
    }

    private String getMd_fragment_listenings() {
        this.md_fragment_listenings = String.format("- [`%s`](/listenings)\n", "Listenings");
        for (File listening : this.listenings) {
            this.md_fragment_listenings += String.format("  - [`%s`](/listenings/%s)\n", Utils.titleReplace(listening.getName()), Utils.nameReplace(listening.getName()));
        }
        return this.md_fragment_listenings;
    }

    private String getMd_fragment_passages() {
        this.md_fragment_passages = String.format("- [`%s`](/passages)\n", "Passages");
        for (File passage : this.passages) {
            this.md_fragment_passages += String.format("  - [`%s`](/passages/%s)\n", Utils.titleReplace(passage.getName()), Utils.nameReplace(passage.getName()));
        }
        return this.md_fragment_passages;
    }
}
