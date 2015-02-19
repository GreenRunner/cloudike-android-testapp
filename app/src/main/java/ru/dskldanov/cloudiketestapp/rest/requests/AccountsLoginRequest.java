package ru.dskldanov.cloudiketestapp.rest.requests;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;

import ru.dskldanov.cloudiketestapp.model.Token;

/**
 * Запрос авторизации пользователя, в ответ возвращается авторизационный @Token
 */
public class AccountsLoginRequest extends JsonBaseSpiceRequest<Token> {

    private static final String TAG = AccountsLoginRequest.class.getSimpleName();

    private final String ACCOUNTS_LOGIN_URL = "https://saas.cloudike.com/api/1/accounts/login/";
    private final String PARAM_EMAIL = "email";
    private final String PARAM_PASSWORD = "password";

    private GenericUrl url;


    public AccountsLoginRequest( String email, String password ) throws Exception {
        super(Token.class);
        url = new GenericUrl( ACCOUNTS_LOGIN_URL );
        url.put( PARAM_EMAIL , email );
        url.put( PARAM_PASSWORD , password );
        Log.d( TAG , "url : " + url.toString() );
    }

    @Override
    protected void populateHeaders( HttpRequest request ) {
        // do nothing
    }

    @Override
    public HttpRequest prepareHttpRequest() throws Exception {
        return getPostHttpRequest(null);
    }

    @Override
    public GenericUrl getURL()  {
        return url;
    }
}
