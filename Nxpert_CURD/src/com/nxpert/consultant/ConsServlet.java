package com.nxpert.consultant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nxpert.customer.Customer;


@WebServlet("/view")
public class ConsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConsDao consDao;
	
	public void init() {
		consDao = new ConsDao();
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
			case "/delete":
				deleteConsultant(request, response);
				break;
			case "/update":
				updateConsultant(request, response);
				break;
			default:
				listConsultant(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Consultant> listConsultant = ConsDao.selectAllConsultant();
		request.setAttribute("listConsultant", listConsultant);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ConsultantWeb.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void updateConsultant(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String customer_name = request.getParameter("customer_name");
		String consultant_name = request.getParameter("consultant_name");

		Consultant consultant = new Consultant(id, customer_name, consultant_name);
		consDao.updateConsultant(consultant);
		response.sendRedirect("list");
	}
	
	private void deleteConsultant(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		consDao.deleteConsultant(id);
		response.sendRedirect("list");

	}

}
