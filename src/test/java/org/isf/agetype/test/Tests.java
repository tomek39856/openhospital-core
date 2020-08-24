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
package org.isf.agetype.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.isf.agetype.model.AgeType;
import org.isf.agetype.service.AgeTypeIoOperations;
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
	private static TestAgeType testAgeType;
	private static TestAgeTypeContext testAgeTypeContext;

    @Autowired
    AgeTypeIoOperations ageTypeIoOperations;
    
	
	@BeforeClass
    public static void setUpClass()  
    {
    	jpa = new DbJpaUtil();
    	testAgeType = new TestAgeType();
    	testAgeTypeContext = new TestAgeTypeContext();
    	
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
    	testAgeType = null;
    	testAgeTypeContext = null;

    	return;
    }
	
		
	@Test
	public void testAgeTypeGets()
	{
		String code = "";
			

		try 
		{		
			code = _setupTestAgeType(false);
			_checkAgeTypeIntoDb(code);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
				
		return;
	}
	
	@Test
	public void testAgeTypeSets()
	{
		String code = "";
			

		try 
		{		
			code = _setupTestAgeType(true);
			_checkAgeTypeIntoDb(code);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}
	
	@Test
	public void testIoGetAgeType() 
	{
		String code = "";
		
		
		try 
		{		
			code = _setupTestAgeType(false);
			AgeType foundAgeType = (AgeType)jpa.find(AgeType.class, code); 
			ArrayList<AgeType> ageTypes = ageTypeIoOperations.getAgeType();
			
			assertEquals(foundAgeType.getDescription(), ageTypes.get(ageTypes.size()-1).getDescription());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}
	
	@Test
	public void testIoUpdateAgeType() 
	{
		String code = "";
		boolean result = false;
		
		
		try 
		{		
			code = _setupTestAgeType(false);
			AgeType foundAgeType = (AgeType)jpa.find(AgeType.class, code); 
			foundAgeType.setFrom(4);
			foundAgeType.setTo(40);
			ArrayList<AgeType> ageTypes = new ArrayList<AgeType>();
			ageTypes.add(foundAgeType);
			result = ageTypeIoOperations.updateAgeType(ageTypes);
			AgeType updateAgeType = (AgeType)jpa.find(AgeType.class, code);

			assertTrue(result);
			assertEquals(4, updateAgeType.getFrom());
			assertEquals(40, updateAgeType.getTo());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail();
		}
		
		return;
	}
	
	@Test
	public void testIoGetAgeTypeByCode()
	{	
		String code = "";
		
		try 
		{		
			code = _setupTestAgeType(false);
			AgeType ageType = (AgeType)jpa.find(AgeType.class, code); 
			AgeType foundAgeType = ageTypeIoOperations.getAgeTypeByCode(9);
			
			assertEquals(ageType.getFrom(), foundAgeType.getFrom());
			assertEquals(ageType.getTo(), foundAgeType.getTo());
			assertEquals(ageType.getDescription(), foundAgeType.getDescription());
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
		testAgeTypeContext.saveAll(jpa);
        		
        return;
    }
	
    private void _restoreContext() throws OHException 
    {
		testAgeTypeContext.deleteNews(jpa);
        
        return;
    }
        
	private String _setupTestAgeType(
			boolean usingSet) throws OHException 
	{
		AgeType ageType;
		

    	jpa.beginTransaction();	
    	ageType = testAgeType.setup(usingSet);
		jpa.persist(ageType);
    	jpa.commitTransaction();
    	
		return ageType.getCode();
	}
		
	private void  _checkAgeTypeIntoDb(
			String code) throws OHException 
	{
		AgeType foundAgeType;
		

		foundAgeType = (AgeType)jpa.find(AgeType.class, code); 
		testAgeType.check(foundAgeType);
		
		return;
	}	
}
