package com.closerbuy.vendor.presentation.view

/**
 * Created by Vijay on 22/3/20.
 */

class ResponseModel<T>(var status: Int, var mResponse: T?, var error: Throwable?) {


    companion object {

        val SUCCESS = 201
        val ERROR = 202

        fun <T> setResponse(response: T): ResponseModel<T> {
            return ResponseModel(
                SUCCESS,
                response,
                null
            )
        }

        fun <T> setError(error: Throwable): ResponseModel<T> {
            return ResponseModel(
                ERROR,
                null,
                error
            )
        }
    }

}