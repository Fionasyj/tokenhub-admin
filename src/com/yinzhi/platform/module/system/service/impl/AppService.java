package com.yinzhi.platform.module.system.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.module.system.dao.AppDao;
import com.yinzhi.platform.module.system.service.IAppService;



@Service
public class AppService implements IAppService{
	
	@Autowired
	private AppDao AppDao;


}
