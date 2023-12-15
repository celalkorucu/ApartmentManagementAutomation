package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Mesaj;
import Model.Yonetici;
import Model.Borc;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.ZonedDateTime;
import java.util.*;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Window.Type;

public class GirisGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField pw_sifre;
	Mesaj mesaj = new Mesaj();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisGUI frame = new GirisGUI();
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
	public GirisGUI() {
		setResizable(false);
		setTitle("Y\u00D6NET\u0130C\u0130 OTOMASYON");

		ZonedDateTime zonedDateTime = ZonedDateTime.now();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Y\u00F6netici Otomasyonuna Ho\u015Fgeldiniz ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel.setBounds(244, 11, 412, 40);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("Yonetim Logo.png"));
		lblNewLabel_1.setBounds(300, 88, 300, 166);
		contentPane.add(lblNewLabel_1);

		pw_sifre = new JPasswordField();
		pw_sifre.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		pw_sifre.setBounds(350, 372, 200, 40);
		contentPane.add(pw_sifre);

		pw_sifre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (pw_sifre.getText().length() >= 4) {
					e.consume();
				}
				char c = e.getKeyChar();

				if (!Character.isDigit(c)) {
					e.consume();
				}

			}
		});

		JLabel lblNewLabel_2 = new JLabel("Sisteme girmek i\u00E7in y\u00F6netici \u015Fifrenizi giriniz");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(225, 321, 450, 40);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_6.setBackground(Color.RED);
		lblNewLabel_6.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(560, 372, 314, 25);
		contentPane.add(lblNewLabel_6);

		JButton btn_giris_yap = new JButton("Giri\u015F Yap");
		btn_giris_yap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Yonetici yonetici = new Yonetici();

				yonetici = yonetici.dogrula(pw_sifre.getText());
				if (pw_sifre.getText().equals("")) {
					mesaj.normal_mesaj("alan");
				} 
				else if (pw_sifre.getText().length() < 4) {

					lblNewLabel_6.setText("* Lütfen tüm alanlarý doldurunuz !!!");

				}
				else {
					
					if (yonetici != null) {
						YoneticiGUI yGUI = new YoneticiGUI(yonetici);
						yGUI.setVisible(true);
						dispose();
					}

					if (yonetici == null) {
						mesaj.normal_mesaj("bulunamadý");
					}

				}
			}
		});
		btn_giris_yap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_giris_yap.setBounds(390, 450, 120, 40);
		contentPane.add(btn_giris_yap);

		JLabel lblNewLabel_3 = new JLabel(
				zonedDateTime.getDayOfMonth() + "." + zonedDateTime.getMonthValue() + "." + zonedDateTime.getYear());
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(774, 62, 100, 40);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(zonedDateTime.getHour() + ":" + zonedDateTime.getMinute()+":"+zonedDateTime.getSecond());
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(784, 11, 80, 40);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(new ImageIcon("Yonetim Saat.png"));
		lblNewLabel_5.setBounds(729, 19, 25, 25);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel(new ImageIcon("Yonetim Tarih.png"));
		lblNewLabel_5_1.setBounds(729, 70, 25, 25);
		contentPane.add(lblNewLabel_5_1);

		Timer t = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ZonedDateTime zo = ZonedDateTime.now();
				lblNewLabel_4.setText(zo.getHour() + ":" + zo.getMinute() + ":" + zo.getSecond());

			}
		});
		t.start();
	}
}
