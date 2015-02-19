package ru.dskldanov.cloudiketestapp.model;

import com.google.api.client.util.Key;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * Autorization Token
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    @Key
    public String id;

    @Key
    public String token;

    @Key
    public String deviceDescription;

    @Key
    public long created;

    @Key
    public long expires;


    @Key
    public long userId;

    @Key
    public long userEId;

    @Key
    public String login;

    @Key
    public String name;

    @Key
    public String offerUrl;

}
