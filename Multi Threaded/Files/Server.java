import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
     //here we use functional interface (Because they are first class citizen)
    public Consumer<Socket> getConsumer(){
        // created a lamda function which returns nothing...basically lamda func is used to implement the functional interface

        return (clientSocket)->{
            try{
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                System.out.println("Hello from the server");
                toClient.close();
                clientSocket.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        };

    }
    public static void main(String[] args) {
        int port = 9091;

        Server server = new Server();
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is listening on port" + port);
            while (true){
                //this is the new socket created
                Socket acceptSocket = serverSocket.accept();
                Thread thread = new Thread(()->server.getConsumer().accept(acceptSocket));
                thread.start();
            }


        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
