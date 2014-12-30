package com.mysoft.b2b.netdisk.mapper;

import com.mysoft.b2b.netdisk.api.UploadFileInfo;


/**
 * 上传文件MAPPER
 * @author lvzj
 *
 */
public interface UploadFileMapper {

	/**
	 * 保存文件
	 * @param file
	 */
	public void insertFile(UploadFileInfo file);
	
	/**
	 * 查询文件
	 * @param file
	 * @return
	 */
	public UploadFileInfo selectFile(UploadFileInfo file);
}
