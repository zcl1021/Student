package com.hbsi.bean;

import java.util.List;

/*
 * 定义Bean类封装分页数据
 */
public class DoPage {
     private int count;//总记录数
     private int pageSize;//每页显示多少条
     private int totalPage;//总页数
     private int nowPage;//当前是第几页
     private List list;//用来封装当前页要显示的记录
     private String sql;//高级查询时用到的子查询条件
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
     
}
