package com.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Dao.CategoryDao;
import com.domain.Category;

@Controller
public class CategoryController {
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private Category category;
	@Autowired
	HttpSession httpsession;
	
	
	/*@RequestMapping(name="/category/get/{id}", method=RequestMethod.GET)
	public ModelAndView getCategory(@RequestParam("id")String id)
	
	{
		
		category = categoryDao.get(id);
		ModelAndView mv = new ModelAndView("home");
		return mv.addObject("category", category);
	}*/
	
	@PostMapping("/category/save")
	
	
	public ModelAndView saveCategory(@RequestParam ("id")String id,
			@RequestParam("name")String name,
			@RequestParam ("description") String description)
	{
		ModelAndView mv = new ModelAndView("rediret:/managecategories");
		category.setId(id);
		category.setName(name);
		category.setDescription(description);
		
		if(categoryDao.save(category))
		{
			
			mv.addObject("CategorySuccessMessage", "The category saved successfully");
			/*List<Category> categories = categoryDao.list();
			httpsession.setAttribute("categories", categories);*/
		}
		else
		{
		
			mv.addObject("CategoryErrorMessage", "could not save the category");
		}
		
	
		return mv;
		
	}
	
	@PutMapping("/category/update")
	
	
	public ModelAndView updateCategory(@ModelAttribute Category cateory)
	{
	
				ModelAndView mv = new ModelAndView("rediret:/managecategories");
		
		if(categoryDao.update(category)==true) 
		{
			
			mv.addObject("successMessage", "The catgory updated successfully");
		}
		else
		{
		
			mv.addObject("errorMessage", "could not updated the category");
		}
		return mv;
	}
	
	@GetMapping(name="/category/delete")
	public ModelAndView deleteCategory(@RequestParam String id) {
	System.out.println("going to delete category : " + id);
	
	{
		ModelAndView mv = new ModelAndView("rediret:/managecategories");
	
		if( categoryDao.delete(id)== true)
		{
		
			mv.addObject("successMessage", "The catgory deleted successfully");
		}
		else
		{
		
			mv.addObject("errorMessage", "could not updated the category");
		}
		return mv;
	}
		
		
	}
	@GetMapping("/category/edit")
	public ModelAndView editCategory(@RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("rediret:/managecategories");
		category=categoryDao.get(id);
		
		mv.addObject("selectedCategory", category);
		httpsession.setAttribute("selectedCategory", category);
		return mv;
		
	}
	
	
	
	@GetMapping("/categories")
	public ModelAndView getAllCategories()
	{
		ModelAndView mv = new ModelAndView("home");
		List<Category> categories = categoryDao.list();
return mv.addObject("categories", categories);
		
	}
	
}

