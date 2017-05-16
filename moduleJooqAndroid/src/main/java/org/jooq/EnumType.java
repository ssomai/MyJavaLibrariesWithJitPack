package org.jooq;

import javax.xml.validation.Schema;

/**
 * Created by ssomai on 15/05/2017.
 */

public interface EnumType {

    /**
     * The literal as defined in the database
     */
    String getLiteral();

    /**
     * The schema of the enum type, if applicable (Postgres schema-scope enum
     * type only). Otherwise, this returns <code>null</code>
     */
    Schema getSchema();

    /**
     * The type name as registered in the database, if applicable (Postgres
     * schema-scope enum type only). Otherwise, this returns <code>null</code>
     */
    String getName();
}
