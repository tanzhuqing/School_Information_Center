package cn.heu.hmps.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * @author MaRui
 * @E-mail:marui-2007@163.com
 * @qq:552013500
 * @version ����ʱ�䣺2011-1-17 ����07:16:01
 * ��˵��:��ݿ������
 */
public class NewPublicDao 
{
	Connection 	con = null;
	Statement 	stmt = null;
	PreparedStatement pstmt=null;
	/**
	 * ʵ��Dao
	 */
	public NewPublicDao(){
		
	}
	/**
	 * ��������
	 * @param String sql  as tiao
	 * @return String[] 
	 */
	public String[] getStrings(String sql){
		int shu = 0;
		ResultSet rstiao=this.query(sql);
		try {
			while(rstiao.next()){
				shu++;
			}
		} 
		catch (SQLException e1) {
			e1.getMessage();
		}
		ResultSet rs=this.query(sql);
		String[] shus=new String[2];
		try {
			String[] emails=new String[shu];
			int ge=0;
			while(rs.next()){
				emails[ge]=rs.getString("tiao");
				ge++;
			}
			shus=emails;
		} 
		catch (SQLException e) {
			e.getMessage();
		}
		finally{
			try {
				rs.close();
				rstiao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shus;
	}
	/**
	 * ��ѯ ����getList
	 */
	public ArrayList getList(String sql)
	{
		ArrayList<HashMap<String, String>> list=new ArrayList<HashMap<String, String>>();
		ResultSet rs=this.query(sql);
		try {
			ResultSetMetaData a = rs.getMetaData();
			int shu = a.getColumnCount();
			while(rs.next()){
				HashMap<String, String> m=new HashMap<String, String>();
				for(int i=0;i<shu;i++){
					String ColumnName = a.getColumnName(i+1);
					int ColumnType = a.getColumnType(i+1);
					if(ColumnType==4){
						m.put(""+ColumnName+"",""+rs.getInt(""+ColumnName+"") );	
					}else{
						m.put(""+ColumnName+"",rs.getString(""+ColumnName+"") );
					}
				}
				list.add(m);
				//m.clear();
				m=null;//���MAP
			}
		} 
		catch (SQLException e) {
			e.getMessage();
		}
		finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	/**
	 *ִ��SQL
	 *update delete 
	 */
	public int excute_sql(String sql){
		int i=0;
		i= this.update(sql);
		return i;
	}
	/**
	 * �����ݱ��ֶ�
	 * @param sql
	 * @return
	 */
	public String[] getColumn(String sql){
		
		ResultSet rs=this.query(sql);
		ResultSetMetaData a;
		try {
			a = rs.getMetaData();
			int shu = a.getColumnCount();
			//�ֶ��� �ֶ���
			String[] zdname=new String[shu];
			String[] zdshu=new String[shu];
			for(int i=0;i<shu;i++){
				String ColumnName = a.getColumnName(i+1);
				zdname[i]=ColumnName;
			}
			return zdname;
		} catch (SQLException e) {
			System.out.println("����ֶ�����~~");
			return null;
		}
		
	}
	/**
	 * ����MAP
	 * HashMap<String, String>
	 */
	public HashMap<String, String> getMap_new(String sql)
	{
		HashMap<String, String> m=new HashMap<String, String>();
		ResultSet rs=this.query(sql);
		try {
			ResultSetMetaData a = rs.getMetaData();
			int shu = a.getColumnCount();
			while(rs.next()){
				for(int i=0;i<shu;i++){
					String ColumnName = a.getColumnName(i+1);
					int ColumnType = a.getColumnType(i+1);
					if(ColumnType==4){
						m.put(""+ColumnName+"",""+rs.getInt(""+ColumnName+"") );	
					}else{
						m.put(""+ColumnName+"",rs.getString(""+ColumnName+"") );
					}
				}
			}
		} 
		catch (SQLException e) {
			e.getMessage();
		} 
		finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	/**
	 * ͳ������
	 * @param sql as tiao
	 * @return int
	 */
	public int getCount(String sql) {
		int tiao=0;
		ResultSet rs = this.query(sql);
		try {
			while(rs.next()){
				tiao=rs.getInt("tiao");
			}
		} catch (SQLException e) {
			System.out.println("getCount����ʱ���:"+e.getMessage());
		} 
		finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tiao;
	}
	/**=====================================================*/
	/**======================��ݿ����=======================*/
	/**=====================================================*/
	/**
	* ��b��ݿ�t��
	*/
	public Connection getConnection() {
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/heumobile?user=root&password=hrbeu@)!@&useUnicode=true&characterEncoding=UTF8");
        }
        catch(Exception e){
        	System.out.println(e.getMessage());
        	System.out.println("����l��connection���");
        }
        return con;        
    } 
	
	
    /**
     * �����
     */
	public ResultSet query(String sql){
		ResultSet rs=null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("ִ��queryʱ����SQL��䣺"+sql);
		}
		return rs;
	}
	/**
	 * �·���prepareStatement
	 * @param sql
	 * @return
	 */
	public PreparedStatement prepareStatement(String sql){

		try {
			 pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("ִ��Ԥ����preparedStatement��?"+e.getMessage());
		}
		return pstmt;
	}
	
    public int executePreUpdate() {
        int rowcount = 0;
        if (con == null)
            return 0;
        try {
            rowcount = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ִ��executePreUpdateʱ����:---"  + e.getMessage());
        }
        return rowcount;
    }
	
	
	
	/**
	 * �޸ķ���
	 * @param sql
	 * @return -1 and tiao
	 */
	public int update(String sql){
		int tiao=0;
		try {
			stmt = con.createStatement();
			tiao = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Statementִ��Updateʱ����"+e.getMessage());
		} 
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return tiao;
	}
	/**
	 * ��bt��
	 */
	public void creatDBconn(){
		con = getConnection();
	}
	/**
	* �ͷ���ݿ�l��
	* @throws SQLException
	*/
	public void closeDBConn(){
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("�ر�Conectionʱ��?"+e.getMessage());
		}
	}
}
