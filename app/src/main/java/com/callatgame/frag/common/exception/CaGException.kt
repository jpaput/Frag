package com.callatgame.frag.common.exception

class CaGException(val type : ErrorType, textMessage :String) : Throwable(textMessage) {

    constructor(type : ErrorType) : this(type, "")
}

enum class ErrorType() {
    AUTHORIZATION_ERROR,
    BACKEND_ERROR,
    NETWORK_ERROR,
    UNEXCEPTED_ERROR
}