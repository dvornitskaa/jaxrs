package oksana.dvornitska.resources;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oksana.dvornitska.model.WarehouseCollection;
import oksana.dvornitska.model.WarehouseDto;
import oksana.dvornitska.resources.beans.WarehouseFilter;
import oksana.dvornitska.service.WarehouseService;
import oksana.dvornitska.service.interfaces.WarehouseServiceI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/warehouses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WarehouseResource {

	WarehouseServiceI warehouseService = new WarehouseService();

    @GET
    public Response getCollection(@BeanParam WarehouseFilter warehouseFilter) {
        return Response
                .status(Response.Status.OK)
                .entity(warehouseService.getCollection(warehouseFilter))
                .build();

    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") int id) {
        return Response
                .status(Response.Status.OK)
                .entity(warehouseService.getById(id))
                .build();

    }

    @POST
    public Response create(WarehouseDto warehouse) {
        warehouseService.create(warehouse);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    public Response update(WarehouseDto warehouse) {
        warehouseService.update(warehouse);
        return Response
                .status(Response.Status.OK)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        warehouseService.delete(id);
        return Response
                .status(Response.Status.OK)
                .build();
    }

}
