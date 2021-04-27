package com.fh.service.xinghuo;

import com.fh.dao.DAO;
import com.fh.entity.huoxing.TbAppKey;
import com.fh.entity.huoxing.TbAppVersion;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminServiceImpl implements  AdminService {

    @Resource(name = "daoSupport")
    private DAO daoSupport;

    @Override
    public List<TbAppKey> addOrUpdateAppKey(List<TbAppKey> tbAppKeyList) {
        List<TbAppKey> resultList=new ArrayList<>();
        for (TbAppKey key:tbAppKeyList){

            try {
                TbAppKey tbAppKey= (TbAppKey) daoSupport.findForObject("tbAppKeyMapper.find", key);

                if(tbAppKey!=null){//已经存在,返回
                    key.setDescription("已存在");
                    resultList.add(key);
                }
                else {//不存在，添加
                    daoSupport.save("tbAppKeyMapper.save",key);
                }

            } catch (Exception e) {
                e.printStackTrace();
                key.setDescription("插入失败");
                resultList.add(key);
            }
        }
        return resultList;
    }

    @Override
    public List<TbAppKey> findAll(String appName) {
        List<TbAppKey> res=new ArrayList<>();
        try {
            res= (List<TbAppKey>) daoSupport.findForList("tbAppKeyMapper.findALl",appName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String updateAppkeyExpiration(String dateStr,String appKey,String appName) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateStr);
            Map<String,Object> param=new HashMap<>();
            param.put("date",date);
            param.put("appKey",appKey);
            param.put("appName",appName);
            daoSupport.update("tbAppKeyMapper.updateExpiration",param);
            return "更新成功";
        }catch (ParseException e) {
            e.printStackTrace();
            return "时间格式不对！";
        }catch (Exception e) {
            e.printStackTrace();
            return "内部错误，导致更新失败！";
        }
    }

    @Override
    public String updateAppVersion(String appName, String version, String fileName) {
        Map<String,Object> param=new HashMap<>();
        param.put("version",version);
        param.put("appName",appName);
        param.put("fileName",fileName);
        param.put("modifyTime",new Date());
        try {
            daoSupport.update("tbAppVersionMapper.updateAppVersion",param);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @Override
    public TbAppVersion findAppVersionByName(String appName) {
        TbAppVersion tbAppVersion= null;
        if(StringUtils.isEmpty(appName)){
            return null;
        }
        try {
            tbAppVersion = (TbAppVersion) daoSupport.findForObject("tbAppVersionMapper.findByAppName",appName);
            return  tbAppVersion;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbAppVersion;
    }
}
