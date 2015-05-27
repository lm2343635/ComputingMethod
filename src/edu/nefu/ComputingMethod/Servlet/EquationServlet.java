package edu.nefu.ComputingMethod.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nefu.ComputingMethod.Equation.Dichotomy;

@WebServlet("/EquationServlet")
public class EquationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public EquationServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "Dichotomy":
			solveDichotomy(request,response);
			break;
		default:
			break;
		}
	}

	private void solveDichotomy(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String sNumber=request.getParameter("number");
		int number=Integer.parseInt(sNumber); 
		double accuracy=Double.parseDouble(request.getParameter("accuracy"));
		double start=Double.parseDouble(request.getParameter("start"));
		double end=Double.parseDouble(request.getParameter("end"));
		double [] coefficient=new double[number+1];
		for(int i=number;i>=0;i--)
			coefficient[i]=Double.parseDouble(request.getParameter("coefficient"+i));
		Dichotomy dichotomy=new Dichotomy(start, end, coefficient, accuracy);
		request.getSession().setAttribute("Dichotomy", dichotomy);
		response.sendRedirect("Equation/DichotomySolve.jsp");
	}

}
