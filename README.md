Test Project for PAXWEB-344
===========================

This project is an effort to figure out the troubles related to PAXWEB-344 and
registering filters that should be used across multiple bundles.

Projects
--------

* bundle1
   * `Bundle1Servlet` and `Bundle1Filter` share a default http context associated to
     the bundle and are registered via Whiteboard.
   * `Bundle1SharedFilter` registers itself to the default shared http context.

* bundle2
   * `Bundle2SharedServlet` and `Bundle2SharedFilter` are registered with the
     default shared http context.

Building
--------

Run `mvn install` in the same directory as this file and that will take care of
the artifacts created to test this.

This work is based on the following patch to Pax Web. Apply this to the master
branch (3.0.0-SNAPSHOT) and perform a build to get updated artifacts before
running Pax Runner.

```diff
--- a/pax-web-spi/src/main/java/org/ops4j/pax/web/service/spi/model/ServerModel.java
+++ b/pax-web-spi/src/main/java/org/ops4j/pax/web/service/spi/model/ServerModel.java
@@ -248,7 +248,7 @@ public class ServerModel
                                       final boolean allowReAsssociation )
     {
         final Bundle currentBundle = m_httpContexts.putIfAbsent( httpContext, bundle );
-        if( ( !!allowReAsssociation ) && currentBundle != null && currentBundle != bundle )
+        if( ( !allowReAsssociation ) && currentBundle != null && currentBundle != bundle )^M
         {
             throw new IllegalStateException(
                 "Http context " + httpContext + " is already associated to bundle " + currentBundle
```

Running
-------

Pax Runner is used to deploy these bundles into a live environment. The command
below will put everything together for you.

```
pax-run org.ops4j.pax.web.344.composite
```
