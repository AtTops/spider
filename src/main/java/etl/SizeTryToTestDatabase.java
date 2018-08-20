package etl;


import spider.mglp.enums.UrlEnum;
import spider.mglp.util.DbFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <p>pakage: mglp.report</p>
 * <p>
 * descirption:
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/15 上午11:10</pre>
 */
public class SizeTryToTestDatabase {
    public static void insert(String type, String localDate) throws IOException {
        Calendar cld = Calendar.getInstance();
        Timestamp timeStamp = new Timestamp(cld.getTime().getTime());
        String jsonFile;
        if (type.equals("size")) {
            jsonFile = UrlEnum.JSON_SIZE_SUCCESS_ADJUST.getDesc() + "size_" + localDate + ".json";
        } else {
            jsonFile = UrlEnum.JSON_TRY_SUCCESS_ADJUST.getDesc() + "try_" + localDate + ".json";
        }
        int status = 1;
        InputStreamReader read = new InputStreamReader(new FileInputStream(new File(jsonFile)));
        BufferedReader br = new BufferedReader(read);
        String line;
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            conn = DbFactory.getConnection("sys-config-test.xml");
            preStmt = conn.prepareStatement("insert into spu_size_info(spu_code,type,status,content,create_time,update_time)values(?,?,?,?,?,?)");
            while ((line = br.readLine()) != null) {
                //String s = "{\"spu\":\"103018156886\",\"尺码\":\"XS码\",\"腰围（腰口）\":\"72\",\"腰围（松紧）\":\"67\",\"臀围\":\"84\",\"脚口/下摆\":\"95\",\"侧长\":\"56\"}";
                String spu_code = line.substring(8, 20);
                String content = "{" + line.substring(22);
                preStmt.setString(1, spu_code);
                preStmt.setString(2, type);
                preStmt.setInt(3, status);
                preStmt.setString(4, content);
                preStmt.setTimestamp(5, timeStamp);
                preStmt.setTimestamp(6, timeStamp);
                preStmt.addBatch();
            }

            preStmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbFactory.closePreStmt(preStmt);
            DbFactory.closeConnection(conn);
        }
    }
}
