package com.persistence.base.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class OverrideTag extends BodyTagSupport{
	private static final long serialVersionUID = -8379959647039117369L;
	private String name;
  
	public void setName(String name){
		this.name = name;
	}

	public int doStartTag()throws JspException{
		return isOverrided() ? 0 : 2;
	}

	public int doEndTag()throws JspException{
		if (isOverrided()) {
			return 6;
		}
		BodyContent b = getBodyContent();

		String varName = Utils.getOverrideVariableName(this.name);

		this.pageContext.getRequest().setAttribute(varName, b.getString());
		return 6;
	}

	private boolean isOverrided(){
		String varName = Utils.getOverrideVariableName(this.name);
		return this.pageContext.getRequest().getAttribute(varName) != null;
	}
}



