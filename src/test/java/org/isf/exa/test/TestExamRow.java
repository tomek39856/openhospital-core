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
package org.isf.exa.test;


import static org.junit.Assert.assertEquals;

import org.isf.exa.model.Exam;
import org.isf.exa.model.ExamRow;
import org.isf.utils.exception.OHException;

public class TestExamRow 
{	
    private String description = "TestDescription";
    
			
	public ExamRow setup(
			Exam exam,
			boolean usingSet) throws OHException 
	{
		ExamRow examRow;
	
				
		if (usingSet)
		{
			examRow = new ExamRow();
			_setParameters(examRow, exam);
		}
		else
		{
			// Create ExamRow with all parameters 
			examRow = new ExamRow(exam, description);
		}
				    	
		return examRow;
	}
	
	public void _setParameters(
			ExamRow examRow,
			Exam exam) 
	{	
		examRow.setDescription(description);
		examRow.setExamCode(exam);
		
		return;
	}
	
	public void check(
			ExamRow examRow) 
	{		
    	assertEquals(description, examRow.getDescription());
		
		return;
	}
}
