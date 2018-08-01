package com.spm.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExcelUtil
{
    public static void main(String[] args) throws Exception
    {
        String path = "D:\\data1.xls";
        //readFile(path);
        loadScoreInfo(path);
        List<JSONObject> rows = loadScoreInfo(path);
        List<Object> list = new ArrayList<>();
        for (JSONObject row : rows){
            Object idata =new Object();
            /*
            idata.setCarNumber(row.getString("carNumber"));*/
            list.add(idata);
        }
        System.out.print(list.size());
    }


    public static List<JSONObject> loadScoreInfo(String xlsPath) throws Exception
    {
        List<JSONObject> temp = new ArrayList<JSONObject>();
        FileInputStream fileIn = new FileInputStream(xlsPath);
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(xlsPath);
        } catch (Exception ex) {
            wb = new HSSFWorkbook(fileIn);
        }
        //获取Excel文档中的第一个表单
        Sheet sht = wb.getSheetAt(0);
        int num = sht.getPhysicalNumberOfRows();
        //对Sheet中的每一行进行迭代
        int sn = 36000;
        Map<String,Object> map = new HashMap<String,Object>();
        for(int i=1;i<num;i++){
            Row r = sht.getRow(i);
            //创建实体类
            JSONObject info=new JSONObject();
            String keyn = "";
            if (r.getCell(3)!=null) {
                r.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                keyn =  r.getCell(3).getStringCellValue();
                info.put("parkingSpaceNumber",keyn);
                map.put(keyn,1);
            }else {
                info.put("parkingSpaceNumber", Integer.toString(sn++));
                map.put(keyn,1);
            }
            //取出当前行第1个单元格数据，并封装在info实体stuName属性上
            r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            info.put("userName", r.getCell(0).getStringCellValue());

            if (r.getCell(1) !=null) {
                r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                info.put("identityCard", r.getCell(1).getStringCellValue());
            }
            if (r.getCell(2) !=null) {
                r.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                info.put("mobileNumber", r.getCell(2).getStringCellValue());
            }

            if (r.getCell(4)!=null) {
                r.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                info.put("carNumber", r.getCell(4).getStringCellValue());
            }
            info.put("startTime", r.getCell(5).getDateCellValue().getTime());
            info.put("endTime", r.getCell(6).getDateCellValue().getTime());
            info.put("spaceType",(int) r.getCell(7).getNumericCellValue());

            if (r.getCell(8)!=null){
                r.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                info.put("cardNo",r.getCell(8).getStringCellValue());
            }

            if (r.getCell(9)!=null){
                r.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                info.put("remark",r.getCell(9).getStringCellValue());
            }
            temp.add(info);
        }
        fileIn.close();
        return temp;
    }
}
