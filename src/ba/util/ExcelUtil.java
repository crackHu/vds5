package ba.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellFeatures;
import jxl.CellType;
import jxl.Range;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtil
{

  public static void main(String[] args)
  {
     ExcelUtil.ecport();
//      String ss = "$main.erwer$";
//      System.out.println(ss.indexOf("$main.") != -1 );
//
//      System.out.println(ss.lastIndexOf("$")==ss.length()-1);
  }
  
  public static void ecport(){
    String xlsPath = "c:/123.xls";//模板 
    
    Workbook wb;
    try
    {
      wb = Workbook.getWorkbook(new File(xlsPath));
      String xlsPath1 = "c:/1234.xls";
      //打开一个文件的副本，并且指定数据写回到原文件 
      WritableWorkbook sheet=Workbook.createWorkbook(new File(xlsPath1),wb); 
      Map<String,Map> main_datails = new HashMap<String,Map>();
      
      //main
      Map<String,Object> main = new HashMap<String,Object>();

      
      Map<String,Object> main_entity0 = new HashMap<String,Object>();
      Map<String,Object> main_entity1 = new HashMap<String,Object>();
      Map<String,Object> main_entity2 = new HashMap<String,Object>();
      //dept,name,total
      main_entity0.put("dept", "局领导");
      main_entity0.put("name", "正局长");
      main_entity0.put("total", "3");
      
      main_entity1.put("dept", "副局领导");
      main_entity1.put("name", "副局长");
      main_entity1.put("total", "1");
      
      main_entity2.put("dept", "副局领导");
      main_entity2.put("name", "副局长");
      main_entity2.put("total", "1");
      
      main.put("total", 3);
      main.put("entity0", main_entity0);
      main.put("entity1", main_entity1);
      main.put("entity2", main_entity2);
      //details
      Map<String,Object> datails = new HashMap<String,Object>();
      
      
      Map<String,Object> dtl_entity0 = new HashMap<String,Object>();
      
      
      dtl_entity0.put("total", 3);
      Map<String,Object> entity0 = new HashMap<String,Object>();
      entity0.put("name", "name");
      entity0.put("name1", "1");
      entity0.put("sex", "男");
      entity0.put("age", "10");
      entity0.put("age1", "11");
      entity0.put("age2", "12");
      entity0.put("age3", "13");
      entity0.put("age4", "14");
      entity0.put("age5", "15");
      entity0.put("age6", "16");
      entity0.put("age7", "17");
      
      Map<String,Object> entity1 = new HashMap<String,Object>();
      entity1.put("name", "name1");
      entity1.put("name1", "2");
      entity1.put("sex", "男");
      entity1.put("age", "20");
      entity1.put("age1", "21");
      entity1.put("age2", "22");
      entity1.put("age3", "23");
      entity1.put("age4", "24");
      entity1.put("age5", "25");
      entity1.put("age6", "26");
      entity1.put("age7", "27");
      
      Map<String,Object> entity2 = new HashMap<String,Object>();
      entity2.put("name", "name2");
      entity2.put("name1", "3");
      entity2.put("sex", "男2");
      entity2.put("age", "30");
      entity2.put("age1", "31");
      entity2.put("age2", "32");
      entity2.put("age3", "33");
      entity2.put("age4", "34");
      entity2.put("age5", "35");
      entity2.put("age6", "36");
      entity2.put("age7", "37");

      
      
      dtl_entity0.put("entity0", entity0);
      dtl_entity0.put("entity1", entity1);
      dtl_entity0.put("entity2", entity2);
      
      datails.put("entity0", dtl_entity0);
      
      Map<String,Object> dtl_entity1 = new HashMap<String,Object>();
      dtl_entity1.put("total", 1);
      Map<String,Object> entity10 = new HashMap<String,Object>();
      entity10.put("name", "name");
      entity10.put("name1", "name");
      entity10.put("sex", "男");
      entity10.put("age", "10");
      entity10.put("age1", "11");
      entity10.put("age2", "12");
      entity10.put("age3", "13");
      entity10.put("age4", "14");
      entity10.put("age5", "15");
      entity10.put("age6", "16");
      entity10.put("age7", "17");
      dtl_entity1.put("entity0", entity10);
      
      datails.put("entity1", dtl_entity1);
      
      Map<String,Object> dtl_entity2 = new HashMap<String,Object>();
      dtl_entity2.put("total", 1);
      Map<String,Object> entity11 = new HashMap<String,Object>();
      entity11.put("name", "name");
      entity11.put("name1", "name");
      entity11.put("sex", "男");
      entity11.put("age", "10");
      entity11.put("age1", "11");
      entity11.put("age2", "12");
      entity11.put("age3", "13");
      entity11.put("age4", "14");
      entity11.put("age5", "15");
      entity11.put("age6", "16");
      entity11.put("age7", "17");
      dtl_entity2.put("entity0", entity11);
      
      datails.put("entity2", dtl_entity2);
      
      //总的map
      main_datails.put("main", main);
      main_datails.put("details", datails);
      
      Map<String,String> reamrks = new HashMap<String,String>();
      reamrks.put("name", "马全兴");
      reamrks.put("time", "2011年7月9日");
      reamrks.put("remark", "这个是说明！");
      
      ExcelUtil.exportExcel(sheet,"",3,11,8,10,10,20,main_datails,reamrks);
      
      sheet.write();
      sheet.close();
    }
    catch (BiffException e)
    {
      // TODO Auto-generated catch block
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
    }
    catch (WriteException e)
    {
      // TODO Auto-generated catch block
    } 
  }
  
  public static void test(){
    try
    {
      //get file.   
      String xlsPath = "d:/test.xls";//模板 
      WritableWorkbook wwb = Workbook.createWorkbook(new File(xlsPath));
      wwb.createSheet("mqx", 0);
      WritableSheet sheet = wwb.getSheet(0);
      String[] colWidthArray = { "20", "20", "400" };
      String[] header = { "323", "12312", "12323" };
      String[] remarksArray = { "remarksArray323", "remarksArray12312",    "remarksArray12323" };
      String[][] records = { { "1 ", "abc ", "sdfsdfsd" },{ "2 ", "edf ", "sdfsdfsd" } };
      ExcelUtil.ExcelExport(0, 1, sheet, header, colWidthArray, remarksArray,
          records, ExcelUtil.getDefaultWriterHeaderFormat(), ExcelUtil
              .getDefaultWriterContentFormat(), true);
      wwb.write();
      wwb.close();

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  
  public static int getTotalRows(int mainStart, int mainEnd){
    return mainEnd - mainStart +1;
  }
  public static void exportExcel(WritableWorkbook  sheets,String  sheetName,int mainStart,int mainEnd ,int detalStart,int detalEnd,int totalCols,int totalRows
      ,Map<String,Map> main_datails,Map<String,String> reamrks){
    try
    {
//      sheets.createSheet(sheetName, 0);
      WritableSheet sheet = sheets.getSheet(0); 
      Map mainMap = main_datails.get("main");
      int main_ds_total = (Integer)mainMap.get("total");
      
      Map detailsMap = main_datails.get("details");
      
        //变量赋值全局变量 模板的col与rows
        //修正循环行数  子循环开始行 - 开始循环行 + 1
        int revise = 0;
        for (int kk =0; kk < totalRows-1; kk++) {
          Cell[] cells = sheet.getRow(kk);
          for (int jj = 0; jj < totalCols; jj++) {
            if(cells.length<=jj){
              break;
            }

            String cellContent = cells[jj].getContents().trim();
            if(cellContent.indexOf("$detail.") != -1 && cellContent.lastIndexOf("$")==cellContent.length()-1 && revise == 0){
               revise = kk;
            }
            if(cellContent.indexOf("$remark.") != -1 && cellContent.lastIndexOf("$")==cellContent.length()-1){
               String field = cellContent.replace("$remark.","");
               field = field.replace("$", "");
               String value = (String)reamrks.get(field);
               modiStrCell(sheet,jj,kk,value,null);
            }
          }
        }
        revise = revise - mainStart + 1;
        Range[] range = sheet.getMergedCells();
        int rows = getTotalRows(mainStart,mainEnd);
        int rowStart = mainEnd+1;
        
        int detail_rows = getTotalRows(detalStart,detalEnd);
        


        
        int times = main_ds_total; 
        int total = 0;
       for(int groups = 0;groups < times; groups++){
         Map entityMap = (Map)mainMap.get("entity"+groups);
        copyCells(sheet,range,1,mainStart,totalCols,rows,0,rowStart+(rows)*(groups)+total);  //12
        //遍历赋值main
        for (int kk =rowStart+(rows)*(groups)+total-1; kk < rowStart+(rows)*(groups)+total-1+rows; kk++) {
          Cell[] cells = sheet.getRow(kk);
          for (int jj = 0; jj < totalCols; jj++) {
            if(cells.length<=jj){
              break;
            }
            String cellContent = cells[jj].getContents().trim();
            if(cellContent.indexOf("$main.") != -1 && cellContent.lastIndexOf("$")==cellContent.length()-1){
               String field = cellContent.replace("$main.","");
               field = field.replace("$", "");
               //WritableSheet dataSheet, int col, int row, String str, CellFormat format
               String value = (String)entityMap.get(field);
               modiStrCell(sheet,jj,kk,value,null);
            }
          }
        }
        
        Map detailMap = (Map)detailsMap.get("entity"+groups);
        int detail_ds_total = Integer.valueOf((Integer) detailMap.get("total"));
        int i1 = detail_ds_total;
        //删除复制行
        for(int ii=0;ii<detail_rows;ii++){
           sheet.removeRow(rowStart+rows*(groups)+total+revise-1);
        }
        //System.out.println("删除行"+(rowStart+rows*(groups)+total+revise-1));
        for(int j=1;j<=i1;j++){
          //System.out.println("第"+groups+1+"次  details "+(rowStart+rows*(groups)+total+revise+(j-1)*detail_rows)+"行");
          Map dtentityMap = (Map)detailMap.get("entity"+(j-1));
          //int kk = rowStart+rows*(groups)+total+revise+(j-1)*detail_rows;
          copyCells(sheet,range,1,detalStart,totalCols,detail_rows,0,rowStart+rows*(groups)+total+revise+(j-1)*detail_rows);  
          //遍历赋值detail
          for (int kk =rowStart+rows*(groups)+total+revise+(j-1)*detail_rows-1; kk < (rowStart+rows*(groups)+total+revise+(j-1)*detail_rows-1+detail_rows); kk++) { //处理模板的原有的一行
            Cell[] cells = sheet.getRow(kk);
            for (int jj = 0; jj < totalCols; jj++) {
              if(cells.length<=jj){
                break;
              }
              String cellContent = cells[jj].getContents().trim();
              if(cellContent.indexOf("$detail.") != -1 && cellContent.lastIndexOf("$")==cellContent.length()-1){
                 String field = cellContent.replace("$detail.","");
                 field = field.replace("$", "");
                 //WritableSheet dataSheet, int col, int row, String str, CellFormat format
                 String value = (String)dtentityMap.get(field);
                 modiStrCell(sheet,jj,(kk),value,null);
              }
            }
          }
      }
        total = total +i1*detail_rows-detail_rows;
      }
      // 删除原来的模板
      
      for(int i=mainStart;i<=mainEnd;i++){
       sheet.removeRow(mainStart-1);
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  
  public static WritableCellFormat getDefaultWriterHeaderFormat()
  {
    try
    {
      WritableFont headerFont = new WritableFont(WritableFont
          .createFont("华文行楷"), 12, WritableFont.BOLD, false,
          UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
      WritableCellFormat headerFormat = new WritableCellFormat(headerFont);
      headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
      headerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
      headerFormat.setWrap(true);
      headerFormat.setBackground(jxl.format.Colour.WHITE);
      WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"),
          12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
          jxl.format.Colour.BLACK);
      WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
      titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
      titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
      titleFormat.setWrap(true);
      titleFormat.setBackground(jxl.format.Colour.WHITE);
      return titleFormat;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public static WritableCellFormat getDefaultWriterContentFormat()
  {
    try
    {
      WritableFont contentFont = new WritableFont(
          WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
          UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
      WritableCellFormat contentFormat = new WritableCellFormat(contentFont);
      contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
      //contentFormat.setAlignment(Alignment.CENTRE);
      contentFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
      contentFormat.setWrap(true);
      contentFormat.setBackground(jxl.format.Colour.WHITE);
      return contentFormat;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public static void ExcelExport(int x, int y, WritableSheet sheet,
      String[] headerArray, String[] colWidths, String[] remarks,
      String[][] contentsArray, WritableCellFormat titleFormat,
      WritableCellFormat contentFormat, boolean needNo) throws WriteException
  {
    int tempY = 0;
    int tempX = 1;
    sheet.setColumnView(0, 5);
    sheet.addCell(new Label(0, 0, "", titleFormat));
    for (int i = 0; i < headerArray.length; i++)
    {
      String header = headerArray[i];
      header = header.replaceAll("<center>", "");
      header = header.replaceAll("</center>", "");
      int colWidth = 30;
      if (i < colWidths.length)
      {
        colWidth = Integer.valueOf(colWidths[i]);
      }
      sheet.setColumnView(y + tempY, colWidth);
      sheet.addCell(new Label(y + (tempY++), x, header, titleFormat));
    }
    x++;
    for (String[] contents : contentsArray)
    {
      tempY = 0;
      for (String content : contents)
      {
        if (content == null)
        {
          content = "";
        }
        content = content.replaceAll("<br/>", "\r\n");
        sheet.addCell(new Label(y + (tempY++), x, content, contentFormat));
      }
      if (needNo)
        sheet.addCell(new Label(y - 1, x, String.valueOf(tempX++),
            contentFormat));
      x++;
    }

    //增加备注
    for (int i = 0; i < remarks.length; i++)
    {
      sheet.addCell(new Label(0, contentsArray.length + 2 + i, remarks[i],
          ExcelUtil.getDefaultWriterContentFormat3()));
    }
  }

  public static WritableCellFormat getDefaultWriterContentFormat1()
  {
    try
    {
      WritableFont contentFont = new WritableFont(
          WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD, false,
          UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
      WritableCellFormat contentFormat = new WritableCellFormat(contentFont);
      contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
      //contentFormat.setAlignment(Alignment.CENTRE);
      contentFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
      contentFormat.setWrap(true);
      contentFormat.setBackground(jxl.format.Colour.WHITE);
      return contentFormat;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public static WritableCellFormat getDefaultWriterContentFormat2()
  {
    try
    {
      WritableFont contentFont = new WritableFont(
          WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD, false,
          UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
      WritableCellFormat contentFormat = new WritableCellFormat(contentFont);
      contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
      //contentFormat.setAlignment(Alignment.CENTRE);
      contentFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
      contentFormat.setBackground(jxl.format.Colour.WHITE);
      return contentFormat;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public static WritableCellFormat getDefaultWriterContentFormat3()
  {
    try
    {
      WritableFont contentFont = new WritableFont(
          WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD, false,
          UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
      WritableCellFormat contentFormat = new WritableCellFormat(contentFont);
      contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
      //            contentFormat.setAlignment(Alignment.CENTRE);
      contentFormat.setBorder(jxl.format.Border.ALL,
          jxl.format.BorderLineStyle.THIN, jxl.format.Colour.GRAY_25);
      contentFormat.setBackground(jxl.format.Colour.WHITE);
      return contentFormat;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public static void ExcelExport(int x, int y, WritableSheet sheet,
      String[] headerArray, String[][] contentsArray,
      WritableCellFormat titleFormat, WritableCellFormat contentFormat,
      int colWidth, boolean needNo) throws WriteException
  {
    int tempY = 0;
    int tempX = 1;
    for (String header : headerArray)
    {
      header = header.replaceAll("<center>", "");
      header = header.replaceAll("</center>", "");

      sheet.setColumnView(y + tempY, colWidth);
      sheet.addCell(new Label(y + (tempY++), x, header, titleFormat));
    }
    x++;
    for (String[] contents : contentsArray)
    {
      tempY = 0;
      for (String content : contents)
      {
        if (content == null)
        {
          content = "";
        }
        content = content.replaceAll("<br/>", "\r\n");
        sheet.addCell(new Label(y + (tempY++), x, content, contentFormat));
      }
      if (needNo)
        sheet.addCell(new Label(y - 1, x, String.valueOf(tempX++),
            contentFormat));
      x++;
    }
    System.out.println();
  }

  /**
   * 设置模板的内容,比如原来是<name>:你好,则转换后会变成 张三:你好
   * @param ws WritableSheet对象
   * @param col 模板的第几列
   * @param row 模板的第几行
   * @param key 相当于上面的<name>关键字
   * @param value 替换的字符
   * @return 返回替换后的Label对象,返回后可以使用label.getContents()查看修改后的内容
   * @throws Exception
   */
  public static Label setTemple(WritableSheet ws, int col, int row, String key,
      String value) throws Exception
  {
    Cell cell = ws.getCell(col, row);
    String temp = cell.getContents().replace(key, value);
    Label label = new Label(col, row, temp, cell.getCellFormat());
    ws.addCell(label);
    return label;
  }

  /**
   * 设置模板的内容,比如原来是<name>:你好,则转换后会变成 张三:你好
   * @param ws WritableSheet对象
   * @param col 模板的第几列
   * @param row 模板的第几行
   * @param args 存放key和value
   * @return 返回替换后的Label对象,返回后可以使用label.getContents()查看修改后的内容
   * @throws Exception
   */
  public static Label setTemple(WritableSheet ws, int col, int row,
      Map<String, String> args) throws Exception
  {
    Cell cell = ws.getCell(col, row);
    String temp = cell.getContents();
    for (Map.Entry<String, String> arg : args.entrySet())
    {
      temp = temp.replace(arg.getKey(), arg.getValue());
    }
    Label label = new Label(col, row, temp, cell.getCellFormat());
    ws.addCell(label);
    return label;
  }

  /**
   * 返回一个Excel的Copy,做模板的时候有用
   * @param oldObject 模板的路径或者File对象
   * @param newObject 新Excel的路径或者File对象
   * @return
   */
  public static WritableWorkbook getCopy(Object oldObject, Object newObject)
  {
    try
    {
      File oldFile = null;
      File newFile = null;
      if (oldObject instanceof String)
        oldFile = new File((String) oldObject);
      else if (oldObject instanceof File)
        oldFile = (File) oldObject;
      if (newObject instanceof String)
        newFile = new File((String) newObject);
      else if (newObject instanceof File)
        newFile = (File) newObject;
      Workbook wb = Workbook.getWorkbook(oldFile);
      WritableWorkbook wwb = Workbook.createWorkbook(newFile, wb);
      return wwb;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public static void ExcelExport2(int x, int y, WritableSheet sheet,
      String[] headerArray, String[][] contentsArray,
      WritableCellFormat titleFormat, WritableCellFormat contentFormat,
      int colWidth, boolean needNo) throws WriteException
  {
    int tempY = 0;
    int tempX = 1;
    for (String header : headerArray)
    {
      header = header.replaceAll("<center>", "");
      header = header.replaceAll("</center>", "");

      if ("处室名称".equals(header))
      {
        sheet.setColumnView(y + tempY, 13);
        sheet.addCell(new Label(y + (tempY++), x, "处室", titleFormat));
      }
      else if ("姓名".equals(header))
      {
        sheet.setColumnView(y + tempY, 9);
        sheet.addCell(new Label(y + (tempY++), x, header, titleFormat));
      }
      else if ("职务".equals(header))
      {
        sheet.setColumnView(y + tempY, 7);
        sheet.addCell(new Label(y + (tempY++), x, header, titleFormat));
      }
      else
      {
        sheet.setColumnView(y + tempY, colWidth);
        sheet.addCell(new Label(y + (tempY++), x, header, titleFormat));
      }

    }
    x++;
    for (String[] contents : contentsArray)
    {
      tempY = 0;
      for (String content : contents)
      {
        content = content.replaceAll("<br/>", "\r\n");
        sheet.addCell(new Label(y + (tempY++), x, content, contentFormat));
      }
      if (needNo)
        sheet.addCell(new Label(y - 1, x, String.valueOf(tempX++),
            contentFormat));
      x++;
    }
  }

  /**  
   * 修改字符单元格的值  
   * @param dataSheet WritableSheet : 工作表  
   * @param col int : 列  
   * @param row int : 行  
   * @param str String : 字符  
   * @param format CellFormat : 单元格的样式  
   * @throws RowsExceededException  
   * @throws WriteException  
   */
  public static void modiStrCell(WritableSheet dataSheet, int col, int row,
      String str, CellFormat format) throws RowsExceededException,
      WriteException
  {
    // 获得单元格对象   
    WritableCell cell = dataSheet.getWritableCell(col, row);
    // 判断单元格的类型, 做出相应的转化   
    if (cell.getType() == CellType.EMPTY)
    {
      Label lbl = new Label(col, row, str);
      if (null != format)
      {
        lbl.setCellFormat(format);
      }
      else
      {
        lbl.setCellFormat(cell.getCellFormat());
      }
      dataSheet.addCell(lbl);
    }
    else if (cell.getType() == CellType.LABEL)
    {
      Label lbl = (Label) cell;
      lbl.setString(str);
    }
    else if (cell.getType() == CellType.NUMBER)
    {
      // 数字单元格修改   
      Number n1 = (Number) cell;

      //n1.(42.05);   
    }
  }

  /**
   * 拷贝多个单元格
   * 
   * @param sheet
   * @param from1row
   *            需拷贝区域起始行
   * @param from1col
   *            需拷贝区域起始列
   * @param to1row
   *            需拷贝区域结束行
   * @param to1col
   *            需拷贝区域结束列
   * @return boolean 成功或者失败
   * @throws IOException
   * @throws RowsExceededException
   * @throws WriteException
   */
  public static boolean copyCells(WritableSheet sheet, Range[] mergedCell,
      int from1Col, int from1Row, int to1Col, int to1Row, int from2Col,
      int from2Row) throws IOException
  {
    from1Col = from1Col-1;
    from1Row = from1Row-1;
    from2Row = from2Row-1;
    from2Col = from2Col-1;
    to1Row   = to1Row -1;
    // 复制表格的高和长
    int tabHigh = to1Row - from1Row + 1;

    try
    {
      // 制作表格，先合并单元格
      for (int i = 0; i < (to1Row  + 1); i++)
      {

        // 选中区域下一行
        sheet.insertRow(from2Row + i);
        sheet.setRowView(from2Row + i, sheet.getRowHeight(from1Row + i));
        // 对插入行明基的列进行处理，即单元格
        for (int j = 0; j < (to1Col - from1Col); j++)
        {

          CellFormat cf = sheet.getWritableCell(from1Col + j, from1Row + i).getCellFormat();
          CellFeatures cfs = sheet.getWritableCell(from1Col + j, from1Row + i).getCellFeatures();
          String content = sheet.getCell(from1Col + j, from1Row + i).getContents();
          if("![]".equals(content)){
        	continue;
          }
          if (cf == null)
          {
            sheet.addCell(new Label(from1Col + j, from2Row + i, content));
            
          }
          else
          {
            sheet.addCell(new Label(from1Col + j, from2Row + i, content, cf));
          }
        }
      }

      // 合并单元格

      for (int i = 0; i < mergedCell.length; i++)
      {
        int fromRow = mergedCell[i].getTopLeft().getRow();
        int fromCol = mergedCell[i].getTopLeft().getColumn();

        int toRow = mergedCell[i].getBottomRight().getRow();
        int toCol = mergedCell[i].getBottomRight().getColumn();

        // 如果检测到的合并单元格，在复制表格内，则将对应粘贴表的宝马单元格合并。列数=原列数+表高，列数=原列数
        if (fromRow >= from1Row && fromCol >= from1Col && toRow <= to1Row && toCol <= to1Col)
        {
          sheet.mergeCells(fromCol, fromRow + from2Row, toCol, toRow + from2Row);
        }
      }

    }
    catch (RowsExceededException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
    catch (WriteException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**  
   * 修改公式单元格的值  
   * @param dataSheet WritableSheet : 工作表  
   * @param col int : 列  
   * @param row int : 行  
   * @param startPos int : 开始位置  
   * @param endPos int : 结束位置  
   * @param format  
   * @throws RowsExceededException  
   * @throws WriteException  
   */  
  private void modiFormulaCell(WritableSheet dataSheet, int col, int row, int startPos, int endPos, CellFormat format) throws RowsExceededException, WriteException   
  {   
      String f = getFormula(col, row, startPos, endPos);   
      // 插入公式（只支持插入，不支持修改）   
      WritableCell cell = dataSheet.getWritableCell(col, row);   
      if (cell.getType() == CellType.EMPTY)   
      {                       
          // 公式单元格   
          Formula lbl = new Formula(col, row, f);   
          if(null != format)   
          {   
              lbl.setCellFormat(format);   
          } else  
          {   
              lbl.setCellFormat(cell.getCellFormat());   
          }   
          dataSheet.addCell(lbl);   
      } else if (cell.getType() == CellType.STRING_FORMULA)   
      {   
          //YTLogger.logWarn("Formula modify not supported!");   
      }   
  }   
     
  /**  
   * 得到公式  
   * @param col int : 列  
   * @param row int : 行  
   * @param startPos int : 开始位置  
   * @param endPos int : 结束位置  
   * @return String  
   * @throws RowsExceededException  
   * @throws WriteException  
   */  
  private String getFormula(int col, int row, int startPos, int endPos)   
          throws RowsExceededException, WriteException   
  {   
      char base = 'A';   
      char c1 = base;   
      StringBuffer formula = new StringBuffer(128);   
      // 组装公式   
      formula.append("SUM(");   
      if (col <= 25)   
      {   
          c1 = (char) (col % 26 + base);   
          formula.append(c1).append(startPos).append(":")   
                 .append(c1).append(endPos).append(")");   
      } else if (col > 25)   
      {   
          char c2 = (char) ((col - 26) / 26 + base);   
          c1 = (char) ((col - 26) % 26 + base);   
          formula.append(c2).append(c1).append(startPos).append(":")   
                 .append(c2).append(c1).append(endPos).append(")");   
      }   

      return formula.toString();   
  }   
     
  /**  
   * 插入图表工作表  
   * @param wwb WritableWorkbook : 工作簿  
   * @param vecImg Vector : 图像链表  
   * @throws RowsExceededException  
   * @throws WriteException  
   */  
//  private void insertImgsheet(WritableWorkbook wwb, Vector vecImg)   
//          throws RowsExceededException, WriteException   
//  {   
//      // 插入图像   
//      WritableSheet imgSheet;   
//      if((wwb.getSheets()).length < 2)   
//      {   
//          imgSheet = wwb.createSheet("图表", 1);   
//      } else  
//      {   
//          imgSheet = wwb.getSheet(1);   
//      }   
//         
//      for (int i = 0; i < vecImg.size(); i++)   
//      {   
//          ChartImg chart = (ChartImg) vecImg.get(i);   
//          // 插入图像标题   
//          Label lbl = new Label(0, 2 + 20 * i, chart.getImgTitle());   
//          WritableFont font = new WritableFont(WritableFont.ARIAL,   
//                  WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false,   
//                  UnderlineStyle.NO_UNDERLINE, Colour.DARK_BLUE2);   
//          WritableCellFormat background = new WritableCellFormat(font);   
//          background.setWrap(true);   
//          background.setBackground(Colour.GRAY_25);   
//          imgSheet.mergeCells(0, 2 + 20 * i, 9, 2 + 20 * i);   
//          lbl.setCellFormat(background);   
//          imgSheet.addCell(lbl);   
//          // 插入图像单元格   
//          insertImgCell(imgSheet, 2, 4 + 20 * i, 8, 15, chart.getImgName());   
//      }   
//  }   

  /**  
   * 插入图像到单元格（图像格式只支持png）  
   * @param dataSheet WritableSheet : 工作表  
   * @param col int : 列  
   * @param row int : 行  
   * @param width int : 宽  
   * @param height int : 高  
   * @param imgName String : 图像的全路径  
   * @throws RowsExceededException  
   * @throws WriteException  
   */  
  private void insertImgCell(WritableSheet dataSheet, int col, int row, int width,   
          int height, String imgName) throws RowsExceededException, WriteException   
  {   
      File imgFile = new File(imgName);   
      WritableImage img = new WritableImage(col, row, width, height, imgFile);   
      dataSheet.addImage(img);   
  }   
  
  
  /**
   * 写入参数对象
   */
  private void fillParameters(WritableSheet wSheet) {
    List parameters = new ArrayList();
    List parameterDto = new ArrayList();
    for (int i = 0; i < parameters.size(); i++) {
      Cell cell = (Cell) parameters.get(i);
      String key = getKey(cell.getContents().trim());
      String type = getType(cell.getContents().trim());
      try {
        if (type.equalsIgnoreCase("number")) {
//          jxl.NumberCell number = new jxl.NumberCell(cell.getColumn(), cell.getRow(), ""));
//          ((WritableCell) number).setCellFormat(cell.getCellFormat());
//          wSheet.addCell((WritableCell) number);
        } else {
          Label label = new Label(cell.getColumn(), cell.getRow(), "value_ins");
          label.setCellFormat(cell.getCellFormat());
          wSheet.addCell(label);
        }
      } catch (Exception e) {
        //log.error(GlobalConstants.Exception_Head + "写入表格参数对象发生错误!");
        e.printStackTrace();
      }
    }
  }
  
  /**
   * 获取模板键名
   * 
   * @param pKey
   *            模板元标记
   * @return 键名
   */
  private static String getKey(String pKey) {
    String key = null;
    int index = pKey.indexOf(":");
    if (index == -1) {
      key = pKey.substring(3, pKey.length() - 1);
    } else {
      key = pKey.substring(3, index);
    }
    return key;
  }

  /**
   * 获取模板单元格标记数据类型
   * 
   * @param pType
   *            模板元标记
   * @return 数据类型
   */
  private static String getType(String pType) {
    String type = "label";
    if (pType.indexOf(":n") != -1 || pType.indexOf(":N") != -1) {
      type = "number";
    }
    return type;
  }
  
}
