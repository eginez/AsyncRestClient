package org.eginez

import groovyx.net.http.RESTClient
import rx.Observable
import rx.Scheduler
import rx.schedulers.Schedulers

import static rx.util.async.Async.*

class AsyncRestClient {
    @Delegate protected RESTClient restClient


    /**
     * Constructor.
     * @see groovyx.net.http.HTTPBuilder#HTTPBuilder()
     */
    public AsyncRestClient() {
        restClient = new RESTClient()
    }

    /**
     * See {@link groovyx.net.http.HTTPBuilder#HTTPBuilder(Object)}
     * @param defaultURI default request URI (String, URI, URL or {@link groovyx.net.http.URIBuilder})
     * @throws URISyntaxException
     */
    public AsyncRestClient( Object defaultURI ) throws URISyntaxException {
        restClient = new RESTClient(defaultURI)
    }

    /**
     * See {@link groovyx.net.http.HTTPBuilder#HTTPBuilder(Object, Object)}
     * @param defaultURI default request URI (String, URI, URL or {@link groovyx.net.http.URIBuilder})
     * @param defaultContentType default content-type (String or {@link groovyx.net.http.ContentType})
     * @throws URISyntaxException
     */
    public AsyncRestClient( Object defaultURI, Object defaultContentType ) throws URISyntaxException {
        restClient = new RESTClient(defaultURI, defaultContentType)
    }

    /**
     * Convience method to performed a GET using observables
     * @param args named parameters - see
     *  {@link HTTPBuilder.RequestConfigDelegate#setPropertiesFromMap(Map)}
     */
    public Observable<Object> getAsync(Map<String,?> args, Scheduler scheduler = Schedulers.io()){
        return start({ restClient.get(args) }, scheduler)
    }

    /**
     * Convience method to performed a POST using observables
     * @param args named parameters - see
     *  {@link HTTPBuilder.RequestConfigDelegate#setPropertiesFromMap(Map)}
     */
    public Observable<Object> postAsync( Map<String,?> args, Scheduler scheduler = Schedulers.io() ) {
        return start({ restClient.post(args) }, scheduler)
    }

    /**
     * Convience method to performed a PUT using observables
     * @param args named parameters - see
     *  {@link HTTPBuilder.RequestConfigDelegate#setPropertiesFromMap(Map)}
     */
    public Observable<Object> putAsync( Map<String,?> args, Scheduler scheduler = Schedulers.io() ) {
        return start({ restClient.put(args) }, scheduler)
    }

    /**
     * Convience method to performed a DELETE using observables
     * @param args named parameters - see
     *  {@link HTTPBuilder.RequestConfigDelegate#setPropertiesFromMap(Map)}
     */
    public Observable<Object> deleteAsync( Map<String,?> args, Scheduler scheduler = Schedulers.io()){
        return start({ restClient.delete(args) }, scheduler)
    }

}
