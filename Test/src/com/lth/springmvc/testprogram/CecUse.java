package com.lth.springmvc.testprogram;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.Color;

/**
 * @author litianhua
 * @date 2018年3月29日
 * 
 */
public class CecUse {

	private JFrame frame;
	private JTextField eOperatorId;
	private JLabel label_1;
	private JTextField eKey;
	private JLabel label_2;
	private JTextField eIv;
	private JLabel label_3;
	private JTextField eSigKey;
	private JLabel label_4;
	private JTextField eTimeStamp;
	private JLabel label_5;
	private JTextField eSeq;
	private JLabel label_6;
	private JTextField eText;
	private JTextArea eRelust;
	private JTextArea eData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CecUse window = new CecUse();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CecUse() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("配置参数");
		label.setFont(new Font("黑体", Font.PLAIN, 15));
		label.setBounds(367, 10, 60, 20);
		frame.getContentPane().add(label);

		JLabel lblOper = new JLabel("OperatorID：");
		lblOper.setHorizontalAlignment(SwingConstants.CENTER);
		lblOper.setBounds(10, 49, 85, 15);
		frame.getContentPane().add(lblOper);

		eOperatorId = new JTextField();
		eOperatorId.setFont(new Font("宋体", Font.PLAIN, 13));
		eOperatorId.setBounds(105, 46, 249, 21);
		frame.getContentPane().add(eOperatorId);
		eOperatorId.setColumns(10);

		label_1 = new JLabel("秘    钥：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(417, 49, 66, 15);
		frame.getContentPane().add(label_1);

		eKey = new JTextField();
		eKey.setFont(new Font("宋体", Font.PLAIN, 13));
		eKey.setColumns(10);
		eKey.setBounds(493, 46, 249, 21);
		frame.getContentPane().add(eKey);

		label_2 = new JLabel("加密向量：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(29, 95, 66, 15);
		frame.getContentPane().add(label_2);

		eIv = new JTextField();
		eIv.setFont(new Font("宋体", Font.PLAIN, 13));
		eIv.setColumns(10);
		eIv.setBounds(105, 92, 249, 21);
		frame.getContentPane().add(eIv);

		label_3 = new JLabel("签名秘钥：");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(417, 92, 66, 15);
		frame.getContentPane().add(label_3);

		eSigKey = new JTextField();
		eSigKey.setFont(new Font("宋体", Font.PLAIN, 13));
		eSigKey.setColumns(10);
		eSigKey.setBounds(493, 89, 249, 21);
		frame.getContentPane().add(eSigKey);

		label_4 = new JLabel("时间戳：");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(29, 140, 66, 15);
		frame.getContentPane().add(label_4);

		eTimeStamp = new JTextField();
		eTimeStamp.setFont(new Font("宋体", Font.PLAIN, 13));
		eTimeStamp.setToolTipText("20180101000000");
		eTimeStamp.setColumns(10);
		eTimeStamp.setBounds(105, 137, 249, 21);
		frame.getContentPane().add(eTimeStamp);

		label_5 = new JLabel("自增序列：");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(417, 137, 66, 15);
		frame.getContentPane().add(label_5);

		eSeq = new JTextField();
		eSeq.setFont(new Font("宋体", Font.PLAIN, 13));
		eSeq.setToolTipText("0001");
		eSeq.setColumns(10);
		eSeq.setBounds(493, 134, 249, 21);
		frame.getContentPane().add(eSeq);

		label_6 = new JLabel("生成操作");
		label_6.setFont(new Font("黑体", Font.PLAIN, 15));
		label_6.setBounds(367, 174, 60, 20);
		frame.getContentPane().add(label_6);

		eText = new JTextField();
		eText.setFont(new Font("宋体", Font.PLAIN, 14));
		eText.setBounds(43, 225, 311, 28);
		frame.getContentPane().add(eText);
		eText.setColumns(10);

		JLabel label_7 = new JLabel("充电订单号/设备认证流水号");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(105, 200, 184, 15);
		frame.getContentPane().add(label_7);

		JButton b4StartSeq = new JButton("生成订单号");
		b4StartSeq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (eOperatorId.getText().length() < 9) {
					eText.setForeground(Color.RED);
					eText.setText("OperatorId格式不正确");
				} else {
					eText.setForeground(Color.BLACK);
					eText.setText(getStartChargeSeq(eOperatorId.getText()));
				}
			}
		});
		b4StartSeq.setFont(new Font("宋体", Font.PLAIN, 13));
		b4StartSeq.setBounds(417, 224, 113, 28);
		frame.getContentPane().add(b4StartSeq);

		JButton b4EquipSeq = new JButton("生成设备认证流水号");
		b4EquipSeq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (eOperatorId.getText().length() < 9) {
					eText.setForeground(Color.RED);
					eText.setText("OperatorId格式不正确");
				} else{
					eText.setForeground(Color.BLACK);
					eText.setText(getStartChargeSeq(eOperatorId.getText()));
				}
			}
		});
		b4EquipSeq.setBounds(570, 227, 155, 23);
		frame.getContentPane().add(b4EquipSeq);

		eRelust = new JTextArea();
		eRelust.setFont(new Font("宋体", Font.PLAIN, 14));
		eRelust.setEditable(false);
		eRelust.setLineWrap(true);
		eRelust.setRows(20);
		eRelust.setBounds(41, 430, 701, 112);
		frame.getContentPane().add(eRelust);

		eData = new JTextArea();
		eData.setFont(new Font("宋体", Font.PLAIN, 14));
		eData.setLineWrap(true);
		eData.setBounds(43, 315, 699, 75);
		frame.getContentPane().add(eData);

		JLabel label_8 = new JLabel("数据处理");
		label_8.setFont(new Font("黑体", Font.PLAIN, 15));
		label_8.setBounds(367, 277, 60, 20);
		frame.getContentPane().add(label_8);

		JLabel label_9 = new JLabel("原数据");
		label_9.setBounds(43, 285, 52, 20);
		frame.getContentPane().add(label_9);

		JLabel label_10 = new JLabel("处理结果");
		label_10.setBounds(41, 400, 85, 20);
		frame.getContentPane().add(label_10);

		JButton b4Sig = new JButton("签  名");
		b4Sig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isNull()) {
					eRelust.setForeground(Color.RED);
					eRelust.setText("参数不能为空");
				} else {
					String content = eOperatorId.getText().trim() + eData.getText().trim() + eTimeStamp.getText().trim()
							+ eSeq.getText().trim();
					eRelust.setForeground(Color.BLACK);
					eRelust.setText(AESandHMacMD5.getHmacMd5Str(eSigKey.getText().trim(), content));
				}
			}
		});
		b4Sig.setFont(new Font("宋体", Font.PLAIN, 15));
		b4Sig.setBounds(73, 562, 113, 40);
		frame.getContentPane().add(b4Sig);

		JButton b4Encrypt = new JButton("加  密");
		b4Encrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isNull()) {
					eRelust.setForeground(Color.RED);
					eRelust.setText("参数不能为空");
				} else {
					try {
						eRelust.setForeground(Color.BLACK);
						eRelust.setText(AESandHMacMD5.aesEncrypt(eData.getText(), eKey.getText(), eIv.getText()));
					} catch (Exception e1) {
						eRelust.setText("加密错误");
						e1.printStackTrace();
					}
				}
			}
		});
		b4Encrypt.setFont(new Font("宋体", Font.PLAIN, 15));
		b4Encrypt.setBounds(255, 562, 113, 40);
		frame.getContentPane().add(b4Encrypt);

		JButton b4Decrypt = new JButton("解   密");
		b4Decrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isNull()) {
					eRelust.setForeground(Color.RED);
					eRelust.setText("参数不能为空");
				} else {
					try {
						eRelust.setForeground(Color.BLACK);
						eRelust.setText(AESandHMacMD5.aesDecrypt(eData.getText(), eKey.getText(), eIv.getText()));
					} catch (Exception e1) {
						eRelust.setText("解密错误");
						e1.printStackTrace();
					}
				}
			}
		});
		b4Decrypt.setFont(new Font("宋体", Font.PLAIN, 15));
		b4Decrypt.setBounds(436, 562, 113, 40);
		frame.getContentPane().add(b4Decrypt);

		JButton b4Clean = new JButton("清  除");
		b4Clean.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eData.setText("");
			}
		});
		b4Clean.setFont(new Font("宋体", Font.PLAIN, 15));
		b4Clean.setBounds(612, 562, 113, 40);
		frame.getContentPane().add(b4Clean);
		
		JButton b4UseDefaultSet = new JButton("使用推荐参数");
		b4UseDefaultSet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eIv.setText("9uUH0gx110juq6ta");
				eKey.setText("ovHSp5SnAIgil18o");
				eSigKey.setText("mjv82gtPuuSYMoed");
				eSeq.setText("0001");
				eTimeStamp.setText("20180101000000");
			}
		});
		b4UseDefaultSet.setBounds(649, 9, 113, 23);
		frame.getContentPane().add(b4UseDefaultSet);
	}

	public String getStartChargeSeq(String id) {
		String seq = id;
		Random random = new Random();
		for (int i = 0; i < 18; i++) {
			seq += String.valueOf(random.nextInt(9));
		}
		return seq;
	}

	public boolean isNull() {
		if (eIv.getText().isEmpty() || eKey.getText().isEmpty() || eOperatorId.getText().isEmpty()
				|| eSeq.getText().isEmpty() || eSigKey.getText().isEmpty() || eTimeStamp.getText().isEmpty()) {
			return true;
		}
		return false;
	}
}
