package com.base.mybatis;

/**
 * 
 * @ClassName: BaseModel
 * @Description: Model基类，所有的Model类均需要继承此类
 * @author
 * @date 2010-11-8 下午05:13:30
 * 
 */
public abstract class AbstractBaseEntity extends AbstractBaseQueryEntity
{

    private static final long  serialVersionUID = 1L;

    public static final String LINE_SEPARATOR   = System.getProperty("line.separator");

    private boolean            distinct         = true;

    public boolean isDistinct()
    {
        return distinct;
    }

    public void setDistinct(boolean distinct)
    {
        this.distinct = distinct;
    }

}
