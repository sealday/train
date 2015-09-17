package me.theegg.train;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RunningBuy extends JFrame {

    JPanel jp=new JPanel();
    String str;

    JComboBox<String> jc=new JComboBox<>(new MyComboBox());
    public RunningBuy(){
        super();
        setTitle("空闲房间查询");
        setBounds(200, 40, 800, 600);
        setVisible(true);
        setLayout(null);
        JLabel roomkind=new JLabel ("房间种类");
        roomkind.setBounds(100,80,120,40);
        add(roomkind);
        Container container=getContentPane();
        container.add(jp);
        jp.setBounds(280, 80, 160, 40);

        jc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println(e.getItem());
            }
        });

        jc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //int	index=jc.getSelectedIndex();
        jp.add(jc);

        JTextField tf =new JTextField();
        tf.setBounds(100,200,300,100);
        add(tf);






		/*if(index==1){
			gconn.select("select `roomID` from `tb_room` where `roomtype`= '标准双人间' and `roomstate` = 'E'");
		    try{String selectid=gconn.hotelrs.getString("roomID");
			tf.setText(selectid);}
		    catch(Exception e){
		    	e.printStackTrace();
		    	System.out.println("插入失败");
		    }

		}//*/

    }
    class MyComboBox extends AbstractListModel<String> implements ComboBoxModel<String>{

        String selecteditem=null;
        String[] test={"单人间","标准双人间","标准三人间","豪华套房"};
        int selectedindex;
        public String getElementAt(int index){
            return test[index];
        }
        public int getSize(){
            return test.length;
        }
        public void setSelectedItem(Object item){
            selecteditem=(String)item;

        }

        public Object getSelectedItem(){
            return selecteditem;
        }

        public int getSelectedIndex(){
            return selectedindex;
        }

    }//class
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jc) {
            int index = jc.getSelectedIndex();
            switch (index) {
                case 0:
                    //这里可以做别的事情，这样写只是为了告诉你有这个方法。
                    //box.getSelectedItem() 的返回值是 object
                    System.out.println(jc.getSelectedItem().toString());
                    break;
                case 1:
                    System.out.println(jc.getSelectedItem().toString());
                    break;
                case 2:
                    System.out.println(jc.getSelectedItem().toString());
                    break;
            }
        }
    }




}

