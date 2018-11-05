package cn.fts.po;

import java.util.Date;

public class ActionHelper {

    /**
     *
     * 状态码说明
     *
     * 共五位
     *
     * 第一位：
     * 增：1
     * 删：2
     * 预览：3
     * 下载：4
     *
     * 第二位：
     * 用户：1
     * 系统：2
     *
     * 第三位：
     * 快速文本：1
     * 正常文件：2
     *
     * 第四位：
     * 单条：1
     * 批量：2
     *
     * 第五位：
     * 成功：1
     * 失败：2
     *
     * 用不着或者没有则用0补齐
     */

    /**
     * 操作种类：
     * 11101-增加 用户 快速文本 成功
     */
    public static final String ADD_USER_FTEXT_SUCCESS = "11101";

    public static Action addUserFTextSuccess(String fileid,String desc,String by) {
        return new Action(fileid,"【用户】-【新增快速文本】-【成功】",desc,by,ADD_USER_FTEXT_SUCCESS,new Date());
    }

    /**
     * 操作种类：
     * 11102-增加 用户 快速文本 失败
     */
    public static final String ADD_USER_FTEXT_FAILED = "11102";

    public static Action addUserFTextFailed(String fileid,String desc,String by) {
        return new Action(fileid,"【用户】-【新增快速文本】-【失败】",desc,by,ADD_USER_FTEXT_FAILED,new Date());
    }

    /**
     * 操作种类：
     * 11201-增加 用户 正常文件 成功
     */
    public static final String ADD_USER_UPLOAD_SUCCESS = "11201";

    public static Action addUserUploadSuccess(String fileid,String desc,String by) {
        return new Action(fileid,"【用户】-【新增正常文件】-【成功】",desc,by,ADD_USER_UPLOAD_SUCCESS,new Date());
    }

    /**
     * 操作种类：
     * 11202-增加 用户 正常文件 失败
     */
    public static final String ADD_USER_UPLOAD_FAILED = "11202";

    public static Action addUserUploadFailed(String fileid,String desc,String by) {
        return new Action(fileid,"【用户】-【新增正常文件】-【失败】",desc,by,ADD_USER_UPLOAD_FAILED,new Date());
    }

    /**
     * 操作种类：
     * 21011-删除 用户 单条 成功
     */
    public static final String DELETION_USER_SUCCESS = "21011";

    public static Action deleteUserSuccess(String fileid,String desc,String by) {
        return new Action(fileid,"【用户】-【删除单条】-【成功】",desc,by,DELETION_USER_SUCCESS,new Date());
    }

    /**
     * 操作种类：
     * 21012-删除 用户 单条 失败
     */
    public static final String DELETION_USER_FAILED = "21012";

    public static Action deleteUserFailed(String fileid,String desc,String by) {
        return new Action(fileid,"【用户】-【删除单条】-【失败】",desc,by,DELETION_USER_FAILED,new Date());
    }

    /**
     * 操作种类：
     * 22021-删除 系统 批量 成功
     */
    public static final String DELETION_SYS_MULTI_SUCCESS = "22021";

    public static Action deleteSysMultiSuccess(String desc) {
        return new Action("000000","【系统】-【删除批量】-【成功】",desc,"系统",DELETION_SYS_MULTI_SUCCESS,new Date());
    }

    /**
     * 操作种类：
     * 22022-删除 系统 批量 失败
     */
    public static final String DELETION_SYS_MULTI_FAILED = "22022";

    public static Action deleteSysMultiFailed(String desc) {
        return new Action("000000","【系统】-【删除批量】-【失败】",desc,"系统",DELETION_SYS_MULTI_FAILED,new Date());
    }

    /**
     * 操作种类：
     * 31001-预览 用户 成功
     */
    public static final String PREVIEW_USER_SUCCESS = "31001";

    public static Action previewUserSuccess(String fileid,String desc,String by) {
        return new Action(fileid,"【用户】-【预览】-【成功】",desc,by,PREVIEW_USER_SUCCESS,new Date());
    }

    /**
     * 操作种类：
     * 31002-预览 用户 失败
     * */
    public static final String PREVIEW_USER_FAILED = "31002";

    public static Action previewUserFailed(String fileid,String desc,String by) {
        return new Action(fileid,"【用户】-【预览】-【失败】",desc,by,PREVIEW_USER_FAILED,new Date());
    }

    /**
     * 操作种类：
     * 41001-下载 用户 成功
     * */
    public static final String DOWNLOAD_USER_SUCCESS = "41001";

    public static Action downloadUserSuccess(String fileid,String desc,String by) {
        return new Action(fileid,"【用户】-【下载】-【成功】",desc,by,DOWNLOAD_USER_SUCCESS,new Date());
    }

    /**
     * 操作种类：
     * 41002-下载 用户 失败
     * */
    public static final String DOWNLOAD_USER_FAILED = "41002";

    public static Action downloadUserFailed(String fileid,String desc,String by) {
        return new Action(fileid,"【用户】-【下载】-【失败】",desc,by,DOWNLOAD_USER_FAILED,new Date());
    }

}