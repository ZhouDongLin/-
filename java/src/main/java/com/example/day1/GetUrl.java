package com.example.day1;

import com.example.unit.DataTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "GetUrl",urlPatterns = {"/get_url"})
public class GetUrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        PrintWriter writer = resp.getWriter();
        String url = DataTool.downloadPic(key);
        writer.write(url);
        writer.flush();
        writer.close();

    }
}
