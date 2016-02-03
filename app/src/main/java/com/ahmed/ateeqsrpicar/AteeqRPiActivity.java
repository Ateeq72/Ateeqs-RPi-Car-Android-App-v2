//Coded by ateeq72@xd.0a")
//Thanks to jrbenito for UDP stuff @ https://github.com/jrbenito/Android-UDP-Terminal
//Thanks to zerokol for JoyStickView!
//anyone can use the code but please provide proper credits! Thanks :)
package com.ahmed.ateeqsrpicar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.zerokol.views.JoystickView;
import com.zerokol.views.JoystickView.OnJoystickMoveListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AteeqRPiActivity extends Activity {
	
	private TextView angleTextView;
	private TextView powerTextView;
	private TextView directionTextView;
	//porting as others views
	private JoystickView joystick;
	
	
	private int sendPacket(int lPorta,String ipHost, int port, String payload) throws IOException {
					
		InetAddress address = InetAddress.getByName(ipHost);
		DatagramSocket socket = new DatagramSocket(lPorta);
		DatagramPacket packet = new DatagramPacket(payload.getBytes(),payload.length(),address,port);
		socket.send(packet);
		socket.disconnect();
		socket.close();
		return 0;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText edLporta = (EditText) findViewById(R.id.editTextLocalPort);
        final EditText edIP = (EditText) findViewById(R.id.editTextIp);
        final EditText edPorta = (EditText) findViewById(R.id.editTextPorta);
           
      
        angleTextView = (TextView) findViewById(R.id.angleTextView);
        powerTextView = (TextView) findViewById(R.id.powerTextView);
        directionTextView = (TextView) findViewById(R.id.directionTextView);
        
        // referring as others views
        joystick = (JoystickView) findViewById(R.id.joystickView);

        joystick.setOnJoystickMoveListener(new OnJoystickMoveListener() {
        @Override
        public void onValueChanged(int angle, int power, int direction) {
        angleTextView.setText(" " + String.valueOf(angle) + "°");
        powerTextView.setText(" " + String.valueOf(power) + "%");
        switch (direction) {
        	case JoystickView.FRONT:
        		 	directionTextView.setText(R.string.front_lab);
        		 	        		 	
        		 	try {
        		 		int port = Integer.parseInt(edPorta.getText().toString());
        		 		int lport = Integer.parseInt(edLporta.getText().toString());
        		 		sendPacket(lport,edIP.getText().toString(), port, "0;1");
        		 		       		 		
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        				break;
        			}
        			
        	case JoystickView.FRONT_RIGHT:
        			directionTextView.setText(R.string.left_front_lab);
        			
        			try {
        		 		int port = Integer.parseInt(edPorta.getText().toString());
        		 		int lport = Integer.parseInt(edLporta.getText().toString());
        		 		 sendPacket(lport,edIP.getText().toString(), port, "-15;1");
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			break;
        	case JoystickView.RIGHT:
        			directionTextView.setText(R.string.left_lab);
        			
        			try {
        		 		int port = Integer.parseInt(edPorta.getText().toString());
        		 		int lport = Integer.parseInt(edLporta.getText().toString());
        		 		 sendPacket(lport,edIP.getText().toString(), port, "-15;0");
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			break;
        	case JoystickView.RIGHT_BOTTOM:
        			directionTextView.setText(R.string.bottom_left_lab);
        			
        			try {
        		 		int port = Integer.parseInt(edPorta.getText().toString());
        		 		int lport = Integer.parseInt(edLporta.getText().toString());
        		 		 sendPacket(lport,edIP.getText().toString(), port, "-15;2");
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			break;
        	case JoystickView.BOTTOM:
        			directionTextView.setText(R.string.bottom_lab);
        			
        			try {
        		 		int port = Integer.parseInt(edPorta.getText().toString());
        		 		int lport = Integer.parseInt(edLporta.getText().toString());
        		 		 sendPacket(lport,edIP.getText().toString(), port, "0;2");
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			break;
        	case JoystickView.BOTTOM_LEFT:
        			directionTextView.setText(R.string.right_bottom_lab);
        			
        			try {
        		 		int port = Integer.parseInt(edPorta.getText().toString());
        		 		int lport = Integer.parseInt(edLporta.getText().toString());
        		 		 sendPacket(lport,edIP.getText().toString(), port, "15;2");
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			break;
        	case JoystickView.LEFT:
        			directionTextView.setText(R.string.right_lab);
        			
        			try {
        		 		int port = Integer.parseInt(edPorta.getText().toString());
        		 		int lport = Integer.parseInt(edLporta.getText().toString());
        		 		 sendPacket(lport,edIP.getText().toString(), port, "15;0");
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			break;
        	case JoystickView.LEFT_FRONT:
        			directionTextView.setText(R.string.front_right_lab);
        			
        			try {
        		 		int port = Integer.parseInt(edPorta.getText().toString());
        		 		int lport = Integer.parseInt(edLporta.getText().toString());
        		 		 sendPacket(lport,edIP.getText().toString(), port, "15;1");
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			break;
        	default:
        			directionTextView.setText(R.string.center_lab);
        			
        			try {
        		 		int port = Integer.parseInt(edPorta.getText().toString());
        		 		int lport = Integer.parseInt(edLporta.getText().toString());
        		 		 sendPacket(lport,edIP.getText().toString(), port, "0;0");
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        }
        }
        },JoystickView.DEFAULT_LOOP_INTERVAL);
        }        
    
    }

