package ra.productcrud.controller;

import ra.productcrud.model.Product;
import ra.productcrud.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/ProductController")
public class ProductController extends HttpServlet {
    protected ProductService productService;

    @Override
    public void init() throws ServletException {
        productService= new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null){
            displayProducts(request,response);
        }else {
            switch (action){
                case "CREATE":
                    request.getRequestDispatcher("/WEB-INF/newProduct.jsp").forward(request,response);
            }
        }
    }

    protected void displayProducts(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        List<Product> products = productService.findAll();
        request.setAttribute("products",products);
        request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        System.out.println(action);
        if(action!=null){
            switch (action){
                case "ADD":
                    String name = request.getParameter("name");
                    String des = request.getParameter("des");
                    Double price = Double.valueOf(request.getParameter("price"));
                    int stock = Integer.parseInt(request.getParameter("stock"));
                    String imageUrl = request.getParameter("imageUrl");
                    Product newProduct= new Product(null,name,des,price,stock,imageUrl);
                    productService.save(newProduct);
                    displayProducts(request,response);
                    break;
            }
        }
    }
}