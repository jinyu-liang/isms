package com.is.pretrst.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.pretrst.dao.DRecordReplyDaoImpl;
import com.is.pretrst.entity.DRecordReply;
import com.is.pretrst.entity.query.DRecordReplyQuery;
import com.is.utils.PublicDict;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 *
 * @ClassName: DRecordReplyServiceImpl
 * @Description: DRecordReply表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:35 *
 */
@Component
@Transactional 
public class DRecordReplyServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DRecordReply.class);
	private DRecordReplyDaoImpl drecordReplyDaoImpl;
    @Autowired
    public void setDrecordReplyDaoImpl(DRecordReplyDaoImpl drecordReplyDaoImpl)
    {
        this.drecordReplyDaoImpl = drecordReplyDaoImpl;
    }
    /**
     * 分页查询回复列表页
     * @param queryEntity
     * @return Page
     */
    public Page pageQuery(DRecordReplyQuery queryEntity){
        return drecordReplyDaoImpl.pageQuery("DRecordReply.selectByPage", queryEntity);
    }
    
    /**
     * 查找一个对象 
     * @param entity
     * @return DRecordReply
     */
    public  DRecordReply  selectOneByEntity(DRecordReply entity){
        return drecordReplyDaoImpl.selectOneByEntity(entity);
    }
    /**
     * 更新对象
     * @param entity
     * @return int
     */
    public int updateByEntity(DRecordReply reply){
        //新建一个查询对象
        DRecordReply  entity = new DRecordReply();
        entity.setReplyId(reply.getReplyId());
        entity.setRecordId(reply.getRecordId());
        entity.setReplyUserCd(reply.getReplyUserCd());
        
        List<DRecordReply> drecordReplyList = drecordReplyDaoImpl.selectByEntity(entity);
        if(drecordReplyList!=null&&drecordReplyList.size()>0){
            reply.setUpdateTm(new Date());
            int i =  drecordReplyDaoImpl.updateByEntity(reply);//已存在当前用户的回复，更新
            if(i>0){
            // 添加日志
            OperLogUtil.insertOperLog(reply.getReplyId(), PublicDict.MODEL_SENDMETRAL,
                    "发料管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新出门单回复", "更新成功",
                    "更新出门单回复", "d_record_reply");}
            return i;
        }else{
            /*UserDetail user = SpringSecurityUtils.getCurrentUser();
            if(user!=null&&!StringUtils.isEmpty(user.getUserId())){
                entity.setReplyUserCd(user.getUserId());
            }*/
            reply.setCreateTm(new Date());//还没有回复过，添加
            reply.setUpdateTm(new Date());
            reply.setReplyId(KeyGen.getCommonKeyGen(PublicDict.DRECORD_REPLY));
            reply.setRecordType("0");//0为发料信息回复
            reply.setDeleteCd("0");//删除区分
            reply.setExtraFlg("0");//回复额外区分 重要，星标等-备用
            reply.setRootReplyId("0");//根回复项目ID-备用
            reply.setParentReplyId("0");//父回复项目ID-备用
            
            int i = drecordReplyDaoImpl.insert(reply);
            if(i>0){
             // 添加日志
                OperLogUtil.insertOperLog(entity.getReplyId(), PublicDict.MODEL_SENDMETRAL,
                        "发料管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加出门单回复", "添加成功",
                        "添加出门单回复", "d_record_reply");
            }
            return i;
        }
        
    }
}
