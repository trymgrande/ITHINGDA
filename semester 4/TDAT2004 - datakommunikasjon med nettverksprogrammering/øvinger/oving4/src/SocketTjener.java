import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class SocketTjener {
	public static void main(String[] args) throws IOException {
		final int PORTNR = 1251;

		ServerSocket tjener = new ServerSocket(PORTNR);
		System.out.println("Logg for tjenersiden. N� venter vi...");




		while (true) {
			Socket forbindelse = tjener.accept();  // venter inntil noen tar kontakt
			Thread klienttr�d = new TradKlientHandterer(forbindelse);
			klienttr�d.start();
		}
	}
}