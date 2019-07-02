
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Employe extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        
        EmployeModel em = new EmployeModel();

        if(request.getParameter("add") != null) {
            em.add(id, username, password, phone);
        } else if(request.getParameter("edit") != null) {
            em.edit(id, username, password, phone);
        }else if (request.getParameter("delete") != null) {
            em.delete(id);
        }

        PrintWriter out = response.getWriter();
        out.println("<h1>Congratuation!</h1>");
        out.println("<p>You successfully save changes.</p>");
        out.println("<br><a href='http://localhost:8080'>Back</a>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
