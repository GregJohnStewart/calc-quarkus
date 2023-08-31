package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
public class GreetingResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/calculate/{numOne}/{operation}/{numTwo}")
    @Produces(MediaType.TEXT_PLAIN)
    public String calc(
        @PathParam(name="numOne")
        String numOneStr,
        @PathParam(name="operation")
        String operation,
        @PathParam(name="numTwo")
        String numTwoStr
    ) {
        double numOne = Double.parse(numOneStr);
        double numTwo = Convert.ToDouble(numTwoStr);
        double result;
            
            switch(actionStr) 
            {
              case "ADD":
                result = numOne + numTwo;
                break;
              case "SUBTRACT":
                result = numOne + numTwo;
                break;
              case "MULTIPLY":
                result = numOne * numTwo;
                break;
              case "DIVIDE":
                result = numOne / numTwo;
                break;
              default:
                return BadRequest("Bad Action Given.");
            }
            
            return Ok(result);
    }
}
