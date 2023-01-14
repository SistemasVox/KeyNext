package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class vwHome extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3220329755539333386L;
	private JPanel contentPane;
	private JTextArea txtArea;
	private int lp;
	private static String temp;
	private static ArrayList<String> listClear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwHome frame = new vwHome();
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
	public vwHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbtitulo = new JLabel("Key Next");
		lbtitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbtitulo.setBounds(10, 11, 584, 44);
		contentPane.add(lbtitulo);

		JButton btnLer = new JButton("Reader");
		btnLer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lerDados();
			}
		});
		btnLer.setBounds(10, 357, 89, 23);
		contentPane.add(btnLer);

		JButton btnProximo = new JButton("Next");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextKey();
			}
		});
		btnProximo.setBounds(109, 357, 89, 23);
		contentPane.add(btnProximo);

		JButton bntAnteiror = new JButton("Previous");
		bntAnteiror.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		bntAnteiror.setBounds(208, 357, 89, 23);
		contentPane.add(bntAnteiror);

		JButton btnLimpar = new JButton("Clear");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnLimpar.setBounds(307, 357, 89, 23);
		contentPane.add(btnLimpar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 584, 289);
		contentPane.add(scrollPane);

		txtArea = new JTextArea();
		txtArea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		txtArea.setWrapStyleWord(true);
		txtArea.setToolTipText("Área de Apresentação");
		txtArea.setText("Cansado de usar CTRL + C, CTRL + V?");
		txtArea.setLineWrap(true);
		txtArea.setEditable(false);
		scrollPane.setViewportView(txtArea);

		JButton bntSair = new JButton("Exit");
		bntSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		bntSair.setBounds(505, 357, 89, 23);
		contentPane.add(bntSair);

		JButton btnTop = new JButton("On Top");
		btnTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAlwaysOnTop(true);
			}
		});
		btnTop.setBounds(406, 357, 89, 23);
		contentPane.add(btnTop);
	}

	protected void back() {
		temp = "";
		if (listClear != null) {
			temp += ((lp + 1) + "º: " + listClear.get(lp) + " <=" + "\n");
			mensagem(temp);
			ctrlC(listClear.get(lp).trim());
			if (lp > 0) {
				lp--;
			}
		} else {
			mensagem("Nenhuma chave lida ainda.");
		}
	}

	protected void clear() {
		mensagem("");
		listClear = null;
	}

	protected void nextKey() {
		temp = "";
		if (listClear != null) {
			temp += ((lp + 1) + "º: " + listClear.get(lp) + " <=" + "\n");
			mensagem(temp);
			ctrlC(listClear.get(lp).trim());
			if (lp < listClear.size() - 1) {
				lp++;
			}
		} else {
			mensagem("Nenhuma chave lida ainda.");
		}

	}

	private void ctrlC(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	protected void lerDados() {
		String tudo = JOptionPane.showInputDialog("Keys:");
		while (tudo.contains("   ")) {
			tudo = tudo.replaceAll("   ", "  ");
		}
		String[] list = tudo.split(" ");
		tudo = "";
		listClear = new ArrayList<>();
		for (int i = 0; i < list.length; i++) {
			if (list[i].length() > 20) {
				listClear.add(list[i]);
			}
		}
		lp = 0;
		nextKey();
	}

	protected void mensagem(String msg) {
		txtArea.setText(msg);
	}
}
