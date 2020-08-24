/*-
 * #%L
 * OpenHospital
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2020 Informatici Senza Frontiere
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
package org.isf.disease.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.isf.disease.model.Disease;
import org.isf.disease.service.DiseaseIoOperations;
import org.isf.distype.model.DiseaseType;
import org.isf.distype.test.TestDiseaseType;
import org.isf.distype.test.TestDiseaseTypeContext;
import org.isf.utils.db.DbJpaUtil;
import org.isf.utils.exception.OHException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class Tests  
{
	private static DbJpaUtil jpa;
	private static TestDisease testDisease;
	private static TestDiseaseType testDiseaseType;
	private static TestDiseaseContext testDiseaseContext;
	private static TestDiseaseTypeContext testDiseaseTypeContext;

    @Autowired
    DiseaseIoOperations diseaseIoOperation;
    
	
	@BeforeClass
    public static void setUpClass()  
    {
    	jpa = new DbJpaUtil();
    	testDisease = new TestDisease();
    	testDiseaseType = new TestDiseaseType();
    	testDiseaseContext = new TestDiseaseContext();
    	testDiseaseTypeContext = new TestDiseaseTypeContext();
    	
        return;
    }

    @Before
    public void setUp() throws OHException
    {
        jpa.open();
        
        _saveContext();
		
		return;
    }
        
    @After
    public void tearDown() throws Exception 
    {
        _restoreContext();   
        
        jpa.flush();
        jpa.close();
                
        return;
    }
    
    @AfterClass
    public static void tearDownClass() throws OHException 
    {
    	testDisease = null;
    	testDiseaseType = null;
    	testDiseaseContext = null;
    	testDiseaseTypeContext = null;

    	return;
    }
	
		
	@Test
	public void testDiseaseGets() 
	{
		String code = "";
			

		try 
		{		
			code = _setupTestDisease(false);
			_checkDiseaseIntoDb(code);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
				
		return;
	}
	
	@Test
	public void testDiseaseSets() 
	{
		String code = "";
			

		try 
		{		
			code = _setupTestDisease(true);
			_checkDiseaseIntoDb(code);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}
	
	@Test
	public void testIoGetDiseaseByCode()  
	{
		String code = "";
		
		
		try 
		{		
			code = _setupTestDisease(false);
			Disease foundDisease = diseaseIoOperation.getDiseaseByCode(Integer.parseInt(code));
			
			testDisease.check(foundDisease);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}
	
	@Test
	public void testIoGetDiseases() 
	{
		String code = "";
		
		
		try 
		{		
			code = _setupTestDisease(false);
			Disease foundDisease = (Disease)jpa.find(Disease.class, code); 

			ArrayList<Disease> diseases = diseaseIoOperation.getDiseases(foundDisease.getType().getCode(), false, false, false);
			assertTrue(diseases.contains(foundDisease));
			
			diseases = diseaseIoOperation.getDiseases(foundDisease.getType().getCode(), true, false, false);
			assertFalse(diseases.contains(foundDisease));
			foundDisease.setOpdInclude(true);
			jpa.beginTransaction();	
			jpa.persist(foundDisease);
			jpa.commitTransaction();
			diseases = diseaseIoOperation.getDiseases(foundDisease.getType().getCode(), true, false, false);
			assertTrue(diseases.contains(foundDisease));

			foundDisease = (Disease)jpa.find(Disease.class, code);
			diseases = diseaseIoOperation.getDiseases(foundDisease.getType().getCode(), true, true, false);
			assertFalse(diseases.contains(foundDisease));
			foundDisease.setOpdInclude(true);
			foundDisease.setIpdInInclude(true);
			jpa.beginTransaction();	
			jpa.persist(foundDisease);
			jpa.commitTransaction();
			diseases = diseaseIoOperation.getDiseases(foundDisease.getType().getCode(), true, true, false);
			assertTrue(diseases.contains(foundDisease));

			foundDisease = (Disease)jpa.find(Disease.class, code);
			diseases = diseaseIoOperation.getDiseases(foundDisease.getType().getCode(), true, true, true);
			assertFalse(diseases.contains(foundDisease));
			foundDisease.setOpdInclude(true);
			foundDisease.setIpdInInclude(true);
			foundDisease.setIpdOutInclude(true);
			jpa.beginTransaction();	
			jpa.persist(foundDisease);
			jpa.commitTransaction();
			diseases = diseaseIoOperation.getDiseases(foundDisease.getType().getCode(), true, true, true);
			assertTrue(diseases.contains(foundDisease));
			
			diseases = diseaseIoOperation.getDiseases(foundDisease.getType().getCode(), false, true, true);
			assertTrue(diseases.contains(foundDisease));
			
			diseases = diseaseIoOperation.getDiseases(foundDisease.getType().getCode(), false, false, true);
			assertTrue(diseases.contains(foundDisease));
			
			diseases = diseaseIoOperation.getDiseases(foundDisease.getType().getCode(), false, true, false);
			assertTrue(diseases.contains(foundDisease));
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}
	
	@Test
	public void testIoNewDisease() 
	{
		boolean result = false; 
		
		
		try 
		{		
			DiseaseType diseaseType = testDiseaseType.setup(false);

			
			jpa.beginTransaction();	
			Disease disease = testDisease.setup(diseaseType, true);
			jpa.persist(diseaseType);
			jpa.commitTransaction();
			result = diseaseIoOperation.newDisease(disease);

			assertTrue(result);
			_checkDiseaseIntoDb(disease.getCode());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}
	
	@Test
	public void testIoUpdateDisease()
	{
		String code = "";
		boolean result = false;
		
		
		try 
		{		
 			code = _setupTestDisease(false);
 			Disease foundDisease = (Disease)jpa.find(Disease.class, code); 
			jpa.flush();
 			foundDisease.setDescription("Update");
 			result = diseaseIoOperation.updateDisease(foundDisease);
			jpa.open();
 			Disease updateDisease = (Disease)jpa.find(Disease.class, code);

			assertTrue(result);
			assertEquals("Update", updateDisease.getDescription());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}
	
	@Test
	public void testIoHasDiseaseModified() 
	{
		String code = "";
		boolean result = false;
		
		
		try 
		{		
			code = _setupTestDisease(false);
			Disease foundDisease = (Disease)jpa.find(Disease.class, code);
			jpa.flush();
			result = diseaseIoOperation.deleteDisease(foundDisease);
			jpa.open();
			assertTrue(result);
			assertFalse(foundDisease.getIpdInInclude());
			assertFalse(foundDisease.getIpdOutInclude());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}
	
	@Test
	public void testIoDeleteDisease() 
	{
		String code = "";
		boolean result = false;
		
		
		try 
		{		
			code = _setupTestDisease(false);
			Disease foundDisease = (Disease)jpa.find(Disease.class, code);
			jpa.flush();
			result = diseaseIoOperation.deleteDisease(foundDisease);
			jpa.open();
			assertTrue(result);
			assertFalse(foundDisease.getIpdInInclude());
			assertFalse(foundDisease.getIpdOutInclude());
			assertFalse(foundDisease.getOpdInclude());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}

	@Test
	public void testIoIsCodePresent() 
	{
		String code = "";
		boolean result = false;
		

		try 
		{		
			code = _setupTestDisease(false);
			result = diseaseIoOperation.isCodePresent(code);

			assertTrue(result);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}

	@Test
	public void testIoIsDescriptionPresent() 
	{
		String code = "";
		boolean result = false;
		

		try 
		{		
			code = _setupTestDisease(false);
			Disease foundDisease = (Disease)jpa.find(Disease.class, code); 
			result = diseaseIoOperation.isDescriptionPresent(foundDisease.getDescription(), foundDisease.getType().getCode());

			assertTrue(result);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}
	
	
	private void _saveContext() throws OHException 
    {	
		testDiseaseContext.saveAll(jpa);
		testDiseaseTypeContext.saveAll(jpa);
		testDiseaseContext.addMissingKey(jpa);
        		
        return;
    }
	
    private void _restoreContext() throws OHException 
    {
		testDiseaseContext.deleteNews(jpa);
		testDiseaseTypeContext.deleteNews(jpa);
        
        return;
    }
        
	private String _setupTestDisease(
			boolean usingSet) throws OHException 
	{
		Disease disease;
		DiseaseType diseaseType = testDiseaseType.setup(false);
		

    	jpa.beginTransaction();	
    	disease = testDisease.setup(diseaseType, usingSet);
    	jpa.persist(diseaseType);
		jpa.persist(disease);
    	jpa.commitTransaction();
    	
		return disease.getCode();
	}
		
	private void  _checkDiseaseIntoDb(
			String code) throws OHException 
	{
		Disease foundDisease;
		

		foundDisease = (Disease)jpa.find(Disease.class, code); 
		testDisease.check(foundDisease);
		
		return;
	}	
}
