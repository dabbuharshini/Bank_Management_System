import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.*;
import java.net.InetSocketAddress;
import java.io.*;
import java.nio.file.Files;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class BankHttpServer {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(9000),0);

        server.createContext("/", new FrontendHandler());
        server.createContext("/bank", new BankHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server started at http://localhost:9000");
    }
}

class FrontendHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {

        File file = new File("../frontend/index.html");

        byte[] response = Files.readAllBytes(file.toPath());

        exchange.sendResponseHeaders(200,response.length);

        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }
}

class BankHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {

        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(),"utf-8");
        BufferedReader br = new BufferedReader(isr);

        StringBuilder body = new StringBuilder();
        String line;

        while((line = br.readLine()) != null){
            body.append(line);
        }

        String query = body.toString();

        HashMap<String,String> params = new HashMap<>();

        if(query != null && !query.isEmpty()){

            for(String pair : query.split("&")){

                String[] kv = pair.split("=");

                if(kv.length == 2){
                    params.put(
                        URLDecoder.decode(kv[0], StandardCharsets.UTF_8),
                        URLDecoder.decode(kv[1], StandardCharsets.UTF_8)
                    );
                }
            }
        }
        String action = params.get("action");

        String result = "";

        if("create".equals(action))
            result = BankService.createAccount(
                        params.get("name"),
                        params.get("aadhaar"),
                        params.get("phone"),
                        params.get("address")
                    );

        if("deposit".equals(action))
            result = BankService.deposit(
                    Long.parseLong(params.get("id")),
                    Double.parseDouble(params.get("amount"))
            );

        if("withdraw".equals(action))
            result = BankService.withdraw(
                    Long.parseLong(params.get("id")),
                    Double.parseDouble(params.get("amount"))
            );

        if("balance".equals(action))
            result = BankService.checkBalance(
                    Long.parseLong(params.get("id"))
            );

        if("transactions".equals(action))
            result = BankService.getTransactions(
                Long.parseLong(params.get("accountId"))
            );
            
        byte[] response = result.getBytes();

        exchange.sendResponseHeaders(200,response.length);

        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }
}