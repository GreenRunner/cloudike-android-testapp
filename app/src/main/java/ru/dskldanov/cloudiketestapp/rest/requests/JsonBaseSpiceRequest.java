package ru.dskldanov.cloudiketestapp.rest.requests;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

import ru.dskldanov.cloudiketestapp.model.Token;

public abstract class JsonBaseSpiceRequest<RESULT> extends GoogleHttpClientSpiceRequest<RESULT>{

    private static final String TAG = JsonBaseSpiceRequest.class.getSimpleName();

    public JsonBaseSpiceRequest(Class<RESULT> clazz) {
        super(clazz);
    }

    @Override
    public RESULT loadDataFromNetwork() throws Exception {
        HttpRequest request = getHttpRequest();
        if( request == null )
            return  null;
        request.setParser( new JacksonFactory().createJsonObjectParser() );
        HttpResponse response = request.execute();
        Log.d(TAG,"response status code: " + response.getStatusCode() );

        //TODO: add other status code handle
        if(response.getStatusCode() == 200) {
            RESULT parsedResult = response.parseAs(getResultType());
            return parsedResult;
        }

        return null;
    }

    /**
     * Form HttpRequest for loading data in loadDataFromNetwork method
     * @return
     * @throws Exception
     */
    public HttpRequest getHttpRequest() throws Exception{
        HttpRequest request = prepareHttpRequest();
        populateHeaders(request);
        return request;
    }

    /**
     * Populate request headers
     * @param request
     */
    protected abstract void populateHeaders(HttpRequest request);

    /**
     * prepare HttpRequest
     * @return
     * @throws Exception
     */
    public abstract HttpRequest prepareHttpRequest() throws Exception;

    /**
     * Get formed url
     * @return
     */
    public abstract GenericUrl getURL();

    protected HttpRequest getPostHttpRequest(HttpContent content) throws Exception {
        final HttpRequest request = getHttpRequestFactory()
                .buildPostRequest(getURL(), content);
        return request;
    }

    protected HttpRequest getGetHttpRequest() throws Exception {
        final HttpRequest request = getHttpRequestFactory()
                .buildGetRequest( getURL() );
        return request;
    }

}
