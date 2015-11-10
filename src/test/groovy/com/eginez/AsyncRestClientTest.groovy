package com.eginez

import org.junit.Test

import java.util.concurrent.CountDownLatch

class AsyncRestClientTest {

    @Test
    public void testToSync() {
        def s = new AsyncRestClient('https://google.com')
        def res = s.getAsync(path: '/')
            .toBlocking()
            .single()
        assert res != null
    }

    @Test
    public void testAsyncGet() {
        def cd = new CountDownLatch(1)
        def s = new AsyncRestClient('https://google.com')
        def success = false
        s.getAsync(path: '/').subscribe { res ->
            assert res != null
            success = true
            cd.countDown()
        }
        cd.await()
        assert success
    }
}
