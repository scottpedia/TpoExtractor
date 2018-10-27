package net.babustudio.TpoExtractor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AppAncestor {
    void getProperties() throws IOException;

    void getContent() throws SQLException;

    ResultSet getResult();

    void output() throws IOException;
}
