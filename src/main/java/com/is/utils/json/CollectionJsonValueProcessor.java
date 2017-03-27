package com.is.utils.json;

import java.util.Collection;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class CollectionJsonValueProcessor implements JsonValueProcessor
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
            return "null";
        }
        if (value instanceof Collection)
        {
            Collection<?> collection = (Collection<?>) value;
            if (collection.isEmpty())
            {
                return "null";
            }
            else
            {
                return value;
            }
        }
        else
        {
            return value;
        }
    }

}
