/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entitiy.Customer;
import entitiy.History;
import entitiy.KitchenProduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.CustomerFacade;
import session.HistoryFacade;
import session.KitchenFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "MyServlet", urlPatterns = {
    "/addKitchen",
    "/createKitchen",
    "/editKitchenForm",
    "/choiceKitchen",
    "/editKitchen",
    "/addCustomer",
    "/editCustomerForm",
    "/editCustomer",
    "/createCustomer",
    "/listKitchens",
    "/listCustomers",
    "/buyKitchenForm",
    "/buyKitchen",
    "/addMoneyForm",
    "/customerChoice",
    "/addMoney",
})
public class MyServlet extends HttpServlet {
    @EJB
    private KitchenFacade kitchenFacade;
    @EJB
    private CustomerFacade customerFacade;
    @EJB
    private HistoryFacade historyFacade;
    
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String path = request.getServletPath();
        switch (path) {
            case "/addKitchen":
                request.getRequestDispatcher("/WEB-INF/addKitchenForm.jsp").forward(request, response);
                break;
            case "/createKitchen":
                String product = request.getParameter("product");
                String price = request.getParameter("price");
                 request.setAttribute("info", 
                        "Добавлена кухня "+product+
                        ", цена: " + price
                );
                KitchenProduct kitchenProduct = new KitchenProduct(product,Integer.parseInt(price));
                kitchenFacade.create(kitchenProduct);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/addCustomer":
                request.getRequestDispatcher("/WEB-INF/addCustomerForm.jsp").forward(request, response);
                break;
            case "/createCustomer":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String wallet = request.getParameter("wallet");
                
                request.setAttribute("info", 
                        "Покупатель "+firstname+" добавлен"     
                );
                Customer customer = new Customer(firstname,lastname,phone,Integer.parseInt(wallet));
                customerFacade.create(customer);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listKitchens":
                List<KitchenProduct> listKitchens = kitchenFacade.findAll();
                request.setAttribute("listKitchens", listKitchens);
                request.getRequestDispatcher("/WEB-INF/listKitchens.jsp").forward(request, response);
                break;
            case "/listCustomers":
                List<Customer> listCustomers = customerFacade.findAll();
                request.setAttribute("listCustomers", listCustomers);
                request.getRequestDispatcher("/WEB-INF/listCustomers.jsp").forward(request, response);
                break;
            case "/editCustomerForm":
                listCustomers = customerFacade.findAll();
                request.setAttribute("listCustomer", listCustomers);
                request.getRequestDispatcher("/WEB-INF/editCustomerForm.jsp").forward(request, response);
                break;
            case "/editCustomer":
                String id = request.getParameter("customer");
                firstname = request.getParameter("firstname");
                lastname = request.getParameter("lastname");
                phone = request.getParameter("phone");
                wallet = request.getParameter("wallet");
                
                customer = customerFacade.find(Long.parseLong(id));
                customer.setFirstname(firstname);
                customer.setLastname(lastname);
                customer.setPhone(phone);
                customer.setWallet(Integer.parseInt(wallet));
                customerFacade.edit(customer);

                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/buyKitchenForm":
                listKitchens = kitchenFacade.findAll();
                request.setAttribute("listKitchens", listKitchens);
                listCustomers = customerFacade.findAll();
                request.setAttribute("listCustomers", listCustomers);
                request.getRequestDispatcher("/WEB-INF/buyKitchenForm.jsp").forward(request, response);
                break;
            case "/buyKitchen":
                String kitchenProductId = request.getParameter("kitchenProductId");
                String customerId = request.getParameter("customerId");
                if("".equals(kitchenProductId) || kitchenProductId == null
                        || "".equals(customerId) || customerId == null){
                    request.setAttribute("info", "Выберите мебель или покупателя.");
                    request.getRequestDispatcher("/buyKitchenForm").forward(request, response);
                    break;
                }
                kitchenProduct = kitchenFacade.find(Long.parseLong(kitchenProductId));
                customer = customerFacade.find(Long.parseLong(customerId));
                if(customer.getWallet()<kitchenProduct.getPrice()){
                    request.setAttribute("info", "На счету пользователя недостаточно денег. Пополните балланс!");
                    break;
                }else{
                    customer.setWallet(customer.getWallet()-kitchenProduct.getPrice());
                    customerFacade.edit(customer);
                    History history = new History();
                    history.setBoughtDate( new GregorianCalendar().getTime());
                    history.setKitchenProduct(kitchenProduct);
                    history.setBuyeer(customer);
                    historyFacade.create(history);
                    request.setAttribute("info", "Кухонная мебель: "
                                                    +history.getKitchenProduct().getProduct()
                                                    +" была куплена "
                                                    +history.getCustomer().getFirstname() 
                                                    + " "
                                                    +history.getCustomer().getLastname());
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                    }
            case "/editKitchenForm":
                listKitchens = kitchenFacade.findAll();
                request.setAttribute("listKitchens", listKitchens);
                request.getRequestDispatcher("/WEB-INF/editKitchenForm.jsp").forward(request, response);
                break;
            case "/editKitchen":
                id = request.getParameter("kitchenProduct");
                product = request.getParameter("product");
                price = request.getParameter("price");
                
                kitchenProduct = kitchenFacade.find(Long.parseLong(id)); 
                kitchenProduct.setProduct(product);
                kitchenProduct.setPrice(Integer.parseInt(price));
                kitchenFacade.edit(kitchenProduct);

                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
   
                
        }
        }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>



}