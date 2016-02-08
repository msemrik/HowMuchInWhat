package com.domain;

/**
 * Created by M-Sem on 30/11/2015.
 */
public enum MovementStatus {

    EXECUTED(1), REVERTED(2);

    int code;

    MovementStatus(int i) {
        code = i;
    }

    public int getCode(){return code;}
}
