package uv.mx;

import static spark.Spark.*;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class AppDB {
    public static Gson gson = new Gson();

    public static void main(String[] args) {

        port(80);

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));

        System.out.println("Hello World!");
        before((req, res) -> res.type("application/json"));
        
        get("/usuarios", (req, res) -> gson.toJson(DAO.getUsuarios()));

        post("/", (req, res) -> {
            String datosFormulario = req.body();
            Usuario u = gson.fromJson(datosFormulario, Usuario.class);
            
            return DAO.addUsuario(u);
        });
    }
}