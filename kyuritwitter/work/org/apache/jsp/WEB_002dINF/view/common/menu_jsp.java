package org.apache.jsp.WEB_002dINF.view.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/view/common/common.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  \r\n");
      out.write("  \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"sidebar\">\r\n");
      out.write("<table border=\"0\">\r\n");
      out.write("<tr>\r\n");
      out.write("<td colspan=\"3\"><h2>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mydata.usernick }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</h2></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mydata.follow }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("<br>");
      if (_jspx_meth_s_005flink_005f0(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mydata.followed }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("<br>");
      if (_jspx_meth_s_005flink_005f1(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mydata.postNum }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('　');
      out.write('　');
      if (_jspx_meth_s_005flink_005f2(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</div>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_s_005flink_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:link
    org.seasar.struts.taglib.S2LinkTag _jspx_th_s_005flink_005f0 = (org.seasar.struts.taglib.S2LinkTag) _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref.get(org.seasar.struts.taglib.S2LinkTag.class);
    _jspx_th_s_005flink_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005flink_005f0.setParent(null);
    // /WEB-INF/view/common/menu.jsp(7,25) name = href type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flink_005f0.setHref((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("/followlist/followpage/${mydata.userid }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/view/common/menu.jsp(7,25) name = style type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flink_005f0.setStyle("text-decoration: none");
    int _jspx_eval_s_005flink_005f0 = _jspx_th_s_005flink_005f0.doStartTag();
    if (_jspx_eval_s_005flink_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005flink_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005flink_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005flink_005f0.doInitBody();
      }
      do {
        out.write("フォロー<br>している");
        int evalDoAfterBody = _jspx_th_s_005flink_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005flink_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005flink_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref.reuse(_jspx_th_s_005flink_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref.reuse(_jspx_th_s_005flink_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005flink_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:link
    org.seasar.struts.taglib.S2LinkTag _jspx_th_s_005flink_005f1 = (org.seasar.struts.taglib.S2LinkTag) _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref.get(org.seasar.struts.taglib.S2LinkTag.class);
    _jspx_th_s_005flink_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005flink_005f1.setParent(null);
    // /WEB-INF/view/common/menu.jsp(8,27) name = href type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flink_005f1.setHref((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("/followlist/followedlist/${mydata.userid }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/view/common/menu.jsp(8,27) name = style type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flink_005f1.setStyle("text-decoration: none");
    int _jspx_eval_s_005flink_005f1 = _jspx_th_s_005flink_005f1.doStartTag();
    if (_jspx_eval_s_005flink_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005flink_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005flink_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005flink_005f1.doInitBody();
      }
      do {
        out.write("フォロー<br>されている");
        int evalDoAfterBody = _jspx_th_s_005flink_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005flink_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005flink_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref.reuse(_jspx_th_s_005flink_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref.reuse(_jspx_th_s_005flink_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005flink_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:link
    org.seasar.struts.taglib.S2LinkTag _jspx_th_s_005flink_005f2 = (org.seasar.struts.taglib.S2LinkTag) _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref.get(org.seasar.struts.taglib.S2LinkTag.class);
    _jspx_th_s_005flink_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005flink_005f2.setParent(null);
    // /WEB-INF/view/common/menu.jsp(11,24) name = href type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flink_005f2.setHref((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("/main/showdata/${mydata.usernick}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/view/common/menu.jsp(11,24) name = style type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flink_005f2.setStyle("text-decoration: none");
    int _jspx_eval_s_005flink_005f2 = _jspx_th_s_005flink_005f2.doStartTag();
    if (_jspx_eval_s_005flink_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005flink_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005flink_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005flink_005f2.doInitBody();
      }
      do {
        out.write('投');
        out.write('稿');
        out.write('数');
        int evalDoAfterBody = _jspx_th_s_005flink_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005flink_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005flink_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref.reuse(_jspx_th_s_005flink_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005flink_0026_005fstyle_005fhref.reuse(_jspx_th_s_005flink_005f2);
    return false;
  }
}
