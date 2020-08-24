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
package org.isf.dicomtype.test;


import static org.junit.Assert.assertEquals;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Random;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.isf.dicomtype.model.DicomType;
import org.isf.utils.exception.OHException;

public class TestDicomType 
{	    
	private String dicomTypeId = "ZZZ";
	private String dicomTypeDescription = "TestDicomTypeDescription";
			
	public DicomType setup(
			boolean usingSet) throws OHException 
	{
		DicomType dicomType;
	
				
		if (usingSet)
		{
			dicomType = new DicomType();
			_setParameters(dicomType);
		}
		else
		{
			// Create FileDicom with all parameters 
			dicomType = new DicomType(dicomTypeId, dicomTypeDescription);
		}			
		
		return dicomType;
	}
	
	public void _setParameters(
			DicomType dicomType) 
	{	
		dicomType.setDicomTypeID(dicomTypeId);
		dicomType.setDicomTypeDescription(dicomTypeDescription);
		return;
	}
	
	public void check(
			DicomType dicomType) 
	{	
    	assertEquals(dicomTypeId, dicomType.getDicomTypeID());
    	assertEquals(dicomTypeDescription, dicomType.getDicomTypeDescription());
		return;
	}

	public Blob _createRandomBlob(
			int byteCount)
	{	
		Blob blob = null;
		byte[] data;
		
		
		data = new byte[byteCount];		
		new Random().nextBytes(data);		 
		try {
			blob = new SerialBlob(data);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return blob;
	}
}
