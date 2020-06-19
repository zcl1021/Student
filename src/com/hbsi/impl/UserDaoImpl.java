package com.hbsi.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hbsi.bean.DoPage;
import com.hbsi.bean.User;
import com.hbsi.dao.UserDao;
import com.hbsi.db.ConnectionFactory;
import com.hbsi.db.DBClose;

public class UserDaoImpl implements UserDao {
	Connection conn =null;
	PreparedStatement pstat=null;
	ResultSet rs = null;

	//添加用户
	public User addUser(User user) {
		User u=new User();//创建User对象，初始化属性值
		conn=ConnectionFactory.getConnection();
		String sql="insert into user(username,password,usertypes,del_status,ctime) values(?,?,?,?,?)";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPassword());
			pstat.setString(3, user.getUsertypes());
			pstat.setString(4, "2");
			pstat.setString(5, user.getCtime());
			int i = pstat.executeUpdate();
			u=this.lookUser(user);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstat, conn);
		}
		return u;
	}

	//查询用户信息
	public User lookUser(User user) {
		//获取和数据库的连接
		conn=ConnectionFactory.getConnection();
		//定义一个sql语句
		String sql="select * from user where username=? and password=? and usertypes=?";
		try {
			//创建预编译对象
			pstat=conn.prepareStatement(sql);
			//为sql语句中的3 个？赋值
			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPassword());
			pstat.setString(3, user.getUsertypes());
			//执行查询，返回结果集
			rs=pstat.executeQuery();
			//处理结果集
			if(rs.next()) {
				//把结果集中字段名为id的字段值取出，作为参数设置为user对象的属性id的值
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUsertypes(rs.getString("usertypes"));
				user.setDel_status(rs.getString("del_status"));
					}else {
						user.setUsertypes("error");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBClose.close(rs,pstat,conn);
				}
				
				return user;
	}
	//获取总记录数
	public int doCount(DoPage dopage) {
		//定义一个整数count代表总的记录数，初始化值为0；
		int count =0;
		//获取和数据库的连接
		conn = ConnectionFactory.getConnection();
		//定义sql语句,dopage.getsql()是查询的条件where子句
		String sql ="select count(*) from user "+dopage.getSql();
		try {
			pstat=conn.prepareStatement(sql);//创建预编译对象
			rs=pstat.executeQuery();//执行查询，返回结果集
			if(rs.next()) {//如果结果集不为空
				count= rs.getInt(1);//把结果集中获取到的数据取出，赋值给变量count
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstat,conn);//释放资源
		}
		return count;
	}

	public int doTotalPage(DoPage dopage) {
		//定义变量totalPage表示总页数,初始值为0
		int totalPage=0;
		//定义变量m保存总记录数除以每页显示记录数的商
		int m = this.doCount(dopage)/dopage.getPageSize();
		//记录总记录数除以每页显示记录数的余数
		if(this.doCount(dopage)%dopage.getPageSize()>0) {
			totalPage=m+1;//总页数等于总记录数除以每页显示记录数的商加1
		}else {
			totalPage=m;
		}
		return totalPage;
	}

	public DoPage doFindAll(DoPage dopage) {
		//定义一个List对象，用来保存查询到的每一条记录封装成的user对象
		List list = new ArrayList();//定义列表对象初始化为空
		//获取数据库的连接
		conn=ConnectionFactory.getConnection();
		//定义sql语句
		String sql="select * from user "+dopage.getSql()+" limit "
				+(dopage.getNowPage()-1)*dopage.getPageSize()+","+dopage.getPageSize();
		try {
			//创建预编译对象
			pstat=conn.prepareStatement(sql);
			//执行查询返回结果集
			rs=pstat.executeQuery();
			
			//处理结果集
			while(rs.next()) {
				//定义一个user对象，属性初始化为默认
				User user = new User();
				//查询得到记录中id字段的值
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUsertypes(rs.getString("usertypes"));
				user.setDel_status(rs.getString("del_status"));
				user.setCtime(rs.getString("ctime"));
				//把封装好的user对象添加到列表对象中
				list.add(user);
			}
			//把列表对象设置为dopage的属性
			dopage.setList(list);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstat,conn);//释放资源
		}
		return dopage;
	}

	//软删除用户
	public boolean deleteUser(int id) {
		boolean flag = false;
		conn=ConnectionFactory.getConnection();
		String sql="update user set del_status=1 where id="+id;
		try {
			pstat=conn.prepareStatement(sql);
			int i=pstat.executeUpdate();
			if(i>0) {
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	//找回软删除的用户
	public boolean retrieveUser(int id) {
		boolean flag = false;
		conn=ConnectionFactory.getConnection();
		String sql="update user set del_status=2 where id="+id;
		try {
			pstat=conn.prepareStatement(sql);
			int i=pstat.executeUpdate();
			if(i>0) {
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	//修改密码
	public boolean updatePwd(User user) {
		boolean flag=false;
		//获取和数据库的连接
		conn=ConnectionFactory.getConnection();
		try {
			//创建预编译对象
			pstat=conn.prepareStatement("update user set password=? where id=?");
		    //用参数user对象的password属性为第一个？赋值
			pstat.setString(1, user.getPassword());
			//为第二个问号赋值
		    pstat.setInt(2, user.getId());
			//执行修改密码操作
			int i = pstat.executeUpdate();
			if(i>0) {//i>0说明受影响行数大于0
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstat, conn);
		}
		return flag;
	}

}
