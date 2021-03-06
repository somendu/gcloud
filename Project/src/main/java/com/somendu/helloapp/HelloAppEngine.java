package com.somendu.helloapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="HelloAppEngine", urlPatterns={"/hello"})
public class HelloAppEngine
  extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    response.setContentType("text/HTML");
    response.setCharacterEncoding("UTF-8");
    
    response.getWriter().print("Hello App Engine!\r\n");
    
    PrintWriter out = response.getWriter();
    out.println("<a href='" + getServletContext().getContextPath() + "/back'>To Back Page</a>");
  }
}
