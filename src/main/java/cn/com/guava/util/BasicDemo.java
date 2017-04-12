package cn.com.guava.util;

import com.google.common.base.Joiner;

import java.util.List;

/**
 * Created by zhangdi on 2017/04/12.
 */
public class BasicDemo {

    /**
     * 将列表转化为String
     * @param listContent
     * @param separator
     * @return
     */
    public String mkListToString(List<String> listContent, String separator) {
        return Joiner.on(separator).skipNulls().join(listContent);
    }

    /**
     * 将数组转化为String
     * @param listContent
     * @param separator
     * @return
     */
    public String mkString(String[] listContent, String separator) {
        return Joiner.on(separator).skipNulls().join(listContent);
    }
}
