package com.mysoft.b2b.netdisk.util;

import java.io.File;

/**
 * 
 * chengp:    网盘帮助类
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * chengp    1.0           2014年9月9日     Created
 * </pre>
 * @since b2b 2.0.0
 */
public abstract class WpUtils {

    public static final String FILE_SEPARATOR = File.separator;

    /**
     * 获取文件保存路径
     * @param parentPath
     * @param fileType
     * @param use
     * @param fileId
     * @return
     */
    public static String getWpDirectory(String parentPath, String fileType, String use, String fileId) {
        String result = parentPath;

        if (null != parentPath && !(parentPath.endsWith("\\") || parentPath.endsWith("/"))) {
            result = result + File.separator;
        }

        String filePath = fileType + FILE_SEPARATOR + use + FILE_SEPARATOR + getFileSavePath(fileId);
        return result + filePath;
    }

    /**
     * 重置文件名
     * @param fileName
     * @param fileId
     * @return
     */
    public static String getWpFileName(String fileName, String fileId) {
        //上传的文件名
        //后缀
        /*String[] fixs = fileName.split("\\.");
        String fix = fixs.length>=2?fixs[fixs.length-1]:null;*/
        return fileId + ".jpg";
    }

    /**
     * 获取文件类型
     * @param fileType
     * @return
     */
    public static String getWpFileType(String type) {
        if (type != null && !type.equals("")) {
            if ("img".equalsIgnoreCase(type)) {
                return "img";
            }
        }
        return "other";
    }

    /**
     * 当文件用途为空时，默认值
     * @param use
     * @return
     */
    public static String getDefaultUseIfNull(String use) {
        if (use == null || use.trim().equals("")) {
            // 用途 不写， 默认文件名
            return "default";
        }
        return use;
    }

    /**
     * 根据OID生成文件路径 生成四级目录每级5个字符组成
     * @param oid
     * @return
     */
    private static String getFileSavePath(String oid) {
        if (oid.length() <= 20) {
            return oid;
        }
        StringBuilder sb = new StringBuilder();
        // 第一级
        sb.append(oid.substring(0, 5));
        sb.append(FILE_SEPARATOR);
        // 第二级
        sb.append(oid.substring(5, 10));
        sb.append(FILE_SEPARATOR);
        // 第三级
        sb.append(oid.substring(10, 15));
        sb.append(FILE_SEPARATOR);
        // 第四级
        sb.append(oid.substring(15, 20));
        sb.append(FILE_SEPARATOR);
        return sb.toString();
    }

}
