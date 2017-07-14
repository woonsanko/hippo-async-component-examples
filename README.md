# hippo-async-component-examples

Demo Hippo 11.2.1 project to show how Asynchronous HST Component is working in ESI mode.

## Branches to show differences

To show how 

### Phase 0

- ```esiasyncdemo/phase-0```
- Vanilla Hippo project with only News and Events features added by Essentials.

### Phase 1

- ```esiasyncdemo/phase-1```
- Added a new component (```VisitorInfoComponent```) and its template (```visitor-info.ftl```)
  which renders ```User-Agent``` HTTP Request header and current server time
  to simulate dynamic component window portion to be rendered every time,
  regardless of HST Page Caching configuration.
- If you visit [http://localhost:8080/site/](http://localhost:8080/site/),
  you will see the yellow component window below the top menu, showing the ```User-Agent``` header
  and current server time on every visit.

### Phase 2

- ```esiasyncdemo/phase-2```
- Enabled HST Page Caching in configuration. ```hst:cacheable=true```.
- Set ```visitor-info``` component to ```hst:async=true``` and ```hst:asyncmode=esi```.
- Add HST ESI Processor related configuration in ```hst-config.properties```.
- Now, whenever you refresh [http://localhost:8080/site/](http://localhost:8080/site/),
  you will see the data in the yellow component window dynamically changing on every request.
- You can also see the Page Diagnostics logging about page caching. Something like this:
  As you can see below, only the ```VisitorInfoComponent``` is invoked while others are being retrieved from the page cache!
- Also, check only the yellow portion in the page is being refreshed when refreshing the page.

        2017-07-14 17:40:47,652 INFO  http-nio-8080-exec-9 [DiagnosticReportingValve.logDiagnosticSummary:46] Diagnostic Summary:
        - HstDelegateeFilterBean (3ms): {request=Request{ method='GET', scheme='http', host='localhost:8080', requestURI='/site/', queryString='null'}}
          |- Sitemap Matching (0ms): {}
          `- Pipeline processing (3ms): {pipeline=DefaultSitePipeline}
             |- Invoke Valve org.hippoecm.hst.core.container.InitializationValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.CmsSecurityValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.SecurityValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.SubjectBasedSessionValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.JCRSessionStatefulConcurrencyValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.ContextResolvingValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.LocalizationValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.ActionValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.ResourceServingValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.PageInfoRenderingValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.ESIPageInfoScanningValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.PageCachingValve (1ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.ContextResolvingValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.ResourceServingValve (0ms): {}
             |- Invoke Valve org.hippoecm.hst.core.container.ComponentRenderingValve (0ms): {}
             `- Invoke Valve org.hippoecm.hst.core.container.AggregationValve (1ms): {}
                |- HstComponentInvokerProfiler (0ms): {method=doBeforeRender, window=visitor-info, component=org.example.components.VisitorInfoComponent, ref=r6_r2_r1}
                `- HstComponentInvokerProfiler (1ms): {method=doRender, window=visitor-info, component=org.example.components.VisitorInfoComponent, ref=r6_r2_r1}
                   `- Dispatcher (1ms): {dispatch=/freemarker/myhippoproject/visitor-info.ftl}


