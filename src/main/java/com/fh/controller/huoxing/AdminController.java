package com.fh.controller.huoxing;


import com.alibaba.fastjson.JSON;
import com.fh.entity.huoxing.TbAppKey;
import com.fh.entity.huoxing.TbAppVersion;
import com.fh.service.xinghuo.AdminService;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * xinghuo 后台管理员操作
 */
@Controller
@RequestMapping(value = "/huoxing")
public class AdminController {

    @Autowired
    private AdminService adminService;



    @RequestMapping(value = "find",method = RequestMethod.GET)
    public ModelAndView findPage(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("system/index/acitive_code");
        return mv;
    }


    /**
     * function： 根据传人excel解析，验证tb_app_key，然后不存在就新增，存在就不做操作，并且返回excel
     * @param file
     * @param response
     */
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public void batchAddByExcel(@RequestParam("file") MultipartFile file,
                                  HttpServletResponse response){
        if(file.isEmpty()){
            return;
        }
        String originalName=file.getOriginalFilename();
        String suffix=originalName.substring(originalName.lastIndexOf(".")+1);
        //交验是否是xls或xlsx文件
        boolean isTrue=validateExcelFormat(suffix);

        if(!isTrue){
            //todo 文件格式不对
            return ;
        }
        //读取文件里的内容
        InputStream inputStream=null;
        OutputStream outputStream=null;
        try {
            inputStream=file.getInputStream();
            //解析文件封装数据
            List<TbAppKey> appKeys = parseTo(inputStream);
            if(appKeys!=null){
                List<TbAppKey> keyList = adminService.addOrUpdateAppKey(appKeys);
                //如果keyList有数据则需要写入excel返回
                if(keyList.size()>0){
                    try {
                        response.reset();
                        String outputFileName=URLEncoder.encode("返回记录","UTF-8");
                        response.setHeader("content-disposition","attachment;filename="+outputFileName+".xls");
                        response.setContentType("application/vnd.ms-excel;charset=utf-8");// 设置contentType为excel格式
                        HSSFWorkbook workbook=new HSSFWorkbook();
                        HSSFSheet sheet = workbook.createSheet();
                        HSSFRow headRow = sheet.createRow(0);
                        HSSFCell headCell0 = headRow.createCell(0);
                        headCell0.setCellType(HSSFCell.CELL_TYPE_STRING);
                        headCell0.setCellValue("APPNAME");

                        Cell headCell1 = headRow.createCell(1);
                        headCell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                        headCell1.setCellValue("APPKEY");

                        Cell headCell2 = headRow.createCell(2);
                        headCell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                        headCell2.setCellValue("失败描述");
                        for(int i=0;i<keyList.size();i++){
                            HSSFRow row = sheet.createRow(i+1);
                            //设置第一列值
                            HSSFCell cell1 = row.createCell(0);
                            cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell1.setCellValue(keyList.get(i).getAppName());
                            //设置第二列值
                            HSSFCell cell2 = row.createCell(1);
                            cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell2.setCellValue(keyList.get(i).getAppKey());

                            //设置第三列值
                            HSSFCell cell3 = row.createCell(2);
                            cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell3.setCellValue(keyList.get(i).getDescription());

                        }
                        outputStream=response.getOutputStream();
                        response.setCharacterEncoding("UTF-8");
                        workbook.write(outputStream);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    finally {
                        if(outputStream!=null){
                            try {
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * function:管理员可以维护注册码,即更新过期时间
     * @param dateStr yyyy-MM-dd格式
     * @param appKey 激活码
     * @param appName app名称
     * @return
     */
    @RequestMapping(value="/update_expiration",method = RequestMethod.POST,produces ="application/json;charset=utf-8")
    @ResponseBody
    public String updateAppkeyDate(@RequestParam("dateStr") String dateStr,
                                   @RequestParam("appKey") String appKey,
                                   @RequestParam("appName") String appName){

        JSONObject object=new JSONObject();
        if(StringUtils.isEmpty(dateStr)){
            object.put("result","过期时间不能为空");
            return object.toString();
        }
        String res=adminService.updateAppkeyExpiration(dateStr,appKey,appName);
        object.put("result",res);
        return object.toString();
    }


    /**
     * 更新app版本和文件名
     * @param appName app名
     * @param version 版本号
     * @param fileName 文件名
     * @return
     */
    @RequestMapping(value="/update_version",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateAppVersion(@RequestParam("appName") String appName,
                                   @RequestParam("version") String version,
                                   @RequestParam("fileName") String fileName){
        String res=adminService.updateAppVersion(appName,version,fileName);
        JSONObject object=new JSONObject();
        object.put("result",res);
        return object.toString();
    }


    /**
     * 根据app名字查找app版本信息
     * @return
     */
    @RequestMapping(value="/find_version",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public ModelAndView findAppVersion(@RequestParam("appName")String appName){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("system/index/acitive_code");
        TbAppVersion tbAppVersion=adminService.findAppVersionByName(appName);
        if(tbAppVersion!=null){
            mv.addObject("mv",tbAppVersion);
        }else {
            mv.addObject("mv","");
        }
        return mv;

    }

    private  boolean validateExcelFormat(String suffixName){
        if(StringUtils.isEmpty(suffixName)){
            return false;
        }
        if(suffixName.equalsIgnoreCase("xls")||suffixName.equalsIgnoreCase("xlsx")){
            return true;
        }
        return false;
    }

    private List<TbAppKey> parseTo(InputStream inputStream) throws Exception {
        if(inputStream==null){
            return null;
        }
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        //todo 数据量很大需要优化
        List<TbAppKey> keyList= new ArrayList<>();
        for (int i=1;i<=rowNum;i++){
            Row row = sheet.getRow(i);
            TbAppKey key = new TbAppKey();

            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            key.setAppName(row.getCell(0).getStringCellValue());

            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            key.setAppKey(row.getCell(1).getStringCellValue());
            key.setMakeTime(new Date());
            key.setModifyTime(new Date());
            keyList.add(key);
        }
        return keyList;
    }

    public static void main(String[] args) {
        //603872613434004888084446 原sys_role admin的rights
        //38960117959924901773890
        //730750818665451459101842455318259469752868045378
//        BigInteger bigInteger = new BigInteger("2417851639229258349412350");
//        System.out.println(bigInteger.testBit(1));
//        System.out.println(bigInteger.testBit(6));
//        System.out.println(bigInteger.testBit(9));
//        System.out.println(bigInteger.testBit(15));
//        System.out.println(bigInteger.testBit(22));
//        System.out.println(bigInteger.testBit(40));
//        System.out.println(bigInteger.testBit(54));
//        System.out.println(bigInteger.testBit(59));
//        System.out.println(bigInteger.testBit(70));
//        System.out.println(bigInteger.testBit(75));
//        System.out.println(bigInteger.testBit(12));

//        for(int i=1;i<=80;i++){
//            bigInteger= bigInteger.setBit(i);
//        }
//        System.out.println(bigInteger);

//        BigInteger bigInteger = new BigInteger("0");
//        BigInteger bit = bigInteger.setBit(1).setBit(6).setBit(9).setBit(15).setBit(22).setBit(40).setBit(54).setBit(59)
//                .setBit(70).setBit(75).setBit(159);
//        System.out.println(bit);


    }

}
