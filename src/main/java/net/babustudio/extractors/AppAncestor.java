package net.babustudio.extractors;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

interface AppAncestor {
// --Commented out by Inspection START (2019/1/6, 10:15):
//    // --Commented out by Inspection (2019/1/6, 10:15):void ge// --Commented out by Inspection (2019/1/6, 10:15):tProperties() throws IOException;
//
//    // --Commented out by Inspection (2019/1/6, 10:15):void getContent() throws SQLException;
// --Commented out by Inspection STOP (2019/1/6, 10:15)

    ResultSet getResult();

    void output() throws IOException;
}
