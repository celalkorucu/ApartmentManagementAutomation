package Helper;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Mesaj {

	public void normal_mesaj(String str) {

		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		UIManager.put("OptionPane.okButtonText", "Tamam");

		switch (str) {
		case "baþarýlý": {

			JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý", "Sistem Bildirimi", 3,
					new ImageIcon("Yonetim Tik.png"));

			break;

		}
		case "hata": {
			JOptionPane.showInternalMessageDialog(null, "Hata", "Sistem Bildirimi", 2,
					new ImageIcon("Yonetim Çarpý.png"));

			break;
		}
		case "bulunamadý": {
			JOptionPane.showMessageDialog(null, "Bu þifreye ait bir kullanýcý bulunamadý ", "Sistem Bildirimi", 0,
					new ImageIcon("Yonetim Bulunamadý.png"));

			break;
		}

		case "alan": {
			JOptionPane.showMessageDialog(null, "Lütfen þifrenizi giriniz", "Sistem Bildirimi", 0,
					new ImageIcon("Yonetim Alan.jpg"));
			break;
		}
		default: {
			JOptionPane.showMessageDialog(null, str, "Sistem Bildirimi", 0, new ImageIcon("Yonetim Alan.jpg"));
			break;
		}
		}
	}

	public int soru_mesaj(String str) {

		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
		switch (str) {
		case "emin": {

			int a = JOptionPane.showInternalConfirmDialog(null, "Emin misiniz ?", "Sistem Bildirimi", 0, 0,
					new ImageIcon("Yonetim Soru.png"));

			return a;
		}
		
		
		default : {
			int b = JOptionPane.showConfirmDialog(null, str, "Sistem Bildirimi", 0, 0, new ImageIcon("Yonetim Soru.png"));
			
			return b ;
		}

		}
	
	}
}
