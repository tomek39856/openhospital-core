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
package org.isf.dicom.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.isf.dicom.model.FileDicom;
import org.isf.dicom.service.DicomIoOperations;
import org.isf.dicomtype.model.DicomType;
import org.isf.dicomtype.test.TestDicomType;
import org.isf.dicomtype.test.TestDicomTypeContext;
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
	private static TestDicom testFileDicom;
	private static TestDicomContext testFileDicomContext;
	private static TestDicomType testDicomType;
	private static TestDicomTypeContext testDicomTypeContext;

    @Autowired
    DicomIoOperations dicomIoOperation;
	
	@BeforeClass
    public static void setUpClass()  
    {
    	jpa = new DbJpaUtil();
    	testFileDicom = new TestDicom();
    	testFileDicomContext = new TestDicomContext();
    	testDicomType = new TestDicomType();
    	testDicomTypeContext = new TestDicomTypeContext();
    	
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
    	testFileDicom = null;
    	testFileDicomContext = null;
    	testDicomType = null;
    	testDicomTypeContext = null;

    	return;
    }
	
		
	@Test
	public void testFileDicomGets() 
	{
		long code = 0;
			

		try 
		{	
			DicomType dicomType = testDicomType.setup(false);
			code = _setupTestFileDicom(false);
			_checkFileDicomIntoDb(code);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
				
		return;
	}
	
	
	@Test
	public void testFileDicomSets()
	{
		long code = 0;
			

		try 
		{		
			code = _setupTestFileDicom(true);
			_checkFileDicomIntoDb(code);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}
	
	
	@Test
	public void testIoGetSerieDetail() 
	{
		long code = 0;
		
		
		try 
		{		
			code = _setupTestFileDicom(false);
			FileDicom foundFileDicom = (FileDicom)jpa.find(FileDicom.class, code); 
			Long[] dicoms = dicomIoOperation.getSerieDetail(foundFileDicom.getPatId(), foundFileDicom.getDicomSeriesNumber());
			
			assertEquals(1, dicoms.length);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}


	@Test
	public void testIoDeleteSerie() 
	{
		long code = 0;
		boolean result = false;
		

		try 
		{		
			code = _setupTestFileDicom(false);
			FileDicom foundFileDicom = (FileDicom)jpa.find(FileDicom.class, code); 
			result = dicomIoOperation.deleteSerie(foundFileDicom.getPatId(), foundFileDicom.getDicomSeriesNumber());

			assertTrue(result);
			result = dicomIoOperation.isCodePresent(code);
			assertFalse(result);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}
	
	
	@Test
	public void testIoLoadFileDicom() 
	{
		long code = 0;
		

		try 
		{		
			code = _setupTestFileDicom(false);
			FileDicom foundFileDicom = (FileDicom)jpa.find(FileDicom.class, code); 
			FileDicom dicom = dicomIoOperation.loadDetails(foundFileDicom.getIdFile(), foundFileDicom.getPatId(), foundFileDicom.getDicomSeriesNumber());
			FileDicom dicom2 = dicomIoOperation.loadDetails(new Long(foundFileDicom.getIdFile()), foundFileDicom.getPatId(), foundFileDicom.getDicomSeriesNumber());
			
			assertEquals(dicom.getDicomInstanceUID(), dicom2.getDicomInstanceUID());
			assertEquals(foundFileDicom.getDicomSeriesDescription(), dicom.getDicomSeriesDescription());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
			fail();
		}
		
		return;
	}


	@Test
	public void testIoLoadPatientFiles() throws Exception{
		long code = _setupTestFileDicom(false);
		FileDicom foundFileDicom = (FileDicom) jpa.find(FileDicom.class, code);
		FileDicom[] dicoms = dicomIoOperation.loadPatientFiles(foundFileDicom.getPatId());
		assertEquals(foundFileDicom.getDicomSeriesDescription(), dicoms[0].getDicomSeriesDescription());
	}

	
	@Test
	public void testIoExist() 
	{
		long code = 0;
		boolean result = false;
		

		try 
		{		
			code = _setupTestFileDicom(false);
			FileDicom foundFileDicom = (FileDicom)jpa.find(FileDicom.class, code); 
			result = dicomIoOperation.exist(foundFileDicom);

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
	public void testIoSaveFile() 
	{
		long code = 0;
			
		
		try 
		{		
			code = _setupTestFileDicom(false);
			FileDicom foundFileDicom = (FileDicom)jpa.find(FileDicom.class, code); 
			foundFileDicom.setDicomSeriesDescription("Update");
			dicomIoOperation.saveFile(foundFileDicom);
			FileDicom updateFileDicom = (FileDicom)jpa.find(FileDicom.class, code); 
			
			assertEquals("Update", updateFileDicom.getDicomSeriesDescription());
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
		testFileDicomContext.saveAll(jpa);
		testDicomTypeContext.saveAll(jpa);
        		
        return;
    }
	
    private void _restoreContext() throws OHException 
    {
		testFileDicomContext.deleteNews(jpa);
		testDicomTypeContext.deleteNews(jpa);
        
        return;
    }
        
	private long _setupTestFileDicom(
			boolean usingSet) throws OHException 
	{
		FileDicom dicom;
		DicomType dicomType;
		
		dicomType = testDicomType.setup(true);
		
    	jpa.beginTransaction();	
    	dicom = testFileDicom.setup(dicomType, usingSet);
    	jpa.persist(dicomType);
		jpa.persist(dicom);
    	jpa.commitTransaction();
    	
		return dicom.getIdFile();
	}
		
	private void  _checkFileDicomIntoDb(
			long code) throws OHException 
	{
		FileDicom foundFileDicom;
		

		foundFileDicom = (FileDicom)jpa.find(FileDicom.class, code); 
		testFileDicom.check(foundFileDicom);
		
		return;
	}	
}
