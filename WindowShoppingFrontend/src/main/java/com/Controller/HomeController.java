package com.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Dao.CategoryDao;
import com.domain.Category;

@Controller
public class HomeController {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private Category category;
	
	@Autowired
	private HttpSession httpsession;
	@GetMapping("/")
	public ModelAndView home() {

		ModelAndView mv = new ModelAndView("home");
List<Category> categories = categoryDao.list();

		
		httpsession.setAttribute("categories", categories);
		return mv;
	}
	

	@GetMapping("/login")
	public ModelAndView login() {

		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedLogin", true);
		return mv;
	}
@GetMapping("/logout")
public ModelAndView logout()
{
	//at the time of login, we add user id in http session
	//at the time of logout, we need o remove user id from httpsession
	ModelAndView mv = new ModelAndView("home");
	httpsession.invalidate();
	return mv.addObject("logoutMessage", "You are successfully logged out");

}
	@GetMapping("/register")
	public ModelAndView register() {

		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedRegister", true);
		return mv;
		}
	}

