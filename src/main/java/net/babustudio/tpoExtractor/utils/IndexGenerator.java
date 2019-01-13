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
