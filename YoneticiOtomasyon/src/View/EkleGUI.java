package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Item;
import Helper.Mesaj;
import Model.Daire;
import Model.Kisi;
import Model.Yonetici;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class EkleGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tf_ekle_ad;
	private JTextField tf_ekle_soyad;
	private JTextField tf_ekle_tel_no;
	private JTextField tf_cikan_tel_no;
	private JTextField tf_cikan_soyad;
	private JTextField tf_cýkan_ad;
	private JTextField tf_giden_ad;
	private JTextField tf_giden_soyad;
	private JTextField tf_giden_tel_no;
	private JTextField tf_guncel_ad;
	private JTextField tf_guncel_soyad;
	private JTextField tf_guncel_tel_no;
	Kisi kisi_cikar = new Kisi();

	JComboBox cb_ekle_daire_no = new JComboBox();
	JComboBox cb_cikar_daire_no = new JComboBox();

	JComboBox cb_guncelle_daire_no = new JComboBox();
	Mesaj mesaj = new Mesaj();

	Daire daire = new Daire();
	static Yonetici yonetici = new Yonetici();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EkleGUI frame = new EkleGUI(yonetici);
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
	public EkleGUI(Yonetici yonetici) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btn_geri = new JButton(new ImageIcon("Yonetim Geri.png"));
		btn_geri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				YoneticiGUI yGUI = new YoneticiGUI(yonetici);
				yGUI.setVisible(true);
				dispose();

			}
		});
		
		
		btn_geri.setBounds(40, 40, 40, 40);
		contentPane.add(btn_geri);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 20));
		tabbedPane.setBounds(40, 120, 810, 400);
		contentPane.add(tabbedPane);

		JPanel panel_ekle = new JPanel();
		panel_ekle.setBackground(Color.WHITE);
		tabbedPane.addTab("Ekle", null, panel_ekle, null);
		panel_ekle.setLayout(null);
		cb_ekle_daire_no.setMaximumRowCount(30);
		cb_ekle_daire_no.setToolTipText("");
		cb_ekle_daire_no.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));

		cb_ekle_daire_no.setBounds(390, 30, 200, 40);
		panel_ekle.add(cb_ekle_daire_no);

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		// boþ daire listeleme combobox

		cb_ekle_daire_no.addItem("*Boþ Olan Daireler");
		for (int i = 0; i < daire.bos_daire_liste().size(); i++) {

			cb_ekle_daire_no.addItem(
					new Item(daire.bos_daire_liste().get(i).getId(), daire.bos_daire_liste().get(i).getDaire_no())
							.getValue());
		}

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		JLabel lbl_daire_no = new JLabel("Daire No : ");
		lbl_daire_no.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_daire_no.setBounds(210, 30, 150, 40);
		panel_ekle.add(lbl_daire_no);

		JLabel lbl_ad = new JLabel("Ad : ");
		lbl_ad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_ad.setBounds(210, 110, 150, 40);
		panel_ekle.add(lbl_ad);

		JLabel lbl_soyad = new JLabel("Soyad : ");
		lbl_soyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_soyad.setBounds(210, 170, 150, 40);
		panel_ekle.add(lbl_soyad);

		tf_ekle_ad = new JTextField();
		tf_ekle_ad.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		tf_ekle_ad.setBounds(390, 111, 200, 40);
		panel_ekle.add(tf_ekle_ad);
		tf_ekle_ad.setColumns(10);

		tf_ekle_soyad = new JTextField();
		tf_ekle_soyad.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		tf_ekle_soyad.setColumns(10);
		tf_ekle_soyad.setBounds(390, 171, 200, 40);
		panel_ekle.add(tf_ekle_soyad);

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		// dolu daire liste combobox

		cb_cikar_daire_no.addItem("*Dolu Olan Daireler");

		for (int i = 0; i < daire.dolu_daire_liste().size(); i++) {

			cb_cikar_daire_no.addItem(
					new Item(daire.dolu_daire_liste().get(i).getId(), daire.dolu_daire_liste().get(i).getDaire_no())
							.getValue());
		}
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		// Ekle Button
		JButton btn_ekle = new JButton("Ekle");
		btn_ekle.setEnabled(false);
		btn_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tf_ekle_ad.getText().equals("") || tf_ekle_soyad.getText().equals("")
						|| tf_ekle_tel_no.getText().equals("")) {
					mesaj.normal_mesaj("Lütfen Tüm Alanlarý Doldurunuz !!");
				} else {
					Kisi kisi = new Kisi((Integer) cb_ekle_daire_no.getSelectedItem(), tf_ekle_ad.getText(),
							tf_ekle_soyad.getText(), tf_ekle_tel_no.getText());

					boolean control = kisi.add_kisi(kisi);
					if (control) {
						mesaj.normal_mesaj("baþarýlý");
						Daire daire = new Daire((Integer) cb_ekle_daire_no.getSelectedItem());
						daire.update_daire_tip_dolu(daire);

						// Update
						update_ekle_cb();

						tf_ekle_ad.setText("");
						tf_ekle_soyad.setText("");
						tf_ekle_tel_no.setText("");

					} else {
						mesaj.normal_mesaj("hata");
					}
				}

			}
		});

		cb_ekle_daire_no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (cb_ekle_daire_no.getSelectedIndex() == 0) {
					btn_ekle.setEnabled(false);
				} else {
					btn_ekle.setEnabled(true);
				}

			}
		});

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		btn_ekle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_ekle.setBounds(273, 308, 150, 40);
		panel_ekle.add(btn_ekle);

		JLabel lbl_tel_no = new JLabel("Tel No : ");
		lbl_tel_no.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_tel_no.setBounds(210, 237, 150, 40);
		panel_ekle.add(lbl_tel_no);

		tf_ekle_tel_no = new JTextField();
		tf_ekle_tel_no.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		tf_ekle_tel_no.setBounds(390, 238, 200, 40);
		panel_ekle.add(tf_ekle_tel_no);
		tf_ekle_tel_no.setColumns(10);
		
		tf_ekle_tel_no.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (tf_ekle_tel_no.getText().length() >= 11) {
					e.consume();
				}
				char c = e.getKeyChar();

				if (!Character.isDigit(c)) {
					e.consume();
				}

			}
		});

		JLabel lblNewLabel = new JLabel("*Bo\u015F Olan Daire Numaralar\u0131");
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		lblNewLabel.setBounds(600, 30, 195, 25);
		panel_ekle.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Çýkart", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lbl_tel_no_1 = new JLabel("Tel No : ");
		lbl_tel_no_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_tel_no_1.setBounds(210, 235, 150, 40);
		panel_1.add(lbl_tel_no_1);

		tf_cikan_tel_no = new JTextField();
		tf_cikan_tel_no.setEnabled(false);
		tf_cikan_tel_no.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		tf_cikan_tel_no.setColumns(10);
		tf_cikan_tel_no.setBounds(390, 236, 200, 40);
		panel_1.add(tf_cikan_tel_no);

		tf_cikan_soyad = new JTextField();
		tf_cikan_soyad.setEnabled(false);
		tf_cikan_soyad.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		tf_cikan_soyad.setColumns(10);
		tf_cikan_soyad.setBounds(390, 167, 200, 40);
		panel_1.add(tf_cikan_soyad);

		JLabel lbl_soyad_1 = new JLabel("Soyad : ");
		lbl_soyad_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_soyad_1.setBounds(210, 166, 150, 40);
		panel_1.add(lbl_soyad_1);

		JLabel lbl_ad_1 = new JLabel("Ad : ");
		lbl_ad_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_ad_1.setBounds(210, 100, 150, 40);
		panel_1.add(lbl_ad_1);

		tf_cýkan_ad = new JTextField();
		tf_cýkan_ad.setEnabled(false);
		tf_cýkan_ad.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		tf_cýkan_ad.setColumns(10);
		tf_cýkan_ad.setBounds(390, 101, 200, 40);
		panel_1.add(tf_cýkan_ad);

		cb_cikar_daire_no.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		cb_cikar_daire_no.setBounds(390, 35, 200, 40);
		panel_1.add(cb_cikar_daire_no);

		JButton btn_cikar = new JButton("Çýkart");
		btn_cikar.setEnabled(false);
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		// combobox_cikar basýldýðý an
		cb_cikar_daire_no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Kisi kisi = new Kisi();

				if (cb_cikar_daire_no.getSelectedIndex() != 0) {

					kisi = kisi.kisi_daire_no((Integer) cb_cikar_daire_no.getSelectedItem());

					tf_cýkan_ad.setText(kisi.getAd());
					tf_cikan_soyad.setText(kisi.getSoyad());
					tf_cikan_tel_no.setText(kisi.getTel_no());

					btn_cikar.setEnabled(true);
				} else {

					btn_cikar.setEnabled(false);

				}

			}
		});

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		JLabel lbl_daire_no_1 = new JLabel("Daire No : ");
		lbl_daire_no_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_daire_no_1.setBounds(210, 30, 150, 40);
		panel_1.add(lbl_daire_no_1);

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		btn_cikar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Kisi kisi = new Kisi();

				int control2 = mesaj
						.soru_mesaj(tf_cýkan_ad.getText() + "  adlý kiþiyi çýkartmak istediðinize emin misiniz ?");
				if (control2 == 0) {

					boolean control = kisi.kisi_deleted((Integer) cb_cikar_daire_no.getSelectedItem());
					Daire daire = new Daire((Integer) cb_cikar_daire_no.getSelectedItem());
					daire.update_daire_tip_bos(daire);
					if (control) {
						mesaj.normal_mesaj("baþarýlý");

						// update
						update_ekle_cb();
						DefaultComboBoxModel model = (DefaultComboBoxModel) cb_cikar_daire_no.getModel();
						model.removeElement(cb_cikar_daire_no.getSelectedItem());

						tf_cýkan_ad.setText("");
						tf_cikan_soyad.setText("");
						tf_cikan_tel_no.setText("");

					} else {
						mesaj.normal_mesaj("hata");
					}
				}

			}
		});

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		btn_cikar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_cikar.setBounds(290, 300, 142, 40);
		panel_1.add(btn_cikar);

		JLabel lblNewLabel_1 = new JLabel("*Dolu Olan Daire Numaralar\u0131");
		lblNewLabel_1.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(600, 35, 195, 25);
		panel_1.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Güncelle", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lbl_daire_no_1_1 = new JLabel("Daire No : ");
		lbl_daire_no_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_daire_no_1_1.setBounds(230, 21, 150, 40);
		panel_2.add(lbl_daire_no_1_1);
		cb_guncelle_daire_no.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));

		cb_guncelle_daire_no.setBounds(364, 21, 200, 40);
		panel_2.add(cb_guncelle_daire_no);

		tf_giden_ad = new JTextField();
		tf_giden_ad.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		tf_giden_ad.setEnabled(false);
		tf_giden_ad.setColumns(10);
		tf_giden_ad.setBounds(180, 92, 200, 40);
		panel_2.add(tf_giden_ad);

		JLabel lbl_ad_1_1 = new JLabel("Ad : ");
		lbl_ad_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_ad_1_1.setBounds(10, 91, 150, 40);
		panel_2.add(lbl_ad_1_1);

		JLabel lbl_soyad_1_1 = new JLabel("Soyad : ");
		lbl_soyad_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_soyad_1_1.setBounds(10, 157, 150, 40);
		panel_2.add(lbl_soyad_1_1);

		tf_giden_soyad = new JTextField();
		tf_giden_soyad.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		tf_giden_soyad.setEnabled(false);
		tf_giden_soyad.setColumns(10);
		tf_giden_soyad.setBounds(180, 158, 200, 40);
		panel_2.add(tf_giden_soyad);

		tf_giden_tel_no = new JTextField();
		tf_giden_tel_no.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		tf_giden_tel_no.setEnabled(false);
		tf_giden_tel_no.setColumns(10);
		tf_giden_tel_no.setBounds(180, 227, 200, 40);
		panel_2.add(tf_giden_tel_no);

		JLabel lbl_tel_no_1_1 = new JLabel("Tel No : ");
		lbl_tel_no_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_tel_no_1_1.setBounds(10, 226, 150, 40);
		panel_2.add(lbl_tel_no_1_1);

		JButton btn_guncelle = new JButton("G\u00FCncelle");
		btn_guncelle.setEnabled(false);

		btn_guncelle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		btn_guncelle.setBounds(320, 292, 142, 40);
		panel_2.add(btn_guncelle);

		JLabel lbl_ad_1_1_1 = new JLabel("Ad : ");
		lbl_ad_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_ad_1_1_1.setBounds(425, 92, 150, 40);
		panel_2.add(lbl_ad_1_1_1);

		tf_guncel_ad = new JTextField();
		tf_guncel_ad.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		tf_guncel_ad.setColumns(10);
		tf_guncel_ad.setBounds(595, 93, 200, 40);
		panel_2.add(tf_guncel_ad);

		tf_guncel_soyad = new JTextField();
		tf_guncel_soyad.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		tf_guncel_soyad.setColumns(10);
		tf_guncel_soyad.setBounds(595, 159, 200, 40);
		panel_2.add(tf_guncel_soyad);

		JLabel lbl_soyad_1_1_1 = new JLabel("Soyad : ");
		lbl_soyad_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_soyad_1_1_1.setBounds(425, 158, 150, 40);
		panel_2.add(lbl_soyad_1_1_1);

		JLabel lbl_tel_no_1_1_1 = new JLabel("Tel No : ");
		lbl_tel_no_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_tel_no_1_1_1.setBounds(425, 227, 150, 40);
		panel_2.add(lbl_tel_no_1_1_1);

		tf_guncel_tel_no = new JTextField();
		tf_guncel_tel_no.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		tf_guncel_tel_no.setColumns(10);
		tf_guncel_tel_no.setBounds(595, 228, 200, 40);
		panel_2.add(tf_guncel_tel_no);

		cb_guncelle_daire_no.addItem("*Dolu Olan Daireler");
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 100, 864, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 525, 864, 2);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ekle : Yeni ki\u015Fi ekler");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(469, 11, 405, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("\u00C7\u0131kart : Se\u00E7ilen ki\u015Fiyi \u00E7\u0131kart\u0131r");
		lblNewLabel_2_1.setForeground(Color.GRAY);
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(469, 40, 405, 24);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("G\u00FCncelle : Se\u00E7ilen daireki ki\u015Fiyi yeni ki\u015Fiyle de\u011Fi\u015Ftirir");
		lblNewLabel_2_2.setForeground(Color.GRAY);
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_2.setBounds(469, 75, 405, 24);
		contentPane.add(lblNewLabel_2_2);

		for (int i = 0; i < daire.dolu_daire_liste().size(); i++) {

			cb_guncelle_daire_no.addItem(
					new Item(daire.dolu_daire_liste().get(i).getId(), daire.dolu_daire_liste().get(i).getDaire_no())
							.getValue());
		}

		cb_guncelle_daire_no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (cb_guncelle_daire_no.getSelectedIndex() != 0) {
					Kisi kisi = new Kisi();
					kisi = kisi.kisi_daire_no((Integer) cb_guncelle_daire_no.getSelectedItem());

					tf_giden_ad.setText(kisi.getAd());
					tf_giden_soyad.setText(kisi.getSoyad());
					tf_giden_tel_no.setText(kisi.getTel_no());

					btn_guncelle.setEnabled(true);
				} else {
					btn_guncelle.setEnabled(false);
				}
			}
		});

		btn_guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tf_guncel_ad.getText().equals("") || tf_guncel_soyad.getText().equals("")
						|| tf_guncel_tel_no.getText().equals("")) {
					mesaj.normal_mesaj("Lütfen Tüm Alanlarý Doldurunuz !!");
				} else {
					Kisi kisi = new Kisi((Integer) cb_guncelle_daire_no.getSelectedItem(), tf_guncel_ad.getText(),
							tf_guncel_soyad.getText(), tf_guncel_tel_no.getText());

					Kisi kisi2 = new Kisi((Integer) cb_guncelle_daire_no.getSelectedItem(), tf_giden_ad.getText(),
							tf_giden_soyad.getText(), tf_giden_tel_no.getText());
					boolean control2 = kisi2.kisi_deleted((Integer) cb_guncelle_daire_no.getSelectedItem());
					boolean control = kisi.add_kisi(kisi);
					if (control && control2) {
						mesaj.normal_mesaj("baþarýlý");
						Daire daire = new Daire((Integer) cb_guncelle_daire_no.getSelectedItem());
						daire.update_daire_tip_dolu(daire);

						// Update
						tf_guncel_ad.setText("");
						tf_guncel_soyad.setText("");
						tf_guncel_tel_no.setText("");

						tf_giden_ad.setText("");
						tf_giden_soyad.setText("");
						tf_giden_tel_no.setText("");

					} else {
						mesaj.normal_mesaj("hata");
					}
				}

			}
		});

	}

	public void update_ekle_cb() {

		cb_ekle_daire_no.removeAllItems();
		cb_ekle_daire_no.addItem("*Boþ Daireler");
		for (int i = 0; i < daire.bos_daire_liste().size(); i++) {

			cb_ekle_daire_no.addItem(
					new Item(daire.bos_daire_liste().get(i).getId(), daire.bos_daire_liste().get(i).getDaire_no())
							.getValue());
		}
	}

}
