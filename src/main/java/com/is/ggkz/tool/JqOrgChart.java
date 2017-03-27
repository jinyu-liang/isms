package com.is.ggkz.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.is.ggkz.entity.GgkzDepartInfo;

public class JqOrgChart
{
    public static String ORGKEY[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+", "{",
            "}", ":", "\"", "|", "<", ">", "?", "`", "[", "]", ";", "'", "\\", ",", ".", "/"};

    private JqOrgChart()
    {

    }

    public static String collection2jOrgChartHtml(Collection<GgkzDepartInfo> collection)
    {
        Map<Integer, List<GgkzDepartInfo>> orgMap = new HashMap<Integer, List<GgkzDepartInfo>>();
        for (GgkzDepartInfo item : collection)
        {
            if ("0".equals(item.getDepartId()))
            {
                List<GgkzDepartInfo> org = new ArrayList<GgkzDepartInfo>();
                org.add(item);
                orgMap.put(1, org);
            }
            else
            {
                String depId = item.getDepartId();
                int idLen = depId.length();
                List<GgkzDepartInfo> org = null;
                if (orgMap.containsKey(idLen))
                {
                    org = orgMap.get(idLen);
                }
                else
                {
                    org = new ArrayList<GgkzDepartInfo>();
                    orgMap.put(idLen, org);
                }
                if (org != null)
                {
                    org.add(item);
                }
            }
        }
        if (!orgMap.isEmpty())
        {
            TreeSet<Integer> keySet = new TreeSet<Integer>();
            keySet.addAll(orgMap.keySet());
            Iterator<Integer> iterator = keySet.iterator();
            StringBuilder sb = new StringBuilder();
            sb.append("<ul id='org' >");
            while (iterator.hasNext())
            {
                Integer key = iterator.next();
                List<GgkzDepartInfo> listItem = orgMap.get(key);
                recursion(sb, listItem, orgMap, key);
            }
            sb.append("</ul>");
            return sb.toString();
        }
        return "";
    }

    private static void recursion(StringBuilder sb, Collection<GgkzDepartInfo> collection, Map<Integer, List<GgkzDepartInfo>> map, Integer key)
    {
        for (GgkzDepartInfo item : collection)
        {
            sb.append("<li id='").append(item.getDepartId()).append("'>").append(item.getDepartName());
            if (map.containsKey(key + 1))
            {
                List<GgkzDepartInfo> listItem = map.get(key + 1);
                List<GgkzDepartInfo> listItemChild = new ArrayList<GgkzDepartInfo>();
                String pdepId = item.getDepartId();
                for (int i = 0; i < listItem.size();)
                {
                    GgkzDepartInfo eachitem = listItem.get(i);
                    String depId = eachitem.getDepartId();
                    if (depId.startsWith(pdepId))
                    {
                        listItemChild.add(eachitem);
                        listItem.remove(i);
                        continue;
                    }
                    i++;
                }
                if (!listItemChild.isEmpty())
                {
                    sb.append("<ul>");
                    recursion(sb, listItemChild, map, key + 1);
                    sb.append("</ul>");
                }
            }
            sb.append("</li>");
        }
    }
}
