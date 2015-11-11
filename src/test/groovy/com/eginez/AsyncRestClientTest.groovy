package com.eginez

import groovy.mock.interceptor.MockFor
import groovyx.net.http.RESTClient
import org.junit.Test

import java.util.concurrent.CountDownLatch

class AsyncRestClientTest {


    @Test
    public void testSync() {
        def httpClient = new MockFor(RESTClient)
        httpClient.demand.get { 'get' }
        httpClient.use {
            def s = new AsyncRestClient('http://properUrl')
            def res = s.getAsync(path: '/')
                    .toBlocking()
                    .single()
            assert res == 'get'
        }
    }

    @Test
    public void testGetAsync() {
        def httpClient = new MockFor(RESTClient)
        httpClient.demand.get { 'get' }
        testAsync(httpClient, 'getAsync')
    }

    @Test
    public void testPostAsync() {
        def httpClient = new MockFor(RESTClient)
        httpClient.demand.post { 'post' }
        testAsync(httpClient, 'postAsync')
    }

    @Test
    public void testPutAsync() {
        def httpClient = new MockFor(RESTClient)
        httpClient.demand.put { 'put' }
        testAsync(httpClient, 'putAsync')
    }

    @Test
    public void testDeleteAsync() {
        def httpClient = new MockFor(RESTClient)
        httpClient.demand.delete { 'delete' }
        testAsync(httpClient, 'deleteAsync')
    }


    private void testAsync(MockFor mock, String method) {
        mock.use {
            def s = new AsyncRestClient('https://someurl.com')
            def cd = new CountDownLatch(1)
            def success = false
            s."$method"(path: '/').subscribe { res ->
                res == method
                success = true
                cd.countDown()
            }
            cd.await()
            assert success
        }
    }

}
