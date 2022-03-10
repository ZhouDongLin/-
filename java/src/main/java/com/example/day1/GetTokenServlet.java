package com.example.day1;

import com.example.unit.DataTool;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "GetTokenServlet", urlPatterns ={"/get_token"})
public class GetTokenServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = DataTool.getToken();
        PrintWriter writer = response.getWriter();
        writer.write(token);
        writer.flush();
        writer.close();

    }

    public void destroy() {
    }
}