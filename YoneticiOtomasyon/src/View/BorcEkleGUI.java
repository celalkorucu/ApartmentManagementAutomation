package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Helper.Mesaj;
import Model.Borc;
import Model.Kisi;
import Model.Yonetici;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.time.Month;
import java.time.ZonedDateTime;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class BorcEkleGUI extends JFrame {

	private JPanel contentPane;
	private JTable table_eklenmemis_liste;
	private JTable table_secilen_borc_liste;
	Borc borc = new Borc();
	DefaultTableModel eklenmemis_model = new DefaultTableModel();
	DefaultTableModel eklenmis_model = new DefaultTableModel();
	Mesaj mesaj = new Mesaj();
	Object kisi_data[];
	Object borc_data[];
	int row;

	static Yonetici yonetici = new Yonetici();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorcEkleGUI frame = new BorcEkleGUI(yonetici);
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
	public BorcEkleGUI(Yonetici yonetici) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		System.out.println("return aidat : "+yonetici.return_aidat());
		eklenmemis_model = new DefaultTableModel();
		Object col_kisi[] = new Object[3];
		col_kisi[0] = "Daire No";
		col_kisi[1] = "Ad";
		col_kisi[2] = "Soyad";
		eklenmemis_model.setColumnIdentifiers(col_kisi);

		kisi_data = new Object[3];

		// Eklenecek liste
		if (borc.borc_eklenecek_liste() != null) {
			for (int i = 0; i < borc.borc_eklenecek_liste().size(); i++) {
				kisi_data[0] = borc.borc_eklenecek_liste().get(i).getDaire_no();
				kisi_data[1] = borc.borc_eklenecek_liste().get(i).getAd();
				kisi_data[2] = borc.borc_eklenecek_liste().get(i).getSoyad();

				eklenmemis_model.addRow(kisi_data);
			}
		}

		// Eklenmiþ Liste

		eklenmis_model = new DefaultTableModel();
		Object col_borc[] = new Object[3];
		col_borc[0] = "Ay";
		col_borc[1] = "Yýl";
		col_borc[2] = "Borç";
		eklenmis_model.setColumnIdentifiers(col_borc);

		borc_data = new Object[3];

		// Eklenecek liste

		ZonedDateTime time = ZonedDateTime.now();

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 120, 864, 430);
		contentPane.add(panel);
		panel.setLayout(null);

		int scroll_uzunluk = borc.borc_eklenecek_liste().size() * 50 + 28;

		if (scroll_uzunluk > 408) {
			scroll_uzunluk = 408;
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 11, 300, scroll_uzunluk);
		panel.add(scrollPane);

		table_eklenmemis_liste = new JTable(eklenmemis_model);
		table_eklenmemis_liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table_eklenmemis_liste.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		scrollPane.setViewportView(table_eklenmemis_liste);

		JLabel lbl_borc = new JLabel("");
		JTextArea area = new JTextArea();

		area.setBounds(444, 11, 66, 168);
		area.setEditable(false);
		area.setFont(new Font("Impact", Font.PLAIN, 17));
		panel.add(area);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(520, 11, 300, 23);
		panel.add(scrollPane_1);

		ListSelectionModel selection_model = table_eklenmemis_liste.getSelectionModel();
		selection_model.addListSelectionListener(new ListSelectionListener() {

			/*
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			@Override
			public void valueChanged(ListSelectionEvent e) {

				DefaultTableModel clear = (DefaultTableModel) table_secilen_borc_liste.getModel();
				clear.setRowCount(0);
				row = table_eklenmemis_liste.getSelectedRow();

				if (row >= 0) {

					int daire_no = (Integer) table_eklenmemis_liste.getValueAt(row, 0);
					String ad = (String) table_eklenmemis_liste.getValueAt(row, 1);
					String soyad = (String) table_eklenmemis_liste.getValueAt(row, 2);

					int scroll_uzunluk2 = borc.odenmemis_borc_liste(daire_no, ad, soyad).size() * 50 + 28;
					if (scroll_uzunluk2 > 408) {
						scroll_uzunluk2 = 408;
					}
					scrollPane_1.setBounds(520, 11, 300, scroll_uzunluk2);

					if (borc.odenmemis_borc_liste(daire_no, ad, soyad).size() > 0) {
						for (int i = 0; i < borc.odenmemis_borc_liste(daire_no, ad, soyad).size(); i++) {
							col_borc[0] = borc.odenmemis_borc_liste(daire_no, ad, soyad).get(i).getBorc_ay();
							col_borc[1] = borc.odenmemis_borc_liste(daire_no, ad, soyad).get(i).getBorc_yil();
							col_borc[2] = borc.odenmemis_borc_liste(daire_no, ad, soyad).get(i).getBorc_aidat();
							eklenmis_model.addRow(col_borc);
						}
					} else {
						area.setText("*Seçilen\nkiþinin\nborcu\nyoktur ");

					}
				}

			}
		});

		table_secilen_borc_liste = new JTable(eklenmis_model);
		table_secilen_borc_liste.setEnabled(false);
		table_secilen_borc_liste.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		scrollPane_1.setViewportView(table_secilen_borc_liste);

		table_secilen_borc_liste.setRowHeight(50);

		JTableHeader header = table_secilen_borc_liste.getTableHeader();
		header.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));

		DefaultTableCellRenderer center_renderer = new DefaultTableCellRenderer();
		center_renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table_secilen_borc_liste.getColumnModel().getColumn(0).setCellRenderer(center_renderer);
		table_secilen_borc_liste.getColumnModel().getColumn(1).setCellRenderer(center_renderer);
		table_secilen_borc_liste.getColumnModel().getColumn(2).setCellRenderer(center_renderer);
		JButton btn_borc_ekle = new JButton("Borç Ekle");
		btn_borc_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table_eklenmemis_liste.getSelectedRow();

				if (row >= 0) {

					ZonedDateTime time2 = ZonedDateTime.now();

					int daire_no = (Integer) table_eklenmemis_liste.getValueAt(row, 0);

					Kisi kisi = new Kisi();
					kisi = kisi.return_kisi_daire_no(daire_no);
					
					Borc borc = new Borc();

					// ------------------------------------------------------------------

					boolean control = borc.borc_ekle(kisi, time2.getMonthValue(), time2.getYear(),
							yonetici.return_aidat());

					// -----------------------------------------------------------------

					if (control) {
						mesaj.normal_mesaj("baþarýlý");

						update_eklenmemis();
						area.setText("");
						int scroll_uzunluk = borc.borc_eklenecek_liste().size() * 50 + 28;
						if (scroll_uzunluk > 408) {
							scroll_uzunluk = 408;
						}
						scrollPane.setBounds(30, 11, 300, scroll_uzunluk);

						scrollPane_1.setBounds(520, 11, 300, 23);
						// metod yazacaz bu metod yöneticinin alacagýný arttýracak(aidat) kadar
						
						yonetici.alinacak_borc_ekle();

						// kisi toplam borcuna ekleyen metod
						
						kisi.update_kisi_tum_borc(kisi);

					} else {
						mesaj.normal_mesaj("hata");
					}

				}

			}
		});
		btn_borc_ekle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_borc_ekle.setBounds(350, 190, 160, 50);
		panel.add(btn_borc_ekle);

		JLabel lblNewLabel_3 = new JLabel(time.getDayOfMonth() + "." + time.getMonthValue() + "." + time.getYear());
		if (time.getMonthValue() < 10) {
			lblNewLabel_3.setText(time.getDayOfMonth() + ".0" + time.getMonthValue() + "." + time.getYear());
		}

		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(730, 27, 100, 40);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_5_1 = new JLabel(new ImageIcon("Yonetim Tarih.png"));
		lblNewLabel_5_1.setBounds(700, 35, 25, 25);
		contentPane.add(lblNewLabel_5_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 107, 864, 2);
		contentPane.add(separator);

		table_eklenmemis_liste.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_eklenmemis_liste.getColumnModel().getColumn(1).setPreferredWidth(300);
		table_eklenmemis_liste.getColumnModel().getColumn(2).setPreferredWidth(300);

		table_eklenmemis_liste.setRowHeight(50);

		JTableHeader header2 = table_eklenmemis_liste.getTableHeader();
		header2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		center_renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table_eklenmemis_liste.getColumnModel().getColumn(0).setCellRenderer(center_renderer);
		table_eklenmemis_liste.getColumnModel().getColumn(1).setCellRenderer(center_renderer);
		table_eklenmemis_liste.getColumnModel().getColumn(2).setCellRenderer(center_renderer);

		lbl_borc.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		lbl_borc.setBounds(350, 11, 160, 93);
		panel.add(lbl_borc);

		JButton btn_geri = new JButton(new ImageIcon("Yonetim Geri.png"));
		btn_geri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				YoneticiGUI yGUI = new YoneticiGUI(yonetici);
				yGUI.setVisible(true);
				dispose();
			}
		});
		btn_geri.setBounds(40, 11, 40, 40);
		contentPane.add(btn_geri);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		lblNewLabel.setBounds(100, 11, 286, 25);
		contentPane.add(lblNewLabel);

		Month m = time.getMonth();
		String ay = m.name();

		lblNewLabel.setText(ay_ad(ay));

		JLabel lblNewLabel_1 = new JLabel("Se\u00E7ili olan ki\u015Finin mevcut bor\u00E7lar\u0131");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(528, 78, 304, 31);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Bu ay i\u00E7in hen\u00FCz bor\u00E7 eklenmemi\u015F ki\u015Filer");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(38, 78, 304, 31);
		contentPane.add(lblNewLabel_2);

	}

	public void update_eklenmemis() {

		DefaultTableModel clear = (DefaultTableModel) table_eklenmemis_liste.getModel();

		clear.setRowCount(0);

		if (borc.borc_eklenecek_liste().size() != 0) {
			for (int i = 0; i < borc.borc_eklenecek_liste().size(); i++) {
				kisi_data[0] = borc.borc_eklenecek_liste().get(i).getDaire_no();
				kisi_data[1] = borc.borc_eklenecek_liste().get(i).getAd();
				kisi_data[2] = borc.borc_eklenecek_liste().get(i).getSoyad();

				eklenmemis_model.addRow(kisi_data);
			}
		}

	}

	public String ay_ad(String ay) {

		String return_at_ad = "";
		switch (ay) {
		case "JANUARY": {

			return_at_ad = "Ocak";
			break;
		}
		case "FEBRUARY": {
			return_at_ad = "Þubat";
			break;
		}
		case "MARCH": {
			return_at_ad = "Mart";
			break;
		}
		case "APRIL": {
			return_at_ad = "Nisan";
			break;
		}
		case "MAY": {
			return_at_ad = "Mayýs";
			break;
		}
		case "JUNE": {
			return_at_ad = "Haziran";
			break;
		}

		case "JULY": {
			return_at_ad = "Temmuz";
			break;
		}
		case "AUGUST": {

			return_at_ad = "Aðustos";
			break;
		}
		case "SEPTEMBER": {
			return_at_ad = "Eylül";
			break;
		}
		case "OCTOBER": {
			return_at_ad = "Ekim";
			break;
		}
		case "NOVEMBER": {
			return_at_ad = "Kasým";
			break;
		}
		case "DECEMBER": {
			return_at_ad = "Aralýk";
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + ay);
		}

		return return_at_ad;
	}
}
