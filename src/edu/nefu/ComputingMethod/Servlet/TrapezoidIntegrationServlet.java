package edu.nefu.ComputingMethod.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nefu.ComputingMethod.NumericalIntegration.TrapezoidIntegration;
import edu.nefu.ComputingMethod.bean.Polynomial;

@WebServlet("/TrapezoidIntegrationServlet")
public class TrapezoidIntegrationServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;

    public TrapezoidIntegrationServlet() 
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
		case "TrapezoidIntegration":
			solveTrapezoidIntegration(request,response);
			break;
		default:
			break;
		}
	}

	private void solveTrapezoidIntegration(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		int a=Integer.parseInt(request.getParameter("a"));
		int b=Integer.parseInt(request.getParameter("b"));
		int n=Integer.parseInt(request.getParameter("n"));
		int number=Integer.parseInt(request.getParameter("number"));
		double [] coefficient=new double[number+1];
		for(int i=0;i<coefficient.length;i++)
			coefficient[i]=Double.parseDouble(request.getParameter("coefficient"+i));
		Polynomial polynomial=new Polynomial(number, coefficient);
		TrapezoidIntegration tIntegration=new TrapezoidIntegration(polynomial);
		double s=tIntegration.calculateIntegration(a, b, n);
		response.sendRedirect("NumericalIntegration/TrapezoidIntegrationSolve.jsp?s="+s);
	}

}
