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
package org.orbiswps.scripts.scripts.Table

import org.orbiswps.groovyapi.input.*
import org.orbiswps.groovyapi.output.*
import org.orbiswps.groovyapi.process.*

/********************/
/** Process method **/
/********************/

/**
 * This process deletes the given columns from the given table.
 * The user has to specify (mandatory):
 *  - The input table
 *  - The column to delete
 *
 * @author Sylvain PALOMINOS
 */
@Process(
        title = ["Delete columns","en","Suppression de colonnes","fr"],
        description = ["Delete columns from a table.","en",
                "Supprime des colonnes d'une table.","fr"],
        keywords = ["Table,Delete","en",
                "Table,Suppression","fr"],
        properties = ["DBMS_TYPE", "H2GIS",
                "DBMS_TYPE", "POSTGIS"], 
        version = "1.0",
        identifier = "orbisgis:wps:official:deleteColumns"
)
def processing() {
    //Build the start of the query
    for (String columnName : columnNames) {
        String query = String.format("ALTER TABLE %s DROP COLUMN `%s`", tableName, columnName)
        //Execute the query
        sql.execute(query)
    }
    literalOutput = "Delete done."
}


/****************/
/** INPUT Data **/
/****************/

/** This JDBCTable is the input data source table. */
@JDBCTableInput(
        title = ["Table","en","Table","fr"],
        description = ["The table to edit.","en","La table à éditer.","fr"],
        identifier = "tableName"
)
String tableName

/**********************/
/** INPUT Parameters **/
/**********************/

/** Name of the columns of the JDBCTable tableName to remove. */
@JDBCColumnInput(
        title = ["Columns","en","Colonnes","fr"],
        description = ["The columns to remove names.","en",
                "Le nom des colonnes à supprimer.","fr"],
        jdbcTableReference = "tableName",
        identifier = "columnNames"
)
String[] columnNames


/** Output message. */
@LiteralDataOutput(
        title = ["Output message","en",
                "Message de sortie","fr"],
        description = ["The output message.","en",
                "Le message de sortie.","fr"],
        identifier = "literalOutput")
String literalOutput

