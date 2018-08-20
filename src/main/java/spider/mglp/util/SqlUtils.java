package spider.mglp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spider.mglp.enums.SqlEnum;
import spider.mglp.pojo.ProductSpuWithBLOBs;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * <p>pakage: spider.mglp.util</p>
 *
 * descirption: Sql语句执行都在这里了，sql语句在SqlEnum.java中，是个枚举类。
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/10 下午12:38</pre>
 */
public class SqlUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlUtils.class);

    /**
     * 爬虫获取的图像链接，存入数据库
     * 遍历arrayList<ProductSpuWithBLOBs>，拿出每个对象，拼接添加sql语句，完成后，batch批量插入。
     * 以避免每一个对象插入一次，造成大量的数据库建立链接以及销毁链接的开销
     *
     * @param basciSql 枚举变量，@see{SqlEnum.java}
     * @param records  ProductSpuWithBLOBs对象集合
     */
    public static void updateImgsUrlByBatch(String basciSql, ArrayList<ProductSpuWithBLOBs> records) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        // "update product_spu_local set spider_imgs_all = ? where spu_code = ?"
        try {
            conn = DbFactory.getConnection("sys-config-local.xml");
            preStmt = conn.prepareStatement(basciSql);
            for (ProductSpuWithBLOBs pswb : records) {
                preStmt.setString(1, pswb.getSpiderImgsAll());
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
     * 获取今天在架的Spu_code、taobao_link    ===========================   [ 线上数据库 ]
     *
     * @param ifAll 如果传入，则查找按日期处理评论标签，TODO
     * @return HashMap
     */
    public static HashMap<String, String> getSpuCodeAndTbLink(String... ifAll) {
        System.out.println("获取Spu_code、taobao_link");
        HashMap<String, String> hashMap = new HashMap<>(512, 0.8f);
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs;
        String sql;
        if (ifAll.length > 0) {
            sql = SqlEnum.SELECT_SPU_TLINK_BY_SPUCODE.getDesc();
        } else {
            sql = SqlEnum.SELECT_SPU_TLINK.getDesc();
        }
        // "select spu_code,taobao_link from product_spu_local;"
        try {
            conn = DbFactory.getConnection("sys-config-online.xml");
            preStmt = conn.prepareStatement(sql);
            if (ifAll.length > 0) {
                // 两个日期
                preStmt.setString(1, ifAll[0]);
                preStmt.setString(2, ifAll[1]);
            }
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

    /**
     * 获取Spu_code、color
     *
     * @return hashmap
     */
    public static HashMap<String, String> getSpuCodeAndColor() {
        System.out.println("查找spu_code和color");
        HashMap<String, String> hashMap = new HashMap<>(512, 0.8f);
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs;
        // "select spu_code,taobao_link from product_spu_local;"
        try {
            conn = DbFactory.getConnection("sys-config-online.xml");
            preStmt = conn.prepareStatement("select spu_code,color from product_spu;");
            rs = preStmt.executeQuery();
            while (rs.next()) {
                String spu_code = rs.getString("spu_code");
                int color = rs.getInt("color");
                switch (color) {
                    case 1:
                        hashMap.put(spu_code, "白色系");
                        break;
                    case 2:
                        hashMap.put(spu_code, "灰色系");
                        break;
                    case 3:
                        hashMap.put(spu_code, "黑色系");
                        break;
                    case 4:
                        hashMap.put(spu_code, "红色系");
                        break;
                    case 5:
                        hashMap.put(spu_code, "橙色系");
                        break;
                    case 6:
                        hashMap.put(spu_code, "黄色系");
                        break;
                    case 7:
                        hashMap.put(spu_code, "绿色系");
                        break;
                    case 8:
                        hashMap.put(spu_code, "蓝色系");
                        break;
                    case 9:
                        hashMap.put(spu_code, "紫色系");
                        break;
                    case 10:
                        hashMap.put(spu_code, "粉色系");
                        break;
                    case 11:
                        hashMap.put(spu_code, "驼色系");
                        break;
                    case 12:
                        hashMap.put(spu_code, "焦糖色系");
                        break;
                    case 13:
                        hashMap.put(spu_code, "多色系");
                        break;
                    case 14:
                        hashMap.put(spu_code, "金色系");
                        break;
                    case 15:
                        hashMap.put(spu_code, "银色系");
                        break;
                    case 16:
                        hashMap.put(spu_code, "杏色系");
                        break;
                    case 17:
                        hashMap.put(spu_code, "白色_蓝色");
                        break;
                    case 18:
                        hashMap.put(spu_code, "白色_红色");
                        break;
                    case 19:
                        hashMap.put(spu_code, "白色_黑色");
                        break;
                    case 20:
                        hashMap.put(spu_code, "黑色_蓝色");
                        break;
                    case 21:
                        hashMap.put(spu_code, "藏蓝色系");
                        break;
                    case 22:
                        hashMap.put(spu_code, "浅牛仔蓝");
                        break;
                    case 23:
                        hashMap.put(spu_code, "深牛仔蓝");
                        break;
                    default:
                        System.out.println("default");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbFactory.closePreStmt(preStmt);
            DbFactory.closeConnection(conn);
        }
        return hashMap;
    }

    /**
     * 获取Spu_code、spider_imgs_all
     *
     * @return hashmap
     * @num: 0是查询所有没有下载的，其他数字过滤是下载了的，一般用于回滚或者验证。
     */
    public static HashMap<String, String> getSpuCodeAndImgsUrl(int num) {
        HashMap<String, String> hashMap = new HashMap<>(512, 0.7f);
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs;
        String sql;
        sql = SqlEnum.SCODE_AND_IMGSURL_FLAG.getDesc();
        // "SELECT spu_code,spider_imgs_all from product_spu_local where length(spider_imgs_all) > 4 and download_flag= ？"
        try {
            conn = DbFactory.getConnection("sys-config-local.xml");
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, num);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                hashMap.put(rs.getString("spu_code"), rs.getString("spider_imgs_all"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbFactory.closePreStmt(preStmt);
            DbFactory.closeConnection(conn);
        }
        return hashMap;
    }

    /**
     * 获取item_id、imgs_link（图片数据链接，手动导入的那份）
     *
     * @return hashmap
     */
    public static HashMap<String, String> getItemIdAndImgsUrl() {
        System.out.println("+++++++++++++++++++++");
        HashMap<String, String> hashMap = new HashMap<>(512, 0.7f);
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs;
        // "SELECT item_id,url from itemId_urls where url IS NOT NULL"
        try {
            conn = DbFactory.getConnection("sys-config-local.xml");
            preStmt = conn.prepareStatement(SqlEnum.SELECT_ITEMID_LINK.getDesc());
            rs = preStmt.executeQuery();
            while (rs.next()) {
                hashMap.put(rs.getString("item_id"), rs.getString("url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbFactory.closePreStmt(preStmt);
            DbFactory.closeConnection(conn);
        }
        return hashMap;
    }

    public static void setDownloaded(int flag, String spuCode) {
        Connection conn = null;
        Statement stm = null;
        boolean ret;
        // "SELECT item_id,url from itemId_urls where url IS NOT NULL"
        try {
            conn = DbFactory.getConnection("sys-config-local.xml");
            stm = conn.createStatement();
            // TODO:change test database table_name
            ret = stm.execute("update product_spu_local set download_flag = " + flag + " where spu_code = " + spuCode);
            int count = stm.getUpdateCount();
            if (!ret) {
                System.out.println(count + "条数据修改成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DbFactory.closeConnection(conn);
        }
    }

    /*下方注释的几个方法，是与之前更新线上图片、回滚数据库有关的，但是现在应该是没有作用了，但是先留着，可能会有用？*/


    /**
     * 获取图片已经上传了的spu 【已经不再使用】
     *
     * @return HashSet  spu集合
     */
    @Deprecated
    public static HashSet<String> getUploadedSpu() {
        HashSet<String> hashSet = new HashSet<>(512, 0.7f);
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs;
        String sql;
        sql = SqlEnum.SPU_UPLOADED.getDesc();
        // "SELECT spu_code from product_spu_local where length(spider_imgs_all) > 4 and upload_flag = '1'"
        try {
            conn = DbFactory.getConnection("sys-config-local.xml");
            preStmt = conn.prepareStatement(sql);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                hashSet.add(rs.getString("spu_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbFactory.closePreStmt(preStmt);
            DbFactory.closeConnection(conn);
        }
        return hashSet;
    }

//
//    /*
//     * UPDATE_CDN_IMGS 更新/回滚的是本地库local
//     */
//    public static void updateOrBackUpLocalCdnImgs(String head_image, String detailsImgs, String spuCode, String status) {
//        Connection conn = null;
//        PreparedStatement preStmt = null;
//        try {
//            conn = DbFactory.getConnection("sys-config-local.xml");
//            if (status.equals("update")) {
//                // 执行的是更新
//                preStmt = conn.prepareStatement(SqlEnum.UPDATE_CDN_IMGS_LOCAL.getDesc());
//            } else {
//                // 执行的是回滚
//                preStmt = conn.prepareStatement(SqlEnum.BACK_CDN_IMGS_LOCAL.getDesc());
//
//            }
//            preStmt.setString(1, head_image);
//            preStmt.setString(2, detailsImgs);
//            preStmt.setString(3, spuCode);
//            preStmt.executeUpdate();
//            LOGGER.info(status + "\t" + spuCode + "\t" + head_image + "\t" + detailsImgs);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DbFactory.closePreStmt(preStmt);
//            DbFactory.closeConnection(conn);
//        }
//    }
//
//    // 更新/回滚线上库，由于赶时间，还没写批量更新 TODO
//    public static void updateOrBackUpOnlineSpuTable(String head_image, String detailsImgs, String spuCode, String status) {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Connection conn = null;
//        PreparedStatement preStmt = null;
//        String url = "jdbc:mysql://rm-m5eh88dfuiq4djnylxo.mysql.rds.aliyuncs.com:3306/shendeng-admin-db";
//        try {
//            conn = DriverManager.getConnection(url, "rs_read", "JlhZeJb1xd7X6BI5ucmEsxvebof9soCO");
//            preStmt = conn.prepareStatement(SqlEnum.UPDATE_CDN_IMGS_ONLINE.getDesc());
//            preStmt.setString(1, head_image);
//            preStmt.setString(2, detailsImgs);
//            preStmt.setString(3, spuCode);
//            preStmt.executeUpdate();
//            LOGGER.info(status + "\t" + spuCode + "\t" + head_image + "\t" + detailsImgs);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (preStmt != null) {
//                    preStmt.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (conn != null) {
//                    conn.close();
//                    System.out.println("远程数据库连接已断开！！");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // 查询本地的，更新线上库
//    public static void updateOnline(int download_flag) {
//        int updateCount = 0;
//        Connection conn = null;
//        PreparedStatement preStmt = null;
//        ResultSet rs;
//        try {
//            conn = DbFactory.getConnection();
//            // 查找这次更新的内容，查找的是本地的（下载flag过滤，以及upload_flag == true的）
//            // 暂时是：select head_image,details_images,spu_code from product_spu_local where download_flag > ? and upload_flag = '1';
//            preStmt = conn.prepareStatement(SqlEnum.PREPARE_UPDATE_SPU.getDesc());
//            preStmt.setInt(1, download_flag);
//            rs = preStmt.executeQuery();
//            while (rs.next()) {
//                updateCount++;
//                String head_image = rs.getString("head_image");
//                String details_images = rs.getString("details_images");
//                String spu_code = rs.getString("spu_code");
//                // 更新线上的库
//                SqlUtils.updateOrBackUpOnlineSpuTable(head_image, details_images, spu_code, "update");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DbFactory.closePreStmt(preStmt);
//            DbFactory.closeConnection(conn);
//        }
//        LOGGER.info("更新 线上库共\t{}\t条数据", updateCount);
//    }
//
//    /**
//     * 回滚线上库/线下库
//     * 1、查询本次我更新了的所有的spu_code（这是第一个库，查询的是product_spu_local）
//     * 2、从本地备份的sql文件，生成product_spu，根据1中的spu_code，查找备份中的image、details_images、spu_code
//     * 3、根据2中的数据，更新线上库，@see{updateOrBackUpOnlineSpuTable(String head_image, String detailsImgs, String spuCode)}
//     * bool值为true，回滚线下库local
//     */
//    public static void backOnlineOrLocal(boolean local) {
//        int backCount = 0;
//        Connection conn = null;
//        PreparedStatement preStmt = null;
//        ResultSet rs;
//        // "select spu_code from product_spu_local where download_flag = '1' and length(details_images) > 50"
//        try {
//            conn = DbFactory.getConnection();
//
//            // 查询本次我更新了的所有的spu_code，TODO：每次需要修改传入 Sql 的 Like 的值
//            preStmt = conn.prepareStatement(SqlEnum.BACK_SPU_CODE.getDesc());
//            rs = preStmt.executeQuery();
//            while (rs.next()) {
//                backCount++;
//                String spu_code = rs.getString("spu_code");
//                // 去查找备份库的数据并回滚数据
//                SqlUtils.backProductSpu(spu_code, local);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DbFactory.closePreStmt(preStmt);
//            DbFactory.closeConnection(conn);
//        }
//        if (local) {
//            LOGGER.info("回滚  线下库共\t{}\t条数据", backCount);
//        } else {
//            LOGGER.info("回滚  线上库共\t{}\t条数据", backCount);
//        }
//    }
//
//    /**
//     * 根据spucode，查询备份库中的String head_image, String detailsImgs, String spuCode
//     */
//    public static void backProductSpu(String spuCode, boolean local) {
//        Connection conn = null;
//        PreparedStatement preStmt = null;
//        ResultSet rs;
//        try {
//            conn = DbFactory.getConnection();
//            // 查询备份库，select head_image,details_images,spu_code from product_spu where spu_code = ?
//            preStmt = conn.prepareStatement(SqlEnum.BACK_DATA.getDesc());
//            preStmt.setString(1, spuCode);
//            rs = preStmt.executeQuery();
//            while (rs.next()) {
//                String head_image = rs.getString("head_image");
//                String details_images = rs.getString("details_images");
//                String spu_code = rs.getString("spu_code");
//                if (local) {
//                    // 回滚线下local库
//                    SqlUtils.updateOrBackUpLocalCdnImgs(head_image, details_images, spu_code, "back-up");
//                } else {
//                    // 去回滚线上的库
//                    SqlUtils.updateOrBackUpOnlineSpuTable(head_image, details_images, spu_code, "back-up");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DbFactory.closePreStmt(preStmt);
//            DbFactory.closeConnection(conn);
//        }
//    }

}
