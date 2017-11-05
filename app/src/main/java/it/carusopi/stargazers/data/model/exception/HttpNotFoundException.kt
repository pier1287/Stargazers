package it.carusopi.stargazers.data.model.exception

/**
 * Created by carusopi on 04/11/2017.
 */

class HttpNotFoundException(message: String?): HttpErrorException(message, 404)
