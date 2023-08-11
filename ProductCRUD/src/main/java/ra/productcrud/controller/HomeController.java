package ra.productcrud.controller;

import ra.productcrud.model.User;
import ra.productcrud.service.UserService;
import ra.productcrud.util.Encoder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomeController", value = "/HomeController")
public class HomeController extends HttpServlet {
    protected  UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action ==null){

    }else {
        switch (action){
            case "LOGIN":
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
                break;
                case "REGISTER":
                    request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
                break;
            case "HOME":
                request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);
                break;
            case "LOGOUT":
                HttpSession session = request.getSession();
                session.removeAttribute("user_login");
                response.sendRedirect("/HomeController?action=HOME");
                break;
        }
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action ==null){

        }else {
            switch (action){
                case "LOGIN":
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    User userLogin = userService.login(email, Encoder.encode(password));
                    if (userLogin == null){
                        request.setAttribute("loginError","Tài khoản hoặc mật khẩu không đúng");
                        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
                    }else {
                        if(userLogin.isStatus()){
                            // lưuu tk đăng nhập vào session , chuyển về trANG HÔM
                            HttpSession session = request.getSession();
                            session.setAttribute("user_login",userLogin);
                            response.sendRedirect("/HomeController?action=HOME");
                        }else {
                            // tài khoản bị khóa
                            request.setAttribute("loginError","Tài khoản này đã bị khóa");
                            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
                        }
                    }
                    break;
                case "REGISTER":
                    String emailNew = request.getParameter("email");
                    String passwordNew = request.getParameter("password");
                    userService.save(new User(emailNew,Encoder.encode(passwordNew)));
                    response.sendRedirect("/HomeController?action=LOGIN");
                    break;
            }
        }
    }
}