//package com.mrone.config;
//
//import com.alibaba.otter.canal.client.CanalConnector;
//import com.alibaba.otter.canal.client.CanalConnectors;
//import com.alibaba.otter.canal.protocol.CanalEntry;
//import org.springframework.beans.factory.InitializingBean;
//import com.alibaba.otter.canal.protocol.Message;
//import org.springframework.stereotype.Component;
//
//import java.net.InetSocketAddress;
//import java.util.List;
//
//
///**
// * @program: shiro
// * @description:
// * @author: Mr.One
// * @create: 2022-09-10 20:13
// **/
//
//@Component
//public class CanalClient implements InitializingBean {
//
//
//    private final static int BATCH_SIZE = 1000;
//
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        // 创建链接
//        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("101.43.31.192", 11111), "example", "root", "123456");
//        try {
//            //打开连接
//            connector.connect();
//            //订阅数据库表,全部表
//            connector.subscribe(".*..*");
//            //回滚到未进行ack的地方，下次fetch的时候，可以从最后一个没有ack的地方开始拿
//            connector.rollback();
//            while (true) {
//                // 获取指定数量的数据
//                Message message = connector.getWithoutAck(BATCH_SIZE);
//                //获取批量ID
//                long batchId = message.getId();
//                //获取批量的数量
//                int size = message.getEntries().size();
//                //如果没有数据
//                if (batchId == -1 || size == 0) {
//                    try {
//                        //线程休眠2秒
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    //如果有数据,处理数据
//                    printEntry(message.getEntries());
//                    List<CanalEntry.Entry> entries = message.getEntries();
//                }
//                //进行 batch id 的确认。确认之后，小于等于此 batchId 的 Message 都会被确认。
//                connector.ack(batchId);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            connector.disconnect();
//        }
//    }
//
//    /**
//     * 打印canal server解析binlog获得的实体类信息
//     */
//    private static void printEntry(List<CanalEntry.Entry> entrys) {
//        for (CanalEntry.Entry entry : entrys) {
//            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
//                //开启/关闭事务的实体类型，跳过
//                continue;
//            }
//            //RowChange对象，包含了一行数据变化的所有特征
//            //比如isDdl 是否是ddl变更操作 sql 具体的ddl sql beforeColumns afterColumns 变更前后的数据字段等等
//            CanalEntry.RowChange rowChage;
//            try {
//                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
//            } catch (Exception e) {
//                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(), e);
//            }
//            //获取操作类型：insert/update/delete类型
//            CanalEntry.EventType eventType = rowChage.getEventType();
//
//            //判断是否是DDL语句
//            if (rowChage.getIsDdl()) {
//                System.out.println("是DDL语句" + rowChage.getSql());
//            }
//
//            // 根据不同的语句类型，处理不同的业务
//            if (eventType == CanalEntry.EventType.INSERT) {
//                // 是新增语句，业务处理。如果新增的时候数据没有发生变化的情况下，是不会被执行
//                System.out.println("库名：" + entry.getHeader().getSchemaName() + "-- 表名：" + entry.getHeader().getTableName() + "新增数据");
//                // 获取RowChange对象里面的每一行信息
//                for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
//                    printColumn(rowData.getAfterColumnsList());
//                }
//            } else if (eventType == CanalEntry.EventType.DELETE) {
//                // 是删除语句，业务处理。如果删除的时候是没有数据的情况下，是不会被执行
//                System.out.println("库名：" + entry.getHeader().getSchemaName() + "-- 表名：" + entry.getHeader().getTableName() + "删除数据");
//                // 获取RowChange对象里面的每一行信息
//                for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
//                    printColumn(rowData.getBeforeColumnsList());
//                }
//            } else if (eventType == CanalEntry.EventType.UPDATE) {
//                // 是修改语句，业务处理。如果修改的时候是没有修改任何数据的情况下，是不会被执行
//                System.out.println("库名：" + entry.getHeader().getSchemaName() + "-- 表名：" + entry.getHeader().getTableName() + "修改数据");
//                // 获取RowChange对象里面的每一行信息
//                for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
//                    printColumn(rowData.getBeforeColumnsList());
//                    printColumn(rowData.getAfterColumnsList());
//                }
//            }
//        }
//    }
//
//    private static void printColumn(List<CanalEntry.Column> columns) {
//        for (CanalEntry.Column column : columns) {
//            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
//        }
//    }
//
//
//}
//
