package cn.com.guava.files;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangdi on 2017/04/12.
 */
public class FilesTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add(null);
        list.add("c");
        list.add("");

        String str = Joiner.on(",").skipNulls().join(list);
        System.out.println(str);
    }
}
