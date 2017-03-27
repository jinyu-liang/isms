package com.is.utils.json;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor
{

    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig)
    {
        return process(value, jsonConfig);
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig)
    {
        return process(value, jsonConfig);
    }

    private Object process(Object value, JsonConfig jsonConfig)
    {
        if (value == null)
        {
            return "";
        }
        if (value instanceof Date)
        {
            SimpleDateFormat sdf = null;
            Date date = (Date) value;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (calendar.get(Calendar.SECOND) == 0 && calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.HOUR) == 0)
            {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            }
            else
            {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            return sdf.format(date);
        }
        else
        {
            return value;
        }
    }

}
