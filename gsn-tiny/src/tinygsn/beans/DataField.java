/**
* Global Sensor Networks (GSN) Source Code
* Copyright (c) 2006-2014, Ecole Polytechnique Federale de Lausanne (EPFL)
*
* This file is part of GSN.
*
* GSN is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* GSN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with GSN. If not, see <http://www.gnu.org/licenses/>.
*
* File: gsn-tiny/src/tinygsn/beans/DataField.java
*
* @author Do Ngoc Hoan
*/


package tinygsn.beans;

import java.io.Serializable;
import tinygsn.utils.GSNRuntimeException;

public final class DataField implements Serializable {

	private static final long serialVersionUID = -8841539191525018987L;

	private String description = "Not Provided";

	private String name;

	private byte dataTypeID = -1;

	private String type;

	private DataField() {
	}

	public DataField(final String fieldName, final String type,
			final String description) throws GSNRuntimeException {
		this.name = fieldName;
		this.type = type;
		this.dataTypeID = DataTypes.convertTypeNameToGSNTypeID(type);
		this.description = description;
	}

	/*
	 * Use this constructor only with types which require precision parameter
	 * (char, varchar, blob, binary)
	 */
	public DataField(final String fieldName, final String type,
			final int precision, final String description) throws GSNRuntimeException {
		this.name = fieldName;
		this.type = type + "(" + precision + ")";
		this.dataTypeID = DataTypes.convertTypeNameToGSNTypeID(this.type);
		this.description = description;
	}

	public DataField(final String name, final String type) {
		this.name = name;
		this.type = type;
		this.dataTypeID = DataTypes.convertTypeNameToGSNTypeID(type);
	}

	public DataField(String colName, byte dataTypeID) {
		this.name = colName;
		this.dataTypeID = dataTypeID;
		this.type = DataTypes.TYPE_NAMES[this.dataTypeID];
	}

	public String getDescription() {
		return this.description;
	}
	transient boolean fieldNameConvertedToLowerCase = false;

	public String getName() {
		if (fieldNameConvertedToLowerCase == false) {
			fieldNameConvertedToLowerCase = true;
			this.name = name.toLowerCase();
		}
		return this.name;
	}

	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof DataField))
			return false;

		final DataField dataField = (DataField) o;
		if (this.name != null ? !this.name.equals(dataField.name)
				: dataField.name != null)
			return false;
		return true;
	}

	/**
	 * @return Returns the dataTypeID.
	 */
	public byte getDataTypeID() {
		if (this.dataTypeID == -1)
			this.dataTypeID = DataTypes.convertTypeNameToGSNTypeID(this.type);
		return this.dataTypeID;
	}

	public int hashCode() {
		return (this.name != null ? this.name.hashCode() : 0);
	}

	public String toString() {
		final StringBuilder result = new StringBuilder();
		result.append("[Field-Name:").append(this.name).append(", Type:")
				.append(DataTypes.TYPE_NAMES[this.getDataTypeID()])
				.append("[" + this.type + "]").append(", Decription:")
				.append(this.description).append("]");
		return result.toString();
	}

	/**
	 * @return Returns the type. This method is just used in the web interface for
	 *         detection the output of binary fields.
	 */
	public String getType() {
		return this.type;
	}
}
