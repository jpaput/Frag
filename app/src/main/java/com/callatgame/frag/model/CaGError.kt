package com.callatgame.frag.model

class CaGError(val type : ErrorType, val message :String) {
}

enum class ErrorType() {
    BACKEND_ERROR,
    NETWORK_ERROR,
    UNEXCEPTED_ERROR
}