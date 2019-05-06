package com.pier;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther zhongweiwu
 * @date 2018/9/4 15:57
 */
public class Test extends B{

    public void a(Map map){
        System.out.println("main"+map.size());
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();
        B test = new Test();
        test.a(map);
    }

}
class B{
    public void a(HashMap map){
        System.out.println("B"+map.size());
    }
}
