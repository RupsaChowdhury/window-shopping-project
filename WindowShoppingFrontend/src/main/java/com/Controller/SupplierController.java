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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.Dao.SupplierDao;
import com.domain.Supplier;

@Controller
public class SupplierController {
	@Autowired
	private SupplierDao supplierDao;
	
	@Autowired
	private Supplier supplier;
	@Autowired
	HttpSession httpsession;
	
	/*@RequestMapping(name="/getsupplier/", method = RequestMethod.GET)
	public ModelAndView getSupplier(@RequestParam String id)
	{
	supplier = supplierDao.get(id);
		
		
		ModelAndView mv = new ModelAndView("home");
		return mv.addObject("supplier", supplier);
	}
	*/
@PostMapping("/supplier/save")
	
	
	public ModelAndView saveSupplier(@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("address") String address)
	{System.out.println("saveSupplier method is calling");
	{
	
				ModelAndView mv = new ModelAndView("redirect:/managesuppliers");
				supplier.setId(id);
				supplier.setName(name);
				supplier.setAddress(address);
				if(supplierDao.save(supplier))
				{
					
					mv.addObject("successMessage", "The Supplier saved successfully");
				}
				else
				{
					
					mv.addObject("errorMessage", "could not save the Supplier");
				}
				return mv;
			}
			}
		@PutMapping("/supplier/update")


		public ModelAndView updateSupplier(@ModelAttribute Supplier supplier)
		{
			ModelAndView mv = new ModelAndView("home");

			if(supplierDao.update(supplier)==true)
			{
			
				mv.addObject("successMessage", "The supplier updated successfully");
			}
			else
			{
			
				mv.addObject("errorMessage", "could not updated the supplier");
			}
			return mv;
		}

		/*@GetMapping(name="/supplier/delete}")
		public ModelAndView deleteSupplier(@RequestParam String id)

		{
			ModelAndView mv = new ModelAndView("redirect:/managesuppliers");
			
			if( supplierDao.delete(id)== true)
			{
				
				mv.addObject("SuppliersuccessMessage", "The supplier deleted successfully");
			}
			else
	{
		
		mv.addObject("SuppliererrorMessage", "could not updated the supplier");
	}
	return mv;
	
	
}*/

@GetMapping("/supplier/edit/")
public ModelAndView editSupplier(@RequestParam String id) {
	ModelAndView mv = new ModelAndView("redirect:/managesuppliers");
	
	supplier = supplierDao.get(id);
	
	httpsession.setAttribute("selectedSupplier", supplier);

	return mv;
}
@GetMapping("/suppliers")
public ModelAndView getAllSuppliers()
{
	ModelAndView mv = new ModelAndView("home");
	List<Supplier> suppliers = supplierDao.list();
	return mv.addObject("suppliers", suppliers);
}
}
		
