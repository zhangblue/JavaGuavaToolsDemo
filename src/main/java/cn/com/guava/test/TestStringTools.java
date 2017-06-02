package cn.com.guava.test;

import cn.com.guava.util.StringTools;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * Created by zhangdi on 2017/06/02.
 */
public class TestStringTools {

    public static void main(String[] args) {

        StringTools stringTools = new StringTools();


        String str = "a\tA\tc\td\t";
        String str1 = "11111n,为2111";
        String str2 = "11111213";

        //System.out.println(str.split("\t").length);
        //
        //List<String> list  = stringTools.splitString(str,"\t",false);
        //System.out.println(list.size());
        //for(String c:list){
        //    System.out.println(c.length());
        //}

        //System.out.println(Character.isISOControl('\t'));

        //System.out.println(CharMatcher.javaLetterOrDigit().);


        System.out.println(Joiner.on("-").join(Splitter.fixedLength(2).split(str1)));





    }
}
