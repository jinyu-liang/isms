package com.base.mybatis;

public class BaseCondition<Entity>
{
    private Entity record;

    private Entity conditon;

    public Entity getRecord()
    {
        return record;
    }

    public void setRecord(Entity record)
    {
        this.record = record;
    }

    public Entity getConditon()
    {
        return conditon;
    }

    public void setConditon(Entity conditon)
    {
        this.conditon = conditon;
    }

}
