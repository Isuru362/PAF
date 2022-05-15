
package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;//for map
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
* Servlet implementation class EbillsAPI
*/@WebServlet("/EbillsAPI")
public class EbillsAPI extends HttpServlet {
private static final long serialVersionUID = 1L;
Ebill ebillObj = new Ebill(); //convert request parameters to the map
private static Map<String, String> getParasMap(HttpServletRequest request) {
Map<String, String> map = new HashMap<String, String>(); try {
Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
scanner.close(); String[] params = queryString.split("&"); for (String param : params) {
String[] p = param.split("=");
map.put(p[0], p[1]);
}
} catch (Exception e) { } return map;
}
/**
* @see HttpServlet#HttpServlet()
*/
public EbillsAPI() {
super();
} /**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.getWriter().append("Served at: ").append(request.getContextPath());
} /**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//sending values to insert function
String output = ebillObj.insertEbill( request.getParameter("ebillid"),
request.getParameter("accno"),
request.getParameter("amount"),
request.getParameter("startdate"),
request.getParameter("enddate");
request.getParameter("refno"));
//sending the output to client
response.getWriter().write(output);
}
/**
* @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
*/
protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//parameter map
Map<?, ?> paras = getParasMap(request); //getting values from the map and sending to update function
String output = ebillObj.updateEbill(paras.get("hidEbillIDSave").toString(),
paras.get("ebillid").toString(),
paras.get("accno").toString(),
paras.get("amount").toString(),
paras.get("startdate").toString(),
paras.get("enddate").toString(),
paras.get("refno").toString());
//sending the output to client
response.getWriter().write(output);
}
/**
* @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
*/
protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//parameter map
	Map<?, ?> paras = getParasMap(request); //getting values from the map and sending to delete function
	String output = ebillObj.deleteEbill( paras.get("ebillid").toString());
	//sending the output to client
	response.getWriter().write(output);
	}}

