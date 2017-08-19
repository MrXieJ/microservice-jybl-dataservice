package com.neo.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

import com.neo.entity.PatientEntity;

public class PatientProvider {
	public String update(PatientEntity patient){
		return new SQL(){
			{
				UPDATE("patient_info");
				if(patient.getName()!=null){
					SET("name = #{name}");
				}
				if(patient.getId_card()!=null){
					SET("id_card = #{id_card}");
				}
				if(patient.getSex()!=null){
					SET("sex = #{sex}");
				}
				if(patient.getAge()!=0){
					SET("age = #{age}");
				}
				if(patient.getPhone()!=null){
					SET("phone = #{phone}");
				}
				if(patient.getAddress()!=null){
					SET("address = #{address}");
				}
				if(patient.getDetailed_address()!=null){
					SET("detailed_address = #{detailed_address}");
				}
				if(patient.getHead_pic()!=null){
					SET("head_pic = #{head_pic}");
				}
				WHERE("wechat_id = #{wechat_id}");
			}
		}.toString();
	}
}
