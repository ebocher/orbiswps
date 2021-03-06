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
 * This process insert the given values in the given table.
 * The user has to specify (mandatory):
 *  - The input table (JDBCTable)
 *  - The values to insert (LiteralData)
 *
 * The user can specify (optional) :
 *  - The field list concerned by the value insertion
 *
 * @author Sylvain PALOMINOS
 */
@Process(
        title = ["Insert values in a table","en",
                "Insertion de valeurs dans une table","fr"],
        description = ["Insert values into a table.","en",
                "Insert de valeurs dans une table.","fr"],
        keywords = ["Table,Insert,Values", "en",
                "Table,Insertion,Valeurs", "fr"],
        properties = ["DBMS_TYPE", "H2GIS",
                "DBMS_TYPE", "POSTGIS"],
        version = "1.0",
        identifier = "orbisgis:wps:official:insertValues")
def processing() {
    //Build the query
    String queryBase = "INSERT INTO " + tableName;
    if (fieldList != null) {
        queryBase += " (";
        String fieldsStr = ""
        for (String field : fieldList) {
            if (field != null) {
                if (!fieldsStr.isEmpty()) {
                    fieldsStr += ", ";
                }
                fieldsStr += field;
            }
        }
        queryBase += fieldsStr+") ";
}
    queryBase += " VALUES (";
    //execute the query for each row
    String[] rowArray = values.split(":")
    for(String row : rowArray){
        String query = queryBase
        String[] valueArray = row.split(";", -1)
        //Retrieve the values to insert
        String formatedValues = ""
        for(String value : valueArray){
            if(!formatedValues.isEmpty()){
                formatedValues += ",";
            }
            if(value.isEmpty()){
                formatedValues += "NULL"
            }
            else{
                formatedValues += "'" + value + "'";
            }
        }
        query += formatedValues + ");"
        //execute the query
        sql.execute(query)
    }
    literalOutput = "Insert done."
}


/****************/
/** INPUT Data **/
/****************/

/** This JDBCTable is the input data source table. */
@JDBCTableInput(
        title = ["Table","en",
                "Table","fr"],
        description = ["The table to edit.","en",
                "La table à éditer.","fr"],
        identifier = "tableName")
String tableName

/**********************/
/** INPUT Parameters **/
/**********************/

/** Field list concerned by the value insertion. */
@JDBCColumnInput(
        title = ["Columns","en",
                "Colonnes","fr"],
        description = [
                "The columns concerned by the value insertion.","en",
                "Les colonnes concernés par les insertions de valeurs.","fr"],
        jdbcTableReference = "tableName",
        multiSelection = true,
        minOccurs = 0,
        identifier = "fieldList")
String[] fieldList

/** Coma separated values to insert. */
@LiteralDataInput(
        title = ["Values","en",
                "Valeurs","fr"],
        description = [
                "The input values. The values should be separated by a ',' and rows by ';'","en",
                "Les valeurs à insérer. Elles doivent etre séparées par une ',' et les lignes par un ';'","fr"],
        identifier = "values")
String values

/** String output of the process. */
@LiteralDataOutput(
        title = ["Output message","en",
                "Message de sortie","fr"],
        description = [
                "The output message.","en",
                "Le message de sortie.","fr"],
        identifier = "literalOutput")
String literalOutput

