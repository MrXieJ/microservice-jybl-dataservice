package com.neo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.entity.DoctorEntity;
import com.neo.entity.ServiceEntity;

public interface DoctorMapper {
	// 根据电话和密码查询账号是否存在
	@Select("select * from doctor_info where phone = #{0} and password = #{1}")
	DoctorEntity selectByPhoneAndPassword(String phone, String password);

	// 存入医生二维码
	@Update("update dactor_info set QRcode_pic = #{QRcode_pic}")
	void updateQRcode(String QRcode_pic);

	// 根据电话返回所有数据
	@Select("select * from doctor_info where phone = #{phone} ")
	DoctorEntity selectByPhone(String phone);

	@Insert("insert into doctor_info (phone,password) values (#{0},#{1})")
	void insertDoctor(String phone, String password);

	// 返回所有医生数据
	@Select("select * from doctor_info ")
	List<DoctorEntity> findAllDoctor();

	// 返回所有的服务包
	@Select("select * from service")
	List<ServiceEntity> findAllService();
	//存头像
	@Select("update doctor_info set head_pic = #{head_pic} where phone = #{phone}")
	void updateHead(@Param("head_pic")String head_pic, @Param("phone")String phone);

	
	
	
	
	// 查找token
	@Select("select token from token where token = #{token}")
	String selectToken(String token);

	// 删除token
	@Delete("delete from token where token = #{token}")
	void deleteToken(String token);
	//根据手机号删除token
	@Delete("delete from token where phone = #{phone}")
	void deletePhonetoken(String phone);
	// 插入phone和token
	@Select("insert into token (phone,token) values (#{phone},#{token})")
	String savePhonetoken(@Param("phone") String phone, @Param("token") String token);

	// 根据phone查找token
	@Select("select token from token where phone = #{phone}")
	String selectPhonetoken(String phone);
	//根据电话更新token
	@Select("update token set token = #{token} where phone =#{phone}")
	String updateToken(@Param("token")String token, @Param("phone")String phone);
}
