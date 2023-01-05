package com.dnt.cloud.cache.test.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UserBean {

	private String name;
	private int age;
	private Gender gender;
	private Date birthday;

	private UserBean spouse;

	private List<UserBean> children = new ArrayList<>();

	private Map<Integer, String> map = new HashMap<>();

	public UserBean() {

	}

	public UserBean(String name, int age, Gender gender, Date birthday) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public UserBean getSpouse() {
		return spouse;
	}

	public void setSpouse(UserBean spouse) {
		this.spouse = spouse;
	}

	public List<UserBean> getChildren() {
		return children;
	}

	public void setChildren(List<UserBean> children) {
		this.children = children;
	}

	public Map<Integer, String> getMap() {
		return map;
	}

	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}

	public static UserBean getInstance() {
		UserBean husband = new UserBean("张三", 35, Gender.MALE, new Date());

		UserBean wife = new UserBean("张三妻", 35, Gender.FEMAIL, new Date());
		husband.setSpouse(wife);

		UserBean son1 = new UserBean("张三大", 8, Gender.MALE, new Date());
		UserBean son2 = new UserBean("张三二", 5, Gender.MALE, new Date());
		UserBean daughter = new UserBean("张三三", 5, Gender.FEMAIL, new Date());
		List<UserBean> children = Arrays.asList(son1, son2, daughter);

		husband.setChildren(children);
		wife.setChildren(children);

		Map<Integer, String> map = new HashMap<>();
		map.put(1, "one");
		map.put(3, "three");
		husband.setMap(map);

		return husband;
	}

	public static void main(String[] args) {
		System.out.println(UserBean.getInstance());
		System.out.println(Integer.MIN_VALUE);
		
		
		Integer i1 = new Integer(1);
		
		Integer i2 = 2;
		
		System.out.println(i1.getClass().isPrimitive());
		System.out.println(i2.getClass().isPrimitive());
		
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
