import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer extends Thread {
	private DatagramSocket socket;
	private boolean running;
	private byte[] buf = new byte[256];

	public UDPServer() throws SocketException {
		socket = new DatagramSocket(4445);
	}
	public void run() {
		running = true;
		while (running) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("server running...");

			// receive message from client
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			packet = new DatagramPacket(buf, buf.length, address, port);
			String received = new String(packet.getData(), 0, packet.getLength());
			System.out.println(received);

			if (received.equals("")) {
				running = false;
				continue;
			}

			// return message for client
			String response = calculate(received.trim());
			DatagramPacket newPacket = new DatagramPacket(response.getBytes(), response.getBytes().length, address, port);
			String newPacketString = new String(newPacket.getData(), 0, newPacket.getLength());
			System.out.println(newPacketString);
			try {
				socket.send(newPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		socket.close();
	}
	private static String calculate(String input) {
		//checks input format
		String result;
		if (!(input.matches("(\\d+([+]|[-]))\\d+"))) {
			//invalid input
			return "invalid input";
		}
		//interprets numbers
		String[] integers = input.split("[+]|[-]");
		int integer1 = Integer.parseInt(integers[0]);
		int integer2 = Integer.parseInt(integers[1]);

		// calculates -
		if (input.indexOf('-') != -1) {
			result = Integer.toString(integer1 - integer2);
		}
		// calculates +
		else if (input.indexOf('+') != -1) {
			result = Integer.toString(integer1 + integer2);
		}
		else {
			result = "unexpected operator";
		}
		return result;
	}

	public static void main(String[] args) throws SocketException {
		UDPServer udpServer = new UDPServer();
		udpServer.run();
	}
}