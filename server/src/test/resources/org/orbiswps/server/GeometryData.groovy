/*
 * OrbisWPS contains a set of libraries to build a Web Processing Service (WPS)
 * compliant with the 2.0 specification.
 *
 * OrbisWPS is part of the OrbisGIS platform
 *
 * OrbisGIS is a java GIS application dedicated to research in GIScience.
 * OrbisGIS is developed by the GIS group of the DECIDE team of the
 * Lab-STICC CNRS laboratory, see <http://www.lab-sticc.fr/>.
 *
 * The GIS group of the DECIDE team is located at :
 *
 * Laboratoire Lab-STICC – CNRS UMR 6285
 * Equipe DECIDE
 * UNIVERSITÉ DE BRETAGNE-SUD
 * Institut Universitaire de Technologie de Vannes
 * 8, Rue Montaigne - BP 561 56017 Vannes Cedex
 *
 * OrbisWPS is distributed under GPL 3 license.
 *
 * Copyright (C) 2015-2017 CNRS (Lab-STICC UMR CNRS 6285)
 *
 *
 * OrbisWPS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * OrbisWPS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * OrbisWPS. If not, see <http://www.gnu.org/licenses/>.
 *
 * For more information, please consult: <http://www.orbisgis.org/>
 * or contact directly:
 * info_at_ orbisgis.org
 */
package org.orbiswps.server

import org.orbiswps.groovyapi.input.GeometryInput
import org.orbiswps.groovyapi.output.GeometryOutput
import org.orbiswps.groovyapi.process.Process
/********************/
/** Process method **/
/********************/

/**
 * Test script for the Geometry
 * @author Sylvain PALOMINOS
 */
@Process(title = ["Geometry test","en","Test du Geometry","fr"],
        description = ["Test script using the Geometry ComplexData.","en",
                "Scripts test pour l'usage du ComplexData Geometry.","fr"],
        keywords = ["test,script,wps","en","test,scripte,wps","fr"],
        identifier = "orbisgis:test:geometry",
        metadata = ["website","metadata"]
)
def processing() {
    geometryOutput = inputGeometry;
}


/****************/
/** INPUT Data **/
/****************/

/** This Geometry is the input data source. */
@GeometryInput(
        title = ["Input Geometry","en","Entrée Geometry","fr"],
        description = ["A Geometry input.","en","Une entrée Geometry.","fr"],
        keywords = ["input","en","entrée","fr"],
        dimension = 3,
        excludedTypes = ["MULTIPOINT", "POINT"],
        minOccurs = 0,
        maxOccurs = 2,
        identifier = "orbisgis:test:geometry:input",
        metadata = ["website","metadata"]
        )
String inputGeometry

/*****************/
/** OUTPUT Data **/
/*****************/

/** This Geometry is the output data source. */
@GeometryOutput(
        title = ["Output Geometry","en","Sortie Geometry","fr"],
        description = ["A Geometry output.","en","Une sortie Geometry.","fr"],
        keywords = ["output","en","sortie","fr"],
        dimension = 2,
        geometryTypes = ["POLYGON", "POINT"],
        identifier = "orbisgis:test:geometry:output",
        metadata = ["website","metadata"]
)
String geometryOutput

