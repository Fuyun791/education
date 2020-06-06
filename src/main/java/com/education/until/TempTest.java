package com.education.until;

import com.education.entity.OnlineCourseDiscuss;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dell
 */
public class TempTest {

    private  static final Set<Long> ONLINE_COURSE_DISCUSS_SET = new HashSet<Long>(Arrays.asList(1L,2L,3L,4L,5L));


    private static final HashMap<Integer,String> ONLINE_DISCUSS_PERSON = new HashMap<>();

    static {
        ONLINE_DISCUSS_PERSON.put(101,"蔡");
        ONLINE_DISCUSS_PERSON.put(102,"浩");
        ONLINE_DISCUSS_PERSON.put(1,"咖啡");
        ONLINE_DISCUSS_PERSON.put(2,"测试1号");
    }

    /**
     * 模拟mysql外键是否存在的规则
     * @param onlineCourseDiscuss
     * @return
     */
    public static int isExist(OnlineCourseDiscuss onlineCourseDiscuss) {
        int result = 1;
        if (!ONLINE_COURSE_DISCUSS_SET.contains(onlineCourseDiscuss.getOnlineCourseId())){
            result = 0;
        }
        if (!ONLINE_DISCUSS_PERSON.containsKey(onlineCourseDiscuss.getDiscussPerson())) {
            result = 0;
        }
        if (onlineCourseDiscuss.getDiscussToPerson() != null && !ONLINE_DISCUSS_PERSON.containsKey(onlineCourseDiscuss.getDiscussToPerson())) {
            result = 0;
        }
        return result;
    }

    public static int isExist(Integer id,String name) {
        int result = 0;
        if (name.equals(ONLINE_DISCUSS_PERSON.get(id))) {
            result = 1;
        }
        return result;
    }

}
