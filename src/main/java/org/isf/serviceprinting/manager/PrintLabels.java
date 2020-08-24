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
package org.isf.serviceprinting.manager;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;

import org.isf.utils.db.DbSingleJpaConn;
import org.isf.utils.exception.OHServiceException;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class PrintLabels {
	public static final int toDisplay = 0;
	public static final int toPdf = 1;
	public static final int toPrint = 2;
	
	public PrintLabels(String filename, Integer patId  )throws OHServiceException {
		try{
		HashMap<String, String> parameters = new HashMap<String, String>();

		parameters.put("patientID", String.valueOf(patId));

		StringBuilder sbFilename = new StringBuilder();
		sbFilename.append("rpt");
		sbFilename.append(File.separator);
		String jasperFileName = filename;
	
		sbFilename.append(jasperFileName);
		sbFilename.append(".jasper");
		File jasperFile = new File(sbFilename.toString());
		Connection conn = DbSingleJpaConn.getConnection();
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(jasperFile);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
		JasperPrintManager.printReport(jasperPrint, true);

	} catch (Exception e) {
		e.printStackTrace();
	}
		
		
	}
}
