import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf = new byte[256];
    public UDPClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }
    public String sendEcho(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }
    public void close() {
    socket.close();
    }

    public static void main(String[] args) throws IOException {
        UDPClient udpClient = new UDPClient();
        System.out.println("Skriv hva du vil på formatet \'heltall1[+|-]heltall2\', så skal jeg regne ut, avslutt med linjeskift: ");
        Scanner scanner = new Scanner(System.in);
        String serverRequest = scanner.nextLine();
        while (!serverRequest.equals("")) {
            String response = (udpClient.sendEcho(serverRequest));
            System.out.println("server said: " + response);
            serverRequest = scanner.nextLine();
        }
    }
}