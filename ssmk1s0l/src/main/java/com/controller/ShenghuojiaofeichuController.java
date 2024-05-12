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

import com.entity.ShenghuojiaofeichuEntity;
import com.entity.view.ShenghuojiaofeichuView;

import com.service.ShenghuojiaofeichuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;

/**
 * 生活缴费处
 * 后端接口
 * @author 
 * @email 
 * @date 2023-05-25 09:46:02
 */
@RestController
@RequestMapping("/shenghuojiaofeichu")
public class ShenghuojiaofeichuController {
    @Autowired
    private ShenghuojiaofeichuService shenghuojiaofeichuService;




    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShenghuojiaofeichuEntity shenghuojiaofeichu, 
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("kehu")) {
			shenghuojiaofeichu.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ShenghuojiaofeichuEntity> ew = new EntityWrapper<ShenghuojiaofeichuEntity>();


		PageUtils page = shenghuojiaofeichuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shenghuojiaofeichu), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShenghuojiaofeichuEntity shenghuojiaofeichu, 
		HttpServletRequest request){
        EntityWrapper<ShenghuojiaofeichuEntity> ew = new EntityWrapper<ShenghuojiaofeichuEntity>();

		PageUtils page = shenghuojiaofeichuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shenghuojiaofeichu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShenghuojiaofeichuEntity shenghuojiaofeichu){
       	EntityWrapper<ShenghuojiaofeichuEntity> ew = new EntityWrapper<ShenghuojiaofeichuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shenghuojiaofeichu, "shenghuojiaofeichu")); 
        return R.ok().put("data", shenghuojiaofeichuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShenghuojiaofeichuEntity shenghuojiaofeichu){
        EntityWrapper< ShenghuojiaofeichuEntity> ew = new EntityWrapper< ShenghuojiaofeichuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shenghuojiaofeichu, "shenghuojiaofeichu")); 
		ShenghuojiaofeichuView shenghuojiaofeichuView =  shenghuojiaofeichuService.selectView(ew);
		return R.ok("查询生活缴费处成功").put("data", shenghuojiaofeichuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShenghuojiaofeichuEntity shenghuojiaofeichu = shenghuojiaofeichuService.selectById(id);
        return R.ok().put("data", shenghuojiaofeichu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShenghuojiaofeichuEntity shenghuojiaofeichu = shenghuojiaofeichuService.selectById(id);
        return R.ok().put("data", shenghuojiaofeichu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShenghuojiaofeichuEntity shenghuojiaofeichu, HttpServletRequest request){
    	shenghuojiaofeichu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shenghuojiaofeichu);

        shenghuojiaofeichuService.insert(shenghuojiaofeichu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShenghuojiaofeichuEntity shenghuojiaofeichu, HttpServletRequest request){
    	shenghuojiaofeichu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shenghuojiaofeichu);

        shenghuojiaofeichuService.insert(shenghuojiaofeichu);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ShenghuojiaofeichuEntity shenghuojiaofeichu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shenghuojiaofeichu);
        shenghuojiaofeichuService.updateById(shenghuojiaofeichu);//全部更新
        return R.ok();
    }

    
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shenghuojiaofeichuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
