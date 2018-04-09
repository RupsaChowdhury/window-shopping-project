package com.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import com.Dao.CartDao;
import com.Dao.ProductDao;
import com.domain.Cart;

@Controller
public class CartController {
    @Autowired
    private ProductDao productDao;
	@Autowired
	private CartDao cartDao;
	@Autowired 
	private Cart cart;
	@Autowired 
	private HttpSession httpsession;
	
	
	public ModelAndView order()
	
	
	{
		
		ModelAndView mv= new ModelAndView("home");
		String loggedInUserID=(String)httpsession.getAttribute("loggedInUserID");
		if(cartDao.update(loggedInUserID))
		{
			mv.addObject("SuccessMessage","Your order places successfully");
		}
		else
		{
			mv.addObject("error message","Your order could not placed");
		}
		return mv;
	}
	
	
	
	@PostMapping("/cart/add")
	
	
	public ModelAndView addToCart(@RequestParam String productName, @RequestParam int price, @RequestParam int quantity)
	{
		ModelAndView mv = new ModelAndView("home");
		String loggedInUserId = (String) httpsession.getAttribute("loggedInUserId");
		if(loggedInUserId==null)
		{
			mv.addObject("errorMessage", "plese login to add any product to acrt");
			return mv;
		}
		cart.setEmailID(loggedInUserId);
		cart.setPrice(price);
		cart.setQuantity(quantity);
		
		if(cartDao.save(cart))
		{
			mv.addObject("successMessage", "product added to cart succssfully");
		}	
		else
		{
			mv.addObject("errorMessage", "couldnot add product..pleas try after sometime");
		}
		return mv;
	}
	
	//get my cart details
	@GetMapping("/mycart/")
	public ModelAndView getMyCartDetails()
	{
		ModelAndView mv = new ModelAndView("home");
		//it will return all the products which are added to cart
		String loggedInUserId = (String) httpsession.getAttribute("loggedInUserID");
		if(loggedInUserId==null);
		{
			mv.addObject("errorMessage", "Please login to see your cart details");
		}	
		
		
		List<Cart> cartList = cartDao.list(loggedInUserId);
		mv.addObject("cartList", cartList);
		return mv;
	}
	@GetMapping("/mycart")
	public ModelAndView myCart()
	{
		ModelAndView mv = new ModelAndView("home");
		return mv.addObject("isUserClickedMyCart", "myCart");
	
	
}
}

