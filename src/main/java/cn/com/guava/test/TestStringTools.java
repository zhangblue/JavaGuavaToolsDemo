package cn.com.guava.test;

import cn.com.guava.files.GuavaFilesTools;
import cn.com.guava.util.StringTools;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zhangdi on 2017/06/02.
 */
public class TestStringTools {

    public static void main(String[] args) {
        new TestStringTools().optimizationIndex();
    }


    public void shards0() {
        StringTools stringTools = new StringTools();
        GuavaFilesTools guavaFilesTools = new GuavaFilesTools();

        try {
            List<String> listAll = Files.readLines(new File("/Users/zhangdi/test_folder/yellow_index/indexs"), Charsets.UTF_8);

            List<String> listLines = listAll.subList(1, listAll.size());

            List<String> listNew = new ArrayList<>();
            for (String line : listLines) {
                String[] comumn = stringTools.trimManySpace(line).split(" ");
                if (comumn[0].equals("yellow")) {

                    String strl = "curl -XPUT 'http://cmserver:9200/" + comumn[2] + "/_settings' -d'{\"index\":{\"number_of_replicas\":1}}'";
                    listNew.add(strl);
                }
            }

            guavaFilesTools.appendContentToFile(listNew, new File("/Users/zhangdi/test_folder/yellow_index/shard1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 优化index
     */
    public void optimizationIndex() {
        StringTools stringTools = new StringTools();
        GuavaFilesTools guavaFilesTools = new GuavaFilesTools();

        try {
            List<String> listAll = Files.readLines(new File("/Users/zhangdi/test_folder/merge_index/all_index"), Charsets.UTF_8);

            List<String> listLines = listAll.subList(1, listAll.size());

            List<String> listOld = new ArrayList<>();
            List<String> listNew = new ArrayList<>();
            long nowtime = Calendar.getInstance().getTimeInMillis() / 1000;
            StringTools stringTools1 = new StringTools();

            for (String line : listLines) {
                String[] comumn = stringTools.trimManySpace(line).split(" ");
                //String indexName = comumn[2];
                //if (indexName.startsWith(".marvel-es-1-")) {
                //    //删除3天前的表
                //    String day = stringTools1.trimBeginKey(indexName, ".marvel-es-1-");
                //    if (day.compareTo("2017.06.01") < 0) {
                //        listNew.add("echo 'delete " + comumn[2] + "' >> logs");
                //        listNew.add("curl -XDELETE 'http://cmserver:9200/" + comumn[2] + "'");
                //    }
                //} else if (indexName.split("_").length == 2) {
                //    String[] indexs = indexName.split("_");
                //    //合并4天前的表
                //    if (indexs[1].length() == 8 && indexs[1].compareTo("20170601") < 0) {
                //        listNew.add("echo 'begin " + comumn[2] + "' >> logs");
                //        listNew.add("curl -XPOST 'http://cmserver:9200/" + comumn[2] + "/_optimize?max_num_segments=1&wait_for_merge=false'");
                //    } else if (indexs[1].length() == 6 && indexs[1].compareTo("201706") < 0) {
                //        listNew.add("echo 'begin " + comumn[2] + "' >> logs");
                //        listNew.add("curl -XPOST 'http://cmserver:9200/" + comumn[2] + "/_optimize?max_num_segments=1&wait_for_merge=false'");
                //    }
                //}
                listNew.add("echo 'begin " + comumn[2] + "' >> logs");
                listNew.add("curl -XPOST 'http://cmserver:9200/" + comumn[2] + "/_optimize?max_num_segments=1'");
            }
            guavaFilesTools.appendContentToFile(listNew, new File("/Users/zhangdi/test_folder/merge_index/optimizationIndex"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除close的index
     */
    public void deleteOldIndex() {
        StringTools stringTools = new StringTools();
        GuavaFilesTools guavaFilesTools = new GuavaFilesTools();

        try {
            List<String> listAll = Files.readLines(new File("/Users/zhangdi/test_folder/index_merge/namenode01_index"), Charsets.UTF_8);
            List<String> listLines = listAll.subList(1, listAll.size());
            List<String> listNew = new ArrayList<>();
            for (String line : listLines) {
                String[] comumn = stringTools.trimManySpace(line).split(" ");
                if (comumn[1].equals("close")) {
                    listNew.add("curl -XDELETE 'namenode01:9200/" + comumn[2] + "'");
                }
            }
            guavaFilesTools.appendContentToFile(listNew, new File("/Users/zhangdi/test_folder/index_merge/delete_close_index"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
