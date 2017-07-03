package org.baeldung.spring.data.couchbase;

import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;

class CustomTypeKeyCouchbaseConfig extends MyCouchbaseConfig {

    @Override
    public String typeKey() {
        return MappingCouchbaseConverter.TYPEKEY_SYNCGATEWAY_COMPATIBLE;
    }
}
