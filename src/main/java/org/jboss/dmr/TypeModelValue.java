/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.dmr;

import java.io.DataOutput;
import java.io.IOException;

/**
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
final class TypeModelValue extends ModelValue {
    private final ModelType value;

    private TypeModelValue(final ModelType value) {
        super(ModelType.TYPE);
        this.value = value;
    }

    void writeExternal(final DataOutput out) throws IOException {
        out.writeByte(value.getTypeChar());
    }

    private static final TypeModelValue BOOLEAN = new TypeModelValue(ModelType.BOOLEAN);
    private static final TypeModelValue BYTES = new TypeModelValue(ModelType.BYTES);
    private static final TypeModelValue DECIMAL = new TypeModelValue(ModelType.BIG_DECIMAL);
    private static final TypeModelValue DOUBLE = new TypeModelValue(ModelType.DOUBLE);
    private static final TypeModelValue INT = new TypeModelValue(ModelType.INT);
    private static final TypeModelValue LONG = new TypeModelValue(ModelType.LONG);
    private static final TypeModelValue LIST = new TypeModelValue(ModelType.LIST);
    private static final TypeModelValue OBJECT = new TypeModelValue(ModelType.OBJECT);
    private static final TypeModelValue STRING = new TypeModelValue(ModelType.STRING);
    private static final TypeModelValue TYPE = new TypeModelValue(ModelType.TYPE);
    private static final TypeModelValue UNDEFINED = new TypeModelValue(ModelType.UNDEFINED);

    static TypeModelValue of(ModelType type) {
        switch (type) {
            case LONG: return LONG;
            case INT: return INT;
            case BOOLEAN: return BOOLEAN;
            case STRING: return STRING;
            case DOUBLE: return DOUBLE;
            case BIG_DECIMAL: return DECIMAL;
            case BYTES: return BYTES;
            case LIST: return LIST;
            case TYPE: return TYPE;
            case OBJECT: return OBJECT;
            default: return UNDEFINED;
        }
    }

    boolean asBoolean() {
        return value != ModelType.UNDEFINED;
    }

    boolean asBoolean(final boolean defVal) {
        return value != ModelType.UNDEFINED;
    }

    String asString() {
        return value.toString();
    }

    ModelType asType() {
        return value;
    }

    /**
     * Determine whether this object is equal to another.
     *
     * @param other the other object
     * @return {@code true} if they are equal, {@code false} otherwise
     */
    public boolean equals(Object other) {
        return other instanceof TypeModelValue && equals((TypeModelValue)other);
    }

    /**
     * Determine whether this object is equal to another.
     *
     * @param other the other object
     * @return {@code true} if they are equal, {@code false} otherwise
     */
    public boolean equals(TypeModelValue other) {
        return this == other || other != null && other.value == value;
    }

    public int hashCode() {
        return value.hashCode();
    }
}
