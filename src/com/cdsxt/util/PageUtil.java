package com.cdsxt.util;

public class PageUtil {
	/**
	 * 
	 * @param count    总条数
	 * @param curPage  当前页
	 */
	public PageUtil(int count,int curPage){
		this.count=count;
		this.curPage=curPage;
		this.navCount=count%this.pageRow>0?count/this.pageRow+1:count/this.pageRow;
		this.lastPage=this.navCount;
		this.startRow=(curPage-1)*10;
		this.prevPage=curPage-1>0?curPage-1:this.firstPage;
		this.nextPage=curPage+1>this.lastPage?this.lastPage:curPage+1;
		if(this.navCount<10){
			this.startNav=this.firstPage;
			this.endNav=this.lastPage;
		}else{
			if(curPage<=6){
				this.startNav=this.firstPage;
				this.endNav=10;
			}else if(curPage>=this.navCount-4){
				this.startNav=this.lastPage-9;
				this.endNav=this.lastPage;
			}else{
				this.startNav=curPage-5;
				this.endNav=curPage+4;
			}
		}
	}
	
	//总条数
	private int count;
	//当前页
	private int curPage;
	//每页显示条数
	private int pageRow=10;
	//总导航数
	private int navCount;
	//首页
	private int firstPage=1;
	//尾页
	private int lastPage;
	//起始行
	private int startRow;
	//前一页
	private int prevPage;
	//后一页
	private int nextPage;
	//起始导航
	private int startNav;
	//结束导航
	private int endNav;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageRow() {
		return pageRow;
	}
	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}
	public int getNavCount() {
		return navCount;
	}
	public void setNavCount(int navCount) {
		this.navCount = navCount;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getStartNav() {
		return startNav;
	}
	public void setStartNav(int startNav) {
		this.startNav = startNav;
	}
	public int getEndNav() {
		return endNav;
	}
	public void setEndNav(int endNav) {
		this.endNav = endNav;
	}
	
}
