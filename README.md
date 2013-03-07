# Routing Time Measurement Filter

This servlet filter will print the total time spent in your routing layer to standard out. It works by computing the difference between the x-request-start header value and the current time. The time printed out will be the difference between when the routing layer recieved your request and when your application recieves it. This accounts for time spent in the router itself (which should be relatively constant) and time spent waiting for your application to have an available thread to handle the request.

## Usage

First copy the servlet filter into an appropriate place in the source code of your app.

Then update your web.xml with something like the following:

      <filter>
          <filter-name>RoutingFilter</filter-name>
          <filter-class>
              org.example.RoutingTimeFilter
          </filter-class>
      </filter>
      <filter-mapping>
          <filter-name>RoutingFilter</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>

Replacing org.example.RoutingTimeFilter with your own package location.
