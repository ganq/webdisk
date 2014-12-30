package com.mysoft.b2b.netdisk.provider;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysoft.b2b.netdisk.api.NetDiskService;

@RunWith(SpringJUnit4ClassRunner.class)
public class NetDiskServiceTest extends BaseTestCase {
	
	private static final Logger logger = Logger.getLogger(NetDiskServiceTest.class);

	@Autowired
	private NetDiskService netdiskService;

	
	@Test
	public void testGetFile() {
		logger.info("================testSaveUser()=======================");
//	
//		try {
//			byte [] bs = netdiskService.getFileByte("1");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	@Test
	public void testSaveFile() {
		logger.info("================testSaveUser()=======================");
	
		//netdiskService.u
		
	}
}
