package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

import Helper.Mesaj;
import Model.Yonetici;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class AidatGuncelleGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tf_guncel_aidat;
	private JTextField tf_mevcut_aidat;
	static Yonetici yonetici = new Yonetici();
	Mesaj mesaj = new Mesaj();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AidatGuncelleGUI frame = new AidatGuncelleGUI(yonetici);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AidatGuncelleGUI(Yonetici yonetici) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 335, 400, 330);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		panel.setBounds(10, 94, 364, 186);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btn_aidat_guncelle = new JButton("G\u00FCncelle");
		btn_aidat_guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int guncel_aidat = Integer.parseInt(tf_guncel_aidat.getText());

				if (tf_guncel_aidat.getText().equals("")) {

					mesaj.normal_mesaj("Güncel Aidatý Giriniz ");

				} else {
					int control = mesaj.soru_mesaj("Mevcut Aidatý : (" + yonetici.aidat_mevcut() + ")   ,   "
							+ guncel_aidat + " ile deðiþtirmek istediðinize emin misiniz ?");

					if (control == 0) {
						boolean control2 = yonetici.aidat_guncel(guncel_aidat);
						if (control2) {
							mesaj.normal_mesaj("baþarýlý");

							YoneticiGUI yGUI = new YoneticiGUI(yonetici);
							yGUI.setVisible(true);
							dispose();
							tf_guncel_aidat.setText("");
							tf_mevcut_aidat.setText("");
						} else {
							mesaj.normal_mesaj("hata");
						}

					}
				}
			}
		});
		btn_aidat_guncelle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_aidat_guncelle.setBounds(240, 134, 100, 40);
		panel.add(btn_aidat_guncelle);

		JLabel lblNewLabel_1 = new JLabel("G\u00FCncel Aidat (TL)  :");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(30, 93, 160, 30);
		panel.add(lblNewLabel_1);

		tf_guncel_aidat = new JTextField();
		tf_guncel_aidat.setHorizontalAlignment(SwingConstants.CENTER);
		tf_guncel_aidat.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));

		tf_guncel_aidat.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

				char kartNumaraGirilenDeger = e.getKeyChar();

				if ((kartNumaraGirilenDeger < '0' || kartNumaraGirilenDeger > '9') && kartNumaraGirilenDeger != '\b') {
					e.consume();
				}
				if (tf_guncel_aidat.getText().length() > 10) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		tf_guncel_aidat.setColumns(10);
		tf_guncel_aidat.setBounds(200, 93, 140, 30);
		panel.add(tf_guncel_aidat);

		tf_mevcut_aidat = new JTextField(yonetici.aidat_mevcut() + "");
		tf_mevcut_aidat.setHorizontalAlignment(SwingConstants.CENTER);
		tf_mevcut_aidat.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		tf_mevcut_aidat.setEnabled(false);
		tf_mevcut_aidat.setColumns(10);
		tf_mevcut_aidat.setBounds(200, 14, 140, 30);
		panel.add(tf_mevcut_aidat);

		JLabel lblNewLabel = new JLabel("Mevcut Aidat (TL)  : ");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel.setBounds(30, 14, 160, 30);
		panel.add(lblNewLabel);

		JButton btn_geri = new JButton(new ImageIcon("Yonetim Geri.png"));
		btn_geri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				YoneticiGUI yGUI = new YoneticiGUI(yonetici);
				yGUI.setVisible(true);
				dispose();
			}
		});
		btn_geri.setBounds(30, 135, 40, 40);
		panel.add(btn_geri);

		JLabel lblNewLabel_2 = new JLabel("Aidat G\u00FCncelleme ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 15, 364, 24);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(
				"G\u00FCncel Aidat Tutar\u0131n\u0131z\u0131 A\u015Fa\u011F\u0131ya Giriniz ");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(30, 46, 340, 24);
		contentPane.add(lblNewLabel_3);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 80, 364, 2);
		contentPane.add(separator);
	}
}
