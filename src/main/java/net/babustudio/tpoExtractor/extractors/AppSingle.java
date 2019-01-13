package net.babustudio.tpoExtractor.extractors;

// --Commented out by Inspection START (2019/1/6, 10:15):
//public class AppSingle extends App {
//
//    @Override
//    public void output() throws IOException {
//        File dir = new File(this.outputDirectory);
//        if (!dir.exists() || !dir.isDirectory()) {
//            throw new IOException("Failed to access the directory.");
//        }
//        dir.createNewFile();
//        File mapping = new File(this.outputDirectory + "/" + "[" + "ULTIMATE_COLLECTION" + "].txt");
//        mapping.createNewFile();
//        for (Article article : this.articles) {
//            FileWriter fileWriter = new FileWriter(mapping, true);
//            fileWriter.write(String.format("\n\n---------------------[%s] %s----------------------\n\n", article.getArticleID(), article.getTitle()));
//            fileWriter.write(article.getContent());
//            fileWriter.close();
//        }
//    }
//}
// --Commented out by Inspection STOP (2019/1/6, 10:15)
