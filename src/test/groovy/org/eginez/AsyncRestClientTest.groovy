package org.eginez

import groovyx.net.http.RESTClient
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mockito

import java.util.concurrent.CountDownLatch

class AsyncRestClientTest {


    @Test
    public void testGetAsync() {
        def httpClient = Mockito.mock(RESTClient.class)
        Mockito.when(httpClient.get(Matchers.any())).thenReturn('get')
        testAsync(httpClient, 'getAsync')
    }

    @Test
    public void testPostAsync() {
        def httpClient = Mockito.mock(RESTClient.class)
        Mockito.when(httpClient.get(Matchers.any())).thenReturn('get')
        testAsync(httpClient, 'postAsync')
    }

    @Test
    public void testPutAsync() {
        def httpClient = Mockito.mock(RESTClient.class)
        Mockito.when(httpClient.get(Matchers.any())).thenReturn('get')
        testAsync(httpClient, 'putAsync')
    }

    @Test
    public void testDeleteAsync() {
        def httpClient = Mockito.mock(RESTClient.class)
        Mockito.when(httpClient.get(Matchers.any())).thenReturn('get')
        testAsync(httpClient, 'deleteAsync')
    }


    private void testAsync(RESTClient mock, String method) {
        def cd = new CountDownLatch(1)
        def success = false
        AsyncRest."$method"(mock, [path: '/']).subscribe { res ->
            res == method
            success = true
            cd.countDown()
        }
        cd.await()
        assert success
    }

}
