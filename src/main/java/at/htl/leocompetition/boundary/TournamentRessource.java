package at.htl.leocompetition.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("tournament")
public class TournamentRessource {
    @GET
    public Response getTournament() {
        return Response.ok().entity("Test Turnier").build();
    }
}
