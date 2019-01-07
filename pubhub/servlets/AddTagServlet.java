package examples.pubhub.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/AddTag")

public class AddTagServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean successfulPost = false;
		
		String isbn13 = request.getParameter("isbn13");
		
		Tag tag = new Tag();
		
		tag.setIsbn13(isbn13);
		tag.setTag(request.getParameter("tag"));
		
		TagDAO tagDAO = DAOUtilities.getTagDAO();
		successfulPost = tagDAO.addBookTag(tag);
		
		if(successfulPost == true) {
			request.getSession().setAttribute("message", "Tag was added successfully!");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect(request.getContextPath() + "/ViewBookDetails?isbn13=" + isbn13);
		} else {
			request.getSession().setAttribute("message", "Could not update this Tag... :(");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
		
		
		
	}

}
