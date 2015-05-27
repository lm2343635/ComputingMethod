package edu.nefu.ComputingMethod.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nefu.ComputingMethod.LinearEquations.GaussianElimination;
import edu.nefu.ComputingMethod.LinearEquations.Iteration;
import edu.nefu.ComputingMethod.bean.Matrix;
import edu.nefu.ComputingMethod.bean.Vector;

@WebServlet("/LinearEquationsServlet")
public class LinearEquationsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
       
    public LinearEquationsServlet() 
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
		case "GaussianElimination":
			solveGaussianElimination(request,response);
			break;
		case "Iteration":
			solveIteration(request,response);
			break;
		default:
			break;
		}
	}

	private void solveGaussianElimination(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String sNumber=request.getParameter("number");
		int number=Integer.parseInt(sNumber); 
		double [][] CoefficientMatrix=new double[number][number];
		double [] ConstantTerm=new double[number];
		for(int i=0;i<number;i++)
			for(int j=0;j<number;j++)
				CoefficientMatrix[i][j]=Double.parseDouble(request.getParameter("CoefficientMatrix"+i+j));
		for(int i=0;i<number;i++)
			ConstantTerm[i]=Double.parseDouble(request.getParameter("ConstantTerm"+i));
		GaussianElimination gaussianElimination=new GaussianElimination(number, CoefficientMatrix, ConstantTerm);
		request.getSession().setAttribute("GaussianElimination", gaussianElimination);
		response.sendRedirect("LinearEquations/GaussianEliminationSolve.jsp");
	}
	
	private void solveIteration(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		int number=Integer.parseInt(request.getParameter("number")); 
		double relaxationFactor=Double.parseDouble(request.getParameter("relaxationFactor"));
		double accuracy=Double.parseDouble(request.getParameter("accuracy"));
		double [][] CoefficientMatrix=new double[number][number];
		double [] ConstantTerm=new double[number];
		for(int i=0;i<number;i++)
			for(int j=0;j<number;j++)
				CoefficientMatrix[i][j]=Double.parseDouble(request.getParameter("CoefficientMatrix"+i+j));
		for(int i=0;i<number;i++)
			ConstantTerm[i]=Double.parseDouble(request.getParameter("ConstantTerm"+i));
		Matrix coefficientMatrix=new Matrix(number, number);
		Vector constantTerm=new Vector(number);
		coefficientMatrix.setData(CoefficientMatrix);
		constantTerm.setData(ConstantTerm);
		Iteration iteration=new Iteration(number, relaxationFactor, accuracy, coefficientMatrix, constantTerm);
		request.getSession().setAttribute("iteration", iteration);
		response.sendRedirect("LinearEquations/IterationSolve.jsp");
	}
}
