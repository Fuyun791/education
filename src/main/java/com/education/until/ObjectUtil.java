package com.education.until;

import java.util.Map;
import java.util.Set;

/**
 * @author dell
 */
public class ObjectUtil {

    public static String mapToString(Map<String, String[]> paramMap){
        if (paramMap == null) {
            return "";
        }
        int i = 0;
        StringBuilder string = new StringBuilder();
        Set<Map.Entry<String,String[]>> mapSet = paramMap.entrySet();
        int size = mapSet.size();
        for (Map.Entry<String,String[]> entry : mapSet) {
            string.append(entry.getKey());
            string.append(": ");
            string.append(entry.getValue()[0]);
            if (i != size - 1) {
                string.append(",");
            }
            i++;
        }
        return string.toString();
    }

}
