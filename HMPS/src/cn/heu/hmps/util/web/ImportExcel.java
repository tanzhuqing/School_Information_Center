package cn.heu.hmps.util.web;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.*;

/**
 * 本工具类用于获取指定路径Excel文件数据，通过调用upload方法上传至服务器端，
 * 通过调用getData方法返会string[][]类型数据
 * @author yyb
 */
public class ImportExcel {
	/**
	 * 
	 * @param file 本地文件
	 * @param root 上传服务器路径
	 * @param tablename 更新的表名
	 * @return boolean  上传是否成功
	 */
	public static boolean upload(File file,String root,String tablename){
        InputStream is =null;
        OutputStream os = null;
        File  destFile = null;
        try{
        is = new FileInputStream(file);
        destFile = new File(root, tablename+".xls");
        os = new FileOutputStream(destFile);
        int len = 0;
        byte []buffer = new byte[500];
        while (-1 != (len = is.read(buffer))){
            os.write(buffer, 0, len);
        }
        return true;
        }catch(IOException e){
        	e.printStackTrace();
        	return false;
        }finally{
        try {
			is.close();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        }
	}
	/**
	 * 
	 * @param root  文件在服务器的地址
 	 * @param tablename 对应表名（即文件名）
	 * @return Object[][] 二维数组形式存储Excel对应表格数据
	 */
	public static String[][] getData(String root,String tablename){
		Workbook rwb = null;
//    	String[][] s=null;
		String[][] o = null;
		try {
			// 构建Workbook对象 只读Workbook对象
			// 直接从本地文件创建Workbook
			// 从输入流创建Workbook
			InputStream is = new FileInputStream(root+"\\"+tablename+".xls");
			rwb = Workbook.getWorkbook(is);
			// Sheet(术语：工作表)就是Excel表格左下角的Sheet1,Sheet2,Sheet3但在程序中
			// Sheet的下标是从0开始的
			// 获取第一张Sheet表
			Sheet rs = rwb.getSheet(0);
			// 获取Sheet表中所包含的总列数
			int rsColumns = rs.getColumns();
			// 获取Sheet表中所包含的总行数
			int rsRows = rs.getRows();
			// 获取指这下单元格的对象引用
//			s = new String[rsRows][rsColumns] ;
			o = new String[rsRows][rsColumns];
			for (int i = 0; i < rsRows; i++) {
				for (int j = 0; j < rsColumns; j++) {
					Cell cell = rs.getCell(j, i);
//					s[i][j]=cell.getContents();
					try{
					o[i][j] = cell.getContents();
					}catch(Error e){
						System.out.println("chucuo");
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 操作完成时，关闭对象，翻译占用的内存空间
			rwb.close();
		}
		return o;
	}
	/**
	 *
	 * @param root  文件在服务器的地址
	 * @param tablename  对应表名（即文件名）
	 * @param linenum    所要读取的列数
	 * @return String[]一维数组形式存储Excel对应表格内指定列的数据
	 */
	public static String[] getData(String root,String tablename,int linenum){
		Workbook rwb = null;
//    	String[][] s=null;
		String[] o = null;
		try {
			// 构建Workbook对象 只读Workbook对象
			// 直接从本地文件创建Workbook
			// 从输入流创建Workbook
			InputStream is = new FileInputStream(root+"\\"+tablename+".xls");
			rwb = Workbook.getWorkbook(is);
			// Sheet(术语：工作表)就是Excel表格左下角的Sheet1,Sheet2,Sheet3但在程序中
			// Sheet的下标是从0开始的
			// 获取第一张Sheet表
			Sheet rs = rwb.getSheet(0);
			// 获取Sheet表中所包含的总列数
			int rsColumns = rs.getColumns();
			// 获取Sheet表中所包含的总行数
			int rsRows = rs.getRows();
			// 获取指这下单元格的对象引用
//			s = new String[rsRows][rsColumns] ;
			o = new String[rsRows];
			for (int i = 0; i < rsRows; i++) {
					Cell cell = rs.getCell(linenum, i);
					o[i] = cell.getContents();
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 操作完成时，关闭对象，翻译占用的内存空间
			rwb.close();
		}
		return o;
	}
}
