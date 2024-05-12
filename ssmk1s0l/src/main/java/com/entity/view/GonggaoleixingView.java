package com.entity.view;

import com.entity.GonggaoleixingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 公告类型
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2023-05-25 09:46:02
 */
@TableName("gonggaoleixing")
public class GonggaoleixingView  extends GonggaoleixingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public GonggaoleixingView(){
	}
 
 	public GonggaoleixingView(GonggaoleixingEntity gonggaoleixingEntity){
 	try {
			BeanUtils.copyProperties(this, gonggaoleixingEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
