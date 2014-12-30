package com.mysoft.b2b.netdisk.api;

import java.io.IOException;

import com.mysoft.b2b.commons.exception.PlatformCheckedException;

/**
 * 网盘服务接口,提供用户相关服务
 * @author subh
 */
public interface NetDiskService {
    /**
     * 返回码  大小错误 
     */
    public static final String RESULT_ERROR_SIZE = "101";

    /**
     * 返回码  后缀格式错误 
     */
    public static final String RESULT_ERROR_SUFFIX = "102";

    /**
     * 返回码 保存错误 
     */
    public static final String RESULT_ERROR_SAVE = "103";

    /**
     * 生成网盘文件编号
     * @return
     */
    public String generateWpFileId();

    /**
     * 上传文件(文件未存放好) 得到文件ID
     * @param fileName 文件名
     * @param file 
     * @param use   用途
     * @param type   img / other
     * @return String  文 件ID
     */
    public String uploadFile(String fileName, byte[] file, String use, String type);

    /**
     * 移动文件
     * @param userId
     * @param fileId
     * @param fileName
     * @param targetFilePath
     * @return
     */
    public String moveUploadFile(String userId, String fileId, 
            String fileName, String targetFilePath) throws PlatformCheckedException;

    /**
     * 保存上传文件记录(文件已经存放好)
     * @param UploadFileInfo
     */
    public UploadFileInfo createUploadFileInfo(UploadFileInfo info);

    /**
     * 查询上传文件记录
     * @param UploadFileInfo
     */
    public UploadFileInfo getUploadFileInfo(String fileId);

    /**
     * 得到文件 字节
     * @param fileId
     * @return
     * @throws IOException 
     */
    public byte[] getFileByte(String fileId) throws IOException;

}