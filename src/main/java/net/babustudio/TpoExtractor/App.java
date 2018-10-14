package net.babustudio.TpoExtractor;

import com.google.gson.GsonBuilder;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


/**
 * Hello world!
 */
public class App {
    protected Connection conn = null;
    protected String sentence = "select articleID,title,paragraphDetail from tbl_toefl_paragraph order by articleID;";
    protected String connectionProperties = "";
    protected String outputDirectory = "";
    protected ArrayList<Article> articles = new ArrayList<Article>();

    public App() {
        try { //get the connection to the local database file.
            this.getProperties();
            this.conn = DriverManager.getConnection(this.connectionProperties);
            System.out.println("Successfully connected to the database!");
            System.out.println("Getting properties...");
        } catch (SQLException e) {
            System.err.println("Failed to get the connection! Error(s):\n" + e.getMessage());
            System.exit(1);
        }catch (IOException e){
            System.err.println("Failed to get properties...\n" + e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        try {
            App app = new App();
            app.getContent();
            //app.output();
            app.outputToSingle();
            System.out.println("Program completed.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getProperties() throws IOException{
        InputStream settings = new FileInputStream("settings.properties");
        Properties properties = new Properties();
        properties.load(settings);

        this.connectionProperties = properties.getProperty("databaseUrl");
        this.outputDirectory = properties.getProperty("outputDirectory");
        System.out.println("properties' got.");
    }

    private void getContent() throws SQLException {
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
                    article.content = content;
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

    private ResultSet getResult() {
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
        for (Article article : this.articles) {
            File mapping = new File(this.outputDirectory + "/" + "[" + article.articleID + "] " + article.getTitle() + ".txt");
            mapping.createNewFile();
            FileWriter fileWriter = new FileWriter(mapping);
            fileWriter.write(article.getContent());
            fileWriter.close();
        }
    }

    public void outputToSingle() throws IOException {
        File dir = new File(this.outputDirectory);
        if (!dir.exists() || !dir.isDirectory()) {
            IOException ioException = new IOException("Failed to access the directory.");
        }
        dir.createNewFile();
        File mapping = new File(this.outputDirectory + "/" + "[" + "ULTIMATE_COLLECTION" + "].txt");
        mapping.createNewFile();
        for (Article article : this.articles) {
            FileWriter fileWriter = new FileWriter(mapping,true);
            fileWriter.write(String.format("\n\n---------------------[%s] %s----------------------\n\n",article.getArticleID(),article.getTitle()));
            fileWriter.write(article.getContent());
            fileWriter.close();
        }
    }
    public class Article implements Serializable {
        String articleID, title, content;

        private Article() {
        }

        public String getArticleID() {
            return articleID;
        }

        @Override
        public String toString() {
            return String.format("ArticleID: %s\nTitle: %s\n%s", articleID, title, content);
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String toJson() {
            return new GsonBuilder().create().toJson(this);
        }

        public String toJson(boolean prettyOut) {
            if (prettyOut) return new GsonBuilder().setPrettyPrinting().create().toJson(this);
            else return new GsonBuilder().create().toJson(this);
        }
    }
}
