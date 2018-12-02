package net.babustudio.entries;

import java.util.Scanner;

import org.slf4j.*;

public class Terminal {

    private Scanner scanner = null;
    private static Logger logger = LoggerFactory.getLogger(Terminal.class);
    public Terminal(){
        this.scanner = new Scanner(System.in);

    }

    private boolean queryFilePath(){
        System.out.println("Hello");
        return true;
    }

    public static void main(String[] args) {
        Terminal.logger.info("hello");
    }
}
