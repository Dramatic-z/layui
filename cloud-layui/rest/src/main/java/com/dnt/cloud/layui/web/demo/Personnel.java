package com.dnt.cloud.layui.web.demo;

public class Personnel {
	
	
	public void doSomething(CallBack callBack,String task){
		System.out.println("总经理要你" + task); 
		String result = "做完了";
		// 当事情做完了我们就通过总经理公布的phoneCall方法通知总经理结果  
		callBack.backResult(result);
		
	}
	
	
	
	
	public static void main(String[] args) {
		
		// 首先我们需要一个员工  
        Personnel personnel = new Personnel();  
  
        // 其次把这个员工指派给总经理  
        new Manager(personnel);
	}
}
