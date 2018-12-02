package net.babustudio.tpoExtractor;

import net.babustudio.models.Article;
import net.babustudio.Util;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Hello world!
 */
public class App implements AppAncestor {
    protected Connection conn = null;
    protected final String sentence = "select articleID,title,paragraphDetail from tbl_toefl_paragraph order by articleID;";
    protected String connectionProperties = "";
    protected String outputDirectory = "";
    protected ArrayList<Article> articles = new ArrayList<Article>();

    public App() {
        try { //get the connection to the local database file.
            System.out.println("Getting properties...");
            this.getProperties();
            this.conn = DriverManager.getConnection(this.connectionProperties);
            System.out.println("Successfully connected to the database!");

        } catch (SQLException e) {
            System.err.println("Failed to get the connection! Error(s):\n" + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Failed to get properties...\n" + e.getMessage());
            System.exit(1);
        }
    }

    public void getProperties() throws IOException {
        //InputStream settings = new FileInputStream("settings.properties");
        //Legacy approach for getting the properties is reserved for stability considerations. The one currently being used as shown below:
        InputStream settings = new Thread().getContextClassLoader().getResourceAsStream("settings.properties");
        Properties properties = new Properties();
        properties.load(settings);

        this.connectionProperties = properties.getProperty("databaseUrl");
        this.outputDirectory = properties.getProperty("outputDirectory");
        System.out.println("properties' got.");
    }

    public void getContent() throws SQLException {
        ResultSet result = this.getResult();
        try {
            result.next();
            String content = "";
            String title = "";
            String temp = result.getString("articleID");
            int i = 1;
            while (true) {
                if (result.getString("articleID").hashCode() != temp.hashCode()) {
                    Article article = new Article();
                    article.content = Util.replace(content);
                    article.title = title;
                    article.articleID = String.valueOf(i++);
                    articles.add(article);
                    content = "";
                    temp = result.getString("articleID");
                }
                content += result.getString("paragraphDetail");
                temp = result.getString("articleID");
                title = result.getString("title");
                result.next();
            }
        } catch (SQLException e) {
            System.out.println("Contents completely collected. Closing statment.");
            String out = String.format(
                    "Article Collected : %d\n" +
                            "Output Directory  : %s", this.articles.size(), this.outputDirectory);
            System.out.println(out);
        } finally {
            result.close();
            this.conn.close();
        }
    }

    public ResultSet getResult() {
        PreparedStatement statement = null;
        try {
            statement = this.conn.prepareStatement(this.sentence);
        } catch (SQLException e) {
            System.err.println("Errors occurred during Statement creating, aborting!\n " + e.getMessage());
            System.exit(1);
        }
        ResultSet result = null;
        try {
            result = statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Errors occurred during query, aborting!\n " + e.getMessage());
            System.exit(1);
        }
        return result;
    }

    public void output() throws IOException {
        File dir = new File(this.outputDirectory);
        if (!dir.exists() || !dir.isDirectory()) {
            IOException ioException = new IOException("Failed to access the directory.");
        }
        dir.createNewFile();
        System.out.println("Exporting...");
        int realSequence = 0;
        for (Article article : this.articles) {
            if(article.content.length() < 10) continue;
            realSequence++;
            File mapping = new File(this.outputDirectory + "/" + "[" + realSequence + "] " + article.getTitle() + ".txt");
            mapping.createNewFile();
            FileWriter fileWriter = new FileWriter(mapping);
            fileWriter.write(article.getContent());
            fileWriter.close();
        }
        System.out.println("Done");
    }

}
