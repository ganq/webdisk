package com.mysoft.b2b.netdisk.api;

import java.io.Serializable;
import java.util.Date;

/**
 * 上传文件
 * @author lvzj
 */
@SuppressWarnings("serial")
public class UploadFileInfo implements Serializable {
    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 用途
     */
    private String usage;

    /**
     * 文件类型 
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UploadFileInfo() {
        super();
    }

    public UploadFileInfo(String fileId, String filePath, Date createTime) {
        super();
        this.fileId = fileId;
        this.filePath = filePath;
        this.createTime = createTime;
    }

    public UploadFileInfo(String fileId, String filePath, String usage, String type) {
        super();
        this.fileId = fileId;
        this.filePath = filePath;
        this.usage = usage;
        this.type = type;
    }

    @Override
    public String toString() {
        return "UploadFileInfo [fileId=" + fileId + ", filePath=" + filePath + ", use=" + usage + ", type=" + type + ", createTime="
                + createTime + "]";
    }

}
