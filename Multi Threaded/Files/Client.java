import java.io.IOException;

public class Client {
    //making a fuctional interface
    public Runnable getRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                int port = 9091;
            }
        };

    }
    public static void main(String[] args) {
        Client client = new Client();
        for(int i =0;i<100;i++){
            try{
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            }catch (Exception ex){
                return;
            }
        }

    }
}
