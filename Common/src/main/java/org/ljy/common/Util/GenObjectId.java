package org.ljy.common.util;

import java.util.UUID;

public class GenObjectId {
    public static String getRandObjectId() {
        return UUID.randomUUID().toString();
    }
}
