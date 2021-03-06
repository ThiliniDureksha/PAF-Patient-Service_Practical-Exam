package com;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PatientRepository;

/**
 * Servlet implementation class PatientAPI
 */
@WebServlet("/PatientAPI")
public class PatientAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PatientRepository patientRepository = new PatientRepository();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = patientRepository.AddPatients(
				request.getParameter("Firstname"),
				request.getParameter("Lastname"),
				request.getParameter("Gender"),
				request.getParameter("Birthday"),
				request.getParameter("Email"),
				request.getParameter("Nic"),
				request.getParameter("Phone"),
				request.getParameter("username"),
				request.getParameter("password"));
				response.getWriter().write(output); 
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap1(request);
		 String output = patientRepository.updatePatients(paras.get("hidItemIDSave").toString(),
				 paras.get("Firstname").toString().replace('+',' ') ,
				 paras.get("Lastname").toString().replace('+',' ') ,
				 paras.get("Gender").toString(),
				 paras.get("Birthday").toString(),
				 paras.get("Email").toString().replaceAll("%40","@"),
				 paras.get("Nic").toString(),
				 paras.get("Phone").toString(),
				 paras.get("username").toString(),
				 paras.get("password").toString());
				 response.getWriter().write(output); 
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				Map paras = getParasMap1(request);
				System.out.println(paras.get("Patient_id "));
				String output = patientRepository.deletePatients(paras.get("Patient_id ").toString());

				response.getWriter().write(output);	
	}
	
	private static Map getParasMap1(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { //System.out.println(param);
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}

	
}
