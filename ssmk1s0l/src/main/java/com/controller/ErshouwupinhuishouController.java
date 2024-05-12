package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ErshouwupinhuishouEntity;
import com.entity.view.ErshouwupinhuishouView;

import com.service.ErshouwupinhuishouService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;

/**
 * 二手物品回收
 * 后端接口
 * @author 
 * @email 
 * @date 2023-05-25 09:46:03
 */
@RestController
@RequestMapping("/ershouwupinhuishou")
public class ErshouwupinhuishouController {
    @Autowired
    private ErshouwupinhuishouService ershouwupinhuishouService;




    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ErshouwupinhuishouEntity ershouwupinhuishou, 
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("kehu")) {
			ershouwupinhuishou.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ErshouwupinhuishouEntity> ew = new EntityWrapper<ErshouwupinhuishouEntity>();


		PageUtils page = ershouwupinhuishouService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ershouwupinhuishou), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ErshouwupinhuishouEntity ershouwupinhuishou, 
		HttpServletRequest request){
        EntityWrapper<ErshouwupinhuishouEntity> ew = new EntityWrapper<ErshouwupinhuishouEntity>();

		PageUtils page = ershouwupinhuishouService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ershouwupinhuishou), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ErshouwupinhuishouEntity ershouwupinhuishou){
       	EntityWrapper<ErshouwupinhuishouEntity> ew = new EntityWrapper<ErshouwupinhuishouEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ershouwupinhuishou, "ershouwupinhuishou")); 
        return R.ok().put("data", ershouwupinhuishouService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ErshouwupinhuishouEntity ershouwupinhuishou){
        EntityWrapper< ErshouwupinhuishouEntity> ew = new EntityWrapper< ErshouwupinhuishouEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ershouwupinhuishou, "ershouwupinhuishou")); 
		ErshouwupinhuishouView ershouwupinhuishouView =  ershouwupinhuishouService.selectView(ew);
		return R.ok("查询二手物品回收成功").put("data", ershouwupinhuishouView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ErshouwupinhuishouEntity ershouwupinhuishou = ershouwupinhuishouService.selectById(id);
        return R.ok().put("data", ershouwupinhuishou);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ErshouwupinhuishouEntity ershouwupinhuishou = ershouwupinhuishouService.selectById(id);
        return R.ok().put("data", ershouwupinhuishou);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ErshouwupinhuishouEntity ershouwupinhuishou, HttpServletRequest request){
    	ershouwupinhuishou.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(ershouwupinhuishou);

        ershouwupinhuishouService.insert(ershouwupinhuishou);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ErshouwupinhuishouEntity ershouwupinhuishou, HttpServletRequest request){
    	ershouwupinhuishou.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(ershouwupinhuishou);

        ershouwupinhuishouService.insert(ershouwupinhuishou);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ErshouwupinhuishouEntity ershouwupinhuishou, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ershouwupinhuishou);
        ershouwupinhuishouService.updateById(ershouwupinhuishou);//全部更新
        return R.ok();
    }

    
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ershouwupinhuishouService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
