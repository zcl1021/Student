package com.hbsi.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hbsi.bean.DoPage;
import com.hbsi.bean.Student;
import com.hbsi.bean.User;
import com.hbsi.dao.StuDao;
import com.hbsi.db.ConnectionFactory;
import com.hbsi.db.DBClose;

public class StudentDaoImpl implements StuDao {
	Connection conn =null;
	PreparedStatement pstat=null;
	ResultSet rs = null;
	//添加学生信息
	public boolean addStu(Student student) {
		boolean flag =false;
		conn=ConnectionFactory.getConnection();
		String sql="insert into student(id,sid,sname,sex,age,address,phone) values(?,?,?,?,?,?,?)";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1,student.getId());
			pstat.setInt(2, student.getSid());
			pstat.setString(3, student.getSname());
			pstat.setString(4, student.getSex());
			pstat.setString(5, student.getAge());
			pstat.setString(6, student.getAddress());
			pstat.setString(7, student.getPhone());
			int i=pstat.executeUpdate();
			if(i>0) {//说明有用户添加成功
				flag=true;//如果添加数据成功，把布尔值设为true
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstat, conn);
		}
		return flag;
	}
	//查看学生姓名
	public Student lookStudentName(Student student) {
		conn=ConnectionFactory.getConnection();
		String sql = "select sname from student where id=?";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, student.getId());
			rs=pstat.executeQuery();
			if(rs.next()) {
				student.setSname(rs.getString("sname"));
					}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstat,conn);
		}
		return student;
	}
	@Override
	public Student lookStu(Student student) {
		conn=ConnectionFactory.getConnection();
		String sql = "select * from student where id=?";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, student.getId());
			rs=pstat.executeQuery();
			if(rs.next()) {
				student.setSid(rs.getInt("sid"));
				student.setSname(rs.getString("sname"));
				student.setSex(rs.getString("sex"));
				student.setAge(rs.getString("age"));
				student.setAddress(rs.getString("address"));
				student.setPhone(rs.getString("phone"));
					}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstat,conn);
		}
		return student;
	}
	@Override
	public int doCount(DoPage dopage) {
		//定义一个整数count代表总的记录数，初始化值为0；
				int count =0;
				//获取和数据库的连接
				conn = ConnectionFactory.getConnection();
				//定义sql语句,dopage.getsql()是查询的条件where子句
				String sql ="select count(*) from student "+dopage.getSql();
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
	@Override
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
	@Override
	public DoPage doFindAll(DoPage dopage) {
		//定义一个List对象，用来保存查询到的每一条记录封装成的user对象
				List list = new ArrayList();//定义列表对象初始化为空
				//获取数据库的连接
				conn=ConnectionFactory.getConnection();
				//定义sql语句
				String sql="select * from student "+dopage.getSql()+" limit "
						+(dopage.getNowPage()-1)*dopage.getPageSize()+","+dopage.getPageSize();
				try {
					//创建预编译对象
					pstat=conn.prepareStatement(sql);
					//执行查询返回结果集
					rs=pstat.executeQuery();
					
					//处理结果集
					while(rs.next()) {
						//定义一个studnet对象，属性初始化为默认
						Student student = new Student();
						//查询得到记录中字段的值
						 student.setId(rs.getInt("id"));
                         student.setSid(rs.getInt("sid"));
                         student.setSname(rs.getString("sname"));
                         student.setSex(rs.getString("sex"));
                         student.setAge(rs.getString("age"));
                         student.setAddress(rs.getString("address"));
                         student.setPhone(rs.getString("phone"));
						//把封装好的user对象添加到列表对象中
						list.add(student);
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
	//修改学生信息
	public boolean updateStu(Student student) {
		boolean flag = false;
		conn=ConnectionFactory.getConnection();
		try {
			pstat=conn.prepareStatement("update student set sname=?,sex=?,age=?,address=?,phone=? where id=? ");
			pstat.setString(1, student.getSname());
			pstat.setString(2, student.getSex());
			pstat.setString(3, student.getAge());
			pstat.setString(4, student.getAddress());
			pstat.setString(5, student.getPhone());
			pstat.setInt(6, student.getId());
			
			int i = pstat.executeUpdate();
			if(i>0) {
				flag=true;
			}else {
				System.out.print(student.getId());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstat, conn);
		}
		return flag;
	}
	//删除学生信息
	public boolean deleteStu(int id) {
		boolean flag = false;
		conn=ConnectionFactory.getConnection();
		String sql="delete from student where id ="+id;
		try {
			pstat=conn.prepareStatement(sql);
			int i=pstat.executeUpdate();
			if(i>0) {
				flag=true;
			}
			pstat = conn.prepareStatement("update user set del_status=1 where id="+id);
			pstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstat, conn);
		}
		
		return flag;
	}

}
