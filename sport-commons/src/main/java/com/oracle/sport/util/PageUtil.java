package com.oracle.sport.util;

import java.io.Serializable;
import java.util.List;

public class PageUtil implements Serializable{

	private static final long serialVersionUID = 1L;
    private List list;
    private String str="";

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void init(int pageno, int totalcount,int size, String requestname){
        int totalpage = 0;
        if(pageno!=1){
            str += "<a href='"+requestname+"&amp;isShow=0&amp;pageNo=1&amp;size=8'><font size='2'>首页</font></a>";
            str += "<a href='"+requestname+"&amp;isShow=0&amp;pageNo="+(pageno-1)+"&amp;size=8'><font size='2'>上一页</font></a>";
        }
        if(totalcount%size==0){
            totalpage = totalcount/size;
        }else{
            totalpage = (totalcount/size)+1;
        }
        for(int i = 1;i <= totalpage;i++){
            if(i==pageno){
                str += "<strong>"+i+"</strong>";
            }else{
                str += "<a href='"+requestname+"&amp;isShow=0&amp;pageNo="+i+"&amp;size=8'>"+i+"</a>";
            }
        }
        if(pageno!=totalpage){
            str += "<a href='"+requestname+"&amp;isShow=0&amp;pageNo="+(pageno+1)+"&amp;size=8'><font size='2'>下一页</font></a>";
            str += "<a href='"+requestname+"&amp;isShow=0&amp;pageNo="+totalpage+"&amp;size=8'><font size='2'>尾页</font></a>";
        }
        str +="共<var>"+totalpage+"</var>页 到第<input type=\"text\" size=\"3\" id=\"PAGENO\"/>页 <input type=\"button\"\n" +
                "                                                                           onclick=\"javascript:window.location.href = '"+requestname+"?&amp;isShow=0&amp;pageNo=' + $('#PAGENO').val() \"\n" +
                "                                                                           value=\"确定\" class=\"hand btn60x20\" id=\"skip\"/>";
        setStr(str);
    }
}
