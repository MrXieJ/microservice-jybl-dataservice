package com.neo.controller;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neo.entity.DoctorEntity;
import com.neo.entity.ServiceEntity;
import com.neo.mapper.DoctorMapper;

@CacheConfig(cacheNames = "doctorcache")
@RestController
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private DoctorMapper dm;

	// 根据电话和密码查询账号是否存在
	@RequestMapping("/loginverify")
	Boolean loginVerify(@RequestParam String phone, @RequestParam String password) {
		DoctorEntity doctor = dm.selectByPhoneAndPassword(phone, password);
		if (doctor != null) {
			return true;
		} else {
			return false;
		}
	}

	// 根据电话返回医生的信息
	// @Cacheable(key="#phone")
	@RequestMapping("/findbyphone")
	DoctorEntity selectByPhone(@RequestParam String phone) {
		DoctorEntity doctor = dm.selectByPhone(phone);
		return doctor;
	}

	// 插入医生信息
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	String register(@RequestParam String phone, @RequestParam String password) {
		dm.insertDoctor(phone, password);
		return "success";
	}

	// 返回所有医生信息
	@RequestMapping("/findall")
	List<DoctorEntity> findAllDoctor() {
		return dm.findAllDoctor();
	}

	// 返回所有服务包
	@RequestMapping("/service")
	List<ServiceEntity> findAllService() {
		return dm.findAllService();
	}
	//存头像
	@RequestMapping(value="/updatehead",method=RequestMethod.POST)
	void updateHead(@RequestParam String head_pic,@RequestParam String phone){
		dm.updateHead(head_pic, phone);
	}
	
	
	
	// 插入手机和token
	@CachePut(value = "token", key = "#token")
	@RequestMapping(value = "/savephonetoken")
	String savePhonetoken(@RequestParam("phone") String phone, @RequestParam("token") String token) {
		dm.savePhonetoken(phone, token);
		return token;
	}

	// 查找token
	@Cacheable(value = "token", key = "#token")
	@RequestMapping(value = "/selecttoken")
	String selectToken(@RequestParam String token) {
		return dm.selectToken(token);
	}

	// 根据手机查找token
	@Cacheable(value = "token", key = "#token")
	@RequestMapping(value = "/selectphonetoken")
	String selectPhonetoken(@RequestParam("phone") String phone) {
		String token = dm.selectPhonetoken(phone);
		return token;
	}

	// 删除token
	@CacheEvict(value = "token", key = "#token")
	@RequestMapping(value = "/deletetoken")
	void deleteToken(@RequestParam String token) {
		dm.deleteToken(token);
	}
	//根据手机号删除token
	@CacheEvict(value = "token", key = "#token")
	@RequestMapping(value = "/deletephonetoken")
	void deletephonetoken(@RequestParam String phone) {
		dm.deletePhonetoken(phone);
	}
	//根据电话更新token
	@CachePut(value = "token", key = "#token")
	@RequestMapping(value = "/updatetoken")
	String updateToken(@RequestParam String token,@RequestParam String phone){
		dm.updateToken(token, phone);
		return token;
	}
}
