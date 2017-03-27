package com.is.utils;

/**
 * 公共字典类，存放业务常量或者字典常量
 *
 * @author life
 *
 */
public class PublicDict
{
    /** session中的user对象 */
    public static final String SESSION_USER            = "session_user";

    /** 用户表主键前缀 */
    public static final String USER_PREFIX             = "USER";

    /** 日志表主键前缀 */
    public static final String SYS_OPER_LOG_PREFIX     = "LOG";

    /**角色表主键*/
    public static final String ROLE_INFO               = "ROLE";

    /**附件信息表主键*/
    public static final String ATTACH                  = "ACH";

    /**附件信息与用户关联表主键*/
    public static final String ATTACHAUTH              = "ATH";

    /**公共控制*/
    public static final String MODEL_GGKZ              = "GGKZ";

    /**系统*/
    public static final String MODEL_SYS               = "SYS";

    /** 日志操作方法 */
    public static final String OPER_LOG_EVENT_ADD      = "1";           // 添加

    public static final String OPER_LOG_EVENT_DEL      = "2";           // 删除

    public static final String OPER_LOG_EVENT_SELECT   = "3";           // 查询

    public static final String OPER_LOG_EVENT_LOGIN    = "7";           //登录

    public static final String OPER_LOG_EVENT_UPDATE   = "4";           // 修改

    public static final String OPER_LOG_EVENT_IN       = "5";           // 导入

    public static final String OPER_LOG_EVENT_OUT      = "6";           // 导出

    public static final String OPER_LOG_RESULT_SUCCESS = "1";           // 操作成功

    public static final String OPER_LOG_RESULT_ERROR   = "0";           // 操作失败

    /********工程现场管理系统添加************/

    /**发料计划货物主键*/
    public static final String DELIVERY_ITEM           = "DEIM";

    /**缩略图显示倍数*/
    public static final float  ZOOM                    = 2;

    /**Excel缩略图片存放地址*/
    public static final String JPGPATH                 = "upload/";

    /**图片名称生成*/
    public static final String IMAGE                   = "IMAGE";

    /**信息回复表主键*/
    public static final String DRECORD_REPLY           = "REPLY";

    /**采购主键*/
    public static final String DReport_id              = "purch";

    /**剩料主键*/
    public static final String Trans_id                = "trans";

    /**剩料明细主键*/
    public static final String Item_id                 = "item";

    /**出门单主键*/
    public static final String DINVOICE_ID             = "INVID";

    /**出门单产品主键*/
    public static final String DINVOICE_ITEM_ID        = "IVIID";

    /**用户图片*/
    public static final String USERPHOTO               = "photo";

    /**消息互动主键*/
    public static final String MsgSend_id              = "MSGSEND";

    public static final String MsgRceive_id            = "MSGRECV";

    public static final String MsgDraft_id             = "MSGDRFT";

    /**外线工程表主键**/
    public static final String D_EXPROJECT             = "EXPRT";

    /**外线工程进度表主键**/
    public static final String D_EXPROGRESS            = "GRES";

    /**报告收件人映射表**/
    public static final String D_RECORD_USER_MAPPING   = "UMPP";

    public static final String project_Id              = "proj";

    /**模块编号**/
    /**发料模块**/
    public static final String MODEL_SENDMETRAL        = "FLGL";

    /**进度模块**/
    public static final String MODEL_EXPROGRESS        = "JDGL";

    /**采购模块**/
    public static final String MODEL_REPORT            = "CGGL";

    /**剩料模块**/
    public static final String MODEL_SCRAPTRANS        = "SLGL";

    /**工地转移*/
    public static final String MODEL_GDZY              = "GDZY";

    /**外线人员模块**/
    public static final String MODEL_PERSON            = "WXGL";

    /**工地模块**/
    public static final String MODEL_WORKSHOP          = "GDGL";

    /**用户模块**/
    public static final String MODEL_USER              = "YHGL";

    /**角色模块**/
    public static final String MODEL_ROLE              = "JSGL";

    /**系统管理模块**/
    public static final String MODEL_SYSTEM            = "XTGL";

    /**系统管理模块**/
    public static final String M_TEAM                  = "team";
    
    /**质量管理模块**/
    public static final String MODEL_WORKSHOP_QUALITY  = "QLTY";
    
    /**工作日志**/
    public static final String M_WORKSHOP_JOBLOG       = "wkrt";
    
    /**工作联系单**/
    public static final String M_WORKSHOP_CONTACT       = "wklxd";
}
