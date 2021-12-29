import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Vindu extends JFrame{
	public Vindu(String tittel){
		setTitle(tittel);
		setSize(200,120); // bredde, lengde
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tegning tegningen= new Tegning();
		add(tegningen);
	}
}

class Tegning extends JPanel{
	public void paintComponent(Graphics tegneflate){
		super.paintComponent(tegneflate);
		setBackground(Color.WHITE);
		tegneflate.drawString("Vamos Barcelona",60,60);
		tegneflate.drawOval(50,40,65,50);
	}
}

class GrafikkEksempel{
	public static void main(String[]args){
		Vindu etVindu = new Vindu("Enkel grafikk");
		etVindu.setVisible(true);
	}
}