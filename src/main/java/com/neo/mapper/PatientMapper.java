package com.neo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.neo.entity.PatientEntity;
import com.neo.mapper.provider.PatientProvider;


public interface PatientMapper {
	// 插入一条患者信息
	@Insert("insert into patient_info (wechat_id,name,id_card,sex,age,phone,address,detailed_address,head_pic)values(#{wechat_id},#{name},#{id_card},#{sex},#{age},#{phone},#{address},#{detailed_address},#{head_pic})")
	void savePatient(PatientEntity patient);
	
	//根据id返回患者信息
	@Select("select * from patient_info where wechat_id = #{wechat_id}")
	@Results({
		@Result(id=true,column="wechat_id",property="wechat_id"),
		@Result(column="name",property="name"),
		@Result(column="id_card",property="id_card"),
		@Result(column="sex",property="sex"),
		@Result(column="age",property="age"),
		@Result(column="phone",property="phone"),
		@Result(column="address",property="address"),
		@Result(column="detailed_address",property="detailed_address"),
		@Result(column="head_pic",property="head_pic")
	})
	PatientEntity selectById(@Param("wechat_id")String wechat_id);
	
	//修改患者信息
	@SelectProvider(type=PatientProvider.class, method="update")
	void update(PatientEntity patient);
	
	//患者关注医生
	@Insert("insert into attention (wechat_id,phone) values (#{wechat_id},#{phone})")
	void updateAttention(@Param("wechat_id")String wechat_id,@Param("phone") String phone);
	
	//根据患者ID返回他关注的医生的ID
	@Select("select phone from attention where wechat_id = #{wechat_id}")
	@Results({
		@Result(column="phone", property="phone")
	})
	String selectDoctorByPatient(String wechat_id);
	
	
}