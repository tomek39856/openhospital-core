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
package org.isf.stat.dto;

import java.io.Serializable;

import net.sf.jasperreports.engine.JasperPrint;

public class JasperReportResultDto implements Serializable {

    private JasperPrint jasperPrint;
    private String jasperFile;
    private String filename;

    public JasperReportResultDto(JasperPrint jasperPrint, String jasperFile, String filename) {
        this.jasperPrint = jasperPrint;
        this.jasperFile = jasperFile;
        this.filename = filename;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public String getJasperFile() {
        return jasperFile;
    }

    public void setJasperFile(String jasperFile) {
        this.jasperFile = jasperFile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
