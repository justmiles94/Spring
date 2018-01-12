package tools.Spring.Controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tools.Spring.FxApp;
import tools.Spring.jdbc.JdbcDriver;
import tools.Spring.jdbc.ResultSetService;

@ComponentScan(basePackages = { "tools.Spring" })
@Controller
public class LandingController {

//	@Autowired
//	FxApp fx;
	
	@Autowired
	JdbcDriver jdbc;
	
	@RequestMapping("main")
	public String main() {
		return "hello my main";
	}

	@RequestMapping("main/es")
	public String Main() {
		return "hello my mMin";
	}

	@RequestMapping(value = "/ex/bars")
	@ResponseBody
	public String getBarBySimplePathWithRequestParam(
	  @RequestParam("id") long id) {
	    return "Get a specific Bar with id=" + id;
	}
	
    @RequestMapping("/landing")
    public String landingpage(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        return "landing";
    }	
    
    @RequestMapping(value = "/landingzone", method = RequestMethod.GET)
    @ResponseBody public String[][] landing(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	System.out.println(name);
    	ResultSetService rss = new ResultSetService();
    	String[][] result = rss.query(name);
        return result;
    }
    
    private String stringify(String[] array){
    	String output = "[";
    	int count = 0;
    	while(array.length != 0 && null != array[count]){
    		output += array[count];
    		if(++count < array.length){
    			output += ", ";
    		}else{
    			break;
    		}
    	}
		return output += "]";
    	
    }

	@RequestMapping("es")
	public String[] es() {
		return new String[]{"hello", "world"};
	}

	@RequestMapping("javafx")
	public String javafx() {
		startfx();
		return "FX Started";
	}
	
	boolean isStarted = false;
	private void startfx(){
		if(!isStarted){
			isStarted = !isStarted;
//			fx.main(null);
		}
	}
}
