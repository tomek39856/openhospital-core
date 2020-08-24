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
package org.isf.visits.test;


import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.isf.patient.model.Patient;
import org.isf.utils.exception.OHException;
import org.isf.visits.model.Visit;
import org.isf.ward.model.Ward;


public class TestVisit 
{	
	private GregorianCalendar date = new GregorianCalendar(10, 9, 8);
	private String note = "TestNote";
	private boolean sms = true;
	private String duration = "10";
	private String service = "testService";
	private Ward ward;
	private Patient patient;
			
	public Visit setup(
			Patient patient,
			boolean usingSet, 
			Ward ward) throws OHException 
	{
		Visit visit;
		this.ward = ward;
		this.patient = patient;
				
		if (usingSet)
		{
			visit = new Visit();
			_setParameters(patient, visit, ward);
		}
		else
		{
			// Create Visit with all parameters 
			visit = new Visit(0, date, patient, note, sms, ward, duration, service);
		}
				    	
		return visit;
	}
	
	public void _setParameters(
			Patient patient,
			Visit visit,
			Ward ward) 
	{	
		visit.setDate(date);
		visit.setNote(note);
		visit.setPatient(patient);
		visit.setSms(sms);
		visit.setWard(ward);
				
		return;
	}
	
	public void check(
			Visit visit) 
	{		
    	assertEquals(date,visit.getDate());
    	assertEquals(note,visit.getNote());
    	assertEquals(sms,visit.isSms());
		assertEquals(ward, visit.getWard());
		assertEquals(patient, visit.getPatient());
		return;
	}
}
