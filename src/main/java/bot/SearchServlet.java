package bot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

@WebServlet("/servlet-url")
public class SearchServlet extends HttpServlet {
    static String Link = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the URL value from the request
        String url = request.getParameter("url");
        System.out.println(url);
        Link = url;
        Seleniumclass obj = new Seleniumclass();
        obj.call();
        String result = obj.performActions("test", "test1");
        System.out.println("No exception till line 24");

     // Set the result as an attribute in the request
        request.setAttribute("result", result.toString());
        System.out.println(result.toString());


        // Forward the request to the index.jsp page
        request.getRequestDispatcher("index.jsp").forward(request, response);
       
        
    }
}
