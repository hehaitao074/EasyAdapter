package com.github.mzule.easyadapter;

/**
 * Created by CaoDongping on 3/21/16.
 */
public class TypeRegisteException extends RuntimeException {
    public TypeRegisteException(String detailMessage) {
        super(detailMessage);
    }

    public TypeRegisteException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
