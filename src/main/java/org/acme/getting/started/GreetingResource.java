package org.acme.getting.started;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello")
public class GreetingResource {

    Logger log = LoggerFactory.getLogger(GreetingResource.class);

    @Inject
    DataSource ds;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        StringBuilder builder = new StringBuilder();
        try(Connection con = ds.getConnection()) {
            String database = con.getMetaData().getDatabaseProductName();
            int version = con.getMetaData().getDatabaseMajorVersion();

            builder.append("Database name: ").append(database).append(" Version: ").append(version);

            return builder.toString();

        } catch(SQLException sqle) {
            log.error("Error connecting to the database", sqle);
            return sqle.getMessage();
        }

    }
}