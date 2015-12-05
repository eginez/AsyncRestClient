package org.eginez

import groovyx.net.http.RESTClient
import rx.Observable
import rx.Scheduler
import rx.schedulers.Schedulers

import static rx.util.async.Async.*

class AsyncRestClient {

    /**
     * Convience method to performed a GET using observables
     * @param args named parameters - see
     *  {@link HTTPBuilder.RequestConfigDelegate#setPropertiesFromMap(Map)}
     */
    public static Observable<Object> getAsync(RESTClient restClient, Map<String,?> args, Scheduler scheduler = Schedulers.io()){
        return start({ restClient.get(args) }, scheduler)
    }

    /**
     * Convience method to performed a POST using observables
     * @param args named parameters - see
     *  {@link HTTPBuilder.RequestConfigDelegate#setPropertiesFromMap(Map)}
     */
    public static Observable<Object> postAsync( RESTClient restClient, Map<String,?> args, Scheduler scheduler = Schedulers.io() ) {
        return start({ restClient.post(args) }, scheduler)
    }

    /**
     * Convience method to performed a PUT using observables
     * @param args named parameters - see
     *  {@link HTTPBuilder.RequestConfigDelegate#setPropertiesFromMap(Map)}
     */
    public static Observable<Object> putAsync(RESTClient restClient, Map<String,?> args, Scheduler scheduler = Schedulers.io() ) {
        return start({ restClient.put(args) }, scheduler)
    }

    /**
     * Convience method to performed a DELETE using observables
     * @param args named parameters - see
     *  {@link HTTPBuilder.RequestConfigDelegate#setPropertiesFromMap(Map)}
     */
    public static Observable<Object> deleteAsync(RESTClient restClient, Map<String,?> args, Scheduler scheduler = Schedulers.io()){
        return start({ restClient.delete(args) }, scheduler)
    }
}
