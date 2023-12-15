package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Model.Borc;
import Model.Yonetici;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TumBorclarGUI extends JFrame {

	private JPanel contentPane;
	private JTable table_tum_borclar;
	static Yonetici yonetici = new Yonetici();
	DefaultTableModel borc_model = new DefaultTableModel();
	Object borc_data[];

	Borc borc = new Borc();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TumBorclarGUI frame = new TumBorclarGUI(yonetici);
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
	public TumBorclarGUI(Yonetici yonetici) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		borc_model = new DefaultTableModel();
		Object col_borc[] = new Object[6];
		col_borc[0] = "D. No";
		col_borc[1] = "Ad";
		col_borc[2] = "Soyad";
		col_borc[3] = "Ay";
		col_borc[4] = "Yýl";
		col_borc[5] = "Aidat";

		borc_model.setColumnIdentifiers(col_borc);

		borc_data = new Object[6];

		if (borc.odenmemis_borc_list().size() != 0) {
			for (int i = 0; i < borc.odenmemis_borc_list().size(); i++) {
				borc_data[0] = borc.odenmemis_borc_list().get(i).getKisi_daire_no();
				borc_data[1] = borc.odenmemis_borc_list().get(i).getKisi_ad();
				borc_data[2] = borc.odenmemis_borc_list().get(i).getKisi_soyad();
				borc_data[3] = borc.odenmemis_borc_list().get(i).getBorc_ay();
				borc_data[4] = borc.odenmemis_borc_list().get(i).getBorc_yil();
				borc_data[5] = borc.odenmemis_borc_list().get(i).getBorc_aidat();
				borc_model.addRow(borc_data);
			}
		}

		int scroll_uzunluk = borc.odenmemis_borc_list().size() * 30 + 28;
		if (scroll_uzunluk > 385) {
			scroll_uzunluk = 385;
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 120, 800, scroll_uzunluk);
		contentPane.add(scrollPane);

		table_tum_borclar = new JTable(borc_model);
		table_tum_borclar.setEnabled(false);
		table_tum_borclar.setBackground(Color.WHITE);
		table_tum_borclar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		scrollPane.setViewportView(table_tum_borclar);

		table_tum_borclar.setRowHeight(30);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 100, 864, 2);
		contentPane.add(separator);

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
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 525, 864, 2);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel = new JLabel("Aktif borcu olan ki\u015Filer ve aylara g\u00F6re bor\u00E7 miktarlar\u0131 ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel.setBounds(83, 11, 714, 40);
		contentPane.add(lblNewLabel);
		table_tum_borclar.getColumnModel().getColumn(0).setPreferredWidth(30);
		table_tum_borclar.getColumnModel().getColumn(1).setPreferredWidth(200);
		table_tum_borclar.getColumnModel().getColumn(2).setPreferredWidth(200);
		table_tum_borclar.getColumnModel().getColumn(3).setPreferredWidth(50);
		table_tum_borclar.getColumnModel().getColumn(4).setPreferredWidth(100);
		table_tum_borclar.getColumnModel().getColumn(5).setPreferredWidth(50);
		
		DefaultTableCellRenderer center_renderer = new DefaultTableCellRenderer();
		center_renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table_tum_borclar.getColumnModel().getColumn(0).setCellRenderer(center_renderer);
		table_tum_borclar.getColumnModel().getColumn(1).setCellRenderer(center_renderer);
		table_tum_borclar.getColumnModel().getColumn(2).setCellRenderer(center_renderer);
		table_tum_borclar.getColumnModel().getColumn(3).setCellRenderer(center_renderer);
		table_tum_borclar.getColumnModel().getColumn(4).setCellRenderer(center_renderer);
		table_tum_borclar.getColumnModel().getColumn(5).setCellRenderer(center_renderer);
	

		JTableHeader header = table_tum_borclar.getTableHeader();
		header.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));

	}
}
