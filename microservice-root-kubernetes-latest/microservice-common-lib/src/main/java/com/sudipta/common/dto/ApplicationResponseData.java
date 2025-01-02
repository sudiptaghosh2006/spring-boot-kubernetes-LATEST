package com.sudipta.common.dto;

//import org.springframework.http.HttpStatus;

public class  ApplicationResponseData<T>
{
    private ServiceResponseStatus responseStatus;
    private T responseData;
//    private HttpStatus httpStatus;


//    public HttpStatus getHttpStatus()
//    {
//        return httpStatus;
//    }

//    public void setHttpStatus(HttpStatus httpStatus)
//    {
//        this.httpStatus = httpStatus;
//    }

    public T getResponseData()
    {
        return responseData;
    }

    public ApplicationResponseData<T> setResponseData(T responseData)
    {
        this.responseData = responseData;
        return this;
    }

    public ServiceResponseStatus getResponseStatus()
    {
	return responseStatus;
    }

    public ApplicationResponseData<T> setResponseStatus(ServiceResponseStatus responseStatus)
    {
	this.responseStatus = responseStatus;
	return this;
    }

}
