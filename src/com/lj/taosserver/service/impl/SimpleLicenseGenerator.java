package com.lj.taosserver.service.impl;

import java.util.Date;
import java.util.UUID;

import com.lj.taosserver.model.data.LicenseModel;
import com.lj.taosserver.service.LicenseGenerator;

public class SimpleLicenseGenerator implements LicenseGenerator {

	@Override
	public LicenseModel generate() {
		LicenseModel licenseModel=new LicenseModel();
		licenseModel.setLicenseSerial(UUID.randomUUID().toString());
		licenseModel.setCreateTime(new Date());
		licenseModel.setEnable(true);
		licenseModel.setExpire(15);
		licenseModel.setLastTime(new Date());
		return licenseModel;
	}

}
