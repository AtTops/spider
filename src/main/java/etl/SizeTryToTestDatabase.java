package etl;


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
    public static void main(String[] args) throws IOException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cld = Calendar.getInstance();
        Timestamp timeStamp = new Timestamp(cld.getTime().getTime());
        //System.out.println(dateTime);
        String type = "size";
        int status = 1;
        InputStreamReader read = new InputStreamReader(new FileInputStream(new File("/Users/wanghai/spider/size/files/json/adjust/size_2018-08-14.json")));
        BufferedReader br = new BufferedReader(read);
        String line;
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            conn = DbFactory.getConnection();
            preStmt = conn.prepareStatement("insert into spu_size_info(spu_code,type,status,content,create_time,update_time)values(?,?,?,?,?,?)");
            while ((line = br.readLine()) != null) {
                //String s = "{\"spu\":\"103018156886\",\"尺码\":\"XS码\",\"腰围（腰口）\":\"72\",\"腰围（松紧）\":\"67\",\"臀围\":\"84\",\"脚口/下摆\":\"95\",\"侧长\":\"56\"}";
                String spu_code = line.substring(8, 20);
                String content = "{" + line.substring(22);
                // todo:插入数据库
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

//    public void insert(String spuCode, String type, int status, String content, Timestamp createTime, Timestamp updateTime) {
//
//    }
}
