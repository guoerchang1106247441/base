package com.persistence.base.tags;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class BlockTag extends TagSupport{
	private static final long serialVersionUID = -8246166191638588615L;
	private String name;

	public void setName(String name){
		this.name = name;
	}

	public int doStartTag()throws JspException{
		return getOverriedContent() == null ? 1 : 0;
	}

	public int doEndTag()throws JspException{
		String overriedContent = getOverriedContent();
		if (overriedContent == null) {
			return 6;
		}
		try{
			this.pageContext.getOut().write(overriedContent);
		}catch (IOException e){
			throw new JspException("write overridedContent occer IOException,block name:" + this.name, e);
		}
		return 6;
	}

	private String getOverriedContent(){
		String varName = Utils.getOverrideVariableName(this.name);
		return (String)this.pageContext.getRequest().getAttribute(varName);
	}
}



