package edu.nefu.ComputingMethod.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nefu.ComputingMethod.InterpolationFitting.LagrangeInterpolation;
import edu.nefu.ComputingMethod.InterpolationFitting.LeastSquaresFitting;
import edu.nefu.ComputingMethod.InterpolationFitting.NewtonInterpolation;
import edu.nefu.ComputingMethod.bean.Point;

@WebServlet("/InterpolationFittingServlet")
public class InterpolationFittingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String task;
       
    public InterpolationFittingServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "LagrangeInterpolation":
			solveLagrangeInterpolation(request,response);
			break;
		case "NewtonInterpolation":
			solveNewtonInterpolation(request,response);
		case "LeastSquaresFitting":
			solveLeastSquaresFitting(request,response);
			break;
		default:
			break;
		}
	}

	private void solveLagrangeInterpolation(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		int number=Integer.parseInt(request.getParameter("number"));
		String x=request.getParameter("x");
		Point [] points=new Point[number+1];
		for(int i=0;i<number+1;i++)
			points[i]=new Point(Double.parseDouble(request.getParameter("p"+i+"x")), Double.parseDouble(request.getParameter("p"+i+"y")));
		LagrangeInterpolation lInterpolation=new LagrangeInterpolation(number, points);
		request.getSession().setAttribute("LagrangeInterpolation",lInterpolation);
		response.sendRedirect("InterpolationFitting/LagrangeInterpolationSolve.jsp?x="+x);
	}
	
	private void solveNewtonInterpolation(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		int order=Integer.parseInt(request.getParameter("number"));
		String x=request.getParameter("x");
		Point [] points=new Point[order+1];
		for(int i=0;i<order+1;i++)
			points[i]=new Point(Double.parseDouble(request.getParameter("p"+i+"x")), Double.parseDouble(request.getParameter("p"+i+"y")));
		NewtonInterpolation nInterpolation=new NewtonInterpolation(order, points);
		request.getSession().setAttribute("NewtonInterpolation",nInterpolation);
		response.sendRedirect("InterpolationFitting/NewtonInterpolationSolve.jsp?x="+x);
	}


	private void solveLeastSquaresFitting(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		int number=Integer.parseInt(request.getParameter("number"));
		int order=Integer.parseInt(request.getParameter("order"));
		Point [] points=new Point[number];
		for(int i=0;i<number;i++)
			points[i]=new Point(Double.parseDouble(request.getParameter("p"+i+"x")), Double.parseDouble(request.getParameter("p"+i+"y")));
		LeastSquaresFitting lFitting=new LeastSquaresFitting(order, points);
		request.getSession().setAttribute("LeastSquaresFitting", lFitting);
		response.sendRedirect("InterpolationFitting/LeastSquaresFittingSolve.jsp");
	}
}
