package com.dnt.cloud.layui.web.demo;

public class Manager implements CallBack {
	
	public Manager(Personnel personnel) {
		  
        // 想让该让员工做什么
        personnel.doSomething(this, "整理公司文件");
    }  
  
    /** 
     * @description 当员工做完总经理让他做的事后就通过该方法通知总经理 
     * @param result 
     * 事情结果 
     */  
    public void phoneCall(String result) {
        System.out.println("事情" + result);
    }

	@Override
	public void backResult(String result) {
		System.out.println("事情" + result);
	}
}
