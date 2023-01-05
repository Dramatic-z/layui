package com.dnt.cloud.common.domain.result;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PageData<E> {

	/** 结果集 */
	List<E> list;

	/** 总条数 */
	private long total;

	/** 当前页 */
	private int pageNum;

	/** 每页的数量 */
	private int pageSize;

	public PageData() {
	}

	public PageData(List<E> list) {
		this.list = list;
	}

	public PageData(List<E> list, Long total, Integer pageNum, Integer pageSize) {
		this.list = list;
		this.total = total;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public static <E> PageData<E> emptyPageData(Class<E> clazz) {
		return new PageData<E>(Collections.emptyList());
	}

	public List<E> getList() {
		return list == null ? Collections.emptyList() : list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Pagination<E> toPagination() {
		return new Pagination<>(this);
	}

	/**
	 * copy from com.github.pagehelper.PageInfo
	 */
	public static class Pagination<E> implements Serializable {

		private static final long serialVersionUID = 1L;

		// 结果集
		private List<E> list;
		// 总记录数
		private long total;
		// 当前页
		private int pageNum;
		// 每页的数量
		private int pageSize;

		// 可以在页面中"显示startRow到endRow 共size条数据"
		// 当前页的数量
		private int size;
		// 当前页面第一个元素在数据库中的行号
		private int startRow;
		// 当前页面最后一个元素在数据库中的行号
		private int endRow;

		// 总页数
		private int pages;

		// 第一页
		private int firstPage;
		// 前一页
		private int prePage;
		// 下一页
		private int nextPage;
		// 最后一页
		private int lastPage;

		// 是否为第一页
		private boolean isFirstPage = false;
		// 是否为最后一页
		private boolean isLastPage = false;
		// 是否有前一页
		private boolean hasPreviousPage = false;
		// 是否有下一页
		private boolean hasNextPage = false;
		// 导航页码数
		private int navigatePages;
		// 所有导航页号
		private int[] navigatepageNums;

		public Pagination(PageData<E> pageData) {
			this(pageData, 8);
		}

		public Pagination(PageData<E> pageData, int navigatePages) {
			this.list = pageData.getList();
			this.pageNum = pageData.getPageNum();
			this.pageSize = pageData.getPageSize();
			this.total = pageData.getTotal();
			this.navigatePages = navigatePages;

			// 计算起止行号
			calculateStartAndEndRow();
			// 计算当前页
			calculatePages();
			// 计算导航页
			calcNavigatepageNums();
			// 计算前后页，第一页，最后一页
			calcPage();
			// 判断页面边界
			judgePageBoudary();
		}

		/**
		 * 
		 */
		private void calculatePages() {
			if (total == -1) {
				pages = 1;
				return;
			}
			if (pageSize > 0) {
				pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
			} else {
				pages = 0;
			}
			if (pageNum > pages) {
				pageNum = pages;
				calculateStartAndEndRow();
			}
		}

		/**
		 * 计算起止行号
		 */
		private void calculateStartAndEndRow() {
			this.size = this.list == null ? 0 : this.list.size();
			this.startRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
			this.endRow = this.startRow + this.pageSize * (this.pageNum > 0 ? 1 : 0);

			if (this.size == 0) {
				this.startRow = 0;
				this.endRow = 0;
			} else {
				this.startRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize + 1 : 1;
				// 计算实际的endRow（最后一页的时候特殊）
				this.endRow = this.startRow - 1 + this.size;
			}
		}

		/**
		 * 计算导航页
		 */
		private void calcNavigatepageNums() {
			// 当总页数小于或等于导航页码数时
			if (pages <= navigatePages) {
				navigatepageNums = new int[pages];
				for (int i = 0; i < pages; i++) {
					navigatepageNums[i] = i + 1;
				}
			} else { // 当总页数大于导航页码数时
				navigatepageNums = new int[navigatePages];
				int startNum = pageNum - navigatePages / 2;
				int endNum = pageNum + navigatePages / 2;

				if (startNum < 1) {
					startNum = 1;
					// (最前navigatePages页
					for (int i = 0; i < navigatePages; i++) {
						navigatepageNums[i] = startNum++;
					}
				} else if (endNum > pages) {
					endNum = pages;
					// 最后navigatePages页
					for (int i = navigatePages - 1; i >= 0; i--) {
						navigatepageNums[i] = endNum--;
					}
				} else {
					// 所有中间页
					for (int i = 0; i < navigatePages; i++) {
						navigatepageNums[i] = startNum++;
					}
				}
			}
		}

		/**
		 * 计算前后页，第一页，最后一页
		 */
		private void calcPage() {
			if (navigatepageNums != null && navigatepageNums.length > 0) {
				firstPage = navigatepageNums[0];
				lastPage = navigatepageNums[navigatepageNums.length - 1];
				if (pageNum > 1) {
					prePage = pageNum - 1;
				}
				if (pageNum < pages) {
					nextPage = pageNum + 1;
				}
			}
		}

		/**
		 * 判定页面边界
		 */
		private void judgePageBoudary() {
			isFirstPage = pageNum == 1;
			isLastPage = pageNum == pages;
			hasPreviousPage = pageNum > 1;
			hasNextPage = pageNum < pages;
		}

		public int getPageNum() {
			return pageNum;
		}

		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getStartRow() {
			return startRow;
		}

		public void setStartRow(int startRow) {
			this.startRow = startRow;
		}

		public int getEndRow() {
			return endRow;
		}

		public void setEndRow(int endRow) {
			this.endRow = endRow;
		}

		public long getTotal() {
			return total;
		}

		public void setTotal(long total) {
			this.total = total;
		}

		public int getPages() {
			return pages;
		}

		public void setPages(int pages) {
			this.pages = pages;
		}

		public List<E> getList() {
			return list;
		}

		public void setList(List<E> list) {
			this.list = list;
		}

		public int getFirstPage() {
			return firstPage;
		}

		public void setFirstPage(int firstPage) {
			this.firstPage = firstPage;
		}

		public int getPrePage() {
			return prePage;
		}

		public void setPrePage(int prePage) {
			this.prePage = prePage;
		}

		public int getNextPage() {
			return nextPage;
		}

		public void setNextPage(int nextPage) {
			this.nextPage = nextPage;
		}

		public int getLastPage() {
			return lastPage;
		}

		public void setLastPage(int lastPage) {
			this.lastPage = lastPage;
		}

		public boolean isIsFirstPage() {
			return isFirstPage;
		}

		public void setIsFirstPage(boolean isFirstPage) {
			this.isFirstPage = isFirstPage;
		}

		public boolean isIsLastPage() {
			return isLastPage;
		}

		public void setIsLastPage(boolean isLastPage) {
			this.isLastPage = isLastPage;
		}

		public boolean isHasPreviousPage() {
			return hasPreviousPage;
		}

		public void setHasPreviousPage(boolean hasPreviousPage) {
			this.hasPreviousPage = hasPreviousPage;
		}

		public boolean isHasNextPage() {
			return hasNextPage;
		}

		public void setHasNextPage(boolean hasNextPage) {
			this.hasNextPage = hasNextPage;
		}

		public int getNavigatePages() {
			return navigatePages;
		}

		public void setNavigatePages(int navigatePages) {
			this.navigatePages = navigatePages;
		}

		public int[] getNavigatepageNums() {
			return navigatepageNums;
		}

		public void setNavigatepageNums(int[] navigatepageNums) {
			this.navigatepageNums = navigatepageNums;
		}

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String toShortString() {
		return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.setExcludeFieldNames(new String[] { "list" }).append("list", "[...]").toString();
	}
}