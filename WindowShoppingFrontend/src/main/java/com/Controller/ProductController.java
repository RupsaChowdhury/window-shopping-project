package com.Controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.Dao.CategoryDao;
import com.Dao.ProductDao;
import com.Dao.SupplierDao;
import com.DaoImpl.ProductDaoImpl;
import com.Util.FileUtil;
import com.domain.Category;
import com.domain.Product;
import com.domain.Supplier;

import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
@Controller
public class ProductController {
	@Autowired
	private ProductDao productdao;
	
	@Autowired
	private Product product;
	@Autowired
	private CategoryDao categorydao;
	
	@Autowired
	private SupplierDao supplierdao;
	
	@Autowired
	HttpSession httpsession;
	
	Logger log = LoggerFactory.logger(ProductDaoImpl.class);
	
	
	private static final String imageDirectory = "ShoppingStoreImages";
	private static String rootPath = System.getProperty("catalina.home");
	
	@GetMapping("/product/get")
	public ModelAndView getProduct(@RequestParam String id)
	{
		product = productdao.get(id);
	ModelAndView mv = new ModelAndView("rediret:/home");
	mv.addObject("selectedproduct", product);
	mv.addObject("isUserSelectedProduct", true);
	mv.addObject("seletedProductImage", rootPath + File.separator + imageDirectory + File.separator + id + "");
	return mv;
}
	/*@RequestMapping(name="/product/get/{id}", method = RequestMethod.GET)
	public ModelAndView getProduct(@RequestParam("id")String id)
	{
	
		product = productdao.get(id);
		
		
		ModelAndView mv = new ModelAndView("home");
		return mv.addObject("product", product);
	}*/
@PostMapping("/product/save")
	

	public ModelAndView saveProduct(@RequestParam("id") String id,
									@RequestParam("name")String name,
									@RequestParam("description") String description,
									@RequestParam("price") String price,
									@RequestParam("categoryID") String categoryID,
									@RequestParam("supplierID") String supplierID,
									@RequestParam("file") MultipartFile file)
	{
	
				ModelAndView mv = new ModelAndView("redirect:/manageproducts");
				product.setId(id);
				product.setName(name);
				product.setDescription(description);
				
				price = price.replace(",","");
				product.setPrice(Integer.parseInt(price));
				//product.setCategory(categoryDAO.get(categoryID));
				//product.setSupplier(supplierDAO.get(supplierID));
				product.setCategoryId(categoryID);
				product.setSupplierID(supplierID);;
					
		if(productdao.save(product))
		{
			
			mv.addObject("productsuccessMessage", "The product saved successfully");
			//call upload image method
			if(FileUtil.fileCopyNIO(file,id + ".PNG"))
			{
				mv.addObject("uploadMessage", "product image successfully uploaded");
			}
			else
			{
				mv.addObject("uploadMessage", "could not upload ");
				
			}
		}
		else
		{
		
			mv.addObject("producterrorMessage", "could not save the product");
		}
		return mv;
	}
	
@PutMapping("/product/update")


public ModelAndView updateProduct(@ModelAttribute Product product)
{
	ModelAndView mv = new ModelAndView("home");

	if(productdao.update(product)==true)
	{

		mv.addObject("successMessage", "The product updated successfully");
	}
	else
	{
		
		mv.addObject("errorMessage", "could not updated the product");
	}
	return mv;
}

/*@GetMapping(name="/product/delete")
public ModelAndView deleteProduct(@RequestParam String id) {
System.out.println("going to delete product : " + id);
{
	ModelAndView mv = new ModelAndView("redirect:/manageproducts");
	
	if( productDao.delete(id)== true)
	{
		mv.addObject("successMessage", "The product deleted successfully");
	}
	else
	{
		
		mv.addObject("errorMessage", "could not updated the product");
	}
	return mv;
}
}*/
	
	@GetMapping ("/product/edit")
	public ModelAndView editProduct(@RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		// based on product id fetch product details.
		product = productdao.get(id);
		// mv.addObject("selectedProduct", product);
		httpsession.setAttribute("selectedProduct", product);
	
		return mv;
	}
	

@GetMapping("/products")
public ModelAndView getAllProducts()
{
	ModelAndView mv = new ModelAndView("home");
	List<Product> products = productdao.list();
	return mv.addObject("products", products);
	
	}
@GetMapping("search")
public ModelAndView searchProduct(@RequestParam("searchString")String searchString)
{
	ModelAndView mv=new ModelAndView("home");
	List<Product> products=productdao.search(searchString);
	mv.addObject("isUserSelectedProduct", true);
	mv.addObject("Products",products);
	log.info("number of products with search string"+searchString+"is/are:"+products.size());
	return mv;
}

	  /*@GetMapping("/product/edit") public ModelAndView editProduct(@RequestParam
	  String Pid) { ModelAndView mv = new
	  ModelAndView("redirect:/manageproducts");
	  
	  product = productDao.get(Pid);
	  
	  httpsession.setAttribute("product", product); return mv;
	  
	  }*/
}

