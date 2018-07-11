package spider.mglp.util;

import spider.mglp.enums.SqlEnum;
import spider.mglp.pojo.ProductSpuWithBLOBs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>pakage: spider.mglp.util,descirption:</p>
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/10 下午12:38</pre>
 */
public class SqlUtils {

    /**
     * 遍历arrayList<ProductSpuWithBLOBs>，拿出每个对象，拼接添加sql语句，完成后，batch批量插入。
     * 以避免每一个对象插入一次，造成大量的数据库建立链接以及销毁链接的开销
     *
     * @param basciSql 枚举变量，@see{SqlEnum.java}
     * @param records  ProductSpuWithBLOBs对象集合
     */
    public static void updateImgsUrlByBatch(String basciSql, ArrayList<ProductSpuWithBLOBs> records) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        // "update product_spu set spider_imgs = ? where spu_code = ?"
        try {
            conn = DbFactory.getConnection();
            preStmt = conn.prepareStatement(basciSql);
            for (int i = 0; i < records.size(); i++) {
                ProductSpuWithBLOBs pswb = records.get(i);
                preStmt.setString(1, pswb.getSpiderImgs());
                preStmt.setString(2, pswb.getSpuCode());
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

    /**
     * 获取Spu_code、taobao_link
     *
     * @return hashmap
     */
    public static HashMap<String, String> getSpuCodeAndTbLink() {
        HashMap<String, String> hashMap = new HashMap<>(512, 0.8f);
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs;
        // "select spu_code,taobao_link from product_spu;"
        try {
            conn = DbFactory.getConnection();
            preStmt = conn.prepareStatement(SqlEnum.SELECT_SPU_TLINK.getDesc());
            rs = preStmt.executeQuery();
            while (rs.next()) {
                hashMap.put(rs.getString("spu_code"), rs.getString("taobao_link"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbFactory.closePreStmt(preStmt);
            DbFactory.closeConnection(conn);
        }
        return hashMap;
    }

}
