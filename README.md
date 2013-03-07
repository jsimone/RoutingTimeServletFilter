# Routing Time Measurement Filter

This servlet filter will print the total time spent in your routing layer to standard out. It works by computing the difference between the `x-request-start` HTTP header value and the current time. The printed value will be the time elapsed between the routing layer recieving and timestamping your request and your application beginning to process it. This accounts for time spent in the router itself (which should be relatively constant) and time spent waiting for your application to have a thread available to handle the request.

To work, the filter requires the `x-request-start` value to be set to milliseconds elapsed since UNIX epoch. The value should not be prepented with `t=` nor use microseconds since UNIX epoch.

If you're interested, you can read more about [how Heroku sets the header value](https://devcenter.heroku.com/articles/http-routing) and about [how New Relic interprets it](https://newrelic.com/docs/features/request-queueing-and-tracking-front-end-time).

## Usage

First, copy the servlet filter class file into an appropriate place in the source code of your app.

Then update your web.xml with something like this:

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

Replace org.example.RoutingTimeFilter with your own package location.
