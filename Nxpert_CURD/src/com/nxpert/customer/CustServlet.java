package com.nxpert.customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class CustServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustDao custDao;
	
	public void init() {
		custDao = new CustDao();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		
		try {
			switch (action) {
			case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertCustomer(request, response);
                break;
			case "/view":
				viewCustomer(request, response);
				break;
			case "/delete":
				deleteCustomer(request, response);
				break;
			case "/update":
				updateCustomer(request, response);
				break;
			default:
				listCustomer(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Customer> listCustomer = custDao.selectAllCustomer();
			request.setAttribute("listCustomer", listCustomer);
			RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerWeb.jsp");
			dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerForm.jsp");
        dispatcher.forward(request, response);
    }
	
	private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
 
        Customer newCustomer = new Customer(id, name);
        custDao.insertCustomer(newCustomer);
        response.sendRedirect("list");
    }
	
	private void viewCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		Customer customer = new Customer(id, name);
		custDao.viewCustomer(customer);
		response.sendRedirect("list");
	}
	
	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		Customer customer = new Customer(id, name);
		custDao.updateCustomer(customer);
		response.sendRedirect("list");
	}
	
	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		custDao.deleteCustomer(id);
		response.sendRedirect("list");

	}
}
