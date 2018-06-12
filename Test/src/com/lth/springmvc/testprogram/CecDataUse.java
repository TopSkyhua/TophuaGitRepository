package com.lth.springmvc.testprogram;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * @author litianhua
 * @date 2018年1月15日
 */
@SuppressWarnings("serial")
public class CecDataUse extends JFrame{

	private String key = "ovHSp5SnAIgil18o";
	private String iv = "9uUH0gx110juq6ta";
	private String OperatorID = "000000555";
	private String SigKey = "mjv82gtPuuSYMoed";
	private String content;
	
	private JTextArea jArea;
	private JTextArea jArea1;
	private JLabel label;
	
	private JButton encrypt;
	private JButton decrypt;
	private JButton sig;
	private JButton clean;

//	JFrame a = new JFrame("数据处理");
//	Container c = new Container();
	// Swing更强调容器的概念，一般不允许之间将组件放置到顶层容器中
	// 而是放在容器框架中，而awt则是直接放的
//	a.setSize(600, 600);
//	a.setLocation(300, 500);
	
	public CecDataUse(){
		setBounds(600, 400, 800, 500);
		Container c = this.getContentPane();
		setLayout(new GridLayout(8, 1));
		label = new JLabel("原数据");
		label.setHorizontalAlignment(0);
		jArea = new JTextArea();
		c.add(label);
		c.add(jArea);
		JLabel label1 = new JLabel("处理结果");
		label1.setHorizontalAlignment(0);
		jArea1 = new JTextArea();
		jArea1.setEditable(false);
		c.add(label1);
		c.add(jArea1);
		encrypt = new JButton("加密");
		decrypt = new JButton("解密");
		sig = new JButton("签名");
		clean = new JButton("清除");
		c.add(encrypt);
		c.add(decrypt);
		c.add(sig);
		c.add(clean);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		encrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jArea1.setText(AESandHMacMD5.aesEncrypt(jArea.getText(), key, iv));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		decrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					jArea1.setText(AESandHMacMD5.aesDecrypt(jArea.getText().toString(), key, iv));
//					System.out.println(jArea.getText().toString());
//					System.out.println(AESandHMacMD5.aesDecrypt(jArea.getText().toString(), key, iv));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		sig.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					String timeStamp = "20180101000000";
					String seq = "0001";
					content = OperatorID + jArea.getText() + timeStamp + seq;
//					System.out.println(content);
					jArea1.setText(AESandHMacMD5.getHmacMd5Str(SigKey, content));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		clean.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					jArea.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
	
		new CecDataUse();
		
	}

}
