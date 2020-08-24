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
import org.isf.exatype.model.ExamType;
import org.isf.utils.exception.OHException;

public class TestExam 
{	
    private String code = "ZZ";
    private String description = "TestDescription";
	private String defaultResult = "TestDefaultResult";
			
	public Exam setup(
			ExamType examtype,
			int procedure,
			boolean usingSet) throws OHException 
	{
		Exam exam;
	
				
		if (usingSet)
		{
			exam = new Exam();
			_setParameters(exam, procedure, examtype);
		}
		else
		{
			// Create Exam with all parameters 
			exam = new Exam(code, description, examtype, procedure, defaultResult);
		}
				    	
		return exam;
	}
	
	public void _setParameters(
			Exam exam,
			int procedure,
			ExamType examtype) 
	{	
		exam.setCode(code);
		exam.setDescription(description);
		exam.setExamtype(examtype);
		exam.setProcedure(procedure);
		exam.setDefaultResult(defaultResult);
		
		return;
	}
	
	public void check(
			Exam exam) 
	{		
    	assertEquals(code, exam.getCode());
    	assertEquals(description, exam.getDescription());
    	assertEquals(defaultResult, exam.getDefaultResult());
		
		return;
	}
}
