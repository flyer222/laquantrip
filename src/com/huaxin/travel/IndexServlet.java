package com.huaxin.travel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huaxin.travel.place.TripPlace;
import com.huaxin.travel.place.TripPlaceService;

public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		TripPlaceService tps=new TripPlaceService();
		List<TripPlace> topPlace=tps.findTopTripplace(3);
		req.setAttribute("top_places", topPlace);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
}
