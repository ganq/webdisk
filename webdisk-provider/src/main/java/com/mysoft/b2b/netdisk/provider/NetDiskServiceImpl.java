package com.mysoft.b2b.netdisk.provider;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysoft.b2b.commons.exception.PlatformCheckedException;
import com.mysoft.b2b.imagesys.api.ImageOperCode;
import com.mysoft.b2b.imagesys.api.ImageService;
import com.mysoft.b2b.netdisk.api.NetDiskService;
import com.mysoft.b2b.netdisk.api.UploadFileInfo;
import com.mysoft.b2b.netdisk.mapper.UploadFileMapper;
import com.mysoft.b2b.netdisk.util.WpUtils;

/**
 * NetDiskServiceImpl接口的实现类,提供用户相关服务
 * 
 * @author lvzj
 * 
 */
public class NetDiskServiceImpl implements NetDiskService {

    private static final Logger logger = Logger.getLogger(NetDiskServiceImpl.class);

    //上传路径
    private String uploadPath;

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Autowired
    private ImageService imageService;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public UploadFileMapper getUploadFileMapper() {
        return uploadFileMapper;
    }

    public void setUploadFileMapper(UploadFileMapper uploadFileMapper) {
        this.uploadFileMapper = uploadFileMapper;
    }

    @Override
    public UploadFileInfo getUploadFileInfo(String fileId) {
        UploadFileInfo info = new UploadFileInfo();
        info.setFileId(fileId);
        info = uploadFileMapper.selectFile(info);
        return info;
    }

    @Override
    public UploadFileInfo createUploadFileInfo(UploadFileInfo uploadFile) {
        if (uploadFile == null || StringUtils.isEmpty(uploadFile.getFileId()) || StringUtils.isEmpty(uploadFile.getFilePath())) {
            return null;
        }
        logger.info("Service createUploadFileInfo start.....");
        logger.info(String.format("uploadfile fileName = %s ，use = %s，type = %s ", uploadFile.getFilePath(), uploadFile.getUsage(),
                uploadFile.getType()));
        //存数据库
        logger.info("controller service createFileUploadData save file=" + uploadFile);
        saveUploadFileInfo(uploadFile);
        logger.info("Service createUploadFileInfo end.....");
        return uploadFile;
    }

    @Override
    public String uploadFile(String fileName, byte[] file, String use, String type) {
        logger.info("Service uploadFile start.....");
        logger.info(String.format("uploadfile fileName = %s ，use = %s，type = %s ", fileName, use, type));
        //生成文件ID
        String oid = this.generateWpFileId();

        use = WpUtils.getDefaultUseIfNull(use);

        type = WpUtils.getWpFileType(type);
        //保存地址
        String path = WpUtils.getWpDirectory(uploadPath, type, use, oid);
        //保存文件名
        fileName = WpUtils.getWpFileName(fileName, oid);

        try {
            logger.info("Service uploadFile to save file:" + path);
            File targetFile = new File(path, fileName);
            FileUtils.writeByteArrayToFile(targetFile, file);
        } catch (IOException e) {
            logger.error("Service uploadFile error", e);
            return null;
        }

        //生成对像 存数据库
        UploadFileInfo uploadFile = new UploadFileInfo(oid, fileName, use, type);
        logger.info("controller service saveFile save file=" + uploadFile);
        saveUploadFileInfo(uploadFile);
        logger.info("Service uploadFile end.....");
        return oid;
    }

    @Override
    public String generateWpFileId() {
        return ObjectId.get().toString();
    }

    @Override
    public byte[] getFileByte(String fileId) throws IOException {
        logger.info("Service getFilePath  use type start.....fileId = " + fileId);
        UploadFileInfo info = new UploadFileInfo();
        info.setFileId(fileId);
        info = uploadFileMapper.selectFile(info);

        if (null == info || StringUtils.isEmpty(info.getFilePath())) {
            return null;
        }

        String path = WpUtils.getWpDirectory(uploadPath, info.getType(), info.getUsage(), info.getFileId());

        logger.info("Service getFilePath to read file:" + path);

        File targetFile = new File(path + info.getFilePath());
        if (!targetFile.exists()) {
            logger.info("controller download file  no exists.....path=" + path);
            return null;
        }
        return FileUtils.readFileToByteArray(targetFile);
    }

    private void saveUploadFileInfo(UploadFileInfo uploadFile) {
        uploadFile.setCreateTime(new Date());
        uploadFileMapper.insertFile(uploadFile);
    }

    @Override
    public String moveUploadFile(String userId, String fileId, String fileName, String targetFilePath) throws PlatformCheckedException {
        logger.info("Service moveUploadFile start.....fileId = " + fileId);
        UploadFileInfo info = this.getUploadFileInfo(fileId);
        if(info!=null){
            String path = WpUtils.getWpDirectory(uploadPath, info.getType(), info.getUsage(), info.getFileId());
            String srcFilePath = path + info.getFilePath();
            
            int result = imageService.moveFile(userId, fileName, srcFilePath, targetFilePath);
            
            if(result != ImageOperCode.SUCCESS){
                throw new PlatformCheckedException("0", "Move file failed");
            }
        }
        logger.info("Service moveUploadFile end.....");
        return fileId;
    }

    public static void main(String[] args) {
        System.out.println(String.format("uploadfile fileName = %s ，use = %s，type = %s ", "1", "2", "3"));
    }

}