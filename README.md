# AsynRestClient
This is an light weight extension of groovy's RESTClient to support asyn call via RxGroovy


For RESTClient documentation: https://github.com/jgritman/httpbuilder/wiki/RESTClient
For RxGroovy documentation: https://github.com/ReactiveX/RxGroovy

Quick example:

```groovy
new AsyncRestClient('https://google.com)
        .getAsync(path:'/')
        .subscribe { res -> println res.data }
```
And of course harness the power of RxJava :D
```groovy
Observable<Object>.zip(
    new AsyncRestClient('http://someurl.com/part_1').getAsync(),
    new AsyncRestClient('http://someurl.com/part_2').getAsync(),
    { res1, res2
        //proccess them eg:
        [res1.data: res2.data]
    })
    .subscribe { println '2 concurrent rest calls sync later for processing'}
```

