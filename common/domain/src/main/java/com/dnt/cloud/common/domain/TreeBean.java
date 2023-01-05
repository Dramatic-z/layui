package com.dnt.cloud.common.domain;

import java.util.List;

public class TreeBean {
	/** 节点id */
	private String id;
	/** 节点业务代码 */
	private String idcode;
	/** 显示文本信息 */
	private String text;
	/** 超链接 */
	private String href;
	/** action */
	private String actionUrl;
	/** 对应js名称 */
	private String[] jsArray;
	/** 描述 */
	private String description;
	/** 超链接打开方式 */
	private String hrefTarget;
	/** 图标 */
	private String icon;
	/** 图标样式 */
	private String iconCls;
	/** 是拖曳的目标？ */
	private boolean isTarget;
	/** 提示 */
	private String qtip;
	/** 提示样式 */
	private String qtipCfg;
	/** 单击打开 */
	private boolean singleClickExpand;
	/** Function 默认Ext.tree.TreeNodeUI,如果想自己提供ui可以自已再继承Ext.tree.TreeNodeUI */
	private String uiProvider;
	/** 定义该节点的class(显示的样式 */
	private String cls;
	/** true或者false,定义该节点是否是叶子节点 false表示还有子节点 true表示没有子节点 */
	private boolean leaf;
	/** 是否允许有子节点 */
	private boolean allowChildren;
	/** 是否有子节点 true 有 */
	private boolean inChildren;
	/** 是否允许拖动 */
	private boolean allowDrag;
	/** 是否允许落下 */
	private boolean allowDrop;
	/** */
	private boolean disabled;
	private boolean draggable;
	private boolean expandable;
	private boolean expanded;
	/** 过滤的json格式 '{a:'1',b:'2'}' */
	private String filterObj;
	/** 过滤的json格式 '{a:'1',b:'2'}' */
	private String filter;
	/** 过滤的json格式 '{a:'1',b:'2'}' */
	private String filterValue;
	/** 过滤的json格式 '{a:'1',b:'2'}' */
	private String parent;
	/** 其它信息,以一定的规律组成方在这个字段里,页面去解析 */
	private String remark;
	/** 子节点的属性 */
	private List<TreeBean> children;

	public boolean isInChildren() {
		return inChildren;
	}

	public void setInChildren(boolean inChildren) {
		this.inChildren = inChildren;
	}

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public boolean isAllowChildren() {
		return allowChildren;
	}

	public void setAllowChildren(boolean allowChildren) {
		this.allowChildren = allowChildren;
	}

	public boolean isAllowDrag() {
		return allowDrag;
	}

	public void setAllowDrag(boolean allowDrag) {
		this.allowDrag = allowDrag;
	}

	public boolean isAllowDrop() {
		return allowDrop;
	}

	public void setAllowDrop(boolean allowDrop) {
		this.allowDrop = allowDrop;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isDraggable() {
		return draggable;
	}

	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}

	public boolean isExpandable() {
		return expandable;
	}

	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isTarget() {
		return isTarget;
	}

	public void setTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}

	public boolean isLeaf() {
		if (null == children || children.isEmpty()) {
			leaf = true;
		} else {
			leaf = false;
		}
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getQtip() {
		return qtip;
	}

	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	public String getQtipCfg() {
		return qtipCfg;
	}

	public void setQtipCfg(String qtipCfg) {
		this.qtipCfg = qtipCfg;
	}

	public boolean isSingleClickExpand() {
		return singleClickExpand;
	}

	public void setSingleClickExpand(boolean singleClickExpand) {
		this.singleClickExpand = singleClickExpand;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUiProvider() {
		return uiProvider;
	}

	public void setUiProvider(String uiProvider) {
		this.uiProvider = uiProvider;
	}

	public List<TreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<TreeBean> children) {
		this.children = children;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getJsArray() {
		return jsArray;
	}

	public void setJsArray(String[] jsArray) {
		this.jsArray = jsArray;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFilterObj() {
		return filterObj;
	}

	public void setFilterObj(String filterObj) {
		this.filterObj = filterObj;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
}
