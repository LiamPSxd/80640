package mx.uv.c80640;

import static spark.Spark.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if(accessControlRequestHeaders != null){
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if(accessControlRequestMethod != null){
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            
            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/hello", (req, res) -> "Hello World");
        get("/eduardo", (req, res) -> "Hello Eduardo");
        get("/alex", (req, res) -> "Hello Alex");
        get("/brandon", (req, res) -> "Hello Brandon");
        get("/", (req, res) -> "hola");

        post("/", (req, res) ->{
            JsonObject oRespuesta= new JsonObject();
            oRespuesta.addProperty("msj","hola");
            //System.out.println(req.queryParams("email")+" "+ req.queryParams("password"));
            //System.out.println(req.body());
        
            if(req.queryParams("email")!=null){
                JsonObject urlString =new JsonObject();
                urlString.addProperty("email", req.queryParams("email"));
                urlString.addProperty("email", req.queryParams("password"));
                oRespuesta.addProperty("email", req.queryParams("email"));
            }else{
                JsonObject peticionCliente = new JsonObject();
                peticionCliente = JsonParser.parseString(req.body()).getAsJsonObject();
                oRespuesta.addProperty("email", peticionCliente.get("email").getAsString());
            }
 
            // System.out.println(peticionCliente.get("email"));
            // System.out.println(peticionCliente.get("password"));
            // System.out.println(parser);

            res.status(200);
            return oRespuesta;
        });
    }
}