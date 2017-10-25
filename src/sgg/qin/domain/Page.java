package sgg.qin.domain;




import java.io.Serializable;
import java.util.List;

import sgg.qin.util.PageData;
import sgg.qin.util.Tools;

/**
 * 
 * @Description: 分页工具类类
 * @author: Qin YunFei
 * @date: 2017年10月13日 下午6:48:20
 * @version V1.0
 */
public class Page implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5915422823966158197L;
	private int showCount=10; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int currentPage;	//当前页
	private int currentResult;	//当前记录起始索引
	private boolean isPage=false; //是否开启分页 默认不开启
	private PageData pd = new PageData(); //分页参数
	private List<?> retlist;  //分页返回数据

	

	@Override
	public String toString() {
		return "Page [showCount=" + showCount + ", totalPage=" + totalPage + ", totalResult=" + totalResult
				+ ", currentPage=" + currentPage + ", currentResult=" + currentResult + ", pd=" + pd + "]";
	}

	public Page(){
			this.showCount = 10;
	
	}
	
	public int getTotalPage() {
		if(totalResult%showCount==0)
			totalPage = totalResult/showCount;
		else
			totalPage = totalResult/showCount+1;
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalResult() {
		return totalResult;
	}
	
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	
	public int getCurrentPage() {
		if(currentPage<=0)
			currentPage = 1;
		if(currentPage>getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

	
	public int getShowCount() {
		return showCount;
	}
	
	public void setShowCount(int showCount) {
		
		this.showCount = showCount;
	}
	
	public int getCurrentResult() {
		currentResult = (getCurrentPage()-1)*getShowCount();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}
	
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	

	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
		if (pd.get("currentPage")!=null) {
			this.currentPage=(int) pd.get("currentPage");
			this.isPage=true;
		}
		if (pd.get("showCount")!=null) {
			this.showCount=(int) pd.get("showCount");
			this.isPage=true;
		}
	}
	
	public List<?> getRetlist() {
		return retlist;
	}

	public void setRetlist(List<?> retlist) {
		this.retlist = retlist;
	}

	public boolean isPage() {
		return isPage;
	}

	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPage;
		result = prime * result + currentResult;
		result = prime * result + (isPage ? 1231 : 1237);
		result = prime * result + ((pd == null) ? 0 : pd.hashCode());
		result = prime * result + ((retlist == null) ? 0 : retlist.hashCode());
		result = prime * result + showCount;
		result = prime * result + totalPage;
		result = prime * result + totalResult;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (currentPage != other.currentPage)
			return false;
		if (currentResult != other.currentResult)
			return false;
		if (isPage != other.isPage)
			return false;
		if (pd == null) {
			if (other.pd != null)
				return false;
		} else if (!pd.equals(other.pd))
			return false;
		if (retlist == null) {
			if (other.retlist != null)
				return false;
		} else if (!retlist.equals(other.retlist))
			return false;
		if (showCount != other.showCount)
			return false;
		if (totalPage != other.totalPage)
			return false;
		if (totalResult != other.totalResult)
			return false;
		return true;
	}
	
	
	
}
