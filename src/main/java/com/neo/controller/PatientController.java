package com.neo.controller;

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

import com.neo.entity.PatientEntity;
import com.neo.mapper.PatientMapper;

@CacheConfig(cacheNames = "patientcache")
@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private PatientMapper pm;
	
	//存入患者信息
	@CachePut(key="#patient.getWechat_id()")
	@RequestMapping(value="/savedetail",method=RequestMethod.POST)
	public PatientEntity savePatient(@RequestBody PatientEntity patient ){
		pm.savePatient(patient);
		return patient;
	}
	
	//根据id返回患者信息
    @Cacheable(key="#wechat_id")
	//@CacheEvict(allEntries=true, beforeInvocation=true)
	@RequestMapping("/findbywechatid")
	public PatientEntity selectById(@RequestBody String wechat_id ){
		return pm.selectById(wechat_id);
	}
	
	//更改信息
	@CachePut(key="#patient.getWechat_id()")
	@RequestMapping(value="/updatepatient", method=RequestMethod.POST)
	public PatientEntity update(@RequestBody PatientEntity patient){
		//PatientEntity p=new PatientEntity("kkw","www","man",17,"18888888888","halg","c12","231","no");
		pm.update(patient);
		return patient;
	}
	
	//患者关注医生
	@RequestMapping(value="/watchdoctor", method=RequestMethod.POST)
	public String watchDoctor(@RequestParam String wechat_id, @RequestParam String phone){
		pm.updateAttention(wechat_id, phone); 
		return "success";
	}
	
	//根据患者ID返回他关注的医生的ID 
	@Cacheable(key="'findmydoctor'+#wechat_id")
	@RequestMapping("/findmydoctor")
	public String findmydoctor(@RequestBody String wechat_id){
		return pm.selectDoctorByPatient(wechat_id);
	}
}
