package com.TestCase;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.Dao.CategoryDao;
import com.domain.Category;

public class CategoryDaoTest {
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static CategoryDao categorydao;
	@Autowired
	private static Category category;
	@BeforeClass
	public static void abcd()
	{
		context = new AnnotationConfigApplicationContext();
		
		context.scan("com.*"); 
		
		context.refresh();
			
		categorydao = (CategoryDao)context.getBean("categorydao");
		category = (Category)context.getBean("category");
	}
	@Test
	public void saveCategoryTestCase()
	{
		category = new Category();
category.setId("Lenovo k5");
		
		category.setName("Mobile");
		category.setDescription("This is Mobile category");
		
	  boolean status = 	( categorydao.save(category));
	  
	  assertEquals("save category test case", true, status);
	}
	
	
	@Test
	public void updateCategoryTestCase()
	{
		//category= new Category();
		category.setId("Redmi");
		category.setName("Mobile");
		category.setDescription("This is a new Mobile Category");
		boolean status = categorydao.update(category);
		assertEquals("update test case", true,status );
	}
	
	@Test
	public void getCategorySuccessTestCase()
	{
		category=  categorydao.get("Mob-001");
		
		assertNotNull("get category test case", category);
		}
		
		@Test
		public void getCategoryFailureTestCase()
		{
			
		category=  categorydao.get("Mob-001");
		
		assertNull("get category test case", category);
		}
		
		@Test
		public void deleteCategorySuccessTestCase()
		{
		boolean status =	 categorydao.delete("Samsung");
		assertEquals("delete category success test case" , true, status);
		
		}
		
		@Test
		public void deleteCategoryFailureTestCase()
		{
		boolean status =	categorydao.delete("Lenovo K5");
		assertEquals("delete category failure test case" , false, status);
		
		}
		
		
		@Test
		public void getAllCategorysTestCase()
		{
		List<Category>	categories = categorydao.list();
		
		assertEquals("get all usres " , 3, categories.size() );
		
		}

	}

	
