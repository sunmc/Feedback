package com.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.TZBGData;

public class PDMMapper {

	public List<TZBGData> selectByWth(String wth){
		List<TZBGData> res = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");//加入oracle的驱动，“”里面是驱动的路径
			String url = "jdbc:oracle:thin:@172.16.77.139:1521:rktceng";// 数据库连接，oracle代表链接的是oracle数据库；thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:）；1521代表链接数据库的端口号；ORCL代表的是数据库名称
			String UserName = "infodba";// 数据库用户登陆名 ( 也有说是 schema 名字的 )
			String Password = "infodba";// 密码
			conn = DriverManager.getConnection(url, UserName, Password);
			stmt = conn.createStatement();
			String sql = "select c.PHT_FILENO,c.PHT_ECNNO,d.Pdate_released,a.PHT_ITEMIDINFO,"
								+ "a.PHT_ITEMNAME,a.PHT_ITEMTH,a.PHT_ORIGINALNUM,a.PHT_NUM,c.PHT_APPLYER "
								+ "from prk_ecnform_new_class_detail a  "
								+ "left join pform b on a.ecnform_new_object_puid = b.puid "
								+ "left join prk_ecnform_new_class c on b.rdata_fileu = c.puid  "
								+ "left join Pworkspaceobject d on b.puid = d.puid "
								+ "where c.PHT_ISCOST = '2'  "
								+ "and a.pht_probno = '"+wth+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs != null){
				while(rs.next()){
					TZBGData data = new TZBGData();
					data.setPHT_FILENO(rs.getString("PHT_FILENO"));
					data.setPHT_ECNNO(rs.getString("PHT_ECNNO"));
					data.setPdate_released(rs.getString("Pdate_released"));
					data.setPHT_ITEMIDINFO(rs.getString("PHT_ITEMIDINFO"));
					data.setPHT_ITEMNAME(rs.getString("PHT_ITEMNAME"));
					data.setPHT_ITEMTH(rs.getString("PHT_ITEMTH"));
					data.setPHT_ORIGINALNUM(rs.getString("PHT_ORIGINALNUM"));
					data.setPHT_NUM(rs.getString("PHT_NUM"));
					data.setPHT_APPLYER(rs.getString("PHT_APPLYER"));
					res.add(data);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try{
				stmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return res;
	}
}
